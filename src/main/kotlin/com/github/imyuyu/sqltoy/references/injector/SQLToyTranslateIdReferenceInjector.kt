package com.github.imyuyu.sqltoy.references.injector

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.github.imyuyu.sqltoy.reference.PsiJavaInjectReference
import com.github.imyuyu.sqltoy.reference.SQLToyTranslateReference
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.codeInsight.ExpectedTypesProvider
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.injection.ReferenceInjector
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiTypesUtil
import com.intellij.sql.isNullOr
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.Nls

class SQLToyTranslateIdReferenceInjector : ReferenceInjector() {

    override fun getReferences(element: PsiElement, context: ProcessingContext, range: TextRange): Array<PsiReference> {
        if(element !is PsiLiteral){
            return emptyArray();
        }

        if(element.value.isNullOr("")){
            return emptyArray();
        }

        return arrayOf(SQLToyTranslateReference(element))
    }

    override fun getId(): String {
        return "sqltoy-translate-id"
    }

    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "SQLToy TRANSLATE ID"
    }
}
