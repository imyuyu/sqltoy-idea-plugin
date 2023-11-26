package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.indexer.SQLIdIndex
import com.github.imyuyu.sqltoy.indexer.SQLIdRecord
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import java.util.function.Consumer

class SymbolChooseByNameContributor: ChooseByNameContributorEx {
    override fun processNames(processor: Processor<in String>, scope: GlobalSearchScope, filter: IdFilter?) {
        val project: Project? = scope.project
        if (project != null) {
            val ids: Collection<String> = SQLIdIndex.getAllIds(project, scope)
            ids.forEach(processor::process)
        }
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
        val project = parameters.project
        val records: Collection<SQLIdRecord> =
            SQLIdIndex.findRecordsByQualifiedId(name, project, parameters.searchScope)
        records.forEach { record: SQLIdRecord ->
            record.getRecordElements(project).forEach(processor::process)
        }
    }
}