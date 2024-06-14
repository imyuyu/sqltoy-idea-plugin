package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.DomElement
import com.intellij.util.xml.Stubbed
import com.intellij.util.xml.SubTagList
import com.intellij.util.xml.SubTagsList

/**
 * The interface cache-translates element.
 *
 * @author imyuyu
 */
@Stubbed
interface CacheTranslate : DomElement {

    @Stubbed
    @SubTagList("sql-translate")
    fun getSqlTranslate() : List<SqlTranslate>

    @Stubbed
    @SubTagList("service-translate")
    fun getRestTranslate() : List<ServiceTranslate>

    @Stubbed
    @SubTagList("rest-translate")
    fun getServiceTranslate() : List<RestTranslate>

    @SubTagsList("sql-translate", "service-translate", "rest-translate")
    fun getAllTranslate() : List<DomTranslateBean>
}