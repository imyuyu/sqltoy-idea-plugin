package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface rest-checker element.
 *
 * @author imyuyu
 */
interface RestChecker : DomCheckerBean {

    @Required
    @Attribute("url")
    fun getUrl() : GenericAttributeValue<String>

    @Attribute("username")
    fun getUsername() : GenericAttributeValue<String>

    @Attribute("password")
    fun getPassword() : GenericAttributeValue<String>
}