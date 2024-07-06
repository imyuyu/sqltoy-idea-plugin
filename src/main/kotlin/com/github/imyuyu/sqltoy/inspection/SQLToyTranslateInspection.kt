package com.github.imyuyu.sqltoy.inspection

import com.github.imyuyu.sqltoy.dom.model.translate.SQLToyTranslate
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.highlighting.BasicDomElementsInspection
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder
import com.intellij.util.xml.highlighting.DomHighlightingHelper

/**
 * XML 检查
 *
 */
class SQLToyTranslateInspection : BasicDomElementsInspection<SQLToyTranslate?>(
    SQLToyTranslate::class.java
) {
    override fun checkDomElement(
        element: DomElement,
        holder: DomElementAnnotationHolder,
        helper: DomHighlightingHelper
    ) {
        super.checkDomElement(element, holder, helper)
    }

    override fun getStaticDescription(): String {
        return "Static SQLToy Translate Inspection"
    }
}