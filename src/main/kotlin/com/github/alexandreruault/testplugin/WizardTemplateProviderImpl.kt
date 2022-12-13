package com.github.alexandreruault.testplugin

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.alexandreruault.testplugin.template.presentationlayer.fragment
import com.github.alexandreruault.testplugin.template.presentationlayer.fragmentPresentationLayerTemplate
import com.github.alexandreruault.testplugin.template.presentationlayer.viewModel

class WizardTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(fragmentPresentationLayerTemplate,fragment,viewModel)
}