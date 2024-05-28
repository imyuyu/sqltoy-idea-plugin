package com.github.imyuyu.sqltoy.dom

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.github.imyuyu.sqltoy.dom.model.translate.SQLToyTranslate
import com.intellij.util.xml.DomFileDescription

class SQLToyTranslateDomFileDescription : DomFileDescription<SQLToyTranslate>(SQLToyTranslate::class.java, "sagacity") {

    override fun initializeFileDescription() {
        registerNamespacePolicy(
            "SQLToyTranslateXml",
            "https://www.sagframe.com/schema/sqltoy-translate",
            "https://www.w3.org/2001/XMLSchema-instance",
            "https://www.sagframe.com/schema/sqltoy/sqltoy-translate.xsd"
        )
    }

}