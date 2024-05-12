package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.dom.model.Sql
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.codeInsight.documentation.DocumentationManagerProtocol
import com.intellij.codeInsight.documentation.DocumentationManagerUtil
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationMarkup.DEFINITION_ELEMENT
import com.intellij.openapi.util.text.HtmlBuilder
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtil
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.util.xml.DomManager


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

        //var stringBuilder = StringBuilder()
        //DocumentationManagerUtil.createHyperlink(stringBuilder, element, element.toString(), "$id",true);

        val sql = DomManager.getDomManager(element.project)
            .getDomElement(PsiTreeUtil.getParentOfType(element, XmlTag::class.java)) as Sql

        return HtmlBuilder().append(
            HtmlChunk.div().setClass(DocumentationMarkup.CLASS_DEFINITION).children(HtmlChunk.tag("pre")
            .addText("SQL ID ")
            .child(HtmlChunk.link(DocumentationManagerProtocol.PSI_ELEMENT_PROTOCOL+PsiUtil.getName(element), id))
            .addText(" is defined in ")
            .child(HtmlChunk.link("psi_element://"+ (PsiUtil.getVirtualFile(element)?.path ?: ""), element.containingFile.name)))
        )
            .append(HtmlChunk.div().setClass(DocumentationMarkup.CLASS_CONTENT).addText(sql.getSqlValue().getValue()!!)).toString();

        /*return "<div class='definition'>" +
                "<pre>XML File : <a href=''>" + element.containingFile.name  + "</a></pre>" +
                "</div>" +
                "<div class='content'>SQLToy SqlId : " + id + "</div>"*/

        //return "SQLToy SqlId : "+element.text
    }
}
