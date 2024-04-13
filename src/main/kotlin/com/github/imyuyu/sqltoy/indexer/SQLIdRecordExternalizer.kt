package com.github.imyuyu.sqltoy.indexer

import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.IOUtil
import java.io.DataInput
import java.io.DataOutput

object SQLIdRecordExternalizer : DataExternalizer<SQLIdRecord> {
    override fun save(out: DataOutput, value: SQLIdRecord) {
        out.writeUTF(value.id);
        out.writeUTF(value.module);
    }

    override fun read(`in`: DataInput): SQLIdRecord {
        val id = `in`.readUTF()
        val module = `in`.readUTF()
        return SQLIdRecord(id,null,module)
    }
}