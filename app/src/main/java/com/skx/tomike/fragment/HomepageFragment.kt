package com.skx.tomike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.skx.common.base.BaseFragment
import com.skx.common.imageloader.ImageLoader
import com.skx.tomike.R

class HomepageFragment : BaseFragment() {

    private var mIvMainImg1: ImageView? = null
    private var mIvMainImg2: ImageView? = null
    private var mIvMainImg3: ImageView? = null
    private var mIvMainImg4: ImageView? = null
    private var mIvMainImg5: ImageView? = null
    private var mIvMainImg6: ImageView? = null

    private var mParam1: String? = null
    private var mParam2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mParam1 = getString(ARG_PARAM1)
            mParam2 = getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)
        mIvMainImg1 = view.findViewById(R.id.homepage_main_1)
        mIvMainImg2 = view.findViewById(R.id.homepage_main_2)
        mIvMainImg3 = view.findViewById(R.id.homepage_main_3)
        mIvMainImg4 = view.findViewById(R.id.homepage_main_4)
        mIvMainImg5 = view.findViewById(R.id.homepage_main_5)
        mIvMainImg6 = view.findViewById(R.id.homepage_main_6)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext?.run {
            ImageLoader.with(this).load(R.drawable.main_clear_sky).into(mIvMainImg1)
            ImageLoader.with(this).load(R.drawable.main_cloud).into(mIvMainImg2)
            ImageLoader.with(this).load(R.drawable.main_lucky).into(mIvMainImg3)
            ImageLoader.with(this).load(R.drawable.main_dazhongsi_1).into(mIvMainImg4)
            ImageLoader.with(this).load(R.drawable.main_dazhongsi_2).into(mIvMainImg5)
            ImageLoader.with(this).load(R.drawable.main_dazhongsi_3).into(mIvMainImg6)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String?, param2: String?): HomepageFragment {
            val fragment = HomepageFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}