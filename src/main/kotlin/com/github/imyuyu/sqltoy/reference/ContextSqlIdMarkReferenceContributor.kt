package com.github.imyuyu.sqltoy.reference

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
                val extendsClassFields = SearchUtil.getExtendsClassFields(psiClass!!)
                for (field in extendsClassFields) {
                    if (JavaUtils.isSqlToyBean(field)) {
                        fieldStrings.add(field.name)
                    }
                }

                val a: Boolean = XmlUtil.isInjectXml(literalExpression, fieldStrings)
                val b: Boolean = XmlUtil.isNewQueryExecutor(literalExpression, fieldStrings)

                if (a || b) {
                    val project = element.getProject()
                    var searchScope = SearchUtil.getSearchScope(project, element)
                    val virtualFiles = FilenameIndex.getAllFilesByExt(
                        project,
                        XmlUtil.EXT,
                        searchScope
                    )
                    val elements: List<PsiElement> = XmlUtil.findXmlPsiElement(project, virtualFiles, value)
                    return elements.stream().map { psiElement: PsiElement? ->
                        PsiJavaInjectReference(
                            element,
                            psiElement!!
                        )
                    }.collect(Collectors.toList()).toTypedArray()
                }
            }
            return PsiReference.EMPTY_ARRAY
        }
    }


}
