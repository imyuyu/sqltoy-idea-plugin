package com.github.imyuyu.sqltoy.indexer

import com.intellij.util.indexing.*
import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor
import it.unimi.dsi.fastutil.ints.IntList
import com.intellij.openapi.util.Pair;


class SQLIdIndex: FileBasedIndexExtension<Pair<SQLToyBeanIndexType, String>, IntList>() {

    override fun getName(): ID<Pair<SQLToyBeanIndexType, String>, IntList> = KEY

    override fun getIndexer(): DataIndexer<Pair<SQLToyBeanIndexType, String>, IntList, FileContent> = SQLIdDataIndexer

    override fun getKeyDescriptor(): KeyDescriptor<Pair<SQLToyBeanIndexType, String>> = SQLToyIndexKeyDescriptor

    override fun getInputFilter(): FileBasedIndex.InputFilter = SQLIdInputFilter

    override fun dependsOnFileContent() = true

    override fun getValueExternalizer(): DataExternalizer<IntList> = SQLIdRecordExternalizer

    override fun getVersion() = 2;

    companion object {
        val KEY = ID.create<Pair<SQLToyBeanIndexType, String>, IntList>("sqltoy.external.id");
    }
}