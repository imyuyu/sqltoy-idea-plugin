package com.github.imyuyu.sqltoy.dom.model

import com.intellij.util.xml.*

/**
 * The interface page-optimize element.
 *
 * @author imyuyu
 */
interface PageOptimize: DomElement {

    @Attribute("alive-max")
    fun getAliveMax(): GenericAttributeValue<Int>

    @Attribute("alive-seconds")
    fun getAliveSeconds(): GenericAttributeValue<Int>

}