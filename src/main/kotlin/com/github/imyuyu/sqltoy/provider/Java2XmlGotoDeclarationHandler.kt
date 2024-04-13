package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.reference.PsiJavaInjectReference
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiReference
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parentOfType
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlToken
import java.util.*
import java.util.stream.Collectors

class Java2XmlGotoDeclarationHandler : GotoDeclarationHandler {
    override fun getGotoDeclarationTargets(
        element: PsiElement?,
        offset: Int,
        editor: Editor
    ): Array<PsiElement> {
        if(element !is PsiJavaToken || element.parent !is PsiLiteralExpression){
            return PsiElement.EMPTY_ARRAY;
        }

        val literalExpression = element.parent as PsiLiteralExpression
        val literalExpressionValue = literalExpression.value
        val value = if (literalExpressionValue is String) literalExpressionValue else null
        if (Objects.nonNull(value) && !value!!.contains(" ")) {
            val psiClass = PsiTreeUtil.getParentOfType(
                element,
                PsiClass::class.java
            )
            val fieldStrings: MutableList<String> = ArrayList()

            val fields = psiClass!!.fields
            for (field in fields) {
                if (JavaUtils.isSqlToyBean(field)) {
                    fieldStrings.add(field.name)
                }
            }
            val extendsClassFields = SearchUtil.getExtendsClassFields(psiClass)
            for (field in extendsClassFields) {
                if (JavaUtils.isSqlToyBean(field)) {
                    fieldStrings.add(field.name)
                }
            }

            val a: Boolean = JavaUtils.isInjectXml(literalExpression, fieldStrings)
            val b: Boolean = JavaUtils.isNewQueryExecutor(literalExpression, fieldStrings)

            if (a || b) {
                val project = element.getProject()
                val searchScope = SearchUtil.getSearchScope(project, element)
                val virtualFiles = FilenameIndex.getAllFilesByExt(
                    project,
                    XmlUtil.EXT,
                    searchScope
                )
                val elements: List<PsiElement> = XmlUtil.findXmlPsiElement(project, virtualFiles, value)
                return elements.toTypedArray();
            }
        }
        return PsiElement.EMPTY_ARRAY
    }
}
