package com.skx.tomike.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.customview.customlayout.PageIndicatorLayout;
import com.skx.tomike.data.bo.HomepageCityAreaBo;
import com.skx.tomike.data.bo.HomepageCityGuideBo;
import com.skx.tomike.data.bo.HomepageLodgeUnitBo;
import com.skx.tomike.data.bo.HomepageOperationBo;
import com.skx.tomike.data.bo.HomepageUGCBo;
import com.skx.tomike.data.bo.HomepageVideoBo;
import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;
import com.skx.tomike.util.WidthHeightTool;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/3/15.
 * <p>
 * 首页 adapter
 */
public class HomepageNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_LODGE_UNIT = 1;
    private static final int TYPE_OPERATION = 2;
    private static final int TYPE_OPERATION_POINT = 3;
    private static final int TYPE_CITY_GUIDE = 4;
    private static final int TYPE_CITY_AREA = 5;
    private static final int TYPE_UGC = 6;
    private static final int TYPE_TOP_OPERATION = 7;

    private Context mContext;
    private List<IViewType> mHomepageData;


    public HomepageNewAdapter(Context context, List<IViewType> mHomepageData) {
        this.mContext = context;
        if (mHomepageData == null) {
            this.mHomepageData = new ArrayList<>();
        } else {
            this.mHomepageData = mHomepageData;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VIDEO:
                return new VideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_video, parent, false));
            case TYPE_TOP_OPERATION:
                return new TopOperationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_video, parent, false));
            case TYPE_LODGE_UNIT:
                return new LodgeUnitViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_house, parent, false));
            case TYPE_OPERATION:
                return new OperationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_operations, parent, false));
            case TYPE_OPERATION_POINT:
//                return new OperationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_operation_s, parent, false));
            case TYPE_CITY_GUIDE:
                return new CityGuideMapViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_city_guide, parent, false));
            case TYPE_CITY_AREA:
                return new CityHouseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_city_house, parent, false));
            case TYPE_UGC:
                return new UGCViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_ugc, parent, false));
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mHomepageData.get(position).getViewType();
    }

    private IViewType getItemData(int position) {
        return mHomepageData.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IViewType itemData = getItemData(position);
        switch (getItemViewType(position)) {
            case TYPE_VIDEO:// 视频模块
                if (itemData instanceof HomepageVideoBo) {
                    final VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                    HomepageVideoBo homepageVideoVo = (HomepageVideoBo) itemData;

                    final List<HomepageBean.HomepageVideoItemInfo> videoList = homepageVideoVo.getHomepageVideo().getItems();
                    HomepageVideoItemAdapter videoItemAdapter = new HomepageVideoItemAdapter(mContext, videoList);
                    videoViewHolder.vp_videos.setAdapter(videoItemAdapter);

                    // 当视频数量>1 时，可左右滑动切换，显示切换btn;否则不可切换，不显示切换按钮
                    if (videoList != null && videoList.size() > 1) {
                        videoViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                        videoViewHolder.imgBtn_next.setVisibility(View.VISIBLE);

                        videoViewHolder.imgBtn_previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = videoViewHolder.vp_videos.getCurrentItem() - 1;
                                if (i > -1) {
                                    videoViewHolder.vp_videos.setCurrentItem(i);
                                }
                            }
                        });
                        videoViewHolder.imgBtn_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = videoViewHolder.vp_videos.getCurrentItem() + 1;
                                if (i < videoList.size()) {
                                    videoViewHolder.vp_videos.setCurrentItem(i);
                                }
                            }
                        });

                        // 视频的左右切换
                        videoViewHolder.vp_videos.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                if (position == 0) {
                                    videoViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                                    videoViewHolder.imgBtn_next.setVisibility(View.VISIBLE);
                                } else if (position == videoList.size() - 1) {
                                    videoViewHolder.imgBtn_previous.setVisibility(View.VISIBLE);
                                    videoViewHolder.imgBtn_next.setVisibility(View.INVISIBLE);
                                } else {
                                    videoViewHolder.imgBtn_previous.setVisibility(View.VISIBLE);
                                    videoViewHolder.imgBtn_next.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    } else {
                        videoViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                        videoViewHolder.imgBtn_next.setVisibility(View.INVISIBLE);
                    }
                }
                break;

            case TYPE_TOP_OPERATION:// 头部运营位模块
                if (itemData instanceof HomepageOperationBo) {
                    final TopOperationViewHolder topOperationViewHolder = (TopOperationViewHolder) holder;

                    HomepageBean.Operation homepageOperation = ((HomepageOperationBo) itemData).getHomepageOperation();
                    final List<HomepageBean.OperationItem> operationList = homepageOperation.getItems();


                    // 运营活动内容。
                    HomepageOperationItemAdapter operationItemAdapter = new HomepageOperationItemAdapter(mContext, operationList);
                    topOperationViewHolder.vp_operation.setAdapter(operationItemAdapter);


                    // 当视频数量>1 时，可左右滑动切换，显示切换btn;否则不可切换，不显示切换按钮
                    if (operationList != null && operationList.size() > 1) {
                        topOperationViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                        topOperationViewHolder.imgBtn_next.setVisibility(View.VISIBLE);

                        topOperationViewHolder.imgBtn_previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = topOperationViewHolder.vp_operation.getCurrentItem() - 1;
                                if (i > -1) {
                                    topOperationViewHolder.vp_operation.setCurrentItem(i);
                                }
                            }
                        });
                        topOperationViewHolder.imgBtn_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = topOperationViewHolder.vp_operation.getCurrentItem() + 1;
                                if (i < operationList.size()) {
                                    topOperationViewHolder.vp_operation.setCurrentItem(i);
                                }
                            }
                        });

                        // 头运营活动的左右切换
                        topOperationViewHolder.vp_operation.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                if (position == 0) {
                                    topOperationViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                                    topOperationViewHolder.imgBtn_next.setVisibility(View.VISIBLE);
                                } else if (position == operationList.size() - 1) {
                                    topOperationViewHolder.imgBtn_previous.setVisibility(View.VISIBLE);
                                    topOperationViewHolder.imgBtn_next.setVisibility(View.INVISIBLE);
                                } else {
                                    topOperationViewHolder.imgBtn_previous.setVisibility(View.VISIBLE);
                                    topOperationViewHolder.imgBtn_next.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    } else {
                        topOperationViewHolder.imgBtn_previous.setVisibility(View.INVISIBLE);
                        topOperationViewHolder.imgBtn_next.setVisibility(View.INVISIBLE);
                    }
                }
                break;

            case TYPE_LODGE_UNIT:// 房源模块
                if (itemData instanceof HomepageLodgeUnitBo) {
                    final LodgeUnitViewHolder lodgeUnitViewHolder = (LodgeUnitViewHolder) holder;
                    HomepageBean.LodgeUnit lodgeUnit = ((HomepageLodgeUnitBo) itemData).getHomepageLodgeUnit();
                    final List<HomepageBean.LodgeUnitItem> lodgeUnitList = lodgeUnit.getItems();

                    // 当房源模块的标题和描述都显示时，展示标题模块，否则不显示标题模块
                    if (!TextUtils.isEmpty(lodgeUnit.title) && !TextUtils.isEmpty(lodgeUnit.desc)) {
                        lodgeUnitViewHolder.ll_titleContainer.setVisibility(View.VISIBLE);
                        lodgeUnitViewHolder.tv_title.setText(lodgeUnit.title);
                        lodgeUnitViewHolder.tv_describe.setText(lodgeUnit.desc);
                    } else {
                        lodgeUnitViewHolder.ll_titleContainer.setVisibility(View.GONE);
                    }

                    // 房源内容。这是要求是无限循环，首位相连，但是为了切换动画，故采用的是假无限循环，
                    HomepageLodgeUnitItemAdapter lodgeUnitItemAdapter = new HomepageLodgeUnitItemAdapter(mContext, lodgeUnitList);
                    lodgeUnitItemAdapter.setLoopTimes(100);
                    lodgeUnitViewHolder.vp_housesImage.setAdapter(lodgeUnitItemAdapter);

                    // 房源数 >1 时： ①显示页签；②设置ViewPager的初始位置
                    if (lodgeUnitList.size() > 1) {
                        lodgeUnitViewHolder.vp_housesImage.setCurrentItem(lodgeUnitList.size() * 50);

                        lodgeUnitViewHolder.widget_pageIndicator.setVisibility(View.VISIBLE);
                        lodgeUnitViewHolder.widget_pageIndicator.setPageCount(lodgeUnitList.size());
                    }else{
                        lodgeUnitViewHolder.widget_pageIndicator.setVisibility(View.INVISIBLE);
                    }

                    lodgeUnitViewHolder.widget_pageIndicator.bindHomepageLodgeUnitViewPage(lodgeUnitViewHolder.vp_housesImage);
                }
                break;

            case TYPE_OPERATION://运营位模块
                if (itemData instanceof HomepageOperationBo) {
                    final OperationViewHolder operationViewHolder = (OperationViewHolder) holder;
                    HomepageBean.Operation homepageOperation = ((HomepageOperationBo) itemData).getHomepageOperation();
                    List<HomepageBean.OperationItem> operationList = homepageOperation.getItems();

                    if (!TextUtils.isEmpty(homepageOperation.title) && !TextUtils.isEmpty(homepageOperation.desc)) {
                        operationViewHolder.ll_titleContainer.setVisibility(View.VISIBLE);
                        operationViewHolder.tv_title.setText(homepageOperation.title);
                        operationViewHolder.tv_describe.setText(homepageOperation.desc);
                    } else {
                        operationViewHolder.ll_titleContainer.setVisibility(View.GONE);
                    }

                    // 运营活动内容。
                    HomepageOperationItemAdapter operationItemAdapter = new HomepageOperationItemAdapter(mContext, operationList);
                    operationViewHolder.vp_operations.setAdapter(operationItemAdapter);

                    // 运营活动数量 >1 时才显示页签，否则不显示页签
                    if (homepageOperation.items.size() > 1) {
                        operationViewHolder.widget_pageIndicator.setVisibility(View.VISIBLE);
                        operationViewHolder.widget_pageIndicator.setPageCount(operationList.size());
                        operationViewHolder.vp_operations.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                operationViewHolder.widget_pageIndicator.indicatorScroll(position, positionOffset);
                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    } else {
                        operationViewHolder.widget_pageIndicator.setVisibility(View.INVISIBLE);
                    }
                }
                break;

            case TYPE_OPERATION_POINT://运营点模块
//                return new OperationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_operation_s, parent, false));
                break;

            case TYPE_CITY_GUIDE:// 城市引导图模块
                if (itemData instanceof HomepageCityGuideBo) {
                    CityGuideMapViewHolder cityGuideMapViewHolder = (CityGuideMapViewHolder) holder;
                    HomepageBean.CityGuide cityGuide = ((HomepageCityGuideBo) itemData).getHomepageCityGuide();
                    //TODO 城市引导图
//                    cityGuideMapViewHolder.homepage_city_guide_image.
                }
                break;

            case TYPE_CITY_AREA:// 城市区
                if (itemData instanceof HomepageCityAreaBo) {
                    final CityHouseViewHolder cityHouseViewHolder = (CityHouseViewHolder) holder;
                    HomepageBean.CityArea cityArea = ((HomepageCityAreaBo) itemData).getHomepageCityArea();

                    //TODO 城市icon   cityHouseViewHolder.tv_city_iconImg
                    cityHouseViewHolder.tv_city_name.setText(cityArea.cityName);
                    cityHouseViewHolder.tv_city_name_pinyin.setText(cityArea.citySubName);

                    final List<HomepageBean.LodgeUnitItem> items = cityArea.items;
                    if (items.size() > 1) {
                        cityHouseViewHolder.widget_pageIndicator.setPageCount(items.size());
                        cityHouseViewHolder.widget_pageIndicator.setVisibility(View.VISIBLE);
                    } else {
                        cityHouseViewHolder.widget_pageIndicator.setVisibility(View.INVISIBLE);
                    }
                    HomepageLodgeUnitItemAdapter adapter = new HomepageLodgeUnitItemAdapter(mContext, items);
                    adapter.setLoopTimes(100);
                    cityHouseViewHolder.vp_lodgeUnit.setAdapter(adapter);
                    cityHouseViewHolder.vp_lodgeUnit.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            cityHouseViewHolder.widget_pageIndicator.indicatorScroll(items.size(), positionOffset);
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                }
                break;

            case TYPE_UGC:
                if (itemData instanceof HomepageUGCBo) {
                    final UGCViewHolder ugcViewHolder = (UGCViewHolder) holder;
                    final List<HomepageBean.UGCItemInfo> ugcItemList = ((HomepageUGCBo) itemData).getHomepageUGC().items;

                    HomepageUGCItemAdapter ugcItemAdapter = new HomepageUGCItemAdapter(mContext, ugcItemList);
                    ugcViewHolder.vp_content.setAdapter(ugcItemAdapter);

                    final int currentItem = ugcViewHolder.vp_content.getCurrentItem();

                    HomepageBean.UGCItemInfo ugcItemInfo = ugcItemList.get(currentItem);
                    // 用户头像   ugcViewHolder.imgv_userInfo_headImage
                    ugcViewHolder.tv_userInfo_name.setText(ugcItemInfo.nickName);
                    ugcViewHolder.tv_userInfo_identity.setText(ugcItemInfo.role);

                    if (ugcItemList.size() > 1) {
                        if (ugcViewHolder.ll_pageIndicator_container.getVisibility() != View.VISIBLE) {
                            ugcViewHolder.ll_pageIndicator_container.setVisibility(View.VISIBLE);
                        }

                        // 刷新页签view
                        if (ugcViewHolder.ll_pageIndicator_currentPage.getChildCount() > 0) {
                            ugcViewHolder.ll_pageIndicator_currentPage.removeAllViews();
                        }
                        // 初始化页签
                        for (int i = 1, j = ugcItemList.size(); i <= j; i++) {
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DpPxSpTool.dip2px(mContext, 25), ViewGroup.LayoutParams.WRAP_CONTENT);
                            TextView tv = new TextView(mContext);
                            tv.setText(String.valueOf(i));
                            tv.setTextColor(Color.parseColor("#323232"));
                            tv.setTextSize(24);
                            tv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                            tv.setLayoutParams(lp);
                            ugcViewHolder.ll_pageIndicator_currentPage.addView(tv);
                        }
                        // 初始化总页数
                        ugcViewHolder.tv_pageIndicator_totalPage.setText(String.valueOf(ugcItemList.size()));

                        ugcViewHolder.vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                ugcViewHolder.ll_pageIndicator_currentPage.scrollTo((int) ((position + positionOffset) * DpPxSpTool.dip2px(mContext, 25)), 0);
                            }

                            @Override
                            public void onPageSelected(int position) {
                                if (position == 0) {
                                    ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_gray);
                                    ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_black);

                                } else if (position == ugcItemList.size() - 1) {
                                    ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_black);
                                    ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_gray);

                                } else {
                                    ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_black);
                                    ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_black);
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        if (currentItem == 0) {
                            ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_gray);
                            ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_black);

                        } else if (currentItem == ugcItemList.size() - 1) {
                            ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_black);
                            ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_gray);

                        } else {
                            ugcViewHolder.imgv_pageIndicator_previous.setImageResource(R.drawable.arrow_homepage_ugc_left_black);
                            ugcViewHolder.imgv_pageIndicator_next.setImageResource(R.drawable.arrow_homepage_ugc_right_black);
                        }
                    } else {
                        ugcViewHolder.ll_pageIndicator_container.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mHomepageData.size();
    }


    /**
     * 首页-视频区ViewHolder
     * 图片宽高比 1:1
     * adapter_homepage_video.xml
     */
    private class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageButton imgBtn_next;
        ImageButton imgBtn_previous;
        ViewPager vp_videos;

        VideoViewHolder(View itemView) {
            super(itemView);
            imgBtn_next = (ImageButton) itemView.findViewById(R.id.homepage_video_nextBtn);
            imgBtn_previous = (ImageButton) itemView.findViewById(R.id.homepage_video_previousBtn);
            vp_videos = (ViewPager) itemView.findViewById(R.id.homepage_video_vp);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vp_videos.getLayoutParams();
            lp.height = lp.width = WidthHeightTool.getScreenWidth(mContext);
            vp_videos.setLayoutParams(lp);
        }
    }

    /**
     * 首页-头运营位ViewHolder
     * 图片宽高比 1:1
     */
    private class TopOperationViewHolder extends RecyclerView.ViewHolder {
        ImageButton imgBtn_next;
        ImageButton imgBtn_previous;
        ViewPager vp_operation;

        TopOperationViewHolder(View itemView) {
            super(itemView);
            imgBtn_next = (ImageButton) itemView.findViewById(R.id.homepage_video_nextBtn);
            imgBtn_previous = (ImageButton) itemView.findViewById(R.id.homepage_video_previousBtn);
            vp_operation = (ViewPager) itemView.findViewById(R.id.homepage_video_vp);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vp_operation.getLayoutParams();
            lp.height = lp.width = WidthHeightTool.getScreenWidth(mContext);
            vp_operation.setLayoutParams(lp);
        }
    }

    /**
     * 首页-房源ViewHolder
     * adapter_homepage_house.xml
     * <p>
     * 图片宽高比 3:2
     */
    private class LodgeUnitViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_titleContainer;
        TextView tv_title;
        TextView tv_describe;
        PageIndicatorLayout widget_pageIndicator;
        TextView tv_priceTag;
        TextView tv_price;
        TextView tv_tag;
        TextView tv_houseTitle;
        ViewPager vp_housesImage;

        LodgeUnitViewHolder(View itemView) {
            super(itemView);
            ll_titleContainer = (LinearLayout) itemView.findViewById(R.id.homepage_house_titleContainer);
            tv_title = (TextView) itemView.findViewById(R.id.homepage_house_title);
            tv_describe = (TextView) itemView.findViewById(R.id.homepage_house_describe);
            widget_pageIndicator = (PageIndicatorLayout) itemView.findViewById(R.id.homepage_house_pageIndicator);
            tv_priceTag = (TextView) itemView.findViewById(R.id.homepage_house_priceTag);
            tv_price = (TextView) itemView.findViewById(R.id.homepage_house_price);
            tv_tag = (TextView) itemView.findViewById(R.id.homepage_house_houseTag);
            tv_houseTitle = (TextView) itemView.findViewById(R.id.homepage_house_houseTitle);
            vp_housesImage = (ViewPager) itemView.findViewById(R.id.homepage_house_housesImage);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vp_housesImage.getLayoutParams();
            lp.width = WidthHeightTool.getScreenWidth(mContext);
            lp.height = lp.width / 3 * 2;
            vp_housesImage.setLayoutParams(lp);
        }
    }

    /**
     * 首页-运营活动ViewHolder
     * adapter_homepage_operation.xml
     * <p>
     * 普通运营位宽高比是2:1
     */
    private class OperationViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_titleContainer;
        TextView tv_title;
        TextView tv_describe;
        PageIndicatorLayout widget_pageIndicator;
        ViewPager vp_operations;

        OperationViewHolder(View itemView) {
            super(itemView);
            ll_titleContainer = (LinearLayout) itemView.findViewById(R.id.homepage_operation_titleContainer);
            tv_title = (TextView) itemView.findViewById(R.id.homepage_operation_title);
            tv_describe = (TextView) itemView.findViewById(R.id.homepage_operation_describe);
            widget_pageIndicator = (PageIndicatorLayout) itemView.findViewById(R.id.homepage_operation_pageIndicator);
            vp_operations = (ViewPager) itemView.findViewById(R.id.homepage_operation_operations);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) vp_operations.getLayoutParams();
            lp.width = WidthHeightTool.getScreenWidth(mContext);
            lp.height = lp.width / 2;
            vp_operations.setLayoutParams(lp);
        }
    }

    /**
     * 首页-城市引导图ViewHolder
     * adapter_homepage_city_guide.xml
     * <p>
     * 图片宽高比15:11
     */
    private class CityGuideMapViewHolder extends RecyclerView.ViewHolder {
        ImageView homepage_city_guide_image;
        TextView homepage_city_guide_image_title;

        CityGuideMapViewHolder(View itemView) {
            super(itemView);
            homepage_city_guide_image = (ImageView) itemView.findViewById(R.id.homepage_city_guide_image);
            homepage_city_guide_image_title = (TextView) itemView.findViewById(R.id.homepage_city_guide_image_title);

            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) homepage_city_guide_image.getLayoutParams();
            lp.width = WidthHeightTool.getScreenWidth(mContext);
            lp.height = lp.width / 15 * 11;
            homepage_city_guide_image.setLayoutParams(lp);
        }
    }

    /**
     * 首页-城市房源ViewHolder
     * adapter_homepage_city_house.xml
     * <p>
     * 图片宽高比 3:2
     */
    private class CityHouseViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout tv_city_title_container;
        ImageView tv_city_iconImg;
        TextView tv_city_name;
        TextView tv_city_name_pinyin;
        PageIndicatorLayout widget_pageIndicator;
        TextView tv_currentLu_priceTag;
        TextView tv_currentLu_price;
        TextView tv_currentLu_tag;
        TextView tv_currentLu_title;
        ViewPager vp_lodgeUnit;

        CityHouseViewHolder(View itemView) {
            super(itemView);
            tv_city_title_container = (RelativeLayout) itemView.findViewById(R.id.homepage_city_title_container);
            tv_city_iconImg = (ImageView) itemView.findViewById(R.id.homepage_city_iconImg);
            tv_city_name = (TextView) itemView.findViewById(R.id.homepage_city_name);
            tv_city_name_pinyin = (TextView) itemView.findViewById(R.id.homepage_city_name_pinyin);
            widget_pageIndicator = (PageIndicatorLayout) itemView.findViewById(R.id.homepage_cityHouse_housePageIndicator);
            tv_currentLu_priceTag = (TextView) itemView.findViewById(R.id.homepage_cityHouse_priceTag);
            tv_currentLu_price = (TextView) itemView.findViewById(R.id.homepage_cityHouse_price);
            tv_currentLu_tag = (TextView) itemView.findViewById(R.id.homepage_cityHouse_houseTag);
            tv_currentLu_title = (TextView) itemView.findViewById(R.id.homepage_cityHouse_houseTitle);
            vp_lodgeUnit = (ViewPager) itemView.findViewById(R.id.homepage_cityHouse_housesImage);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vp_lodgeUnit.getLayoutParams();
            lp.width = WidthHeightTool.getScreenWidth(mContext);
            lp.height = lp.width / 3 * 2;
            vp_lodgeUnit.setLayoutParams(lp);
        }
    }

    /**
     * 首页 - UGC(用户原创内容) ViewHolder
     */
    private class UGCViewHolder extends RecyclerView.ViewHolder {

        ImageView imgv_userInfo_headImage;
        TextView tv_userInfo_name;
        TextView tv_userInfo_identity;
        ViewPager vp_content;
        LinearLayout ll_pageIndicator_container;
        LinearLayout ll_pageIndicator_currentPage;
        TextView tv_pageIndicator_totalPage;
        ImageView imgv_pageIndicator_previous;
        ImageView imgv_pageIndicator_next;

        UGCViewHolder(View itemView) {
            super(itemView);
            imgv_userInfo_headImage = (ImageView) itemView.findViewById(R.id.homepage_ugc_userInfo_headImage);
            tv_userInfo_name = (TextView) itemView.findViewById(R.id.homepage_ugc_userInfo_name);
            tv_userInfo_identity = (TextView) itemView.findViewById(R.id.homepage_ugc_userInfo_identity);
            vp_content = (ViewPager) itemView.findViewById(R.id.homepage_ugc_content);
            ll_pageIndicator_container = (LinearLayout) itemView.findViewById(R.id.homepage_ugc_pageIndicator_container);
            ll_pageIndicator_currentPage = (LinearLayout) itemView.findViewById(R.id.homepage_ugc_pageIndicator_currentPage_container);
            tv_pageIndicator_totalPage = (TextView) itemView.findViewById(R.id.homepage_ugc_pageIndicator_totalPages);
            imgv_pageIndicator_previous = (ImageView) itemView.findViewById(R.id.homepage_ugc_pageIndicator_previous);
            imgv_pageIndicator_next = (ImageView) itemView.findViewById(R.id.homepage_ugc_pageIndicator_next);
        }
    }
}
