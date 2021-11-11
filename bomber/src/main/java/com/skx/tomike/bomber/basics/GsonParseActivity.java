package com.skx.tomike.bomber.basics;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_GSON;

/**
 * 描述 : json 解析
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/25 11:05 AM
 */
@Route(path = ROUTE_PATH_GSON, group = ROUTER_GROUP)
public class GsonParseActivity extends SkxBaseActivity<BaseViewModel> {

    private static final String URL_ENCODE = "UTF-8";

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("json 解析").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_url_encode;
    }

    @Override
    protected void initView() {
        Gson gson = new Gson();
        StoreDetailIndex storeDetailIndex = gson.fromJson(example, StoreDetailIndex.class);
        Log.e(TAG, storeDetailIndex.toString());
    }

    private final String example = "{\n" +
            "    \"storeDetail\": {\n" +
            "        \"storeName\": \"中关村加班群\",\n" +
            "        \"storeId\": \"52384391233538\",\n" +
            "        \"storeType\": \"2\",\n" +
            "        \"landlordId\": \"70001057291706\",\n" +
            "        \"addressInfo\": {\n" +
            "            \"nationId\": \"0\",\n" +
            "            \"provinceId\": \"1\",\n" +
            "            \"cityId\": \"12\",\n" +
            "            \"districtId\": \"173\",\n" +
            "            \"detailAddress\": \"圆明园西门\",\n" +
            "            \"latitude\": \"40.0331564\",\n" +
            "            \"longitude\": \"116.289259\",\n" +
            "            \"nationName\": \"中国\",\n" +
            "            \"provinceName\": \"北京市\",\n" +
            "            \"districtName\": \"海淀区\",\n" +
            "            \"cityName\": \"北京市\",\n" +
            "            \"shortName\": \"北京\",\n" +
            "            \"pinyin\": \"beijing\",\n" +
            "            \"shortPinyin\": \"bj\",\n" +
            "            \"standardCode\": \"110000\",\n" +
            "            \"timezone\": \"+08:00\",\n" +
            "            \"displayAddress\": \"北京市北京市海淀区圆明园西门\",\n" +
            "            \"displayAddressBrief\": \"北京市北京市海淀区圆明园西门\",\n" +
            "            \"isOverSea\": false,\n" +
            "            \"isGangAoTai\": false\n" +
            "        },\n" +
            "        \"storeImages\": [\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,QdZa,1500,1125,2,9fb89515.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,QdZa,1500,1125,2,9fb89515.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,QdZt,1358,801,2,6c3714d5.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,QdZt,1358,801,2,6c3714d5.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,QdZJ,1201,801,2,dd321edb.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,QdZJ,1201,801,2,dd321edb.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,QdZT,1202,801,2,2214f93b.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,QdZT,1202,801,2,2214f93b.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,Qt2N,1068,801,2,fd8a6d47.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,Qt2N,1068,801,2,fd8a6d47.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,Qt2P,1069,801,2,0625f797.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,Qt2P,1069,801,2,0625f797.webp\",\n" +
            "                \"photoType\": \"appearance\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,Qe00,1416,801,2,2fb4227b.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,Qe00,1416,801,2,2fb4227b.webp\",\n" +
            "                \"photoType\": \"shareArea\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,Qe01,1433,801,2,88c0e0ab.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,Qe01,1433,801,2,88c0e0ab.webp\",\n" +
            "                \"photoType\": \"shareArea\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"bigImgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,900,600,1,100,1/s,3,Qe09,1276,851,2,3bd43368.webp\",\n" +
            "                \"imgUrl\": \"https://imgsec.xiaozhustatic1.com:443/imgshowsource/00,648,432,1,100,1/s,3,Qe09,1276,851,2,3bd43368.webp\",\n" +
            "                \"photoType\": \"entertainment\",\n" +
            "                \"jumpUrl\": \"\",\n" +
            "                \"resourceType\": \"image\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"storeTags\": [\n" +
            "            {\n" +
            "                \"title\": \"24小时前台\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"免费停车场\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"收费停车场\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"行李寄存\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"泳池\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"KTV\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"游戏室\",\n" +
            "                \"content\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"title\": \"2020年装修\",\n" +
            "                \"content\": \"\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"facility\": [\n" +
            "            {\n" +
            "                \"type\": \"facility\",\n" +
            "                \"title\": \"基础\",\n" +
            "                \"briefShowNums\": \"3\",\n" +
            "                \"items\": [\n" +
            "                    {\n" +
            "                        \"name\": \"公共WiFi\",\n" +
            "                        \"key\": \"1\",\n" +
            "                        \"display\": true\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"24小时前台\",\n" +
            "                        \"key\": \"2\",\n" +
            "                        \"display\": true\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"餐厅\",\n" +
            "                        \"key\": \"4\",\n" +
            "                        \"display\": true\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"traffic\",\n" +
            "                \"title\": \"交通\",\n" +
            "                \"briefShowNums\": \"3\",\n" +
            "                \"items\": [\n" +
            "                    {\n" +
            "                        \"name\": \"接送机服务\",\n" +
            "                        \"key\": \"1\",\n" +
            "                        \"display\": true\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"接送站服务\",\n" +
            "                        \"key\": \"2\",\n" +
            "                        \"display\": true\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"免费停车场\",\n" +
            "                        \"key\": \"4\",\n" +
            "                        \"display\": true\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"entertainment\",\n" +
            "                \"title\": \"娱乐\",\n" +
            "                \"briefShowNums\": \"3\",\n" +
            "                \"items\": [\n" +
            "                    {\n" +
            "                        \"name\": \"泳池\",\n" +
            "                        \"key\": \"1\",\n" +
            "                        \"display\": true\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"棋牌室\",\n" +
            "                        \"key\": \"2\",\n" +
            "                        \"display\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"KTV\",\n" +
            "                        \"key\": \"8\",\n" +
            "                        \"display\": true\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"online\": true,\n" +
            "        \"isSupportIm\": true\n" +
            "    },\n" +
            "    \"bookNotice\": [\n" +
            "        {\n" +
            "            \"type\": \"checkInNotice\",\n" +
            "            \"name\": \"入住/退房\",\n" +
            "            \"detail\": \"入住日14:00后入住，离店日12:00前离开 (以当地城市时区为准)\",\n" +
            "            \"highLight\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"invoiceNotice\",\n" +
            "            \"name\": \"发票信息\",\n" +
            "            \"detail\": \"如需发票，请联系房东索取\",\n" +
            "            \"highLight\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"childNotice\",\n" +
            "            \"name\": \"儿童政策\",\n" +
            "            \"detail\": \"允许携带儿童\",\n" +
            "            \"highLight\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"foreigner\",\n" +
            "            \"name\": \"境外房客\",\n" +
            "            \"detail\": \"接待境外人士\",\n" +
            "            \"highLight\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"landlordDemand\",\n" +
            "            \"name\": \"房东要求\",\n" +
            "            \"detail\": \"允许举办派对/活动,允许商业拍摄,允许吸烟,允许做饭,允许携带宠物\",\n" +
            "            \"highLight\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"otherDemand\",\n" +
            "            \"name\": \"其他要求\",\n" +
            "            \"detail\": \"已登录-在房客身份\\n昵称tooltip: 房客中心及相关子功能，点击跳转至对应页面（未变动）；\\n点击退出退出登录；\\n房东后台无tooltip，点击判断是该用户是否已是老房东直接进入到个人房东-房东中心首页；新房东在当前页面弹出身份选择弹窗；\\n小猪指南、手机小猪、去小程序领950元礼券，保持原样（对应的tooltip见设计稿）\\n 已登录-在房东身份（见6.2）\",\n" +
            "            \"highLight\": false\n" +
            "        }\n" +
            "    ],\n" +
            "    \"couponInfo\": null,\n" +
            "    \"userIsFavorite\": false,\n" +
            "    \"roomList\": null,\n" +
            "    \"promotionInfo\": null,\n" +
            "    \"promotionRoomId\": \"\",\n" +
            "    \"storeComment\": {\n" +
            "        \"commentCount\": \"4\",\n" +
            "        \"allcommentScore\": \"4.8\",\n" +
            "        \"sanitationScore\": \"4.4\",\n" +
            "        \"securityScore\": \"4.7\",\n" +
            "        \"descriptionScore\": \"5.0\",\n" +
            "        \"locationScore\": \"5.0\",\n" +
            "        \"performanceScore\": \"5.0\",\n" +
            "        \"comments\": [\n" +
            "            {\n" +
            "                \"commentId\": \"\",\n" +
            "                \"commentedRoomName\": \"周六加班双床房2号\",\n" +
            "                \"content\": \"Click: com.xiaozhu.xzdz:id/uptate__left, android.widget.TextView, (303.0,1745.0)\\n升级弹窗\\ncom.xiaozhu.biz.support.business.dialog.XZDialogContentActivity\\nClick: com.xiaozhu.xzdz:id/uptate__left, android.widget.TextView, (303.0,1745.0)\\nClick: com.xiaozhu.xzdz:id/uptate__left, android.widget.TextView, (303.0,1745.0)\\nClick: com.xiaozhu.xzdz:id/uptate__left, android.widget.TextView, (303.0,1745.0)\\nClick: com.xiaozhu.xzdz:id/uptate__left, android.widget.TextView, (303.0,1745.0)\",\n" +
            "                \"checkinday\": \"2020年12月\",\n" +
            "                \"isLandlordReply\": false,\n" +
            "                \"scheduledDays\": \"1\",\n" +
            "                \"commentScore\": \"5.0\",\n" +
            "                \"authorHeadImageUrl\": \"http://image.xiaozhustatic1.com/roundcrop/1/00,128,128,1,100,2/8,0,88,8632,260,260,2b8e819c.jpg\",\n" +
            "                \"authorName\": \"企业房东账号_2\",\n" +
            "                \"images\": null\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
}
