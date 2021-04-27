package com.skx.tomike.bomber.basics


/**
 * 描述 : 门店详情首页接口接收数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:57 AM
 */
data class StoreDetailIndex(
        var storeDetail: StoreDetailInfo?,// 门店信息
        var promotionRoomId: String?,// 查促销二级页使用
        var promotionInfo: List<StorePromotionAndCouponItemInfo>?,// 促销信息
        var couponInfo: List<StorePromotionAndCouponItemInfo>?,// 代金券信息
        var userIsFavorite: Boolean,
        var storeComment: StoreCommentInfo?,// 门店点评信息
        var roomList: List<StoreRoomInfo>?,// 房型列表
        var bookNotice: List<BookNoticeInfo>?,// 预订规则
)

/**
 * 描述 : 门店详情数据bean
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 10:27 AM
 */
data class StoreDetailInfo(
        var storeName: String? = "",
        var storeId: String? = "",
        var storeType: String? = "",
        var landlordId: String? = "",
        var addressInfo: StoreAddressInfo?,
        var storeImages: List<StoreImage>?,
        var storeTags: List<StoreTag>?,
        var facility: List<StoreFacilityInfo>?,
        var online: Boolean,
        var isSupportIm: Boolean
)

/**
 * 描述 : 门店详情 - 门店地址数据bean
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 10:28 AM
 */
data class StoreAddressInfo(var nationId: String? = "",
                            var cityId: String? = "",
                            var cityName: String? = "",
                            var shortName: String? = "",
                            var pinyin: String? = "",
                            var shortPinyin: String? = "",
                            var standardCode: String? = "",
                            var provinceId: String? = "",
                            var timezone: String? = "",
                            var latitude: String? = "",
                            var longitude: String? = "",
                            var displayAddress: String? = ""
)

/**
 * 描述 : 门店详情 - 门店图片数据bean
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 10:28 AM
 */
data class StoreImage(
        var bigImgUrl: String? = "",
        var imgUrl: String? = "",
        var jumpUrl: String? = "",
        // 图片类型：appearance、shareArea、restaurant、entertainment、others，用于区分在相册中的额分类
        var photoType: String? = "",
        // 图片源。image/vr/video
        var resourceType: String? = ""
)

/**
 * 描述 : 门店标签
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:47 AM
 */
data class StoreTag(
        var title: String? = "",
        var content: String? = ""
)

/**
 * 描述 : 门店设备
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:49 AM
 */
data class StoreFacilityInfo(
        var type: String? = "",
        var title: String? = "",
        var briefShowNums: String? = "",
        var items: List<FacilityItem>?
)

/**
 * 描述 : 门店设备条目信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:49 AM
 */
data class FacilityItem(
        var name: String? = "",
        var key: String? = "",
        var display: Boolean
)

data class StorePromotionAndCouponItemInfo(
        var name: String? = ""
)

/**
 * 描述 : 门店点评信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:49 AM
 */
data class StoreCommentInfo(
        var commentCount: String? = "",
        var allcommentScore: String? = "",
        var sanitationScore: String? = "",
        var securityScore: String? = "",
        var descriptionScore: String? = "",
        var locationScore: String? = "",
        var performanceScore: String? = "",
        var comments: List<StoreCommentItem>?
)

/**
 * 描述 : 门店具体的某条点评信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:49 AM
 */
data class StoreCommentItem(
        var commentId: String? = "",
        var content: String? = "",
        var checkinday: String? = "",
        var isLandlordReply: Boolean,
        var scheduledDays: String? = "",
        var commentScore: String? = "",
        var commentedRoomName: String? = "",
        var images: List<StoreCommentImage>?,
        var authorHeadImageUrl: String? = "",
        var authorName: String? = ""
)

/**
 * 描述 : 门店点评图片信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:51 AM
 */
data class StoreCommentImage(
        var thumbImgUrl: String? = "",
        var bigImgUrl: String? = "",
        var imgIntro: String? = ""
)

/**
 * 描述 : 门店房型信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:51 AM
 */
data class StoreRoomInfo(
        var roomId: String? = "",// 房型id
        var skuId: String? = "",// 房型sku
        var roomName: String? = "",
        var imageUrl: String? = "",
        var originalPrice: String? = "",// 原价
        var promotionPrice: String? = "",// 优惠价
        var roomTagsInfo: String? = "",// 房型标签
        var cancelRuleTips: String? = "",
        var sellState: String? = "",// 是否可售，0 不可售；1 可售
        var currency: String? = "",
        var currencySymbol: String? = ""
)

/**
 * 描述 : 门店预订规则
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/11/25 11:54 AM
 */
data class BookNoticeInfo(
        var type: String? = "",
        var name: String? = "",
        var detail: String? = "",
        var highLight: Boolean
)