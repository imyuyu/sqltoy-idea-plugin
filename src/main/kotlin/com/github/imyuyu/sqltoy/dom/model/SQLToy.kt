package com.github.imyuyu.sqltoy.dom.model

import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Namespace
import com.intellij.util.xml.SubTagList
import org.jetbrains.annotations.NotNull

/**
 * The interface sqltoy xml root element.
 *
 * @author imyuyu
 */
@Namespace("SQLToyXml")
interface SQLToy : DomElement {

    /**
     * Gets sql elements.
     *
     * @return the sql elements
     */
    @NotNull
    @SubTagList("sql")
    fun getSqlList(): List<Sql>

}