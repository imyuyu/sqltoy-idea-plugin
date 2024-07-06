package com.github.imyuyu.sqltoy.dom.model.sql

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.github.imyuyu.sqltoy.dom.model.converters.SQLToySqlIdConverter
import com.intellij.util.xml.*
import org.intellij.lang.annotations.Language
import org.jetbrains.annotations.NotNull

/**
 * The interface sql element.
 *
 * @author imyuyu
 */
@Stubbed
interface Sql : DomElement {

    /**
     * Gets id.
     *
     * @return the id
     */
    @NotNull
    //@Referencing(value = SQLToySqlIdConverter::class, soft = true)
    @Required
    @NameValue
    @Attribute("id")
    @Stubbed
    fun getId(): GenericAttributeValue<String>

    @Attribute("type")
    @Stubbed
    fun getType(): GenericAttributeValue<String>

    @Attribute("blank-to-null")
    @Stubbed
    fun getBlankToNull(): GenericAttributeValue<Boolean>

    @Attribute("debug")
    @Stubbed
    fun getDebug(): GenericAttributeValue<Boolean>

    @Required
    @NotNull
    @SubTag("value")
    @Stubbed
    fun getSqlValue(): ValueElement

    @Stubbed
    @SubTag("count-sql")
    fun getCountSql(): ValueElement

    @Stubbed
    @SubTagList("secure-mask")
    fun getSecureMask() : List<SecureMask>

    @Stubbed
    @SubTag("page-optimize")
    fun getPageOptimize() : PageOptimize

    @Stubbed
    @SubTagList("translate")
    fun getTranslate(): List<TranslateConfig>
}