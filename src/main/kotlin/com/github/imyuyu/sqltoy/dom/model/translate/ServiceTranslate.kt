package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.spring.model.converters.SpringBeanIdConverter
import com.intellij.util.xml.*

/**
 * The interface service-translate element.
 *
 * @author imyuyu
 */
@Stubbed
interface ServiceTranslate : DomTranslateBean {

    @Stubbed
    @Required
    @Attribute("service")
    @Referencing(value = SpringBeanIdConverter::class, soft = true)
    fun getService() : GenericAttributeValue<String>

    @Stubbed
    @Required
    @Attribute("keep-alive")
    fun getKeepAlive() : GenericAttributeValue<Int>

    @Stubbed
    @Required
    @Attribute("method")
    fun getMethod() : GenericAttributeValue<String>
}