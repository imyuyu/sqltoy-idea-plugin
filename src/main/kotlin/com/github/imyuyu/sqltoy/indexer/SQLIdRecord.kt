package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.DelegatePsiTarget
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiTarget
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.xml.DomManager
import com.intellij.util.xml.DomTarget
import java.util.*


data class SQLIdRecord(
    val id: String,
    val myDataFile: VirtualFile?,
    val module: String
) {
    fun getRecordElements(project: Project): Collection<SQLIdRecordElement> {
        val recordElements: MutableList<SQLIdRecordElement> = mutableListOf();
        val elements: List<PsiElement> = getElements(project)
        for (element in elements) {
            var target: PsiTarget? = null
            if (element is XmlTag) {
                val domElement = DomManager.getDomManager(project).getDomElement(element as XmlTag)
                if (domElement != null) {
                    target = DomTarget.getTarget(domElement)
                }
            }
            if (target == null) {
                target = DelegatePsiTarget(element)
            }
            recordElements.add(SQLIdRecordElement(this, target))
        }
        return recordElements
    }

    fun getElements(project: Project): List<PsiElement> {
        if (myDataFile == null || !myDataFile.isValid) {
            return emptyList()
        }
        val file = PsiManager.getInstance(project).findFile(myDataFile)
            ?: return emptyList<PsiElement>()

        if(file is XmlFile){

            val fileElement = DomManager.getDomManager(project).getFileElement(file, SQLToy::class.java)
                ?: return emptyList<PsiElement>()

            val result = mutableListOf<PsiElement>()

            val sqlList = fileElement.rootElement.getSqlList()

            for (sql in sqlList) {
                val xmlTag = sql.getId().xmlAttributeValue
                if (xmlTag != null && sql.getId().value == id) {
                    result.add(xmlTag)
                };
            }

            return result;
        }

        return emptyList();
    }

    fun withDataFile(file: VirtualFile?): SQLIdRecord {
        if (Objects.equals(myDataFile, file)) {
            return this;
        }
        return SQLIdRecord(id, file, module);
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SQLIdRecord

        if (id != other.id) return false
        if (module != other.module) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + module.hashCode()
        return result
    }


}
