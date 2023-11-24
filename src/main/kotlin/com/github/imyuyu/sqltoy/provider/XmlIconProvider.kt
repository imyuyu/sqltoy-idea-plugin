package com.github.imyuyu.sqltoy.provider

import com.github.imyuyu.sqltoy.ui.Icons
import com.github.imyuyu.sqltoy.util.XmlUtil
import com.intellij.ide.IconProvider
import com.intellij.psi.PsiElement
import javax.swing.Icon

class XmlIconProvider:IconProvider() {
    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        if(element.containingFile == null){
            return null;
        }

        if(XmlUtil.isSqltoyXml(element)){
            return Icons.XML_ICON;
        }
        return null;
    }
}