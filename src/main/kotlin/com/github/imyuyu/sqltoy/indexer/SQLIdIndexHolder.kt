package com.github.imyuyu.sqltoy.indexer

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.FileBasedIndex

/**
 * index util
 */
object SQLIdIndexHolder {

    fun getAllIds(
        project: Project?,
        scope: GlobalSearchScope?
    ): Collection<String> {
        val ids: MutableList<String> = mutableListOf()
        FileBasedIndex.getInstance().processAllKeys<String>(NAME, ids::add, scope!!, null)
        return ids
    }

    fun processRecordsByIds(
        project: Project?,
        scope: GlobalSearchScope?,
        processor: Processor<in SQLIdRecord>,
        ids: Collection<String>
    ): Boolean {
        val index = FileBasedIndex.getInstance()
        for (id in ids) {
            if (!index.processValues(
                    NAME, id, null,
                    { file: VirtualFile?, value: SQLIdRecord ->
                        val record: SQLIdRecord = value.withDataFile(file)
                        processor.process(record)
                    }, scope!!
                )
            ) {
                return false
            }
        }
        return true
    }

    fun findRecordsByQualifiedId(
        id: String,
        project: Project,
        scope: GlobalSearchScope
    ): Collection<SQLIdRecord> {
        val records: MutableList<SQLIdRecord> = mutableListOf()
        processRecordsByIds(project, scope, records::add, setOf(id))
        return records
    }
}