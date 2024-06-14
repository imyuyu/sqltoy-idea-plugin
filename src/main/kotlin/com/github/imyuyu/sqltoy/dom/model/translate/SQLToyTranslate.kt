package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.constants.SQLToyConstants
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Namespace
import com.intellij.util.xml.Stubbed
import com.intellij.util.xml.SubTag
import com.intellij.util.xml.SubTagList
import org.jetbrains.annotations.NotNull

/**
 * The interface sagacity translate xml root element.
 *
 * @author imyuyu
 */
@Stubbed
@Namespace(SQLToyConstants.TRANSLATE_NAMESPACE_KEY)
interface SQLToyTranslate : DomElement {

    /**
     * Gets translates elements.
     *
     * @return the sql elements
     */
    @Stubbed
    @SubTag("cache-translates")
    fun getTranslates(): CacheTranslate


    /**
     * Gets cache-update-checkers elements.
     */
    @Stubbed
    @SubTag("cache-update-checkers")
    fun getUpdateChecker(): CacheUpdateChecker

}