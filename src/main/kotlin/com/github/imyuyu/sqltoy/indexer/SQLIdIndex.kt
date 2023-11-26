package com.github.imyuyu.sqltoy.indexer

import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.*
import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor
import java.util.*


class SQLIdIndex: FileBasedIndexExtension<String, SQLIdRecord>() {

    object Cache {
        val id = ID.create<String, SQLIdRecord>("sqltoy.external.id");
    }

    private val indexer = SQLIdDataIndexer();

    private val valueExternalizer = SQLIdRecordExternalizer();

    override fun getName(): ID<String, SQLIdRecord> {
        return Cache.id;
    }

    override fun getIndexer(): DataIndexer<String, SQLIdRecord, FileContent> {
        return indexer;
    }

    override fun getKeyDescriptor(): KeyDescriptor<String> {
        return EnumeratorStringDescriptor.INSTANCE;
    }

    override fun getInputFilter(): FileBasedIndex.InputFilter {
        return object : DefaultFileTypeSpecificInputFilter(XmlFileType.INSTANCE) {
            override fun acceptInput(file: VirtualFile): Boolean {
                if (!file.name.endsWith("sql.xml")) {
                    return false
                }
                if (file.isInLocalFileSystem) {
                    return true
                }
                return false;
            }
        }
    }

    override fun dependsOnFileContent(): Boolean {
        return true;
    }

    override fun getValueExternalizer(): DataExternalizer<SQLIdRecord> {
        return valueExternalizer;
    }

    override fun getVersion(): Int {
        return 1;
    }


    companion object {
        fun getAllIds(
            project: Project?,
            scope: GlobalSearchScope?
        ): Collection<String> {
            val ids: MutableList<String> = mutableListOf()
            FileBasedIndex.getInstance().processAllKeys<String>(Cache.id, ids::add, scope!!, null)
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
                if (!index.processValues(Cache.id, id, null,
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
}