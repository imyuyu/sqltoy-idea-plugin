package com.github.imyuyu.sqltoy.dom

import com.github.imyuyu.sqltoy.constants.SQLToyConstants
import com.github.imyuyu.sqltoy.dom.model.translate.SQLToyTranslate
import com.github.imyuyu.sqltoy.ui.Icons
import com.intellij.util.xml.DomFileDescription
import javax.swing.Icon

class SQLToyTranslateDomFileDescription : DomFileDescription<SQLToyTranslate>(SQLToyTranslate::class.java, SQLToyConstants.TRANSLATE_XML_ROOT_NAME) {

    override fun initializeFileDescription() {
        registerNamespacePolicy(
            SQLToyConstants.TRANSLATE_NAMESPACE_KEY,
            SQLToyConstants.TRANSLATE_NAMESPACE_HTTP,
            SQLToyConstants.TRANSLATE_NAMESPACE_HTTPS,
        )
    }

    override fun getFileIcon(flags: Int): Icon? {
        return Icons.XML_ICON
    }
}