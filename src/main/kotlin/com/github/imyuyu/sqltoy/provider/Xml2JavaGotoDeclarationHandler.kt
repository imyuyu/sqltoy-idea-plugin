package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.util.JavaUtils
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parentOfType
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlToken

class Xml2JavaGotoDeclarationHandler : GotoDeclarationHandler {
    override fun getGotoDeclarationTargets(
        sourceElement: PsiElement?,
        offset: Int,
        editor: Editor
    ): Array<PsiElement> {

        if (!sourceElement?.containingFile!!.name.endsWith("sql.xml") ||
            sourceElement !is XmlToken ||
            sourceElement.elementType.toString() != "XML_ATTRIBUTE_VALUE_TOKEN"){
            return emptyArray();
        }

        val xmlAttribute:PsiElement? = sourceElement.parent?.parent;
        if(xmlAttribute == null || xmlAttribute !is XmlAttribute || xmlAttribute.name != "id"){
            return emptyArray();
        }

        val xmlTag: PsiElement? = xmlAttribute.parent;
        if(xmlTag == null || xmlTag !is XmlTag || xmlTag.name != "sql"){
            return emptyArray();
        }

        return JavaUtils.findStatement(sourceElement.project, xmlTag);
    }
}
