package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.module.impl.getModuleNameByFilePath
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.impl.source.xml.XmlFileImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileContent
import java.util.*

class SQLIdDataIndexer: DataIndexer<String, SQLIdRecord, FileContent> {
    override fun map(inputData: FileContent): MutableMap<String, SQLIdRecord> {
        val psiFile = inputData.psiFile
        val mutableMapOf = mutableMapOf<String, SQLIdRecord>()
        if(psiFile is XmlFileImpl && inputData.fileName.endsWith(XmlUtil.EXT)){
            val navigationElement = psiFile.navigationElement
            if (Objects.nonNull(navigationElement)) {
                if (Objects.nonNull(navigationElement.children)) {
                    val child = navigationElement.children[0]
                    if (Objects.nonNull(child)) {
                        if (Objects.nonNull(child.children) && child.children.size > 0) {
                            val child1 = child.children[1]
                            if (Objects.nonNull(child1)) {
                                val psiElements = child1.children
                                if (Objects.nonNull(psiElements) && psiElements.size > 0) {
                                    val xmlTags: List<XmlTag> = ArrayList(
                                        PsiTreeUtil.getChildrenOfAnyType(
                                            child1,
                                            XmlTag::class.java
                                        )
                                    )
                                    for (xmlTag in xmlTags) {
                                        if ("sql" == xmlTag.name) {
                                            val xmlAttribute: XmlAttribute? = xmlTag.getAttribute("id")
                                            val id: String? = xmlAttribute?.value
                                            if (id != null && id != "") {
                                                mutableMapOf[id] = SQLIdRecord(id, psiFile.virtualFile, ModuleUtil.findModuleForFile(psiFile)!!.name)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return mutableMapOf;
    }
}