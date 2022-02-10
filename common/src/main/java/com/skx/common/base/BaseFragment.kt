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
    val TAG: String = javaClass.simpleName


    companion object {
        const val TAG_LIFECYCLE: String = "FragmentLifecycle"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logPrinter("onAttach")
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logPrinter("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logPrinter("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logPrinter("onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logPrinter("onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        logPrinter("onStart")
    }

    override fun onResume() {
        super.onResume()
        logPrinter("onResume")
    }

    override fun onPause() {
        super.onPause()
        logPrinter("onPause")
    }

    override fun onStop() {
        super.onStop()
        logPrinter("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logPrinter("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logPrinter("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        logPrinter("onDetach")
        mContext = null
    }

    private fun logPrinter(content: String) {
        Log.i(TAG_LIFECYCLE, "$TAG -> $content")
    }
}