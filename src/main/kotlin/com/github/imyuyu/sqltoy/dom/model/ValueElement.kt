package com.github.imyuyu.sqltoy.dom.model

import com.intellij.util.xml.*

/**
 * The interface value element.
 *
 * @author imyuyu
 */
interface ValueElement : DomElement {

    /**
     * Gets sql.
     */
    fun getValue(): String?
    /**
     * Sets value.
     *
     * @param content the content
     */
    fun setValue(content: String?)

}