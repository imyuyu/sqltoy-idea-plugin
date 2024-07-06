package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface service-checker element.
 *
 * @author imyuyu
 */
@Stubbed
interface ServiceChecker : DomCheckerBean {

    @Stubbed
    @Required
    @Attribute("service")
    fun getService() : GenericAttributeValue<String>

    @Stubbed
    @Required
    @Attribute("method")
    fun getMethod() : GenericAttributeValue<String>
}