package com.github.imyuyu.sqltoy.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.util.IncorrectOperationException

class PsiJavaInjectReference(var formElemnet: PsiElement, var bingdElement: PsiElement) : PsiReference {
    override fun getElement(): PsiElement {
        return formElemnet
    }

    override fun getRangeInElement(): TextRange {
        val text = formElemnet.text
        val match = text.startsWith("\"") && text.endsWith("\"")
        val len = 2
        return if (match && text.length > len) {
            TextRange(1, formElemnet.textLength - 1)
        } else TextRange(0, formElemnet.textLength)
    }

    override fun resolve(): PsiElement? {
        return bingdElement
    }

    override fun getCanonicalText(): String {
        return bingdElement.text
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(s: String): PsiElement {
        return formElemnet
    }

    @Throws(IncorrectOperationException::class)
    override fun bindToElement(psiElement: PsiElement): PsiElement? {
        return null
    }

    override fun isReferenceTo(psiElement: PsiElement): Boolean {
        return psiElement === resolve()
    }

    override fun getVariants(): Array<Any?> {
        return arrayOfNulls(0)
    }

    override fun isSoft(): Boolean {
        return false
    }
}