package com.github.imyuyu.sqltoy

import com.github.imyuyu.sqltoy.model.highlighting.jam.SQLToyTranslateFindUsagesHandler
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesHandlerFactory
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class SQLToyFindUsagesHandlerFactory: FindUsagesHandlerFactory() {

    override fun canFindUsages(element: PsiElement): Boolean {
        val module = ModuleUtilCore.findModuleForPsiElement(element);
        val containingFile: VirtualFile = element.containingFile.virtualFile
        return XmlUtil.isSqltoyXml(containingFile);
    }

    override fun createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler? {
        if(XmlUtil.isTranslateXml(element)){
            //
            return SQLToyTranslateFindUsagesHandler(element, forHighlightUsages);
        } else {
            return null;
        }
    }
}