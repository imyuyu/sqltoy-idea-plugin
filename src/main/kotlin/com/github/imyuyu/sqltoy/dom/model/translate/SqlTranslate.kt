package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.spring.model.converters.SpringBeanResolveConverter
import com.intellij.util.xml.*
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
@Stubbed
interface SqlTranslate : DomTranslateBean {
    @Stubbed

    @Required
    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Stubbed
    @Required
    @SubTag("sql")
    fun getSql() : ValueElement
}