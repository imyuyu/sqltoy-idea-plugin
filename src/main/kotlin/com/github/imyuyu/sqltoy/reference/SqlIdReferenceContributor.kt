package com.github.imyuyu.sqltoy.reference

import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.StandardPatterns.or
import com.intellij.patterns.PlatformPatterns.psiFile
import com.intellij.patterns.PlatformPatterns.string
import com.intellij.psi.*
import com.intellij.psi.util.parentOfTypes
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.psi.xml.XmlTag
import com.intellij.util.ProcessingContext
import java.util.*

@Deprecated(message = "useless")
class SqlIdReferenceContributor: PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            or(
                PlatformPatterns.psiElement(XmlAttributeValue::class.java).inFile(psiFile().withName(string().endsWith("sql.xml"))),
            ),
            object:PsiReferenceProvider() {
                override fun getReferencesByElement(element: PsiElement, processingContext: ProcessingContext): Array<PsiReference> {
                    if(!XmlUtil.isSqltoyXml(element)){
                        return PsiReference.EMPTY_ARRAY;
                    }

                    val attribute = element.parentOfTypes(XmlAttribute::class)

                    if(attribute?.name != "id"){
                        return PsiReference.EMPTY_ARRAY;
                    }

                    val project = element.getProject();
                    val parent = attribute.parentOfTypes(XmlTag::class);
                    if(parent != null && parent.name == "sql") {
                        val property: TextRange = TextRange(element.textRange.startOffset + 1, element.textRange.endOffset - 1);
                        val elementRangeInHost = InjectedLanguageManager.getInstance(project).injectedToHost(element, element.getTextRange());
                        return arrayOf(PsiSqlIdReference(element, elementRangeInHost));
                    }
                    return PsiReference.EMPTY_ARRAY;
                }
            }
        )
    }
}
