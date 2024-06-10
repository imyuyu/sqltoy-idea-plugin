package com.github.imyuyu.sqltoy.references.injector

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.github.imyuyu.sqltoy.reference.PsiJavaInjectReference
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.codeInsight.ExpectedTypesProvider
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteral
import com.intellij.psi.PsiReference
import com.intellij.psi.injection.ReferenceInjector
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiTypesUtil
import com.intellij.sql.isNullOr
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.Nls

class SQLToySqlIdReferenceInjector : ReferenceInjector() {

    override fun getReferences(element: PsiElement, context: ProcessingContext, range: TextRange): Array<PsiReference> {
        if(element !is PsiLiteral){
            return emptyArray();
        }

        if(element.value.isNullOr("")){
            return emptyArray();
        }

        SQLIdIndexHolder.findRecordsByQualifiedId(
            SQLToyBeanIndexType.SQL_ID,element.value.toString(), element.project, SearchUtil.getSearchScope(element.project, element)
        ).forEach { record ->
            return record.getElements(element.project).map { PsiJavaInjectReference(element, it) }.toTypedArray()
        }

        return arrayOf(PsiJavaInjectReference(element, null))
    }

    override fun getId(): String {
        return "sqltoy-sql-id"
    }

    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "SQLToy SQL ID"
    }
}
