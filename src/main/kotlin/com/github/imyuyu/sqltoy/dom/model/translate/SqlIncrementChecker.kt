package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.sql.dialects.SqlLanguageDialect
import com.intellij.util.xml.*
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlIncrementChecker : Checker {

    @Required
    @SubTag("sql")
    @Language(SqlLanguageDialect.SQL_ID)
    fun getSql() : ValueElement

    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Attribute("has-inside-group")
    fun getHasInsideGroup() : GenericAttributeValue<Boolean>
}