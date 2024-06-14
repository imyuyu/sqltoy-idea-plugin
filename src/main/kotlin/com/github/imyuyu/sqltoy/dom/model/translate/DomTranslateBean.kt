package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.NameValue
import com.intellij.util.xml.Required

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface DomTranslateBean : DomElement {

    @Required
    @NameValue
    @Attribute("cache")
    fun getCache() : GenericAttributeValue<String>

}