package com.github.imyuyu.sqltoy.model.highlighting.jam

import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesOptions
import com.intellij.psi.PsiElement
import com.intellij.usageView.UsageInfo
import com.intellij.util.Processor

class SQLToyTranslateFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean) : FindUsagesHandler(element){

    override fun processElementUsages(
        element: PsiElement,
        processor: Processor<in UsageInfo>,
        options: FindUsagesOptions
    ): Boolean {
        return super.processElementUsages(element, processor, options)
    }
}
