package com.skx.common.net.interceptor

import android.util.Base64
import android.util.Log
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * 描述 : 加解密拦截器。
 * 注：
 * 1.当前只支持 POST请求的加密。
 * 2.POST请求表单和多内容的情况暂时没有进行加密处理，只处理了格式为json的情况
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/29 11:29 下午
 */
class EncryptionInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = encrypt(chain.request())
        val response = chain.proceed(request)
        return decrypt(response)
    }

    @Throws(IOException::class)
    private fun encrypt(request: Request): Request {
        val origin = request.body() ?: return request
        if ("POST".equals(request.method(), ignoreCase = true)) {
            val contentType: MediaType? = origin.contentType()
            if (contentType != null
                    && "application".equals(contentType.type(), ignoreCase = true)
                    && "json".equals(contentType.subtype(), ignoreCase = true)
            ) {
                val buffer = Buffer()
                origin.writeTo(buffer)
                val bodyString = buffer.readUtf8()
                Log.d(TAG, "Encryption，加密前：$bodyString")
                var encryptedBody = ""
                try {
                    encryptedBody = dataEncrypt(bodyString)
                } catch (e: Exception) {
                    Log.e(TAG, "EncryptionInterceptor，加密异常：${e}")
                    e.printStackTrace()
                }
                Log.d(TAG, "Encryption，加密后：$encryptedBody")
                val body: RequestBody = RequestBody.create(MEDIA_TYPE, encryptedBody)
                val builder = request.newBuilder()
                        .header("Content-Type", body.contentType().toString())
                        .header("Content-Length", body.contentLength().toString())
                        .post(body)
                return builder.build()
            }
        }
        return request
    }

    @Throws(IOException::class)
    private fun decrypt(response: Response): Response {
        val responseBody: ResponseBody? = response.body()
        responseBody?.run {
            val value = responseBody.string()
            try {
                // 解密步骤：①...；②...；③...
                Log.d(TAG, "Decryption，原数据=" + value + " mediaType=" + contentType())
                val json: String = dataDecrypt(value)
                Log.d(TAG, "Decryption，解密后数据=$json")

                val body = ResponseBody.create(MEDIA_TYPE, json)
                return response.newBuilder().body(body).build()

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return response
    }

    /**
     * 数据加密。
     * todo 使用自己的加密方案
     */
    private fun dataEncrypt(bodyString: String?): String {
        return bodyString?.run {
            // 加密示例：base64编码
            Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
        } ?: kotlin.run { "" }
    }

    /**
     * 数据解密
     * todo 使用自己的解密方案
     */
    private fun dataDecrypt(bodyString: String): String {
        return String(Base64.decode(bodyString, Base64.DEFAULT))
    }


    companion object {
        private const val TAG = "TomikeInterceptor"
        private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    }
}