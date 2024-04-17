package com.github.imyuyu.sqltoy.facet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.facet.FacetTypeId
import com.intellij.facet.FacetTypeRegistry
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.NlsSafe

class SQLToyFacet(
    facetType: FacetType<out Facet<*>, *>,
    module: Module,
    name: @NlsSafe String,
    configuration: SQLToyFacetConfiguration,
    underlyingFacet: Facet<*>?
) : Facet<SQLToyFacetConfiguration?>(facetType, module, name, configuration, underlyingFacet) {
    companion object {
        val FACET_TYPE_ID: FacetTypeId<SQLToyFacet> = FacetTypeId("sqltoy")

        val sqltoyFacetType: FacetType<SQLToyFacet, SQLToyFacetConfiguration?>
            get() = FacetTypeRegistry.getInstance().findFacetType(FACET_TYPE_ID)
    }
}