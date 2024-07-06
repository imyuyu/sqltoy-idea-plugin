package com.github.imyuyu.sqltoy.dom.model.sql

import com.github.imyuyu.sqltoy.dom.model.converters.SQLToyTranslateConverter
import com.intellij.util.xml.*

/**
 * The interface translate element.
 */
@Stubbed
interface TranslateConfig:DomElement{

    @Stubbed
    @Referencing(SQLToyTranslateConverter::class)
    @Required
    @Attribute("cache")
    fun getCache() : GenericAttributeValue<String>

    @Stubbed
    @Required
    @Attribute("columns")
    fun getColumns():GenericAttributeValue<String>

    @Stubbed
    @Attribute("cache-type")
    fun getCacheType() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("split-regex")
    fun getSplitRegex() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("link-sign")
    fun getLinkSign() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("cache-indexs")
    fun getCacheIndexs() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("uncached-template")
    fun getUncachedTemplate() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("original-columns")
    fun getOriginalColumns() : GenericAttributeValue<String>
}