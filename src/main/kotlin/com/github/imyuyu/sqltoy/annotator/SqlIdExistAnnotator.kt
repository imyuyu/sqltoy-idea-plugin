package com.github.imyuyu.sqltoy.annotator

import com.github.imyuyu.sqltoy.service.EditorService
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.codeInsight.daemon.impl.analysis.RemoveTagIntentionFix
import com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.modcommand.ActionContext
import com.intellij.modcommand.ModPsiUpdater
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.PsiSearchHelper
import com.intellij.psi.util.parentOfTypes
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import com.intellij.util.IncorrectOperationException
import org.jetbrains.annotations.Nls

class SqlIdExistAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if(!XmlUtil.isSqltoyXml(element)){
            return;
        }

        if(element !is XmlAttributeValue){
            return;
        }

        val attribute = element.parentOfTypes(XmlAttribute::class)

        if(attribute?.name != "id"){
            return;
        }

        val parent = attribute.parentOfTypes(XmlTag::class);
        if(parent != null && parent.name == "sql") {
            val text = element.text
            val value = StringUtil.stripQuotesAroundValue(text)
            if(value == ""){
                return
            }
            val match = text.startsWith("\"") && text.endsWith("\"")
            val len = 2
            val range = if (match && text.length > len) {
                TextRange(element.startOffset + 1, element.endOffset - 1)
            } else TextRange(element.startOffset, element.endOffset)


            val mutableListOf = mutableListOf<Any>()
            PsiSearchHelper.getInstance(element.project).processAllFilesWithWordInLiterals(value, GlobalSearchScope.projectScope(element.project)) {
                mutableListOf.add(
                    it
                )
            }

            if(mutableListOf.size == 0){
                holder.newAnnotation(HighlightSeverity.WARNING, String.format("SQL '%s' is never used ", value))
                    .range(range)
                    .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                    .withFix(RemoveTagIntentionFix("'$value'", parent))
                    .create()
            }
        }
    }
}
