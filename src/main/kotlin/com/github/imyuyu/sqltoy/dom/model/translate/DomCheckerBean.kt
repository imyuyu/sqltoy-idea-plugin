package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.converters.SQLToyTranslateConverter
import com.intellij.util.xml.*

/**
 * Checker interface
 */
@Stubbed
interface DomCheckerBean : DomElement {

    @Stubbed
    @Referencing(SQLToyTranslateConverter::class)
    @Required
    @Attribute("cache")
    fun getCache() : GenericAttributeValue<String>

    @Stubbed
    @Attribute("check-frequency")
    fun getCheckFrequency() : GenericAttributeValue<Int>
}
