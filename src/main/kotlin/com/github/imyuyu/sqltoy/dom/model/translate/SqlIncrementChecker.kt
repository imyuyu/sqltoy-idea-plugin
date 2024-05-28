package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.*

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlIncrementChecker : Checker {

    @Required
    @SubTag("sql")
    fun getSql() : ValueElement

    @Attribute("datasource")
    fun getDatasource() : ValueElement

    @Attribute("has-inside-group")
    fun getHasInsideGroup() : GenericAttributeValue<Boolean>
}