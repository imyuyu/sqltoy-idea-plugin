package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.*
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
@Stubbed
interface SqlIncrementChecker : DomCheckerBean {

    @Required
    @SubTag("sql")
    @Language("SQL")
    fun getSql() : ValueElement

    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Attribute("has-inside-group")
    fun getHasInsideGroup() : GenericAttributeValue<Boolean>
}