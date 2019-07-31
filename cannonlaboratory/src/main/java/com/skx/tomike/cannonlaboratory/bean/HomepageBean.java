package com.skx.tomike.cannonlaboratory.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageBean {

    /**
     * 首页- 视频区数据
     * type:video
     */
    public static class HomepageVideo {
        public List<HomepageVideoItemInfo> items;

        public List<HomepageVideoItemInfo> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页- 视频信息
     */
    public static class HomepageVideoItemInfo {
        /**
         * 优酷ID
         */
        public String ykId;
        /**
         * 视频背景图url
         */
        public String imageUrl;
        /**
         * 视频标题
         */
        public String title = "视频标题";
        /**
         * 视频描述
         */
        public String desc = "视频描述 呵呵呵呵呵呵呵哒";
        /**
         * 视频时长
         */
        public String time = "15:12";
        /**
         * 文本和播放按钮颜色样式 dark/light
         */
        public String color = (new Random().nextInt(5)) % 2 == 0 ? "dark" : "light";
    }

    /**
     * 首页-运营区数据
     * type:operation
     */
    public static class Operation {
        /**
         * 是否为1:1布局 true/false
         */
        public boolean isSquare;
        /**
         * 运营标题
         */
        public String title = "运营活动标题";
        /**
         * 运营描述
         */
        public String desc = "运营活动描述 - 测试数据";
        /**
         * 是否隐藏数字按钮 true/false
         */
        public String hideDigitBtn;
        public List<OperationItem> items;

        public List<OperationItem> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页- 运营信息
     */
    public static class OperationItem {
        /**
         * 图片url
         */
        public String imgUrl;
        /**
         * 跳转链接
         */
        public String jumpUrl;
    }

    /**
     * 首页-推荐房源数据
     * type:lodgeunit
     */
    public static class LodgeUnit {
        /**
         * 运营标题
         */
        public String title = "推荐房源 - 标题";
        /**
         * 运营描述
         */
        public String desc = "推荐房源  - 描述";
        /**
         * 是否是海外房源 yes no
         */
        public String abroad = "";
        public String timezone = "";
        /**
         * 是否隐藏数字按钮 true/false
         */
        public String hideDigitBtn;
        public List<LodgeUnitItem> items;

        public List<LodgeUnitItem> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页 - 房源信息
     */
    public static class LodgeUnitItem {
        /**
         * 图片url
         */
        public String imgUrl;
        /**
         * 房源ID
         */
        public String luId;
        /**
         * 房源标签
         */
        public String lodgeUnitTag = "imba";
        /**
         * 房源描述
         */
        public String lodgeUnitDesc = "描述下这个房源";
        /**
         * 房源价格信息
         */
        public LodgeUnitPriceInfo showPrice;

        public LodgeUnitPriceInfo getShowPrice() {
            if (showPrice == null) {
                showPrice = new LodgeUnitPriceInfo();
            }
            return showPrice;
        }
    }

    /**
     * 首页 - 房源价格信息
     */
    public static class LodgeUnitPriceInfo {
        /**
         * 显示价格前置描述
         */
        public String pricePreTip = "";
        /**
         * 显示价格
         */
        public String price = "";
        /**
         * 显示价格后描述文字 如：起
         */
        public String priceTip = "";
        /**
         * 显示价格单位 如： /晚
         */
        public String priceUnitTip = "";
        /**
         * 是否特价
         */
        public String fiexdPrice = "";
        /**
         * 特价开始时间
         */
        public String startDate = "";
        /**
         * 特价结束时间
         */
        public String endDate = "";
        /**
         * 折扣前置描述 如：特价
         */
        public String discountPreTip = "";
        /**
         * 折扣
         */
        public String discount = "";
        /**
         * 是否第一次筛选
         */
        public String firstFilter = "";
        /**
         * 原价格
         */
        public String originalPrice = "";
        /**
         * 原价格描述文字
         */
        public String originalPriceTip = "";
        /**
         * 货币符号
         */
        public String currency = "";
        /**
         * 货币符号描述
         */
        public String currencyTip = "";
        /**
         * 特价是否在选择时间段
         */
        public String inCheckDate = "";
    }

    /**
     * 首页 -运营点数据
     * type :operationPoint
     */
    public static class OperationPoint {
        public List<OperationPointInfo> items;

        public List<OperationPointInfo> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页 - 运营点信息
     */
    public static class OperationPointInfo {
        /**
         * 运营点标题
         */
        public String title = "运营活动 - 标题";
        /**
         * 运营点描述
         */
        public String desc = "运营活动 - 描述";
        /**
         * 跳转链接
         */
        public String jumpUrl = "";
    }

    /**
     * 首页 - 城市引导图数据
     * type:cityGuide
     */
    public static class CityGuide {
        public List<String> showPrice;// 引导图片url数组

        public List<String> getShowPrice() {
            if (showPrice == null) {
                showPrice = new ArrayList<>();
            }
            return showPrice;
        }
    }

    /**
     * 首页-城市区数据
     * type:cityArea
     */
    public static class CityArea {
        /**
         * 城市图标
         */
        public String iconUrl = "";
        /**
         * 城市名称
         */
        public String cityName = "狗不理市";
        /**
         * 城市ID
         */
        public String cityId = "";
        /**
         * 城市英文名称
         */
        public String citySubName = "GOUBULI";
        /**
         * 是否隐藏数字按钮 true/false
         */
        public String hideDigitBtn;
        public List<LodgeUnitItem> items;

        public List<LodgeUnitItem> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页-UGC数据
     * <p>
     * type：UGC
     */
    public static class UGC {
        public List<UGCItemInfo> items;

        public List<UGCItemInfo> getItems() {
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    /**
     * 首页-ugc-单条数据
     */
    public static class UGCItemInfo {
        /**
         * 用户头像url
         */
        public String headImgUrl = "";
        /**
         * 用户昵称
         */
        public String nickName = "牛肉味";
        /**
         * 用户角色 tenant/landlord
         */
        public String role = (new Random().nextInt(5)) % 2 == 0 ? "tenant" : "landlord";
        /**
         * 房源ID
         */
        public String luId = "";
        /**
         * 用户ID
         */
        public String userId = "";
        /**
         * 点评或日记内容
         */
        public String content = "我就是瞎来的测试数据，长度也只是为了凑字数，看不惯你可以自己写啊！";
        /**
         * 点评或日记的时间
         */
        public String time = "2017.01.01 00:00";
    }
}
