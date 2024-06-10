package com.github.imyuyu.sqltoy.dom.model

import com.github.imyuyu.sqltoy.dom.model.converters.SQLToySqlIdConverter
import com.intellij.util.xml.*
import org.jetbrains.annotations.NotNull

/**
 * The interface sql element.
 *
 * @author imyuyu
 */
interface Sql : DomElement {

    /**
     * Gets id.
     *
     * @return the id
     */
    @Referencing(value = SQLToySqlIdConverter::class, soft = true)
    @Required(identifier = true)
    @NameValue
    @Attribute("id")
    fun getId(): GenericAttributeValue<String>

    @Attribute("type")
    fun getType(): GenericAttributeValue<String>

    @Attribute("blank-to-null")
    fun getBlankToNull(): GenericAttributeValue<Boolean>

    @Attribute("debug")
    fun getDebug(): GenericAttributeValue<Boolean>

    @Required
    @NotNull
    @SubTag("value")
    fun getSqlValue(): ValueElement

    @SubTag("count-sql")
    fun getCountSql(): ValueElement

    @SubTagList("secure-mask")
    fun getSecureMask() : List<SecureMask>

    @SubTag("page-optimize")
    fun getPageOptimize() : PageOptimize

    @SubTagList("translate")
    fun getTranslate(): List<TranslateConfig>
}