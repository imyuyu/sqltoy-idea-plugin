package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.indexer.SQLIdIndex
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.JavaUtils
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiLiteralExpression
import com.intellij.util.ProcessingContext

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

                    var project = parameters.position.project

                    var allIds = SQLIdIndex.getAllIds(
                        project,
                        SearchUtil.getSearchScope(project, parameters.position)
                    )
                    for (allId in allIds) {
                        var sqlIdRecord = SQLIdIndex.findRecordsByQualifiedId(
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