package com.github.imyuyu.sqltoy.indexer

import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.DataInputOutputUtil
import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList
import java.io.DataInput
import java.io.DataOutput

object SQLIdRecordExternalizer : DataExternalizer<IntList> {

    override fun save(out: DataOutput, values: IntList) {
        if (out == null) {
            return;
        }

        val size: Int = values.size
        if (size == 1) {
            DataInputOutputUtil.writeINT(out, -values.getInt(0))
        } else {
            DataInputOutputUtil.writeINT(out, size)

            for (i in 0 until size) {
                DataInputOutputUtil.writeINT(out, values.getInt(i))
            }
        }
    }

    override fun read(`in`: DataInput): IntList {
        if (`in` == null) {
            return IntArrayList(0);
        }

        val countOrSingleValue = DataInputOutputUtil.readINT(`in`)
        val result: IntArrayList
        if (countOrSingleValue < 0) {
            result = IntArrayList(1)
            result.add(-countOrSingleValue)
            return result
        } else {
            result = IntArrayList(countOrSingleValue)

            for (i in 0 until countOrSingleValue) {
                result.add(DataInputOutputUtil.readINT(`in`))
            }

            return result
        }
    }
}