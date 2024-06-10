package com.github.imyuyu.sqltoy.indexer

import com.intellij.util.io.DataInputOutputUtil
import com.intellij.util.io.IOUtil
import com.intellij.util.io.KeyDescriptor
import com.intellij.openapi.util.Pair;
import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

object SQLToyIndexKeyDescriptor : KeyDescriptor<Pair<SQLToyBeanIndexType, String>> {

    override fun save(out: DataOutput, pair: Pair<SQLToyBeanIndexType, String>) {
        DataInputOutputUtil.writeINT(out, pair.first.ordinal)
        IOUtil.writeUTF(out, pair.second)
    }

    override fun read(`in`: DataInput): Pair<SQLToyBeanIndexType, String> {
        val type = SQLToyBeanIndexType.entries[DataInputOutputUtil.readINT(`in`)]
        val value = IOUtil.readUTF(`in`)
        return Pair.create(type, value)
    }

    override fun getHashCode(value: Pair<SQLToyBeanIndexType, String>): Int {
        val indexType = value.first as SQLToyBeanIndexType
        return 31 * indexType.ordinal + (value.second?.hashCode() ?: 0)
    }

    override fun isEqual(val1: Pair<SQLToyBeanIndexType, String>, val2: Pair<SQLToyBeanIndexType, String>): Boolean {
        return if (val1.first as SQLToyBeanIndexType != val2.first) {
            false
        } else {
            val1.second == val2.second
        }
    }
}