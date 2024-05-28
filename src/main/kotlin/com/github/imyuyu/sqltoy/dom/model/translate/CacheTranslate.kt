package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.DomElement
import com.intellij.util.xml.SubTagList
import com.intellij.util.xml.SubTagsList

/**
 * The interface cache-translates element.
 *
 * @author imyuyu
 */
interface CacheTranslate : DomElement {

    @SubTagList("sql-translate")
    fun getSqlTranslate() : List<SqlTranslate>

    @SubTagList("service-translate")
    fun getRestTranslate() : List<RestTranslate>

    @SubTagList("rest-translate")
    fun getServiceTranslate() : List<ServiceTranslate>

    @SubTagsList("sql-translate", "service-translate", "rest-translate")
    fun getAllTranslate() : List<Translate>
}