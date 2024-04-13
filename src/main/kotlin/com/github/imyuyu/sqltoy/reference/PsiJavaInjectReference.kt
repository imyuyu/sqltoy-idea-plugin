package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope

class PsiJavaInjectReference(private var formElement: PsiElement, private var targetElement: PsiElement) : PsiPolyVariantReferenceBase<PsiElement>(
    formElement
) {

    override fun getRangeInElement(): TextRange {
        val text = formElement.text
        val match = text.startsWith("\"") && text.endsWith("\"")
        val len = 2
        return if (match && text.length > len) {
            TextRange(1, formElement.textLength - 1)
        } else TextRange(0, formElement.textLength)
    }

    override fun getVariants(): Array<Any?> {
        val allIds = SQLIdIndexHolder.getAllIds(formElement.project, GlobalSearchScope.projectScope(formElement.project))
        return allIds.map {
            LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
        }.toTypedArray();
    }

    override fun multiResolve(p0: Boolean): Array<ResolveResult> {
        return arrayOf(PsiElementResolveResult(targetElement));
    }
}