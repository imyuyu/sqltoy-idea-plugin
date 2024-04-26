package com.github.imyuyu.sqltoy.indexer

import com.intellij.util.indexing.*
import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor


class SQLIdIndex: FileBasedIndexExtension<String, SQLIdRecord>() {

    override fun getName(): ID<String, SQLIdRecord> {
        return NAME
    }

    override fun getIndexer(): DataIndexer<String, SQLIdRecord, FileContent> = SQLIdDataIndexer

    override fun getKeyDescriptor(): KeyDescriptor<String> = EnumeratorStringDescriptor.INSTANCE

    override fun getInputFilter(): FileBasedIndex.InputFilter = SQLIdInputFilter

    override fun dependsOnFileContent() = true

    override fun getValueExternalizer(): DataExternalizer<SQLIdRecord> = SQLIdRecordExternalizer

    override fun getVersion() = 1;

    companion object {
        val NAME = ID.create<String, SQLIdRecord>("sqltoy.external.id");
    }
}