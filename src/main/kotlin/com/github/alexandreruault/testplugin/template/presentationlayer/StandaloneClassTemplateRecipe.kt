package com.github.alexandreruault.testplugin.template.presentationlayer

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.alexandreruault.testplugin.listeners.MyProjectManagerListener
import com.github.alexandreruault.testplugin.manager.ProjectFileManager
import com.github.alexandreruault.testplugin.manager.addPackageName
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createStandaloneFragment
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createStandaloneViewModel
import com.github.alexandreruault.testplugin.template.utils.asKt
import com.github.alexandreruault.testplugin.template.utils.save
import com.github.alexandreruault.testplugin.template.utils.saveClass

fun RecipeExecutor.standaloneFragmentTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = MyProjectManagerListener.projectInstance ?: return
    val diPackageName = "$packageName.di"
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneFragment(className = className).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

}

fun RecipeExecutor.standaloneViewModelTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    viewModelName: String
){
    val (projectData, _, _, manifestOut) = moduleData
    val project = MyProjectManagerListener.projectInstance ?: return
    val diPackageName = "$packageName.di"
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneViewModel(packageName =packageName, viewModelName = viewModelName ).saveClass(
        pfm.getPath(),
        packageName,
        viewModelName.asKt()
    )

}