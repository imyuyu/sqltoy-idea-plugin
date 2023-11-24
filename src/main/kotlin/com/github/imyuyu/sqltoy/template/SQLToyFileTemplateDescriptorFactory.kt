package com.github.imyuyu.sqltoy.template

import com.github.imyuyu.sqltoy.ui.Icons.XML_ICON
import com.intellij.ide.fileTemplates.FileTemplateDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory

/**
 * The type SQLToy file template descriptor factory.
 *
 */
class SQLToyFileTemplateDescriptorFactory : FileTemplateGroupDescriptorFactory {
    override fun getFileTemplatesDescriptor(): FileTemplateGroupDescriptor {
        val group = FileTemplateGroupDescriptor("SQLToy", XML_ICON)
        group.addTemplate(FileTemplateDescriptor(SQLTOY_SQL_XML_TEMPLATE, XML_ICON))
        group.addTemplate(FileTemplateDescriptor(SQLTOY_TRANSLATE_XML_TEMPLATE, XML_ICON))
        return group
    }

    companion object {
        /**
         * The constant SQLTOY_SQL_XML_TEMPLATE.
         */
        const val SQLTOY_SQL_XML_TEMPLATE = "SQLToy Sql.xml"
        const val SQLTOY_TRANSLATE_XML_TEMPLATE = "SQLToy Translate.xml"
    }
}