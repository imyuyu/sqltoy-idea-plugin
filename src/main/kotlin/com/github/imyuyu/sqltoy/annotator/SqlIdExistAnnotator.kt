package com.github.imyuyu.sqltoy.annotator

import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType
import com.intellij.psi.util.parentOfTypes
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlTagValue
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset

class SqlIdExistAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if(!XmlUtil.isSqltoyXml(element)){
            return;
        }

        if(element !is XmlAttributeValue){
            return;
        }

        val attribute = element.parentOfTypes(XmlAttribute::class)

        if(attribute?.name != "id"){
            return;
        }

        val parent = attribute.parentOfTypes(XmlTag::class);
        if(parent != null && parent.name == "sql") {
            val text = element.text
            val match = text.startsWith("\"") && text.endsWith("\"")
            val len = 2
            var range = if (match && text.length > len) {
                TextRange(element.startOffset + 1, element.endOffset - 1)
            } else TextRange(element.startOffset, element.endOffset)

            holder.newAnnotation(HighlightSeverity.ERROR, String.format("重复命名的SQL ID '%s' ", text))
                .range(range)
                .highlightType(ProblemHighlightType.ERROR)
                .create()
        }
    }

}
