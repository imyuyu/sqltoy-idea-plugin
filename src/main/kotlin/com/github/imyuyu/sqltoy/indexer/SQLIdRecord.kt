package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.github.imyuyu.sqltoy.dom.model.Sql
import com.github.imyuyu.sqltoy.dom.model.translate.Translate
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.DelegatePsiTarget
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTarget
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtil
import com.intellij.psi.xml.XmlTag
import com.intellij.testFramework.utils.vfs.getPsiFile
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.DomManager
import com.intellij.util.xml.DomTarget
import java.util.*


data class SQLIdRecord(
    val id: Pair<SQLToyBeanIndexType, String>,
    val myDataFile: VirtualFile?,
    val module: String,
    val offset: Int
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

    /**
     * 获取对应元素
     */
    fun getElements(project: Project): List<PsiElement> {
        if (myDataFile == null || !myDataFile.isValid) {
            return emptyList()
        }

        val domManager = DomManager.getDomManager(project)

        val file = PsiUtil.getPsiFile(project, myDataFile);

        val psiElement: PsiElement? = file.findElementAt(offset);

        val domElement: DomElement? = domManager.getDomElement(
            PsiTreeUtil.getParentOfType(
                psiElement,
                XmlTag::class.java, false
            )
        )

        if (domElement == null || (id.first == SQLToyBeanIndexType.SQL_ID && domElement !is Sql) || (id.first == SQLToyBeanIndexType.TRANSLATE_ID && domElement !is Translate)) {
            return emptyList();
        }

        val result = mutableListOf<PsiElement>()

        result.add(domElement.xmlElement!!);

        return result;
    }

    fun withDataFile(file: VirtualFile?): SQLIdRecord {
        if (Objects.equals(myDataFile, file)) {
            return this;
        }
        return SQLIdRecord(id, file, module, offset);
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SQLIdRecord

        if (id != other.id) return false
        if (module != other.module) return false
        if (offset != other.offset) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + module.hashCode()
        return result
    }


}
