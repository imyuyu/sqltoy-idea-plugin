package com.github.imyuyu.sqltoy.dom.model.sql

import com.intellij.util.xml.Attribute
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.GenericAttributeValue
import com.intellij.util.xml.Stubbed

/**
 * The interface secure-mask element.
 *
 * @author imyuyu
 */
@Stubbed
interface SecureMask: DomElement {

    @Attribute("columns")
    fun getColumns(): GenericAttributeValue<String>

    @Attribute("type")
    fun getType(): GenericAttributeValue<String>

    @Attribute("head-size")
    fun getHeadSize(): GenericAttributeValue<Int>

    @Attribute("tail-size")
    fun getTailSize(): GenericAttributeValue<Int>

    @Attribute("mask_rate")
    fun getMaskRate(): GenericAttributeValue<String>

    @Attribute("mask_code")
    fun getMaskCode(): GenericAttributeValue<String>
}