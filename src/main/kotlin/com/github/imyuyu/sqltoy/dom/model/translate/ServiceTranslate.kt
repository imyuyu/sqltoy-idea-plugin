package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface service-translate element.
 *
 * @author imyuyu
 */
interface ServiceTranslate : DomTranslateBean {

    @Required
    @Attribute("service")
    fun getService() : GenericAttributeValue<String>

    @Required
    @Attribute("keep-alive")
    fun getKeepAlive() : GenericAttributeValue<Int>

    @Required
    @Attribute("method")
    fun getMethod() : GenericAttributeValue<String>
}