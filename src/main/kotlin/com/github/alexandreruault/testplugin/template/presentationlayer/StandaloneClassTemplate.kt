package com.github.alexandreruault.testplugin.template.presentationlayer

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template

val fragment
    get() = template {
        name = "Fragment Standalone"
        description = "Create only the Fragment"
        minApi = 21
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val classNameParam = stringParameter {
            name = "Fragment Name"
            default = "Fragment"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(classNameParam),
        )

        recipe = { data: TemplateData ->
            standaloneFragmentTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                classNameParam.value
            )
        }
    }


val viewModel
    get() = template {
        name = "ViewModel Standalone"
        description = "Create only a viewModel"
        minApi = 21
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val viewModelName = stringParameter {
            name = "ViewModel Name"
            default = "ViewModel"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(viewModelName),
        )

        recipe = { data: TemplateData ->
            standaloneViewModelTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                viewModelName.value
            )
        }
    }

