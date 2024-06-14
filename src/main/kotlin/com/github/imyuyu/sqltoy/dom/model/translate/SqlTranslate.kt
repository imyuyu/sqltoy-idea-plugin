package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.spring.model.converters.SpringBeanResolveConverter
import com.intellij.util.xml.Attribute
import com.intellij.util.xml.Convert
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.Required
import com.intellij.util.xml.SubTag
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlTranslate : DomTranslateBean {

    @Required
    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Required
    @SubTag("sql")
    @Language("SQL")
    fun getSql() : ValueElement
}