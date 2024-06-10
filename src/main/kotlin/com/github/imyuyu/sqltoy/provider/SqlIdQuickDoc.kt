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
        val project = element?.project
        val file = element?.containingFile

        if (element !is XmlAttributeValue && element !is XmlTag) {
            return null
        }

        if(!XmlUtil.isSqltoyXml(element)){
            return null
        }

        val domManager = DomManager.getDomManager(project)
        var sql:Sql? = null;

        if(element is XmlAttributeValue){
            sql = domManager
                .getDomElement(PsiTreeUtil.getParentOfType(element, XmlTag::class.java)) as Sql
        }else{
            sql = domManager.getDomElement(element as XmlTag) as Sql;
        }

        val id = sql.getId().value!!;
        //var stringBuilder = StringBuilder()
        //DocumentationManagerUtil.createHyperlink(stringBuilder, element, element.toString(), "$id",true);



        return HtmlBuilder().append(
            HtmlChunk.div().setClass(DocumentationMarkup.CLASS_DEFINITION).children(HtmlChunk.tag("pre")
            .addText("SQL ID ")
            .child(HtmlChunk.link(DocumentationManagerProtocol.PSI_ELEMENT_PROTOCOL+PsiUtil.getName(element), id))
            .addText(" is defined in ")
            .child(HtmlChunk.link("psi_element://"+ (PsiUtil.getVirtualFile(element)?.path ?: ""), file!!.name)))
        )
            .append(HtmlChunk.div().setClass(DocumentationMarkup.CLASS_CONTENT).addRaw(sql.getSqlValue().getValue()!!
                .replace("            ", "")
                .replace(" ", HtmlChunk.nbsp().toString())
                .replace("\n", HtmlChunk.br().toString()))).toString();

        /*return "<div class='definition'>" +
                "<pre>XML File : <a href=''>" + element.containingFile.name  + "</a></pre>" +
                "</div>" +
                "<div class='content'>SQLToy SqlId : " + id + "</div>"*/

        //return "SQLToy SqlId : "+element.text
    }
}
