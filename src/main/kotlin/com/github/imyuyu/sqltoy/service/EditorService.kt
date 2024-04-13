package com.github.imyuyu.sqltoy.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class EditorService(private val project: Project) {

    private val fileEditorManager: FileEditorManager = FileEditorManager.getInstance(project)

    companion object {
        /**
         * Gets instance.
         *
         * @param project the project
         * @return the instance
         */
        fun getInstance(project: Project): EditorService {
            return project.getService(EditorService::class.java)
        }
    }
}