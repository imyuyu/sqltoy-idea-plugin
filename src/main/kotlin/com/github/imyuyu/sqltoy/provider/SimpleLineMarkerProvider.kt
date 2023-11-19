package com.github.imyuyu.sqltoy.provider

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import java.util.*
import javax.swing.Icon

abstract class SimpleLineMarkerProvider<F : PsiElement?, T : PsiElement> : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        if (!isTheElement(element)) {
            return
        }
        val processResult = apply(element as F)
        if (processResult.isPresent) {
            val arrays = processResult.get()
            val navigationGutterIconBuilder: NavigationGutterIconBuilder<PsiElement> = NavigationGutterIconBuilder.create(getIcon())
            if (arrays.size > 0) {
                navigationGutterIconBuilder.setTooltipTitle(getTooltip(arrays[0], element))
            }
            navigationGutterIconBuilder.setTargets(arrays.toList())
            val lineMarkerInfo = navigationGutterIconBuilder.createLineMarkerInfo(element)
            result.add(lineMarkerInfo)
        }
    }

    /**
     * Is the element boolean.
     *
     * @param element the element
     * @return the boolean
     */
    abstract fun isTheElement(element: PsiElement): Boolean

    /**
     * Apply optional.
     *
     * @param from the from
     * @return the optional
     */
    abstract fun apply(from: F): Optional<out Array<T>>

    /**
     * Gets icon.
     *
     * @return the icon
     */
    abstract override fun getIcon(): Icon
    abstract fun getTooltip(array: T, target: PsiElement): String
}