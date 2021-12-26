package com.skx.tomike.cannon.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.skx.common.utils.dip2px
import com.skx.tomike.cannon.R

/**
 * 描述 : 通用弹窗
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/12/26 7:05 下午
 */
class SkxDialogFragment : DialogFragment() {

    private var mContext: FragmentActivity? = null
    private var mTvTitle: TextView? = null
    private var mTvDesc: TextView? = null
    private var mTvLeftBtn: TextView? = null
    private var mTvRightBtn: TextView? = null

    private var mTitle: String? = null
    private var mDesc: String? = null
    private var mLeftBtnText: String? = null
    private var mRightBtnText: String? = null
    private var mGravity: Int = Gravity.CENTER

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as FragmentActivity?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            mTitle = getString(KEY_TITLE)
            mDesc = getString(KEY_DESC)
            mLeftBtnText = getString(KEY_LEFT_BTN_TEXT)
            mRightBtnText = getString(KEY_RIGHT_BTN_TEXT)
            mGravity = getInt(KEY_GRAVITY)
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(mContext!!)
        if (mContext != null) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_standard_style, null)
            initView(view)
            renderView()
            isCancelable = false
            builder.setView(view)
        }
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.run {
            // 点击返回键是否取消弹窗
            setCancelable(false)
            // 点击外部区域是否取消弹窗
            setCanceledOnTouchOutside(true)
            //点击返回键不消失，需要监听OnKeyListener:
//            setOnKeyListener { _, keyCode, event ->
//                keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN
//            }
        }

        dialog?.window?.apply {
            // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
            setBackgroundDrawableResource(android.R.color.transparent)
            // 设置弹窗动画，如果是底部位置，给一个从下到上的弹窗
            if (Gravity.BOTTOM == mGravity) {
                setWindowAnimations(R.style.dialogWindowAnim)
            }
            // 设置弹窗大小、位置
            val params: WindowManager.LayoutParams? = attributes
            params?.gravity = mGravity
            //  如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
            params?.width = when (mGravity) {
                Gravity.BOTTOM -> resources.displayMetrics.widthPixels
                Gravity.CENTER -> dip2px(this.context, 270f)
                else -> {
                    dip2px(this.context, 270f)
                }
            }
            attributes = params
        }
    }

    private fun initView(view: View) {
        mTvTitle = view.findViewById(R.id.standard_dialog_title)
        mTvDesc = view.findViewById(R.id.standard_dialog_message)
        mTvLeftBtn = view.findViewById(R.id.standard_dialog_btn_left)
        mTvLeftBtn?.setOnClickListener {
            dismiss()
        }
        mTvRightBtn = view.findViewById(R.id.standard_dialog_two_right)
        mTvRightBtn?.setOnClickListener {
            dismiss()
        }
    }

    private fun renderView() {
        mTvTitle?.text = mTitle
        mTvDesc?.text = mDesc
        mTvLeftBtn?.text = mLeftBtnText
        mTvRightBtn?.text = mRightBtnText
    }

    fun setLeftBtnContent(txt: String, l: View.OnClickListener?) {
        mTvLeftBtn?.setOnClickListener(l)
    }

    fun setRightBtnContent(txt: String, l: View.OnClickListener?) {
        mTvRightBtn?.setOnClickListener(l)
    }

    companion object {

        private const val KEY_TITLE = "title"
        private const val KEY_DESC = "desc"
        private const val KEY_LEFT_BTN_TEXT = "leftBtnText"
        private const val KEY_RIGHT_BTN_TEXT = "rightBtnText"
        private const val KEY_GRAVITY = "gravity"

        fun newInstance(title: String?,
                        desc: String?,
                        leftBtnText: String?,
                        rightBtnText: String?,
                        gravity: Int = Gravity.CENTER
        ): SkxDialogFragment {
            return SkxDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                    putString(KEY_DESC, desc)
                    putString(KEY_LEFT_BTN_TEXT, leftBtnText)
                    putString(KEY_RIGHT_BTN_TEXT, rightBtnText)
                    putInt(KEY_GRAVITY, gravity)
                }
            }
        }
    }
}