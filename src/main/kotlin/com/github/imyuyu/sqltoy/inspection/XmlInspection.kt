package com.github.imyuyu.sqltoy.inspection

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.codeInspection.XmlSuppressableInspectionTool
import com.intellij.psi.ElementManipulators
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.XmlElementVisitor
import com.intellij.psi.util.PsiUtilCore
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.analysis.XmlAnalysisBundle
import com.intellij.xml.util.XmlRefCountHolder

/**
 * sqlId not usages check
 */
class XmlInspection : XmlSuppressableInspectionTool() {

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : XmlElementVisitor() {
            override fun visitXmlAttributeValue(value: XmlAttributeValue) {
                if (value.textRange.isEmpty) {
                    return
                }
                val file = value.containingFile as? XmlFile ?: return
                val baseFile = PsiUtilCore.getTemplateLanguageFile(file)
                if (baseFile !== file && baseFile !is XmlFile) {
                    return
                }
                val refHolder = XmlRefCountHolder.getRefCountHolder(file) ?: return

                val parent = value.parent as? XmlAttribute ?: return
                val tag = parent.parent as XmlTag ?: return
                checkValue(value, file, refHolder, tag, holder)
            }
        }
    }

    protected fun checkValue(
        value: XmlAttributeValue,
        file: XmlFile?,
        refHolder: XmlRefCountHolder,
        tag: XmlTag,
        holder: ProblemsHolder
    ) {
        //if (refHolder.isValidatable(tag.parent) && refHolder.isDuplicateIdAttributeValue(value!!)) {
        if (refHolder.isValidatable(tag.parent) && tag.name == "sql" && (value.parent as XmlAttribute).name == "id") {
            if(value.value.isEmpty()){
                /*holder.registerProblem(
                    value,
                    "Invalid sql id",
                    ProblemHighlightType.GENERIC_ERROR,
                    ElementManipulators.getValueTextRange(value)
                )*/
            } else {
                /*holder.registerProblem(
                    value,
                    XmlAnalysisBundle.message("xml.inspections.duplicate.id.reference"),
                    ProblemHighlightType.GENERIC_ERROR,
                    ElementManipulators.getValueTextRange(value)
                )*/
            }
        }
    }

}