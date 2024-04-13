package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.NotNull
import java.util.*
import java.util.stream.Collectors


class ContextSqlIdMarkReferenceContributor: PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            JavaInjectPsiReferenceProvider()
        )
    }


    internal class JavaInjectPsiReferenceProvider : PsiReferenceProvider() {
        @NotNull
        public override fun getReferencesByElement(
            element: PsiElement,
            processingContext: ProcessingContext
        ): Array<out PsiReference> {
            val literalExpression = element as PsiLiteralExpression
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
                    val sqlIdRecords = SQLIdIndexHolder.findRecordsByQualifiedId(value, project, searchScope)

                    val result = mutableListOf<PsiReference>()

                    for (sqlIdRecord in sqlIdRecords) {
                        result.addAll( sqlIdRecord.getElements(project).map { psiElement ->
                            PsiJavaInjectReference(
                                element,
                                psiElement
                            )
                        }.toTypedArray());
                    }
                    return result.toTypedArray();
                }
            }
            return PsiReference.EMPTY_ARRAY
        }
    }


}
