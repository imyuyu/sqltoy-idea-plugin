package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Required
import com.intellij.util.xml.SubTag

/**
 * The interface rest-translate element.
 *
 * @author imyuyu
 */
interface RestTranslate : Translate {

    @Required
    @SubTag("url")
    fun getUrl() : ValueElement
}