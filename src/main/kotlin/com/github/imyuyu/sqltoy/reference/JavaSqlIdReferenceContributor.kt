package com.github.imyuyu.sqltoy.reference

import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.xml.util.XmlUtil

@Deprecated(message = "unused")
class JavaSqlIdReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        val provider = SqlIdReferenceProvider()
        val xmlAttrs = arrayOf("id")
        val xmlFilter = SqlIdReferenceProvider.filter
        XmlUtil
            .registerXmlAttributeValueReferenceProvider(
                registrar,
                xmlAttrs,
                xmlFilter,
                false,
                provider,
                PsiReferenceRegistrar.HIGHER_PRIORITY
            )
    }
}
