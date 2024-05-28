package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.SubTagList

/**
 * The interface cache-update-checkers element.
 *
 * @author imyuyu
 */
interface CacheUpdateChecker : DomElement {

    @Attribute("cluster-time-deviation")
    fun getClusterTimeDeviation() : GenericAttributeValue<Int>

    @SubTagList("sql-increment-checker")
    fun getSqlIncrementChecker() : List<SqlIncrementChecker>

    @SubTagList("service-checker")
    fun getServiceChecker() : List<ServiceChecker>

    @SubTagList("rest-checker")
    fun getRestChecker() : List<RestChecker>
}