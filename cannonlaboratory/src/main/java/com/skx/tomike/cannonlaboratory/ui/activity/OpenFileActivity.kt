package com.skx.tomike.cannonlaboratory.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.TextView
import com.skx.tomike.cannonlaboratory.R
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig
import java.io.File

/**
 * 描述 : 大图压缩加载。
 *
 *
 * 主要知识点：Bitmap 的 inSampleSize，对大图进行大小压缩。
 * 关键属性：android.graphics.BitmapFactory.Options#inJustDecodeBounds、android.graphics.BitmapFactory.Options#inSampleSize
 *
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 3:56 PM
 */
class OpenFileActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val mPath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "xzdz" + File.separator
    // +"debug_api.xml"

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("打开指定的系统目录").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_open_file
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        val mTvPath = findViewById<TextView>(R.id.tv_openFile_path)
        mTvPath.text = "目标目录：${mPath}"
        findViewById<View>(R.id.tv_openFile_openBtn).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_openFile_openBtn2).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_openFile_openBtn -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.setDataAndType(Uri.parse(mPath), "file/*")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            R.id.tv_openFile_openBtn2 -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.setDataAndType(Uri.parse(mPath), "*/*")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(Intent.createChooser(intent, "Open folder"));
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1001 -> {

            }
        }
    }
}