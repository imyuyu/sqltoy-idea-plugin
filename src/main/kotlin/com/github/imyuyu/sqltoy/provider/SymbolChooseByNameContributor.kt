package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLIdRecord
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter

class SymbolChooseByNameContributor: ChooseByNameContributorEx {

    override fun processNames(processor: Processor<in String>, scope: GlobalSearchScope, filter: IdFilter?) {
        val project: Project? = scope.project
        if (project != null) {
            val ids: Collection<String> = SQLIdIndexHolder.getAllIds(project, scope)
            ids.forEach(processor::process)
        }
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
        val project = parameters.project
        var records: Collection<SQLIdRecord> =
            SQLIdIndexHolder.findRecordsByQualifiedId(SQLToyBeanIndexType.SQL_ID,name, project, parameters.searchScope)

        records.forEach { record: SQLIdRecord ->
            record.getRecordElements(project).forEach(processor::process)
        }

        records =
            SQLIdIndexHolder.findRecordsByQualifiedId(SQLToyBeanIndexType.TRANSLATE_ID,name, project, parameters.searchScope)

        records.forEach { record: SQLIdRecord ->
            record.getRecordElements(project).forEach(processor::process)
        }
    }
}