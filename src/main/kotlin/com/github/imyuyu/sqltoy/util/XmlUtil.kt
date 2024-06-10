package com.github.imyuyu.sqltoy.util

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.xml.XmlFile
import com.intellij.util.xml.DomManager


object XmlUtil {
    const val SQL_FILE_SUFFIX: String = "sql.xml";
    const val TRANSLATE_FILE_SUFFIX: String = "translate.xml";

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
        return isXmlFile(element.containingFile) && element.containingFile.name.endsWith(SQL_FILE_SUFFIX)
    }

    fun isTranslateXml(element:PsiElement): Boolean {
        return isXmlFile(element.containingFile) && element.containingFile.name.endsWith(TRANSLATE_FILE_SUFFIX)
    }

    fun isSqltoyXml(file: VirtualFile): Boolean {
        return FileTypeRegistry.getInstance().isFileOfType(file, XmlFileType.INSTANCE) && (
                file.name.endsWith(SQL_FILE_SUFFIX) || file.name.endsWith(TRANSLATE_FILE_SUFFIX)
                )
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