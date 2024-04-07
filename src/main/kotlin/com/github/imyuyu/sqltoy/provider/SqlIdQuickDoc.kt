package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationMarkup.DEFINITION_ELEMENT
import com.intellij.openapi.util.text.HtmlBuilder
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttributeValue


class SqlIdQuickDoc : AbstractDocumentationProvider() {

    override fun generateHoverDoc(element: PsiElement, originalElement: PsiElement?): String? {
        return super.generateHoverDoc(element, originalElement)
    }

    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
        return super.getQuickNavigateInfo(element, originalElement)
    }

    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        if (element !is XmlAttributeValue) {
            return null
        }
        if(!XmlUtil.isSqltoyXml(element)){
            return null
        }

        val id = element.text.replace("\"", "");

        // todo 下一步使用HtmlBuilder

        return "<div class='definition'>" +
                "<pre>XML File : <a href=''>" + element.containingFile.name  + "</a></pre>" +
                "</div>" +
                "<div class='content'>SQLToy SqlId : " + id + "</div>"

        //return "SQLToy SqlId : "+element.text
    }
}
