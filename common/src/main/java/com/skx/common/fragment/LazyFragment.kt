package com.skx.common.fragment

import android.util.Log
import androidx.fragment.app.Fragment


/**
 * 描述 : 懒加载Fragment
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/15 11:30 下午
 *
 * 参考：
 * https://www.jianshu.com/p/de332ecdd14d
 * https://www.jianshu.com/p/ee44f1fa26dd
 */
class LazyFragment : Fragment() {

    companion object {
        private const val TAG = "LazyFragment"
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // onHiddenChanged() 是在 add+show+hide 模式下使用.
        Log.e(TAG, "onHiddenChanged hidden=$hidden")
        if (hidden) {
            onInvisible()
        } else {
            onVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // setUserVisibleHint 是在 ViewPager+Fragment 模式下使用
        Log.e(TAG, "setUserVisibleHint isVisibleToUser=$isVisibleToUser")
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInvisible()
        }
    }

    private fun onVisible() {

    }

    private fun onInvisible() {

    }
}