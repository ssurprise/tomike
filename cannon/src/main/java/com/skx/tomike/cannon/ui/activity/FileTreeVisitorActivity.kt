package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.os.*
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.permission.PermissionController
import com.skx.common.permission.PermissionResultListener
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_FILE_TREE_VISITOR
import java.io.IOException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.absolutePathString


/**
 * 描述 : 文件树访问
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_FILE_TREE_VISITOR)
class FileTreeVisitorActivity : SkxBaseActivity<BaseViewModel<*>>() {


    private val mHandler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mTvProcess.text = "${msg.obj}"
        }
    }

    private val mTvTargetPath by lazy {
        findViewById<TextView>(R.id.tv_fileVisitor_targetPath)
    }
    private val mTvProcess by lazy { findViewById<TextView>(R.id.tv_fileVisitor_process) }
    private val mTvResult by lazy { findViewById<TextView>(R.id.tv_fileVisitor_resultContent) }


    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("文件树访问").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_file_tree_visitor
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv_fileVisitor_startAction).apply {
            setOnClickListener {
                PermissionController.Builder(this@FileTreeVisitorActivity)
                        .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .associateDefaultTip()
                        .callback(object : PermissionResultListener {
                            override fun onSucceed(grantPermissions: List<String>?) {
                                startScan()
                            }

                            override fun onFailed(deniedPermissions: List<String>?) {
                                ToastTool.showToast(this@FileTreeVisitorActivity, "当前没有读权限！")
                            }
                        })
                        .request()
            }
        }
    }


    private fun startScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val targetPath = Environment.getExternalStorageDirectory().absolutePath
            val targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
            Log.e(TAG, "targetPath=${targetPath}")
            val path = Paths.get(targetPath)
            mTvTargetPath.text = targetPath

            var countDirectory = 0 // 文件夹数量
            var countFile = 0 // 文件数量
            var countPic = 0 // 图片数量
            var countVideo = 0 // 视频数量
            var countDoc = 0 // 文档数量
            var countOther = 0 // 其他类型文件数量

            Files.walkFileTree(path, object : SimpleFileVisitor<Path?>() {

                override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                    Log.e(TAG, "preVisitDirectory, path=${dir?.absolutePathString()}")
                    countDirectory++
                    val obtain = Message.obtain()
                    obtain.what = 0
                    obtain.obj = dir?.absolutePathString()
                    mHandler.sendMessage(obtain)
                    return FileVisitResult.CONTINUE
                }

                override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult {
                    Log.e(TAG, "visitFileFailed, path=${file?.absolutePathString()}")
                    return FileVisitResult.CONTINUE
                }

                override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                    Log.e(TAG, "visitFile, path=${file?.absolutePathString()}")
                    countFile++
                    file?.fileName?.toString()?.run {
                        when {
                            this.endsWith(".jpg")
                                    || this.endsWith(".jpeg")
                                    || this.endsWith(".png")
                                    || this.endsWith(".gif")
                                    || this.endsWith(".webp")
                                    || this.endsWith(".bpm")
                            -> {
                                countPic++
                            }
                            this.endsWith(".mp4") // Sony视频 ：mp4、m4v
                                    || this.endsWith(".MP4")// Mpeg-4视频格式
                                    || this.endsWith(".m4v")
                                    || this.endsWith(".avi") // 微软视频 ：avi、wmv、asf、asx
                                    || this.endsWith(".AVI") // AVI是英文Audio Video Interleave的缩写，该格式由微软开发
                                    || this.endsWith(".wmv")
                                    || this.endsWith(".asf")
                                    || this.endsWith(".asx")
                                    || this.endsWith(".rm") // Real Player ：rm、 rmvb
                                    || this.endsWith(".rmvb")
                                    || this.endsWith(".mpg")// MPEG视频 ：mpg、mpeg、mpe
                                    || this.endsWith(".mpeg")
                                    || this.endsWith(".mpe")
                                    || this.endsWith(".mov")// 苹果
                                    || this.endsWith(".swf")// Flash视频格式，文件名以“.swf”或“.flv”结尾。
                                    || this.endsWith(".flv")
                                    || this.endsWith(".3gp")
                            -> {
                                countVideo++
                            }
                            this.endsWith(".doc") // word
                                    || this.endsWith(".docx")
                                    || this.endsWith(".xls")// excel
                                    || this.endsWith(".xlsx")
                                    || this.endsWith(".ppt")// ppt
                                    || this.endsWith(".pptx")
                                    || this.endsWith(".wps")// wps
                                    || this.endsWith(".pdf")// pdf
                                    || this.endsWith(".txt") -> {
                                countDoc++
                            }
                            else -> {
                                countOther++
                            }
                        }
                    }
                    val obtain = Message.obtain()
                    obtain.what = 1
                    obtain.obj = file?.absolutePathString()
                    mHandler.sendMessage(obtain)
                    return FileVisitResult.CONTINUE
                }

                override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult {
                    return FileVisitResult.CONTINUE
                }
            })


            val result = "共扫描文件夹：${countDirectory}个，包含文件：${countFile}个，其中" +
                    "\n图片数量：${countPic}" +
                    "\n视频数量：${countVideo}" +
                    "\n文档数量：${countDoc}" +
                    "\n其他类型文件数量：${countOther}"
            mTvResult.text = result

        } else {
            Log.e(TAG, "设备版本不支持！")
        }
    }
}