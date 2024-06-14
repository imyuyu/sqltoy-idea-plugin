package com.github.imyuyu.sqltoy.dom.model.converters

import com.github.imyuyu.sqltoy.dom.model.sql.Sql
import com.intellij.pom.PomTargetPsiElement
import com.intellij.pom.references.PomService
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolvingHint
import com.intellij.util.ReflectionUtil
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xml.DomTarget
import com.intellij.util.xml.GenericDomValue

class SQLToySqlIdConverterImpl : SQLToySqlIdConverter() {

    override fun createReferences(
        value: GenericDomValue<String?>?,
        element: PsiElement,
        context: ConvertContext?
    ): Array<PsiReference> {
        return arrayOf<PsiReference>(SQLIdReference(element, value));
    }

    class SQLIdReference(element:PsiElement, genericDomValue:GenericDomValue<String?>?): PsiReferenceBase<PsiElement>(element, true), ResolvingHint  {

        private val myGenericDomValue = genericDomValue;

        override fun resolve(): PsiElement? {
            val sql = getSQLIdBean();
            if(sql == null){
                return null;
            }
            val target = DomTarget.getTarget(sql);
            return PomService.convertToPsi(target!!);
        }

        override fun canResolveTo(elementClass: Class<out PsiElement>?): Boolean {
            return ReflectionUtil.isAssignable(
                PomTargetPsiElement::class.java,
                elementClass!!
            )
        }

        private fun getSQLIdBean(): Sql? {
            return myGenericDomValue?.getParentOfType<Sql>(Sql::class.java, false)
        }
    }
}
