package com.github.imyuyu.sqltoy.facet

import com.intellij.facet.FacetType
import com.intellij.framework.detection.DetectedFrameworkDescription
import com.intellij.framework.detection.FacetBasedFrameworkDetector
import com.intellij.framework.detection.FileContentPattern
import com.intellij.framework.detection.FrameworkDetectionContext
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.patterns.ElementPattern
import com.intellij.util.indexing.FileContent

class SQLToyFrameworkDetector : FacetBasedFrameworkDetector<SQLToyFacet, SQLToyFacetConfiguration>("sqltoy", 1) {

    override fun getFacetType(): FacetType<SQLToyFacet, SQLToyFacetConfiguration> {
        return FacetType.findInstance(SQLToyFacetType::class.java)
    }

    override fun getFileType(): FileType {
        return XmlFileType.INSTANCE
    }

    override fun createSuitableFilePattern(): ElementPattern<FileContent> {
        return FileContentPattern.fileContent().xmlWithRootTag("sqltoy")
    }
}