package com.skx.tomike.networkrequest.Interface;

import com.skx.tomike.networkrequest.javabean.JuheRespContent;
import com.skx.tomike.networkrequest.javabean.RespContent;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by shiguotao on 2016/8/31.
 * <p>
 * 网络请求Retrofit接口
 */
public interface INetConnection {
    /*
    Request 学习笔记

    @GET, @POST, @PUT, @DELETE, @PATCH or @HEAD

    1.把请求链接分成base url 和相对url，而不是完整的url，好处是：Retrofit只需要一次请求基本URL，而且要修改基础url的话只需要修改一处，而不是每个方法都修改

    2.必须指定返回类型，如果明确接受类型可以使用Call <指定类型>，Retrofit将自动映射，您将不必进行任何手动解析。如果您想要原始响应，可以使用ResponseBody替代像UserInfo这样的特定类。
    如果您根本不关心服务器的响应，可以使用Void。 在所有这些情况下，您必须将其包装成一个类型的Retrofit Call <>类。

    3.  @Body: send Java objects as request body. 发送一个java对象作为请求体
        @Url:使用动态url
        @Field: send data as form-urlencoded.发送表单数据


     动态URL(dynamic URLs)
     4.动态url： 保留@GET 注解，但是不添加的endpoint url，同时添加 @Url 注解到方法中

     5.关于动态url 和 base url 的冲突：
        5.1
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("https://s3.amazon.com/profile-picture/path");

            request url results in: https://s3.amazon.com/profile-picture/path
        因为设置了完全不同的 host，包括scheme （https://s3.amazon.com vs. https：//your.api.url），所以 OkHttp 的 HttpUrl 会将其解析为动态的。

        5.2
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("profile-picture/path");

            request url results in: https://your.api.url/profile-picture/path
        因为动态定义的 endpoint url 没有指定 host，包括scheme，所以HttpUrl 会把定义的endpoint url 拼接到base url 上组成最终请求的url

        5.3
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/v2/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("/profile-picture/path");

            request url results in: https://your.api.url/profile-picture/path
        这是因为以"/"开始的endpoint url 只会追加到 base url的 host url 上。当使用"/" 作为endpoint url的开头时，host url 后面的内容都将被省略。解决方案：endpoint url 不以"/" 开始。
     */

    /*
        Response 学习笔记
     */

    @GET
    Call<RespContent> requestByGet(@Url String url, @QueryMap Map<String, String> paramsMap);

    @GET
    Call<JuheRespContent> juheRequestByGet(@Url String url, @QueryMap Map<String, String> paramsMap);

    @GET
    Call<ResponseBody> juheRequestByGet2(@Url String url, @QueryMap Map<String, String> paramsMap);

    @POST
    Call<RespContent> requestByPost(@Url String url, @QueryMap Map<String, String> paramsMap);

    /*

    // 设置请求方法为get，相对路径为注解内内容，其中{test}会被@Path注解指定内容替换   @Query用于指定参数
    @GET("/users/{user}/repos")
    Call<RespContent> reposForUser(
            @Path("user") String user
    );
     */
}
