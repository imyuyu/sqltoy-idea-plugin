package com.github.imyuyu.sqltoy.provider

import com.intellij.codeInsight.completion.CompletionConfidence
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl
import com.intellij.util.ThreeState

@Deprecated(message = "not used")
class SqlIdCompletionConfidence : CompletionConfidence() {
    override fun shouldSkipAutopopup(contextElement: PsiElement, psiFile: PsiFile, offset: Int): ThreeState {
        val literalExpression = contextElement.parent
        if (contextElement !is PsiJavaTokenImpl || literalExpression !is PsiLiteralExpression) {
            return ThreeState.UNSURE
        }
        val sqlRef:String? = if (literalExpression.value is String) literalExpression.value as String else null
        if (sqlRef == null) {
            return ThreeState.UNSURE
        }
        /*if (sqlRef.startsWith("&")) {
            return ThreeState.NO
        }
        return ThreeState.UNSURE*/
        return ThreeState.NO
    }
}