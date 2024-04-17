package com.github.imyuyu.sqltoy.facet

import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.openapi.module.JavaModuleType
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import javax.swing.Icon

class SQLToyFacetType internal constructor() :
    FacetType<SQLToyFacet, SQLToyFacetConfiguration>(SQLToyFacet.Companion.FACET_TYPE_ID, "SQLToy", "SQLToy") {

    override fun getIcon(): Icon? {
        return Icons.XML_ICON
    }

    override fun createDefaultConfiguration(): SQLToyFacetConfiguration {
        return SQLToyFacetConfigurationImpl()
    }

    override fun createFacet(
        module: Module,
        name: String,
        configuration: SQLToyFacetConfiguration,
        underlyingFacet: Facet<*>?
    ): SQLToyFacet {
        return SQLToyFacet(this, module, name, configuration, underlyingFacet)
    }

    override fun isSuitableModuleType(moduleType: ModuleType<*>?): Boolean {
        return moduleType is JavaModuleType
    }
}