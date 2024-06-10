package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.spring.model.converters.SpringBeanIdConverter
import com.intellij.sql.dialects.SqlLanguageDialect
import com.intellij.util.xml.*
import org.intellij.lang.annotations.Language

/**
 * The interface sql-translate element.
 *
 * @author imyuyu
 */
interface SqlTranslate : Translate {

    @Language("spring-bean-name")
    @Required
    @Attribute("datasource")
    fun getDatasource() : GenericAttributeValue<String>

    @Required
    @SubTag("sql")
    @Language(SqlLanguageDialect.SQL_ID)
    fun getSql() : ValueElement
}