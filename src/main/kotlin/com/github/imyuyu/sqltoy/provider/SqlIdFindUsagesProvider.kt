package com.github.imyuyu.sqltoy.provider

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.SimpleWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.lang.xml.XmlFindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls

class SqlIdFindUsagesProvider : FindUsagesProvider {

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiLiteralExpression
    }

    override fun getHelpId(psiElement: PsiElement): @NonNls String? {
        return null
    }

    override fun getType(element: PsiElement): @Nls String {
        return ""
    }

    override fun getDescriptiveName(element: PsiElement): @Nls String {
        return ""
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): @Nls String {
        return "111"
    }
}
