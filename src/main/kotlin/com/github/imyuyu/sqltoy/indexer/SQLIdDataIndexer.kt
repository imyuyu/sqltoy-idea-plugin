package com.github.imyuyu.sqltoy.indexer

import com.intellij.openapi.module.ModuleUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileContent

object SQLIdDataIndexer: DataIndexer<String, SQLIdRecord, FileContent> {

    /**
     * map to index
     */
    override fun map(inputData: FileContent): MutableMap<String, SQLIdRecord> {
        val xmlFile = inputData.psiFile as XmlFile
        val mutableMapOf = mutableMapOf<String, SQLIdRecord>()

        val document = xmlFile.document ?: return mutableMapOf;

        val rootTag = document.rootTag ?: return mutableMapOf
        if(rootTag.name != "sqltoy") {
            return mutableMapOf;
        }

        val xmlTags: List<XmlTag> = PsiTreeUtil.getChildrenOfAnyType(
            rootTag,
            XmlTag::class.java
        ).filter { it.name == "sql" }

        for (xmlTag in xmlTags) {
            val xmlAttribute: XmlAttribute? = xmlTag.getAttribute("id")
            val id: String? = xmlAttribute?.value
            if (id != null && id != "") {
                mutableMapOf[id] = SQLIdRecord(id, xmlFile.virtualFile, ModuleUtil.findModuleForFile(xmlFile)!!.name)
            }
        }

        return mutableMapOf;
    }
}