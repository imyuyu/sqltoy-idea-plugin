package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.util.Patterns
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.NotNull


class TranslateIdMarkReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            JavaInjectPsiReferenceProvider()
        )
    }


    internal class JavaInjectPsiReferenceProvider : PsiReferenceProvider() {

        override fun acceptsTarget(target: PsiElement): Boolean {
            if (target !is PsiLiteralExpression) {
                return false;
            }

            return true;
        }

        @NotNull
        override fun getReferencesByElement(
            element: PsiElement,
            processingContext: ProcessingContext
        ): Array<out PsiReference> {

            var accepts = Patterns.translateMethodCallPattern.accepts(element, processingContext);

            if (!accepts) {
                accepts = Patterns.translateMethodCallPattern_2.accepts(element, processingContext);
            }

            // 可能是通过构造器传入
            if (!accepts) {
                accepts = Patterns.translateNewExpressionPattern.accepts(element, processingContext)
            }

            if (accepts) {

                val result = mutableListOf<PsiReference>()

                result.add(SQLToyTranslateReference(element));

                return result.toTypedArray()
            }

            return PsiReference.EMPTY_ARRAY
        }
    }


}
