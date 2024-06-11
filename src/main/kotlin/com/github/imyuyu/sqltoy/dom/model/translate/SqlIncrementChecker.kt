package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.Required
import com.intellij.util.xml.SubTag
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlIncrementChecker : Checker {

    @Required
    @SubTag("sql")
    @Language("SQL")
    fun getSql() : ValueElement

    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Attribute("has-inside-group")
    fun getHasInsideGroup() : GenericAttributeValue<Boolean>
}