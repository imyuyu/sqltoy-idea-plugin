package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.parentOfTypes
import com.intellij.psi.xml.XmlTag

@Deprecated("unused")
class PsiSqlIdReference(private var formElement: PsiElement, private var textRange: TextRange) : PsiPolyVariantReferenceBase<PsiElement>(
    formElement, textRange
) {

    override fun getVariants(): Array<Any?> {
        val allIds = SQLIdIndexHolder.getAllSqlIds(formElement.project, GlobalSearchScope.projectScope(formElement.project))
        return allIds.map {
            LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
        }.toTypedArray();
    }

    override fun multiResolve(p0: Boolean): Array<ResolveResult> {
        val xmlTag: XmlTag = formElement.parentOfTypes(XmlTag::class) ?: return emptyArray()

        return JavaUtils.findStatement(formElement.project, xmlTag).map { psiElement ->
            PsiElementResolveResult(psiElement.children[0])
        }.toTypedArray();
    }
}