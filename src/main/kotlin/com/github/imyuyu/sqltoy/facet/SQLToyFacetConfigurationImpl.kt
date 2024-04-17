package com.github.imyuyu.sqltoy.facet

import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager

class SQLToyFacetConfigurationImpl : SQLToyFacetConfiguration {
    override fun createEditorTabs(
        editorContext: FacetEditorContext,
        validatorsManager: FacetValidatorsManager
    ): Array<FacetEditorTab?> {
        return arrayOfNulls(0)
    }
}