package com.github.imyuyu.sqltoy.indexer

import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiTarget
import com.intellij.psi.impl.PomTargetPsiElementImpl
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag
import com.intellij.util.IncorrectOperationException
import java.util.*

class SQLIdRecordElement(
    record: SQLIdRecord,
    target: PsiTarget
) : PomTargetPsiElementImpl(target), NavigatablePsiElement {
    private val myRecord: SQLIdRecord = record

    val record: SQLIdRecord
        get() = myRecord

    override fun getName(): String {
        return myRecord.id;
    }

    override fun getPresentableText(): String? {
        if (navigationElement is PsiJavaFile) {
            val presentation: ItemPresentation? = (navigationElement as PsiJavaFile).presentation
            if (presentation != null) {
                return presentation.presentableText
            }
        }
        var text: String = myRecord.id
        if (StringUtil.isNotEmpty(myRecord.module)) {
            text += (" (" + myRecord.module) + ")"
        }
        return text
    }

    override fun getLocationString(): String? {
        return VfsUtil.getRelativePath(myRecord.myDataFile!!, myRecord.myDataFile.parent);
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        if (navigationElement is XmlTag) {
            val attribute: XmlAttribute? = (navigationElement as XmlTag).getAttribute("id")
            if (attribute != null) {
                attribute.setValue(name)
                return attribute
            }
        }
        return super.setName(name)
    }

    override fun isEquivalentTo(another: PsiElement): Boolean {
        return if (another is SQLIdRecordElement &&
            myRecord.id == another.myRecord.id
        ) {
            true
        } else super.isEquivalentTo(another)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as SQLIdRecordElement
        return myRecord == that.myRecord && target == that.target
    }

    override fun hashCode(): Int {
        return Objects.hash(myRecord, target)
    }
}