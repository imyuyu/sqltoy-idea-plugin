package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface rest-translate element.
 *
 * @author imyuyu
 */
interface RestTranslate : DomTranslateBean {

    @Required
    @Attribute("url")
    fun getUrl() : GenericAttributeValue<String>
}