package com.github.imyuyu.sqltoy.action

import com.github.imyuyu.sqltoy.template.SQLToyFileTemplateDescriptorFactory
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.ui.Icons.XML_ICON
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile

class CreateSQLToyXmlAction : CreateFileFromTemplateAction(
    "SQLToy File",
    "Create SQLToy Sql File OR Translate File",
    IconLoader.getIcon("/icons/sqltoy_xml.svg", CreateSQLToyXmlAction::class.java)
) {
    override fun buildDialog(
        project: Project, directory: PsiDirectory,
        builder: CreateFileFromTemplateDialog.Builder
    ) {
        builder
            .setTitle("SQLToy File")
            .addKind("SQL XML", XML_ICON, SQLToyFileTemplateDescriptorFactory.SQLTOY_SQL_XML_TEMPLATE)
            .addKind("Translate XML", XML_ICON, SQLToyFileTemplateDescriptorFactory.SQLTOY_TRANSLATE_XML_TEMPLATE)
    }

    override fun createFile(name: String?, templateName: String?, dir: PsiDirectory?): PsiFile? {
        var realName = removeSqltoyExtensionIfPresent(name!!);
        if(templateName == SQLToyFileTemplateDescriptorFactory.SQLTOY_SQL_XML_TEMPLATE){
            realName += ".sql";
        }else if(templateName == SQLToyFileTemplateDescriptorFactory.SQLTOY_TRANSLATE_XML_TEMPLATE){
            realName += "-translate";
        }
        return super.createFile(realName, templateName, dir)
    }

    override fun getActionName(
        directory: PsiDirectory,
        newName: String, templateName: String
    ): String {
        return "Create SQLToy File: $newName"
    }

    private fun removeSqltoyExtensionIfPresent(name: String): String = when {
        name.endsWith(".sql.xml") -> name.removeSuffix(".sql.xml")
        name.endsWith("-translate.xml") -> name.removeSuffix("-translate.xml")
        name.endsWith(".sql") -> name.removeSuffix(".sql")
        else -> name
    }
}