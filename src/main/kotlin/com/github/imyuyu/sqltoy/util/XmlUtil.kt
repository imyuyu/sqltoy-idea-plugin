package com.github.imyuyu.sqltoy.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.xml.XmlFileImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import java.util.*


object XmlUtil {
    const val EXT: String = "sql.xml";

    fun findXmlPsiElement(project: Project, virtualFiles: Collection<VirtualFile>, key: String): List<PsiElement> {
        val result: MutableList<PsiElement> = ArrayList()
        for (virtualFile in virtualFiles) {
            val psiFile = PsiManager.getInstance(project).findFile(virtualFile)
            if (psiFile is XmlFileImpl) {
                val navigationElement = psiFile.navigationElement
                if (Objects.nonNull(navigationElement)) {
                    if (Objects.nonNull(navigationElement.children)) {
                        val child = navigationElement.children[0]
                        if (Objects.nonNull(child)) {
                            if (Objects.nonNull(child.children) && child.children.size > 0) {
                                val child1 = child.children[1]
                                if (Objects.nonNull(child1)) {
                                    val psiElements = child1.children
                                    if (Objects.nonNull(psiElements) && psiElements.size > 0) {
                                        val xmlTags: List<XmlTag> = ArrayList(
                                            PsiTreeUtil.getChildrenOfAnyType(
                                                child1,
                                                XmlTag::class.java
                                            )
                                        )
                                        for (xmlTag in xmlTags) {
                                            if ("sql" == xmlTag.name) {
                                                val xmlAttribute: XmlAttribute? = xmlTag.getAttribute("id")
                                                val id: String? = xmlAttribute?.value
                                                if (key == id) {
                                                    result.add(xmlAttribute.valueElement!!)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    fun isInjectXml(literalExpression: PsiElement, fields: MutableList<String>): Boolean {
        if(literalExpression is PsiNewExpression && literalExpression.text.startsWith("new QueryExecutor")){
            return true;
        }
        val p1 = literalExpression.parent as? PsiExpressionList ?: return false
        val p2 = p1.parent as? PsiMethodCallExpression ?: return false
        val text = p2.text
        return fields.stream().filter { s ->
            text.startsWith("$s.") || text.startsWith("super.$s.") || text.startsWith(
                "this.$s."
            )
        }.findAny().isPresent
    }

    fun isNewQueryExecutor(literalExpression: PsiLiteralExpression, fields: MutableList<String>): Boolean {
        val p1 = literalExpression.parent as? PsiExpressionList ?: return false
        val p2 = p1.parent as? PsiNewExpression ?: return false
        return if (!p2.text.startsWith("new QueryExecutor")) {
            false
        } else isInjectXml(p2, fields)
    }

    fun isSqltoyXml(element:PsiElement): Boolean {
        return isXmlFile(element.containingFile) && element.containingFile.name.endsWith(EXT);
    }

    /**
     * 判断是否是xml文件
     *
     * @param file
     * @return
     */
    fun isXmlFile(file: PsiFile): Boolean {
        return file is XmlFile
    }
}