package com.skx.tomike.bomber.basics

import android.annotation.SuppressLint
import android.util.Log
import android.widget.RadioGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.bomber.R
import com.skx.tomike.bomber.ROUTE_PATH_XML_PARSE
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.InputStreamReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

/**
 * 描述 : xml 解析
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/14 10:09 上午
 */
@Route(path = ROUTE_PATH_XML_PARSE)
class XmlParseActivity : SkxBaseActivity<BaseViewModel>() {

    private val mTvResult: TextView by lazy {
        findViewById(R.id.tv_xmlParse_result)
    }
    private val mTvCostTime: TextView by lazy {
        findViewById(R.id.tv_xmlParse_costTime)
    }
    private val mRGroup: RadioGroup by lazy {
        findViewById(R.id.tv_xmlParse_group)
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("XML 解析").create()
    }

    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_xml_parse
    }

    override fun initView() {
        mRGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.tv_xmlParse_domParse -> {
                    domParse()
                }
                R.id.tv_xmlParse_saxParse -> {
                    saxParse()
                }
                R.id.tv_xmlParse_pullParse -> {
                    pullParse()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun domParse() {
        mTvResult.text = ""
        val start = System.currentTimeMillis()

        // 打开xml文件到输入流
        val stream: InputStream = assets.open("subject.xml")
        // 获取一个 DocumentBuilder 的工厂类
        val builderFactory = DocumentBuilderFactory.newInstance()
        // 获取 DocumentBuilder 的实例对象
        val builder = builderFactory.newDocumentBuilder()
        // 将给定的输入流解析为xml，并用一个DOM 对象来表示。
        val document = builder.parse(stream)
        // 获取xml 的根节点
        val documentElement = document.documentElement
        // 获取根节点下符合标签名称的所有子节点
        val nodeList: NodeList = documentElement.getElementsByTagName("language")

        for (i in 0 until nodeList.length) {
            //获取所有子元素
            val language = nodeList.item(i) as Element
            //获取language的属性（这里即为id）并显示
            mTvResult.append(language.getAttribute("id").toString() + "\n")
            //获取language的子元素 name 并显示
            mTvResult.append(language.getElementsByTagName("name").item(0).textContent + "\n")
            //获取language的子元素usage 并显示
            mTvResult.append(language.getElementsByTagName("usage").item(0).textContent + "\n")
        }
        stream.close()
        val end = System.currentTimeMillis()
        mTvCostTime.text = "DOM 解析消耗的时间为：${end - start}ms"
    }

    /**
     * SAX（Simple API for XML）解析
     */
    @SuppressLint("SetTextI18n")
    private fun saxParse() {
        mTvResult.text = ""
        val start = System.currentTimeMillis()

        //获取文件资源建立输入流对象
        val stream = assets.open("subject.xml")

        // 获取 SAX 解析工厂类
        val factory: SAXParserFactory = SAXParserFactory.newInstance()
        // 获取 SAX 解析器的实例对象
        val parser: SAXParser = factory.newSAXParser()
        // 创建XML解析处理器
        val xmlHandler = SkxSaxHandler(mTvResult)
        // 将xml解析处理器分配给解析器,对文档进行解析,将事件发送给处理器
        parser.parse(stream, xmlHandler)
        stream.close()
        val end = System.currentTimeMillis()
        mTvCostTime.text = "SAX 解析消耗的时间为：${end - start}ms"
    }

    /**
     * pull 解析
     */
    @SuppressLint("SetTextI18n")
    private fun pullParse() {
        mTvResult.text = ""
        val start = System.currentTimeMillis()

        // 1.获取pull 解析的工厂类
        val factory = XmlPullParserFactory.newInstance()
        // 2.生成一个pull 解析器对象
        val pullParser = factory.newPullParser()

        // 打开xml文件到输入流
        val stream: InputStream = assets.open("subject.xml")
        val inputStreamReader = InputStreamReader(stream)
        // 3.给解析器设置输入源
        pullParser.setInput(inputStreamReader)

        // 处理解析事件
        var eventType = pullParser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val name = pullParser.name
            when (eventType) {
                XmlPullParser.START_DOCUMENT -> {
                    Log.i("PULL", "开始解析~")
                }
                XmlPullParser.START_TAG -> {
                    Log.i("PULL", "$name 节点解析开始~")
                    when (name) {
                        "language" -> {
                            val attributeName = pullParser.getAttributeValue(null, "id")
                            mTvResult.append(attributeName + "\n")
                        }
                        "name" -> {
                            mTvResult.append(pullParser.nextText() + "\n")
                        }
                        "usage" -> {
                            mTvResult.append(pullParser.nextText() + "\n")
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    Log.i("PULL", "$name 节点解析结束~")
                }
                XmlPullParser.TEXT -> {
                    // 注：当调用了 nextText(）时，就不会再触发 XmlPullParser.TEXT 事件了。
                    // Log.i("PULL", "XmlPullParser.TEXT-> ${pullParser.text}")
                }
            }
            // 继续下一个解析事件
            eventType = pullParser.next()
        }
        inputStreamReader.close()
        stream.close()
        val end = System.currentTimeMillis()
        mTvCostTime.text = "PULL 解析消耗的时间为：${end - start}ms"
    }
}

class SkxSaxHandler(private val tv: TextView) : DefaultHandler() {

    private var language: Language? = null
    private var languages: ArrayList<Language>? = null

    //当前解析的元素标签
    private var tagName: String? = null
    override fun startDocument() {
        super.startDocument()
        this.languages = ArrayList()
        Log.i("SAX", "读取到文档头,开始解析xml")
    }

    override fun startElement(
            uri: String?,
            localName: String?,
            qName: String?,
            attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
        if (localName.equals("language")) {
            language = Language()
            language?.id = attributes?.getValue("id") ?: ""
            Log.i("SAX", "开始处理 language 元素~")
            tv.append("${language?.id} \n")
        }
        tagName = localName
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        //判断当前标签是否有效
        if (tagName != null && ch != null && ch.isNotEmpty()) {
            val data = String(ch, start, length)
            //读取标签中的内容
            when (tagName) {
                "name" -> {
                    this.language?.name = data
                    tv.append("$data \n")
                    Log.i("SAX", "name 元素内容:${data}")
                }
                "usage" -> {
                    this.language?.usage = data
                    tv.append("$data \n")
                    Log.i("SAX", "usage 元素内容:${data}")
                }
                else -> {
                    Log.i("SAX", "未知元素内容:${tagName} -> $data")
                }
            }
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if (localName.equals("language")) {
            language?.run {
                languages?.add(this)
            }
            language = null
            Log.i("SAX", "处理 language 元素结束~")
        }
        this.tagName = null
    }


    override fun endDocument() {
        super.endDocument()
        Log.i("SAX", "读取到文档尾,xml解析结束")
    }
}

class Language {
    var id: String? = ""
    var name: String? = ""
    var usage: String? = ""
}

