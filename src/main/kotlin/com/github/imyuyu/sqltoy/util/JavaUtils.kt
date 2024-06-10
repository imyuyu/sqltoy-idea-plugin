package com.github.imyuyu.sqltoy.util

import ai.grazie.utils.toDistinctTypedArray
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsSafe
import com.intellij.patterns.PsiClassPattern
import com.intellij.patterns.PsiJavaPatterns
import com.intellij.psi.*
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiLiteralUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilCore
import com.intellij.psi.xml.XmlTag
import org.apache.commons.collections.CollectionUtils
import java.util.*


object JavaUtils {

    private val TARGET_TYPES: Set<String> = setOf<String>(
        "org.sagacity.sqltoy.dao.SqlToyLazyDao",
        "org.sagacity.sqltoy.dao.LightDao",
        "org.sagacity.sqltoy.service.SqlToyCRUDService",
        "com.sagframe.sagacity.sqltoy.plus.dao.SqlToyHelperDao"
    )


    fun findStatement(project: Project, from: XmlTag): Array<PsiElement> {

        val id = from.getAttributeValue("id")!!;

        val virtualFiles =
            FilenameIndex.getAllFilesByExt(project, "java", SearchUtil.getSearchScope(project, from))

        val result: MutableList<PsiElement> = mutableListOf();
        for (virtualFile in virtualFiles) {
            val psiJavaFile = PsiManager.getInstance(project).findFile(virtualFile)

            if (psiJavaFile !is PsiJavaFile) {
                continue
            }

            val originalElement: PsiElement = psiJavaFile.getOriginalElement()
            // get all class
            val psiClassList: List<PsiClass> = ArrayList(
                PsiTreeUtil.getChildrenOfAnyType(
                    originalElement,
                    PsiClass::class.java
                )
            )
            for (psiClass in psiClassList) {
                val keys: MutableList<String> = ArrayList()
                val childrenOfAnyType: MutableList<PsiField> = ArrayList(
                    PsiTreeUtil.getChildrenOfAnyType(
                        psiClass,
                        PsiField::class.java
                    )
                )
                childrenOfAnyType.addAll(SearchUtil.getExtendsClassFields(psiClass))
                for (field in childrenOfAnyType) {
                    if (isSqlToyBean(field)) {
                        keys.add(field.name)
                    }
                }
                if (CollectionUtils.isNotEmpty(keys)) {
                    val psiMethods: List<PsiMethod> = ArrayList(
                        PsiTreeUtil.getChildrenOfAnyType(
                            psiClass,
                            PsiMethod::class.java
                        )
                    )
                    val psiMethodCallExpressions: MutableList<PsiMethodCallExpression> = ArrayList()

                    psiMethodCallExpressions.addAll(PsiTreeUtil.collectElementsOfType(psiClass, PsiMethodCallExpression::class.java))

                    /*for (psiMethod in psiMethods) {
                        val psiCodeBlocks: List<PsiCodeBlock> = ArrayList(
                            PsiTreeUtil.getChildrenOfAnyType(
                                psiMethod,
                                PsiCodeBlock::class.java
                            )
                        )
                        for (psiCodeBlock in psiCodeBlocks) {
                            val psiDeclarationStatements: List<PsiDeclarationStatement> = ArrayList(
                                PsiTreeUtil.getChildrenOfAnyType(
                                    psiCodeBlock,
                                    PsiDeclarationStatement::class.java
                                )
                            )
                            for (psiDeclarationStatement in psiDeclarationStatements) {
                                val psiLocalVariables: List<PsiLocalVariable> = ArrayList(
                                    PsiTreeUtil.getChildrenOfAnyType(
                                        psiDeclarationStatement,
                                        PsiLocalVariable::class.java
                                    )
                                )
                                for (psiLocalVariable in psiLocalVariables) {
                                    psiMethodCallExpressions.addAll(
                                        PsiTreeUtil.getChildrenOfAnyType(
                                            psiLocalVariable,
                                            PsiMethodCallExpression::class.java
                                        )
                                    )
                                }
                            }
                            val psiExpressionStatements: List<PsiExpressionStatement> = ArrayList(
                                PsiTreeUtil.getChildrenOfAnyType(
                                    psiCodeBlock,
                                    PsiExpressionStatement::class.java
                                )
                            )
                            for (psiExpressionStatement in psiExpressionStatements) {
                                psiMethodCallExpressions.addAll(
                                    PsiTreeUtil.getChildrenOfAnyType(
                                        psiExpressionStatement,
                                        PsiMethodCallExpression::class.java
                                    )
                                )
                            }
                        }
                    }*/

                    for (psiMethodCallExpression in psiMethodCallExpressions) {
                        val xmlIdBindPsiElement: PsiElement? = getXmlIdBindPsiElement(psiMethodCallExpression, keys, id)
                        if (xmlIdBindPsiElement != null) {
                            result.add(xmlIdBindPsiElement)
                        }
                    }

                    val psiNewExpressions =
                        PsiTreeUtil.collectElementsOfType(psiClass, PsiNewExpression::class.java)
                    for (psiNewExpression in psiNewExpressions) {
                        val xmlIdBindPsiElement: PsiElement? = getXmlIdBindPsiElement(psiNewExpression, keys, id)
                        if (xmlIdBindPsiElement != null) {
                            result.add(xmlIdBindPsiElement)
                        }
                    }
                }
            }
        }

        return result.toTypedArray();
    }

    fun isSqlToyBean(field: PsiField): Boolean {
        val typeElement = field.typeElement
        if (Objects.nonNull(typeElement)) {
            val innermostComponentReferenceElement = typeElement!!.innermostComponentReferenceElement
            if (Objects.nonNull(innermostComponentReferenceElement)) {
                val canonicalText = innermostComponentReferenceElement!!.canonicalText
                if (TARGET_TYPES.contains(canonicalText)) {
                    return true
                }
            }
        }
        return false
    }

    fun getXmlIdBindPsiElement(
        psiMethodCallExpression: PsiMethodCallExpression,
        keys: List<String>,
        id: String
    ): PsiElement? {
        for (key in keys) {
            val text = psiMethodCallExpression.text
            if (text.startsWith("$key.")
                || text.startsWith("super.$key.")
                || text.startsWith("this.$key.")
            ) {
                val psiExpressionList = PsiTreeUtil.getChildOfAnyType(
                    psiMethodCallExpression,
                    PsiExpressionList::class.java
                )
                if (Objects.nonNull(psiExpressionList)) {
                    val psiLiteralExpressions: List<PsiLiteralExpression> = ArrayList(
                        PsiTreeUtil.getChildrenOfAnyType(
                            psiExpressionList,
                            PsiLiteralExpression::class.java
                        )
                    )
                    for (psiLiteralExpression in psiLiteralExpressions) {
                        if (!psiLiteralExpression.text.contains(" ") && id == PsiLiteralUtil.getStringLiteralContent(
                                psiLiteralExpression
                            )
                        ) {
                            return psiLiteralExpression
                        }
                    }
                }
            }
        }
        return null
    }

    private fun getXmlIdBindPsiElement(
        psiNewExpression: PsiNewExpression,
        keys: List<String>,
        id: String
    ): PsiElement? {
        val text = psiNewExpression.text
        if(!text.startsWith("new QueryExecutor")){
            return null;
        }

        val psiLiteralExpressions = PsiTreeUtil.collectElementsOfType(psiNewExpression, PsiLiteralExpression::class.java);
        for (psiLiteralExpression in psiLiteralExpressions) {
            if (!psiLiteralExpression.text.contains(" ") && id == PsiLiteralUtil.getStringLiteralContent(
                    psiLiteralExpression
                )
            ) {
                return psiLiteralExpression
            }
        }

        return null;
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

    fun isSqlToyClass(className: @NlsSafe String?): Boolean {
        return TARGET_TYPES.contains(className);
    }

    /**
     * 是否是缓存翻译类
     */
    fun isTranslateClass(className: @NlsSafe String?): Boolean {
        return "org.sagacity.sqltoy.config.model.Translate" == className
    }

    fun getPsiSqlToyClass(): Array<String> {
        return TARGET_TYPES.toTypedArray()
    }

    fun findTranslateStatement(project: Project, translateCache: String): Array<PsiElement> {
        val virtualFiles =
            FilenameIndex.getAllFilesByExt(project, JavaFileType.DEFAULT_EXTENSION, GlobalSearchScope.projectScope(project))

        val result: MutableList<PsiElement> = mutableListOf();
        for (virtualFile in virtualFiles) {
            val psiFile = PsiUtilCore.getPsiFile(project, virtualFile)
            if(psiFile !is PsiJavaFile){
                continue;
            }

            val psiLiterals = PsiTreeUtil.collectElementsOfType(psiFile, PsiLiteral::class.java)
            for (psiLiteral in psiLiterals) {

                if(psiLiteral.value.toString() != translateCache){
                    continue;
                }

                if(!Patterns.translateMethodCallPattern.accepts(psiLiteral)){
                    if(!Patterns.translateMethodCallPattern_2.accepts(psiLiteral)) {
                        if (!Patterns.translateNewExpressionPattern.accepts(psiLiteral)) {
                            continue;
                        }
                    }
                }
                result.add(psiLiteral);
            }
        }
        return result.toTypedArray();
    }

}