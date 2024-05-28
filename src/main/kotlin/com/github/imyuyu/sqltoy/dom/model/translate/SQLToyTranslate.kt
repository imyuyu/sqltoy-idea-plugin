package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Namespace
import com.intellij.util.xml.SubTagList
import org.jetbrains.annotations.NotNull

/**
 * The interface sagacity translate xml root element.
 *
 * @author imyuyu
 */
@Namespace("SQLToyTranslateXML")
interface SQLToyTranslate : DomElement {

    /**
     * Gets translates elements.
     *
     * @return the sql elements
     */
    @NotNull
    @SubTagList("cache-translates")
    fun getTranslates(): CacheTranslate


    /**
     * Gets cache-update-checkers elements.
     */
    @SubTagList("cache-update-checkers")
    fun getUpdateChecker(): CacheUpdateChecker

}