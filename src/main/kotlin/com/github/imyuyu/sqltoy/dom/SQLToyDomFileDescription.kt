package com.github.imyuyu.sqltoy.dom

import com.github.imyuyu.sqltoy.dom.model.SQLToy
import com.intellij.util.xml.DomFileDescription

class SQLToyDomFileDescription : DomFileDescription<SQLToy>(SQLToy::class.java, "sqltoy") {

    override fun initializeFileDescription() {
        registerNamespacePolicy(
            "SQLToyXml",
            "http://www.sagframe.com/schema/sqltoy",
            "http://www.w3.org/2001/XMLSchema-instance",
            "http://www.sagframe.com/schema/sqltoy/sqltoy.xsd"
        )
    }

}