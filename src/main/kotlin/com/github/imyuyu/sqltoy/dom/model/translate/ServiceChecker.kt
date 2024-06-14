package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface service-checker element.
 *
 * @author imyuyu
 */
interface ServiceChecker : DomCheckerBean {

    @Required
    @Attribute("service")
    fun getService() : GenericAttributeValue<String>

    @Required
    @Attribute("method")
    fun getMethod() : GenericAttributeValue<String>
}