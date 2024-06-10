package com.github.imyuyu.sqltoy.dom.model

import com.github.imyuyu.sqltoy.dom.model.converters.SQLToyTranslateConverter
import com.intellij.util.xml.*

/**
 * The interface translate element.
 */
interface TranslateConfig:DomElement{

    @Referencing(SQLToyTranslateConverter::class)
    @Required
    @Attribute("cache")
    fun getCache() : GenericAttributeValue<String>

    @Required
    @Attribute("cache")
    fun getColumns():GenericAttributeValue<String>

    @Attribute("cache-type")
    fun getCacheType() : GenericAttributeValue<String>

    @Attribute("split-regex")
    fun getSplitRegex() : GenericAttributeValue<String>

    @Attribute("link-sign")
    fun getLinkSign() : GenericAttributeValue<String>

    @Attribute("cache-indexs")
    fun getCacheIndexs() : GenericAttributeValue<String>

    @Attribute("uncached-template")
    fun getUncachedTemplate() : GenericAttributeValue<String>

    @Attribute("original-columns")
    fun getOriginalColumns() : GenericAttributeValue<String>
}