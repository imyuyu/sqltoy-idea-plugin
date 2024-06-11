package com.github.imyuyu.sqltoy.model.highlighting.jam

import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesOptions
import com.intellij.psi.PsiElement
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlTag
import com.intellij.usageView.UsageInfo
import com.intellij.util.Processor
import com.intellij.util.xml.DomManager

class SQLToyTranslateFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean) : FindUsagesHandler(element){

    override fun processElementUsages(
        element: PsiElement,
        processor: Processor<in UsageInfo>,
        options: FindUsagesOptions
    ): Boolean {
        ReferencesSearch.search(
            DomManager.getDomManager(project).getDomElement(
                PsiTreeUtil.getParentOfType(
                    element.navigationElement,
                    XmlTag::class.java
                )
            )?.xmlTag as XmlTag, options.searchScope
        ).toList()
        return super.processElementUsages(element, processor, options)
    }
}
