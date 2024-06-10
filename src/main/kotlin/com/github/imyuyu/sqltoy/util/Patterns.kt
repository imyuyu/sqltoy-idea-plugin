package com.github.imyuyu.sqltoy.util

import com.intellij.patterns.PsiJavaPatterns

class Patterns {

    companion object {

        val translateMethodCallPattern = PsiJavaPatterns.psiLiteral().methodCallParameter(
            0,
            PsiJavaPatterns.psiMethod().withName(PsiJavaPatterns.string().oneOf("getTranslateCache", "existCache"))
                .definedInClass(
                    PsiJavaPatterns.psiClass()
                        .withQualifiedName(PsiJavaPatterns.string().oneOf(* JavaUtils.getPsiSqlToyClass()))
                )
        );

        val translateMethodCallPattern_2 = PsiJavaPatterns.psiLiteral().methodCallParameter(0,
            PsiJavaPatterns.psiMethod().withName("cacheName")
                .definedInClass("org.sagacity.sqltoy.model.CacheMatchFilter")
        );

        val translateNewExpressionPattern = PsiJavaPatterns.psiLiteral()
            .withSuperParent(2, PsiJavaPatterns.psiNewExpression("org.sagacity.sqltoy.config.model.Translate"));

    }
}