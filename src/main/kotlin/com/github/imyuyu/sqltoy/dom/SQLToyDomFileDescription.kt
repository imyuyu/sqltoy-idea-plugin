package com.github.imyuyu.sqltoy.dom

import com.github.imyuyu.sqltoy.constants.SQLToyConstants
import com.github.imyuyu.sqltoy.dom.model.sql.SQLToy
import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.util.xml.DomFileDescription
import javax.swing.Icon

class SQLToyDomFileDescription : DomFileDescription<SQLToy>(SQLToy::class.java, SQLToyConstants.SQL_XML_ROOT_NAME) {

    override fun initializeFileDescription() {
        registerNamespacePolicy(
            SQLToyConstants.SQL_NAMESPACE_KEY,
            SQLToyConstants.SQL_NAMESPACE_HTTP,
            SQLToyConstants.SQL_NAMESPACE_HTTPS,
        )
    }

    override fun getFileIcon(flags: Int): Icon {
        return Icons.XML_ICON
    }
}