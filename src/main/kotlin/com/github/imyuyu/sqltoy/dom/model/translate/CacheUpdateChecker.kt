package com.github.imyuyu.sqltoy.dom.model.translate

import com.intellij.util.xml.*

/**
 * The interface cache-update-checkers element.
 *
 * @author imyuyu
 */
@Stubbed
interface CacheUpdateChecker : DomElement {

    @Stubbed
    @Attribute("cluster-time-deviation")
    fun getClusterTimeDeviation() : GenericAttributeValue<Int>

    @Stubbed
    @SubTagList("sql-increment-checker")
    fun getSqlIncrementChecker() : List<SqlIncrementChecker>

    @Stubbed
    @SubTagList("service-checker")
    fun getServiceChecker() : List<ServiceChecker>

    @Stubbed
    @SubTagList("rest-checker")
    fun getRestChecker() : List<RestChecker>
}