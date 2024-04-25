package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiLiteralExpression
import com.intellij.util.ProcessingContext

/**
 * 代码完成
 */
internal class SqlIdCompletionContributor : CompletionContributor() {



    init {
        extend(
            CompletionType.BASIC, PlatformPatterns.psiElement(PsiJavaToken::class.java),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {

                    // only support literal expression
                    if(parameters.position.parent !is PsiLiteralExpression){
                        return
                    }

                    val project = parameters.position.project

                    val allIds = SQLIdIndexHolder.getAllIds(
                        project,
                        SearchUtil.getSearchScope(project, parameters.position)
                    )
                    for (allId in allIds) {
                        val sqlIdRecord = SQLIdIndexHolder.findRecordsByQualifiedId(
                            allId,
                            project,
                            SearchUtil.getSearchScope(
                                project, parameters.position
                            )
                        ).first()
                        resultSet.addElement(LookupElementBuilder.create(allId)
                            .withBoldness(true)
                            .withTailText(" ("+sqlIdRecord.myDataFile?.name+")")
                            .withPsiElement(sqlIdRecord.getElements(project).first())
                            .withIcon(Icons.XML_ICON));
                    }
                }
            }
        )
    }
}