package com.github.imyuyu.sqltoy.dom.model.converters

import com.github.imyuyu.sqltoy.dom.model.TranslateConfig
import com.github.imyuyu.sqltoy.dom.model.translate.Checker
import com.github.imyuyu.sqltoy.indexer.SQLIdIndexHolder
import com.github.imyuyu.sqltoy.indexer.SQLToyBeanIndexType
import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.SearchUtil
import com.intellij.codeInsight.daemon.EmptyResolveMessageProvider
import com.intellij.codeInsight.highlighting.HighlightedReference
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.pom.PomTargetPsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolvingHint
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ReflectionUtil
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xml.GenericDomValue

class SQLToyTranslateConverterImpl : SQLToyTranslateConverter() {

    override fun createReferences(
        value: GenericDomValue<String?>?,
        element: PsiElement,
        context: ConvertContext?
    ): Array<PsiReference> {
        return arrayOf<PsiReference>(SQLToyTranslateReference(element, value));
    }

    class SQLToyTranslateReference(element:PsiElement, genericDomValue:GenericDomValue<String?>?): PsiReferenceBase<PsiElement>(element, true), ResolvingHint, HighlightedReference,
        EmptyResolveMessageProvider {

        private val myGenericDomValue = genericDomValue;

        override fun resolve(): PsiElement? {
            var cacheName: String? = null;
            val checker = getTranslateCheckerBean();
            if(checker != null){
                //
                cacheName = checker.getCache().toString();
            }

            if(cacheName == null){
                val translateConfigBean = getTranslateConfigBean()
                if (translateConfigBean != null) {
                    cacheName = translateConfigBean.getCache().toString();
                }
            }

            if(cacheName == null){
                return null;
            }

            SQLIdIndexHolder.findRecordsByQualifiedId(SQLToyBeanIndexType.TRANSLATE_ID, cacheName, element.project, SearchUtil.getSearchScope(element.project, element))
                .forEach { record ->
                    record.getElements(element.project)
                        .forEach {
                            return it;
                        }
                }

            return null;
            /*val target = DomTarget.getTarget(sql);
            return PomService.convertToPsi(target!!);*/
        }

        override fun getVariants(): Array<Any> {
            val allIds = SQLIdIndexHolder.getAllTranslateIds(element.project, GlobalSearchScope.projectScope(element.project))
            return allIds.map {
                LookupElementBuilder.create(it).withIcon(Icons.XML_ICON);
            }.toTypedArray();
        }

        private fun getTranslateConfigBean(): TranslateConfig? {
            return myGenericDomValue?.getParentOfType(TranslateConfig::class.java, false)
        }

        override fun canResolveTo(elementClass: Class<out PsiElement>?): Boolean {
            return ReflectionUtil.isAssignable(
                PomTargetPsiElement::class.java,
                elementClass!!
            )
        }

        private fun getTranslateCheckerBean(): Checker? {
            return myGenericDomValue?.getParentOfType(Checker::class.java, false)
        }

        override fun getUnresolvedMessagePattern(): String {
            return "Cannot resolve translate '${value}'"
        }

        override fun bindToElement(element: PsiElement): PsiElement {
            return getElement()
        }

        override fun isHighlightedWhenSoft(): Boolean {
            return true
        }
    }
}
