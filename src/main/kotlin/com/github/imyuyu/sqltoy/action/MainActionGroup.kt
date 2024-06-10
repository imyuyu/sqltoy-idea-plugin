package com.github.imyuyu.sqltoy.action

import com.intellij.database.psi.DbTable
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.project.Project


/**
 * create java code
 */
class MainActionGroup : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project: Project = event.getProject() ?: return

        //获取选中的PSI元素
        val psiElement = event.getData(LangDataKeys.PSI_ELEMENT)
        var selectDbTable: DbTable? = null
        if (psiElement is DbTable) {
            selectDbTable = psiElement
        }
        if (selectDbTable == null) {
            return;
        }

        //获取选中的所有表
        val psiElements = event.getData(LangDataKeys.PSI_ELEMENT_ARRAY)
        if (psiElements.isNullOrEmpty()) {
            return;
        }

        val dbTableList: MutableList<DbTable> = ArrayList()
        for (element in psiElements) {
            if (element !is DbTable) {
                continue
            }
            dbTableList.add(element)
        }
        if (dbTableList.isEmpty()) {
            return;
        }

        // 弹出form处理
    }
}