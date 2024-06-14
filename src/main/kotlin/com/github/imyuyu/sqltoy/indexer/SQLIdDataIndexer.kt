package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.dom.model.sql.SQLToy
import com.github.imyuyu.sqltoy.dom.model.translate.SQLToyTranslate
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.openapi.util.Pair
import com.intellij.psi.xml.XmlFile
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileContent
import com.intellij.util.xml.DomManager
import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList

object SQLIdDataIndexer: DataIndexer<Pair<SQLToyBeanIndexType, String>, IntList, FileContent> {

    /**
     * map to index
     */
    override fun map(inputData: FileContent): MutableMap<Pair<SQLToyBeanIndexType, String>, IntList> {
        val xmlFile = inputData.psiFile as XmlFile
        val mutableMapOf = mutableMapOf<Pair<SQLToyBeanIndexType, String>, IntList>()

        val domManager = DomManager.getDomManager(inputData.project)
        if(XmlUtil.isTranslateXml(xmlFile)){
            // 缓存翻译的
            val fileElement = domManager.getFileElement(xmlFile, SQLToyTranslate::class.java) ?: return mutableMapOf

            val translates = fileElement.rootElement.getTranslates().getAllTranslate();
            for (translate in translates) {
                val cache = translate.getCache()
                val cacheName = cache.stringValue?.trim()
                if (cacheName != null && cacheName != "") {
                    val pair = Pair.create(SQLToyBeanIndexType.TRANSLATE_ID, cacheName)
                    var intList = mutableMapOf[pair]
                    if (intList == null) {
                        intList = IntArrayList();
                    }
                    intList.add(translate.xmlElement!!.textOffset);
                    mutableMapOf[pair] = intList;
                }
            }
        }else {
            val fileElement = domManager.getFileElement(xmlFile, SQLToy::class.java) ?: return mutableMapOf

            val sqlList = fileElement.rootElement.getSqlList()
            for (sql in sqlList) {
                val id = sql.getId().stringValue?.trim()
                if (id != null && id != "") {
                    val pair = Pair.create(SQLToyBeanIndexType.SQL_ID, id)
                    var intList = mutableMapOf[pair]
                    if (intList == null) {
                        intList = IntArrayList();
                    }
                    intList.add(sql.xmlElement!!.textOffset);
                    mutableMapOf[pair] = intList;
                }
            }
        }

        return mutableMapOf;
    }
}