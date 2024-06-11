package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.codeInsight.daemon.EmptyResolveMessageProvider
import com.intellij.codeInsight.highlighting.HighlightedReference
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope

class SQLToyTranslateReference(element: PsiElement) :
    PsiReferenceBase<PsiElement>(
        element, ElementManipulators.getValueTextRange(element),true
    ), EmptyResolveMessageProvider, HighlightedReference {

    override fun resolve(): PsiElement? {
        val cacheName = value;

        val records = SQLIdIndexHolder.findRecordsByQualifiedId(
            SQLToyBeanIndexType.TRANSLATE_ID,
            cacheName,
            element.project,
            GlobalSearchScope.projectScope(element.project)
        )

        for (record in records) {
            val elements = record.getElements(this.element.project)
            for (element in elements) {
                return element;
            }
        }

        return null;
    }

    override fun bindToElement(element: PsiElement): PsiElement {
        return getElement()
    }

    override fun getVariants(): Array<Any?> {
        val allIds = SQLIdIndexHolder.getAllTranslateIds(element.project, GlobalSearchScope.projectScope(element.project))
        return allIds.map {
            LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
        }.toTypedArray();
    }

    override fun getUnresolvedMessagePattern(): String {
        return "Cannot resolve translate '${value}'"
    }

    override fun isHighlightedWhenSoft(): Boolean {
        return true
    }
}