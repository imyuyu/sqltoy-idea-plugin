package com.github.imyuyu.sqltoy.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiReferenceList
import com.intellij.psi.impl.java.stubs.index.JavaShortClassNameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtil


object SearchUtil {

    fun getSearchScope(project: Project?, element: PsiElement): GlobalSearchScope {
        var searchScope = GlobalSearchScope.projectScope(
            project!!
        )
        val module =
            ProjectRootManager.getInstance(project).fileIndex.getModuleForFile(PsiUtil.getVirtualFile(element)!!)
        if (module != null) {
            searchScope = GlobalSearchScope.moduleScope(module)
        }
        return searchScope
    }

    fun getExtendsClassFields(psiClass: PsiClass): List<PsiField> {

        println("psiClasses = " + psiClass.qualifiedName)
        val psiFields: MutableList<PsiField> = ArrayList()
        val childrenOfAnyType: List<PsiReferenceList> = ArrayList(
            PsiTreeUtil.getChildrenOfAnyType(
                psiClass,
                PsiReferenceList::class.java
            )
        )
        for (psiReferenceList in childrenOfAnyType) {
            val referenceElements = psiReferenceList.referenceElements
            // 防止高版本jdk出现死循环栈溢出
            if (psiReferenceList.referenceElements.isEmpty() || psiReferenceList.role == PsiReferenceList.Role.PERMITS_LIST) {
                continue
            }

            for (referenceElement in referenceElements) {
                val qualifiedName = referenceElement.qualifiedName
                val globalSearchScope = GlobalSearchScope.projectScope(psiClass.project)
                val psiClasses =
                    JavaShortClassNameIndex.getInstance().get(referenceElement.referenceName!!, psiClass.project, globalSearchScope)

                for (aClass in psiClasses) {
                    if (aClass.qualifiedName == qualifiedName) {
                        psiFields.addAll(aClass.fields.toList())
                        psiFields.addAll(getExtendsClassFields(aClass))
                    }
                }
            }
        }
        return psiFields
    }
}