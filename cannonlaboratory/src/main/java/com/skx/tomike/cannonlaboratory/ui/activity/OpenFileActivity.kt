package com.skx.tomike.cannonlaboratory.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import com.skx.tomike.cannonlaboratory.R
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig
import com.skx.tomikecommonlibrary.utils.ToastTool
import java.io.File


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
                startActivity(intent)
            }
            R.id.tv_openFile_targetPath_openBtn2 -> {
                // 如果您想让应用获得对文档提供程序所拥有文档的长期、持续性访问权限，请使用 ACTION_OPEN_DOCUMENT。
                // 例如，照片编辑应用可让用户编辑存储在文档提供程序中的图像。
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.setDataAndType(Uri.parse(mPath), "*/*")
                startActivityForResult(intent, 41)

//                startActivity(Intent.createChooser(intent, "Open folder"))
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
            data?.run {
                val uri: Uri? = this.data
                ToastTool.showToast(mActivity, "Uri: $uri")
                Log.i(TAG, "Uri: $uri")
            }
        }
    }
}