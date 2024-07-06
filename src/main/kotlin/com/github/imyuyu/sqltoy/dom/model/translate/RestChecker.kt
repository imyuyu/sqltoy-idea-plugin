package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface rest-checker element.
 *
 * @author imyuyu
 */
@Stubbed
interface RestChecker : DomCheckerBean {

    @Stubbed
    @Required
    @Attribute("url")
    fun getUrl() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("username")
    fun getUsername() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("password")
    fun getPassword() : GenericAttributeValue<String>
}