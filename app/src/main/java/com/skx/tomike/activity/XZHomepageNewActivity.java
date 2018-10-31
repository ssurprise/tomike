package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skx.tomike.R;
import com.skx.tomike.adapter.HomepageNewAdapter;
import com.skx.tomike.customview.customlayout.HomepageSearchBtnLayout;
import com.skx.tomike.data.bo.HomepageCityAreaBo;
import com.skx.tomike.data.bo.HomepageCityGuideBo;
import com.skx.tomike.data.bo.HomepageLodgeUnitBo;
import com.skx.tomike.data.bo.HomepageOperationBo;
import com.skx.tomike.data.bo.HomepageUGCBo;
import com.skx.tomike.data.bo.HomepageVideoBo;
import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;
import com.skx.tomike.util.ToastTool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XZHomepageNewActivity extends SkxBaseActivity {

    private RecyclerView mRecyclerView;
    private HomepageSearchBtnLayout widget_searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzhomepage_new);
        initializeData();
        refreshView();
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mRecyclerView = (RecyclerView) findViewById(R.id.homepage_recyclerView);
        widget_searchBtn = (HomepageSearchBtnLayout) findViewById(R.id.homepage_searchBtn_container);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initializeView() {
        super.initializeView();
    }

    @Override
    public void installListener() {
        super.installListener();
    }

    @Override
    public void refreshView() {
        super.refreshView();
        HomepageNewAdapter adapter = new HomepageNewAdapter(this, getTestData());
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e("getScrolledDistance", getScrolledDistance() + "");
//                Log.e("dy", dy + "");
                if (dy > 0 && getScrolledDistance() >= 200) {// 向下滑动，超过一定距离后
                    // 缩短搜索框动画
                    widget_searchBtn.closeSearchBtnAnimator();

                } else if (dy < 0 && getScrolledDistance() < 200) {// 上下滑动，超过一点距离后
                    // 扩展搜索框动画
                    widget_searchBtn.extendSearchBtnAnimator();

                }
            }
        });
        widget_searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showToast(XZHomepageNewActivity.this, "跳转到搜索页面");
                widget_searchBtn.gotoSearchPageAnimator();
            }
        });
    }

    private int getScrolledDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        View firstItemPosition = mRecyclerView.getChildAt(0);
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//        Log.e("firstVisibleItemPos", firstVisibleItemPosition + "");

        int itemHeight = firstItemPosition.getHeight();
//        Log.e("firstVisibleItemHeight", itemHeight + "");

        int firstItemBottom = layoutManager.getDecoratedBottom(firstItemPosition);
//        Log.e("firstItemBottom", firstItemBottom + "");

        return (firstVisibleItemPosition + 1) * itemHeight - firstItemBottom;
    }


    public List<IViewType> getTestData() {
        LinkedList<IViewType> linkedList = new LinkedList<>();

        // 视频区
        HomepageBean.HomepageVideo homepageVideo = new HomepageBean.HomepageVideo();

        List<HomepageBean.HomepageVideoItemInfo> videoItemInfos = new ArrayList<>();
        videoItemInfos.add(new HomepageBean.HomepageVideoItemInfo());
        videoItemInfos.add(new HomepageBean.HomepageVideoItemInfo());
        videoItemInfos.add(new HomepageBean.HomepageVideoItemInfo());
        homepageVideo.items = videoItemInfos;

        HomepageVideoBo videoVo = new HomepageVideoBo(homepageVideo);
        linkedList.add(videoVo);

        // 房源区
        HomepageBean.LodgeUnit lodgeUnit = new HomepageBean.LodgeUnit();
        List<HomepageBean.LodgeUnitItem> lodgeUnitItems = new ArrayList<>();
        lodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        lodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        lodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        lodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        lodgeUnit.items = lodgeUnitItems;

        HomepageLodgeUnitBo lodgeUnitVo = new HomepageLodgeUnitBo(lodgeUnit);
        linkedList.add(lodgeUnitVo);

        // 运营区
        HomepageBean.Operation operation = new HomepageBean.Operation();
        List<HomepageBean.OperationItem> operationItems = new ArrayList<>();
        operationItems.add(new HomepageBean.OperationItem());
        operationItems.add(new HomepageBean.OperationItem());
        operationItems.add(new HomepageBean.OperationItem());
        operationItems.add(new HomepageBean.OperationItem());
        operation.items = operationItems;

        HomepageOperationBo operationVo = new HomepageOperationBo(operation);
        linkedList.add(operationVo);

        // 运营点

        // 城市引导图区
        HomepageCityGuideBo cityGuideVo = new HomepageCityGuideBo(new HomepageBean.CityGuide());
        linkedList.add(cityGuideVo);

        // 城市区
        HomepageBean.CityArea cityArea = new HomepageBean.CityArea();
        cityArea.cityName = "狗不理";
        cityArea.citySubName = "GOUBULI";
        List<HomepageBean.LodgeUnitItem> cityLodgeUnitItems = new ArrayList<>();
        cityLodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        cityLodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        cityLodgeUnitItems.add(new HomepageBean.LodgeUnitItem());
        cityArea.items = cityLodgeUnitItems;

        HomepageCityAreaBo cityAreaVo = new HomepageCityAreaBo(cityArea);
        linkedList.add(cityAreaVo);

        // UGC区
        HomepageBean.UGC ugc = new HomepageBean.UGC();
        List<HomepageBean.UGCItemInfo> ugcItemInfos = new ArrayList<>();
        ugcItemInfos.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos.add(new HomepageBean.UGCItemInfo());
        ugc.items = ugcItemInfos;

        HomepageUGCBo ugcVo = new HomepageUGCBo(ugc);
        linkedList.add(ugcVo);

        // 城市区
        HomepageBean.CityArea cityArea2 = new HomepageBean.CityArea();
        cityArea2.cityName = "花果山";
        cityArea2.citySubName = "HUAGUOSHAN";
        List<HomepageBean.LodgeUnitItem> cityLodgeUnitItems2 = new ArrayList<>();
        cityLodgeUnitItems2.add(new HomepageBean.LodgeUnitItem());
        cityLodgeUnitItems2.add(new HomepageBean.LodgeUnitItem());
        cityLodgeUnitItems2.add(new HomepageBean.LodgeUnitItem());
        cityArea2.items = cityLodgeUnitItems2;

        HomepageCityAreaBo cityAreaVo2 = new HomepageCityAreaBo(cityArea2);
        linkedList.add(cityAreaVo2);

        // UGC区
        HomepageBean.UGC ugc2 = new HomepageBean.UGC();
        List<HomepageBean.UGCItemInfo> ugcItemInfos2 = new ArrayList<>();
        ugcItemInfos2.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos2.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos2.add(new HomepageBean.UGCItemInfo());
        ugcItemInfos2.add(new HomepageBean.UGCItemInfo());
        ugc2.items = ugcItemInfos2;

        HomepageUGCBo ugcVo2 = new HomepageUGCBo(ugc2);
        linkedList.add(ugcVo2);

        return linkedList;
    }
}
