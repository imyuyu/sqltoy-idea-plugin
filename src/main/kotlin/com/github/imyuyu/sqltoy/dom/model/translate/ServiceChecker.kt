package com.github.imyuyu.sqltoy.dom.model.translate

import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.util.xml.*
import java.lang.reflect.Method

/**
 * The interface service-checker element.
 *
 * @author imyuyu
 */
interface ServiceChecker : Checker {

    @Required
    @Attribute("service")
    fun getService() : ValueElement

    @Required
    @Attribute("method")
    fun getMethod() : Method
}