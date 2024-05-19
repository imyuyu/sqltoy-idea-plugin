package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.dom.model.Sql
import com.github.imyuyu.sqltoy.dom.model.ValueElement
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.database.console.JdbcConsole
import com.intellij.database.console.JdbcConsoleProvider
import com.intellij.database.dataSource.LocalDataSourceManager
import com.intellij.database.editor.DatabaseEditorHelper
import com.intellij.icons.AllIcons
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlToken
import com.intellij.sql.child
import com.intellij.util.PsiNavigateUtil
import com.intellij.util.xml.DomManager


class RunSqlLineMarkerProvider : LineMarkerProviderDescriptor() {

    override fun getName(): String {
        return "run sql"
    }

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<XmlToken>? {
        val project = element.project

        if (LocalDataSourceManager.getInstance(project).dataSources.isEmpty()) {
            return null;
        };

        if(element is XmlTag
            && element.containingFile.name.endsWith("sql.xml")
            && element.name == "value"
            && DomManager.getDomManager(element.project).getDomElement(element)?.parent is Sql){
            return LineMarkerInfo(
                element.child<XmlToken>()!!,
                element.textRange,
                AllIcons.Actions.Execute,
                { "Run SQL" },
                { e, elt -> runSql(element) },
                GutterIconRenderer.Alignment.CENTER,
                { "Run SQL"}
            )
        }
        return null;
    }

    private fun runSql(e: XmlTag) {
        val project = e.project
        val sql = (DomManager.getDomManager(project).getDomElement(e) as ValueElement).getValue()

        val dataSource = LocalDataSourceManager.getInstance(project).dataSources[0]

        val consoleVirtualFile = DatabaseEditorHelper.getConsoleVirtualFile(dataSource.dataSource)

        var console = JdbcConsoleProvider.getConsole(project, consoleVirtualFile!!)
        if (console == null) {
            console = JdbcConsole.newConsole(project)
                .forFile(consoleVirtualFile)
                .fromDataSource(dataSource)
                .build();
        }

        WriteCommandAction.runWriteCommandAction(project) {
            val document = console.consoleView.currentEditor.document;
            val currentText = document.text
            val newText = if (currentText.isBlank()) {
                sql
            } else {
                "$currentText\n\n$sql"
            }
            document.setText(newText!!);
        }

        PsiNavigateUtil.navigate(console.file);
    }
}