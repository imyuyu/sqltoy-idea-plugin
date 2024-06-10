package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.spring.model.converters.SpringBeanIdConverter
import com.intellij.util.xml.*
import java.lang.reflect.Method

/**
 * The interface service-translate element.
 *
 * @author imyuyu
 */
interface ServiceTranslate : Translate {

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