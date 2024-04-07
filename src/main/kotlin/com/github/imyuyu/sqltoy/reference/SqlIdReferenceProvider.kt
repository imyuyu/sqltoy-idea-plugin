package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.util.JavaUtils.findStatement
import com.github.imyuyu.sqltoy.util.XmlUtil.isSqltoyXml
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.filters.ElementFilter
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag
import com.intellij.util.ProcessingContext
import java.util.*

class SqlIdReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(
        psiElement: PsiElement,
        processingContext: ProcessingContext
    ): Array<PsiReference> {
        return findStatement(psiElement.project, (psiElement.parent.parent as XmlTag))
            .map { e: PsiElement? -> PsiJavaInjectReference(psiElement, e!!) }
            .toTypedArray()
    }

    companion object {
        val filter: ElementFilter
            get() = object : ElementFilter {
                override fun isAcceptable(_element: Any, context: PsiElement?): Boolean {
                    if (_element !is PsiElement) return false
                    val element = _element
                    val parentElement = element.parent
                    val file = element.containingFile.originalFile
                    val vFile = file.virtualFile

                    return vFile != null &&
                            isSqltoyXml(parentElement) &&
                            parentElement is XmlAttribute &&
                            canContainSqlIdReference(
                                parentElement.parent.localName,
                                parentElement.name
                            )
                }

                override fun isClassAcceptable(hintClass: Class<*>?): Boolean {
                    return true
                }
            }

        private fun canContainSqlIdReference(tagName: String?, attrName: String?): Boolean {
            return "sql".equals(tagName, ignoreCase = true)
        }
    }
}
