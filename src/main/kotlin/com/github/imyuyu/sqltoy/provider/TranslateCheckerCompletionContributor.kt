package com.github.imyuyu.sqltoy.provider

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.spring.model.SpringBeanPointer
import com.intellij.spring.model.converters.SpringBeanResolveConverter
import com.intellij.spring.model.converters.SpringCompletionContributor
import com.intellij.spring.model.converters.SpringConverterUtil

class TranslateCheckerCompletionContributor : CompletionContributor() {

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        if (parameters.completionType == CompletionType.SMART) {
            ApplicationManager.getApplication().runReadAction {
                // 获取所有缓存翻译
            }
        }
    }
}