package com.github.imyuyu.sqltoy.util

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.github.imyuyu.sqltoy.indexer.SQLIdRecord
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.xml.XmlFileImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.xml.DomManager
import java.util.*


object XmlUtil {
    const val EXT: String = "sql.xml";

    fun findXmlPsiElement(project: Project, virtualFiles: Collection<VirtualFile>, key: String): List<PsiElement> {
        val result: MutableList<PsiElement> = mutableListOf();
        for (virtualFile in virtualFiles) {
            val psiFile = PsiManager.getInstance(project).findFile(virtualFile)
            if(psiFile !is XmlFile){
                return result;
            }
            val domManager = DomManager.getDomManager(project)
            val fileElement = domManager.getFileElement(psiFile, SQLToy::class.java) ?: return result

            val sqlList = fileElement.rootElement.getSqlList()
            for (sql in sqlList) {
                val id = sql.getId().stringValue?.trim()
                if (id != null && id != "") {
                    if (key == id) {
                        result.add(sql.getId().xmlAttribute!!)
                    }
                }
            }
        }
        return result
    }

    fun isSqltoyXml(element:PsiElement): Boolean {
        return isXmlFile(element.containingFile) && element.containingFile.name.endsWith(EXT);
    }

    fun isSqltoyXml(file: VirtualFile): Boolean {
        return FileTypeRegistry.getInstance().isFileOfType(file, XmlFileType.INSTANCE) && file.name.endsWith(EXT);
    }

    /**
     * 判断是否是xml文件
     *
     * @param file
     * @return
     */
    private fun isXmlFile(file: PsiFile): Boolean {
        return file is XmlFile
    }


}