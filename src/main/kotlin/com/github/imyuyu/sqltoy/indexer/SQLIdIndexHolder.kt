package com.github.imyuyu.sqltoy.indexer

import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.FileBasedIndex
import it.unimi.dsi.fastutil.ints.IntList

/**
 * index util
 */
object SQLIdIndexHolder {

    fun getAllIds(
        project: Project?,
        scope: GlobalSearchScope?
    ): Collection<String> {
        val ids: MutableList<String> = mutableListOf()
        FileBasedIndex.getInstance().processAllKeys(SQLIdIndex.KEY, {
            ids.add(it.second);
            return@processAllKeys true;
        }, scope!!, null)
        return ids
    }

    fun getAllTranslateIds(
        project: Project?,
        scope: GlobalSearchScope?
    ): Collection<String> {
        val ids: MutableList<String> = mutableListOf()
        FileBasedIndex.getInstance().processAllKeys(SQLIdIndex.KEY, {
            if(it.first == SQLToyBeanIndexType.TRANSLATE_ID){
                ids.add(it.second);
            }
            return@processAllKeys true;
        }, scope!!, null)
        return ids
    }

    fun getAllSqlIds(
        project: Project?,
        scope: GlobalSearchScope?
    ): Collection<String> {
        val ids: MutableList<String> = mutableListOf()
        FileBasedIndex.getInstance().processAllKeys(SQLIdIndex.KEY, {
            if(it.first == SQLToyBeanIndexType.SQL_ID){
                ids.add(it.second);
            }
            return@processAllKeys true;
        }, scope!!, null)
        return ids
    }

    fun findRecordsByQualifiedId(
        type:SQLToyBeanIndexType,
        id: String,
        project: Project,
        scope: GlobalSearchScope
    ): Collection<SQLIdRecord> {
        val records: MutableList<SQLIdRecord> = mutableListOf()
        val index = FileBasedIndex.getInstance()
        val pair = Pair.create(type, id)
        index.processValues(
            SQLIdIndex.KEY, pair, null,
            { file: VirtualFile?, value: IntList ->
                records.add(SQLIdRecord(pair, file, ModuleUtil.findModuleForFile(file!!, project)?.name ?: "", value.getInt(0)))
            }, scope
        )
        return records
    }

    fun existsSqlId(type:SQLToyBeanIndexType,
                    id: String,
                    scope: GlobalSearchScope): Boolean{

        val index = FileBasedIndex.getInstance()
        // 判断index是否存在id
        return index.getAllKeys(SQLIdIndex.KEY,scope.project!!).contains(Pair.create(type, id));
    }
}