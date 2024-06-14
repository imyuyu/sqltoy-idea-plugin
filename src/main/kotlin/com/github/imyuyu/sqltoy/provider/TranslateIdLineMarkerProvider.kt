package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.dom.model.translate.DomTranslateBean
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlToken
import com.intellij.util.xml.DomManager
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.swing.Icon


class TranslateIdLineMarkerProvider : SimpleLineMarkerProvider<XmlToken, PsiElement>() {

    override fun isTheElement(element: PsiElement): Boolean {
        return element is XmlToken && isTargetType(element)
    }

    override fun apply(from: XmlToken): Optional<out Array<PsiElement>> {
        val project: Project = from.getProject();

        val tag = PsiTreeUtil.getParentOfType(from, XmlTag::class.java, false) ?: return Optional.empty()

        val domElement = DomManager.getDomManager(from.project).getDomElement(tag)

        if(domElement !is DomTranslateBean){
            return Optional.empty();
        }
        if(domElement.getCache().value.isNullOrBlank()){
            return Optional.empty();
        }

        val elements = JavaUtils.findTranslateStatement(project, domElement.getCache().value!!)
        if(elements.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(elements);
    }

    override fun getIcon(): Icon {
        return AllIcons.FileTypes.Java
    }

    override fun getTooltip(element: PsiElement, target: PsiElement): String {
        var text: String? = null
        if (element is PsiMethod) {
            val psiMethod = element as PsiMethod
            val containingClass = psiMethod.containingClass
            if (containingClass != null) {
                text = containingClass.name + "#" + psiMethod.name
            }
        }
        if (text == null && element is PsiClass) {
            val psiClass = element as PsiClass
            text = psiClass.qualifiedName
        }
        if (text == null && element is PsiLiteralExpression){
            text = element.text;
        }
        if (text == null) {
            text = target.containingFile.text
        }
        return "Jump To Java"
    }

    override fun getName(): String {
        return "translate id line marker";
    }

    private fun isTargetType(@NotNull token: XmlToken): Boolean {
        var targetType: Boolean = false
        if (token.text.endsWith("-translate")) {
            val parent = token.parent
            // 判断当前节点是标签
            if (parent is XmlTag) {
                // 判断当前元素是开始节点
                val nextSibling = token.nextSibling
                if (nextSibling is PsiWhiteSpace) {
                    targetType = true
                }
            }
        }
        return targetType
    }
}