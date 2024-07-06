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
    @Stubbed
    fun getColumns(): GenericAttributeValue<String>

    @Attribute("type")
    @Stubbed
    fun getType(): GenericAttributeValue<String>

    @Attribute("head-size")
    @Stubbed
    fun getHeadSize(): GenericAttributeValue<Int>

    @Attribute("tail-size")
    @Stubbed
    fun getTailSize(): GenericAttributeValue<Int>

    @Attribute("mask_rate")
    @Stubbed
    fun getMaskRate(): GenericAttributeValue<String>

    @Attribute("mask_code")
    @Stubbed
    fun getMaskCode(): GenericAttributeValue<String>
}