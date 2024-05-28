package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.Required

/**
 * Checker interface
 */
interface Checker : DomElement {

    @Required
    @Attribute("cache")
    fun getCache() : ValueElement

    @Attribute("check-frequency")
    fun getCheckFrequency() : GenericAttributeValue<Int>
}
