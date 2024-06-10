package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.sql.psi.SqlLanguage


/**
 * 给函数注入sql
 */
class MethodMultiHostInjector : MultiHostInjector {

    override fun getLanguagesToInject(multiHostRegistrar: MultiHostRegistrar, context: PsiElement) {
        if (context is PsiLiteralExpression && shouldInject(context)) {
            // context注入成sql
            multiHostRegistrar // 开始注入成SQL语言
                .startInjecting(SqlLanguage.INSTANCE)
                .addPlace(
                    null, null,
                    (context as PsiLanguageInjectionHost),
                    innerRangeStrippingQuotes(context)
                )
                .doneInjecting()
        }
    }

    private fun innerRangeStrippingQuotes(context: PsiElement): TextRange {
        return TextRange(1, context.textRange.length - 1);
    }

    private fun shouldInject(element: PsiElement): Boolean {
        val sqlId = (element as PsiLiteralExpressionImpl).value.toString().trim()
        if(StringUtil.isEmpty(sqlId)){
            return false;
        }

        // check sql
        val lowercaseSql = sqlId.lowercase()
        if(!lowercaseSql.startsWith("select ") &&
            !lowercaseSql.startsWith("update ") &&
            !lowercaseSql.startsWith("insert ") &&
            !lowercaseSql.startsWith("delete ") &&
            !lowercaseSql.startsWith("create ") &&
            !lowercaseSql.startsWith("alter ") &&
            !lowercaseSql.startsWith("drop ")
            ){
            return false;
        }

        // 获取父级的PsiExpressionList
        val expressionList = PsiTreeUtil.getParentOfType<PsiExpressionList>(
            element, PsiExpressionList::class.java
        ) ?: return false;

        // 获取父级的方法调用表达式
        val methodCallExpression = PsiTreeUtil.getParentOfType(
            expressionList, PsiMethodCallExpression::class.java
        )

        if (methodCallExpression != null) {
            return checkMethodCallExpression(methodCallExpression, sqlId, element);
        }

        val newExpression = PsiTreeUtil.getParentOfType(
            expressionList, PsiNewExpression::class.java
        ) ?: return false

        return checkNewExpression(newExpression, sqlId, element)
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>?> {
        return mutableListOf(PsiLiteralExpression::class.java)
    }

    private fun checkMethodCallExpression(
        methodCallExpression: PsiMethodCallExpression,
        sqlId: String,
        element: PsiElement
    ): Boolean {
        // 获取方法调用的解析结果
        val method = methodCallExpression.resolveMethod() ?: return false

        val methodName = method.name
        val containingClass = method.containingClass!!

        if ((containingClass.qualifiedName == "org.sagacity.sqltoy.dao.LightDao" || containingClass.qualifiedName == "org.sagacity.sqltoy.dao.SqlToyLazyDao")
            && methodName.startsWith("find") && !SQLIdIndexHolder.existsSqlId(
                SQLToyBeanIndexType.SQL_ID,
                sqlId,
                SearchUtil.getSearchScope(element.project, element)
            )
        ) {
            return true
        }

        return false;
    }

    private fun checkNewExpression(
        newExpression: PsiNewExpression,
        sqlId: String,
        element: PsiElement
    ): Boolean {
        // 获取new表达式的解析结果
        val newExpressionClass = newExpression.resolveConstructor()?.containingClass
        if (newExpressionClass != null && newExpressionClass.qualifiedName == "org.sagacity.sqltoy.model.QueryExecutor" &&
            !SQLIdIndexHolder.existsSqlId(
                SQLToyBeanIndexType.SQL_ID,
                sqlId,
                SearchUtil.getSearchScope(element.project, element)
            )) {
            return true
        }

        return false
    }
}
