package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.intellij.openapi.module.ModuleUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileContent
import com.intellij.util.xml.DomManager

object SQLIdDataIndexer: DataIndexer<String, SQLIdRecord, FileContent> {

    /**
     * map to index
     */
    override fun map(inputData: FileContent): MutableMap<String, SQLIdRecord> {
        val xmlFile = inputData.psiFile as XmlFile
        val mutableMapOf = mutableMapOf<String, SQLIdRecord>()

        val domManager = DomManager.getDomManager(inputData.project)
        val fileElement = domManager.getFileElement(xmlFile, SQLToy::class.java) ?: return mutableMapOf

        val sqlList = fileElement.rootElement.getSqlList()
        for (sql in sqlList) {
            val id = sql.getId().stringValue?.trim()
            if (id != null && id != "") {
                mutableMapOf[id] = SQLIdRecord(id, xmlFile.virtualFile, ModuleUtil.findModuleForFile(xmlFile)!!.name)
            }
        }

        return mutableMapOf;
    }
}