package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Required
import com.intellij.util.xml.SubTag

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlTranslate : Translate {

    @Required
    @Attribute("datasource")
    fun getDatasource() : ValueElement

    @Required
    @SubTag("sql")
    fun getSql() : ValueElement
}