package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.*

/**
 * The interface rest-checker element.
 *
 * @author imyuyu
 */
interface RestChecker : Checker {

    @Required
    @SubTag("url")
    fun getUrl() : ValueElement

    @Attribute("username")
    fun getUsername() : GenericAttributeValue<String>

    @Attribute("password")
    fun getPassword() : GenericAttributeValue<String>
}