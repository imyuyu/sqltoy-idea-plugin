package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.*

/**
 * The interface rest-translate element.
 *
 * @author imyuyu
 */
interface RestTranslate : Translate {

    @Required
    @Attribute("url")
    fun getUrl() : GenericAttributeValue<String>
}