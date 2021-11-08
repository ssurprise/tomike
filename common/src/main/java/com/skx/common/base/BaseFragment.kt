package com.skx.common.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * 描述 : base fragment
 * 目前只是做了些log 记录。
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/24 3:50 下午
 */
open class BaseFragment : Fragment() {

    var mContext: Context? = null

    companion object {
        const val TAG_LIFECYCLE: String = "Lifecycle"
    }

    val TAG: String = javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG_LIFECYCLE, "$TAG -> onAttach")
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG_LIFECYCLE, "$TAG -> onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG_LIFECYCLE, "$TAG -> onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG_LIFECYCLE, "$TAG -> onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG_LIFECYCLE, "$TAG -> onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG_LIFECYCLE, "$TAG -> onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG_LIFECYCLE, "$TAG -> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG_LIFECYCLE, "$TAG -> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG_LIFECYCLE, "$TAG -> onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG_LIFECYCLE, "$TAG -> onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG_LIFECYCLE, "$TAG -> onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG_LIFECYCLE, "$TAG -> onDetach")
        mContext = null
    }
}