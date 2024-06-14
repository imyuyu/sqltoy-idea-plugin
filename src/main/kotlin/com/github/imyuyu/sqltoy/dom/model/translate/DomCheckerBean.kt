package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.converters.SQLToyTranslateConverter
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.Referencing
import com.intellij.util.xml.Required

/**
 * Checker interface
 */
interface DomCheckerBean : DomElement {

    @Referencing(SQLToyTranslateConverter::class)
    @Required
    @Attribute("cache")
    fun getCache() : GenericAttributeValue<String>

    @Attribute("check-frequency")
    fun getCheckFrequency() : GenericAttributeValue<Int>
}
