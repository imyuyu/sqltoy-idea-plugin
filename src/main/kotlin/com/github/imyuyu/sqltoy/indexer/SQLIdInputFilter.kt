package com.github.imyuyu.sqltoy.indexer

import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.indexing.DefaultFileTypeSpecificInputFilter

object SQLIdInputFilter: DefaultFileTypeSpecificInputFilter(XmlFileType.INSTANCE) {

    override fun acceptInput(file: VirtualFile): Boolean {

        if (!XmlUtil.isSqltoyXml(file)) {
            return false
        }

        if (file.isInLocalFileSystem) {
            return true
        }
        return false;
    }

}