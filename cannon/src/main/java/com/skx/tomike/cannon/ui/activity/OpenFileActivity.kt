package com.skx.tomike.cannon.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.View
import android.widget.TextView
import com.skx.tomike.cannon.R
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.ToastTool
import java.io.File
import java.util.*


/**
 * 描述 : 使用存储访问框架访问文件
 * Android 4.4（API 级别 19）引入了存储访问框架 (SAF)。借助 SAF，用户可轻松在其所有首选文档存储提供
 * 程序中浏览并打开文档、图像及其他文件。用户可通过易用的标准界面，以统一方式在所有应用和提供程序中浏览文件，
 * 以及访问最近使用的文件。
 *
 * 官方地址 : https://developer.android.google.cn/guide/topics/providers/document-provider#search
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 3:56 PM
 */
class OpenFileActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val mPath = Environment.getExternalStorageDirectory().absolutePath +
            File.separator + "xzdz" + File.separator
    // +"debug_api.xml"

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("打开指定的系统目录").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_open_file
    }

    // 1.当应用触发 ACTION_OPEN_DOCUMENT Intent 时，该 Intent 会启动选择器，以显示所有匹配的文档提供程序。
    // 2.在 Intent 中添加 CATEGORY_OPENABLE 类别可对结果进行过滤，从而只显示可打开的文档（如图片文件）。
    // 3.intent.setType("image/*") 语句可做进一步过滤，从而只显示 MIME 数据类型为图像的文档。

    @SuppressLint("SetTextI18n")
    override fun initView() {
        val mTvPath = findViewById<TextView>(R.id.tv_openFile_path)
        mTvPath.text = "目标目录：${mPath}"
        findViewById<View>(R.id.tv_openFile_targetPath_openBtn).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_openFile_targetPath_openBtn2).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_openFile_image).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_openFile_video).setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_openFile_targetPath_openBtn -> {
                // 如果您只想让应用读取/导入数据，请使用 ACTION_GET_CONTENT。使用此方法时，应用会导入数据（如图片文件）的副本。
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.setDataAndType(Uri.parse(mPath), "file/*")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent, 40)
            }
            R.id.tv_openFile_targetPath_openBtn2 -> {
                // 如果您想让应用获得对文档提供程序所拥有文档的长期、持续性访问权限，请使用 ACTION_OPEN_DOCUMENT。
                // 例如，照片编辑应用可让用户编辑存储在文档提供程序中的图像。
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.setDataAndType(Uri.parse(mPath), "*/*")
                startActivityForResult(Intent.createChooser(intent, "Open folder"), 41)
            }
            R.id.tv_openFile_image -> {
                // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
                // browser.
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE)

                // Filter to show only images, using the image MIME data type.
                // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
                // To search for all documents available via installed storage providers,
                // it would be "*/*".
                intent.type = "image/*"
                //intent.setType(“image/*”);//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
                startActivityForResult(intent, 42)
            }
            R.id.tv_openFile_video -> {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "video/*"
                startActivityForResult(intent, 43)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            data?.run {
                ToastTool.showToast(mActivity, "Uri: $uri")
                Log.e(TAG, "Uri: $uri")
            }
            openAndroidFile(FileUtils.getRealPath(mActivity, uri))
        }
    }

    private fun openAndroidFile(filepath: String) {
        val intent = Intent(Intent.ACTION_VIEW)//动作，查看
        // 这是比较流氓的方法，绕过7.0的文件权限检查
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }

        val file = File(filepath)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(Uri.fromFile(file), getMIMEType(file)) //设置类型
        startActivity(intent)
    }

    private val MIME_MapTable = arrayOf(arrayOf(".3gp", "video/3gpp"),
            arrayOf(".apk", "application/vnd.android.package-archive"),
            arrayOf(".asf", "video/x-ms-asf"),
            arrayOf(".avi", "video/x-msvideo"),
            arrayOf(".bin", "application/octet-stream"),
            arrayOf(".bmp", "image/bmp"),
            arrayOf(".c", "text/plain"),
            arrayOf(".class", "application/octet-stream"),
            arrayOf(".conf", "text/plain"),
            arrayOf(".cpp", "text/plain"),
            arrayOf(".doc", "application/msword"),
            arrayOf(".docx", "application/msword"),
            arrayOf(".exe", "application/octet-stream"),
            arrayOf(".gif", "image/gif"),
            arrayOf(".gtar", "application/x-gtar"),
            arrayOf(".gz", "application/x-gzip"),
            arrayOf(".h", "text/plain"),
            arrayOf(".htm", "text/html"),
            arrayOf(".html", "text/html"),
            arrayOf(".jar", "application/java-archive"),
            arrayOf(".java", "text/plain"),
            arrayOf(".jpeg", "image/jpeg"),
            arrayOf(".JPEG", "image/jpeg"),
            arrayOf(".jpg", "image/jpeg"),
            arrayOf(".js", "application/x-javascript"),
            arrayOf(".log", "text/plain"),
            arrayOf(".m3u", "audio/x-mpegurl"),
            arrayOf(".m4a", "audio/mp4a-latm"),
            arrayOf(".m4b", "audio/mp4a-latm"),
            arrayOf(".m4p", "audio/mp4a-latm"),
            arrayOf(".m4u", "video/vnd.mpegurl"),
            arrayOf(".m4v", "video/x-m4v"),
            arrayOf(".mov", "video/quicktime"),
            arrayOf(".mp2", "audio/x-mpeg"),
            arrayOf(".mp3", "audio/x-mpeg"),
            arrayOf(".mp4", "video/mp4"),
            arrayOf(".mpc", "application/vnd.mpohun.certificate"),
            arrayOf(".mpe", "video/mpeg"),
            arrayOf(".mpeg", "video/mpeg"),
            arrayOf(".mpg", "video/mpeg"),
            arrayOf(".mpg4", "video/mp4"),
            arrayOf(".mpga", "audio/mpeg"),
            arrayOf(".msg", "application/vnd.ms-outlook"),
            arrayOf(".ogg", "audio/ogg"),
            arrayOf(".pdf", "application/pdf"),
            arrayOf(".png", "image/png"),
            arrayOf(".pps", "application/vnd.ms-powerpoint"),
            arrayOf(".ppt", "application/vnd.ms-powerpoint"),
            arrayOf(".pptx", "application/vnd.ms-powerpoint"),
            arrayOf(".prop", "text/plain"),
            arrayOf(".rar", "application/x-rar-compressed"),
            arrayOf(".rc", "text/plain"),
            arrayOf(".rmvb", "audio/x-pn-realaudio"),
            arrayOf(".rtf", "application/rtf"),
            arrayOf(".sh", "text/plain"),
            arrayOf(".tar", "application/x-tar"),
            arrayOf(".tgz", "application/x-compressed"),
            arrayOf(".txt", "text/plain"),
            arrayOf(".wav", "audio/x-wav"),
            arrayOf(".wma", "audio/x-ms-wma"),
            arrayOf(".wmv", "audio/x-ms-wmv"),
            arrayOf(".wps", "application/vnd.ms-works"),
            arrayOf(".xml", "text/plain"),
            arrayOf(".z", "application/x-compress"),
            arrayOf(".zip", "application/zip"),
            arrayOf("", "*/*"))

    private fun getMIMEType(file: File): String? {
        var type = "*/*"
        val fName = file.name
        //获取后缀名前的分隔符"."在fName中的位置。
        val dotIndex = fName.lastIndexOf(".")
        if (dotIndex < 0) return type
        /* 获取文件的后缀名 */
        val fileType = fName.substring(dotIndex, fName.length).toLowerCase(Locale.getDefault())
        if ("" == fileType) return type
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (i in MIME_MapTable.indices) {
            if (fileType == MIME_MapTable[i][0]) type = MIME_MapTable[i][1]
        }
        return type
    }

}