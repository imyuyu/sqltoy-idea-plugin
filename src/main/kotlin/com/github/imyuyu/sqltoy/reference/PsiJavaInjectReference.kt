package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.codeInsight.daemon.EmptyResolveMessageProvider
import com.intellij.codeInsight.highlighting.HighlightedReference
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope

class PsiJavaInjectReference(private var formElement: PsiElement, private var targetElement: PsiElement?) :
    PsiReferenceBase<PsiElement>(
        formElement, ElementManipulators.getValueTextRange(formElement)
    ), EmptyResolveMessageProvider, HighlightedReference {

    override fun resolve(): PsiElement? {
        return targetElement;
    }

    override fun getVariants(): Array<Any?> {
        val allIds = SQLIdIndexHolder.getAllSqlIds(formElement.project, GlobalSearchScope.projectScope(formElement.project))
        return allIds.map {
            LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
        }.toTypedArray();
    }

    override fun getUnresolvedMessagePattern(): String {
        return "Cannot resolve sql id '${value}'"
    }

}