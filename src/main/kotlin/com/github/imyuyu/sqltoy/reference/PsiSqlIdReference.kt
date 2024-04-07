package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndex
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.parentOfType
import com.intellij.psi.xml.XmlTag

class PsiSqlIdReference(private var formElement: PsiElement, private var textRange: TextRange) : PsiPolyVariantReferenceBase<PsiElement>(
    formElement, textRange
) {

    override fun getVariants(): Array<Any?> {
        val allIds = SQLIdIndex.getAllIds(formElement.project, GlobalSearchScope.projectScope(formElement.project))
        return allIds.map {
            LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
        }.toTypedArray();
    }

    override fun multiResolve(p0: Boolean): Array<ResolveResult> {
        var xmlTag = formElement.parentOfType(XmlTag::class)
        if (xmlTag == null) {
            return emptyArray();
        }
        return JavaUtils.findStatement(formElement.project, xmlTag).map { psiElement ->
            PsiElementResolveResult(psiElement.children.get(0))
        }.toTypedArray();
    }
}