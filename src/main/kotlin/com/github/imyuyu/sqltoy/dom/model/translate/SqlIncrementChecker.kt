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

    @Stubbed
    @Required
    @SubTag("sql")
    fun getSql() : ValueElement

    @Stubbed
    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("has-inside-group")
    fun getHasInsideGroup() : GenericAttributeValue<Boolean>
}