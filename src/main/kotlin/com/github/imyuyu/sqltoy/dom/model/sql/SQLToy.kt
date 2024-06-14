package com.github.imyuyu.sqltoy.dom.model.sql

import com.github.imyuyu.sqltoy.constants.SQLToyConstants
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Namespace
import com.intellij.util.xml.Stubbed
import com.intellij.util.xml.SubTagList
import org.jetbrains.annotations.NotNull

/**
 * The interface sqltoy xml root element.
 *
 * @author imyuyu
 */
@Stubbed
@Namespace(SQLToyConstants.SQL_NAMESPACE_KEY)
interface SQLToy : DomElement {

    /**
     * Gets sql elements.
     *
     * @return the sql elements
     */
    @NotNull
    @SubTagList("sql")
    @Stubbed
    fun getSqlList(): List<Sql>

}