package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Namespace
import com.intellij.util.xml.SubTag
import com.intellij.util.xml.SubTagList
import org.jetbrains.annotations.NotNull

/**
 * The interface sagacity translate xml root element.
 *
 * @author imyuyu
 */
@Namespace("SQLToyTranslateXml")
interface SQLToyTranslate : DomElement {

    /**
     * Gets translates elements.
     *
     * @return the sql elements
     */
    @SubTag("cache-translates")
    fun getTranslates(): CacheTranslate


    /**
     * Gets cache-update-checkers elements.
     */
    @SubTag("cache-update-checkers")
    fun getUpdateChecker(): CacheUpdateChecker

}