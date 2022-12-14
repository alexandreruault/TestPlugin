package com.github.alexandreruault.testplugin.template.standalone

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.alexandreruault.testplugin.listeners.MyProjectManagerListener
import com.github.alexandreruault.testplugin.manager.ProjectFileManager
import com.github.alexandreruault.testplugin.manager.addPackageName
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationLayoutXML
import com.github.alexandreruault.testplugin.template.standalone.klass.createStandaloneActivity
import com.github.alexandreruault.testplugin.template.standalone.klass.createStandaloneFragment
import com.github.alexandreruault.testplugin.template.standalone.klass.createStandaloneViewModel
import com.github.alexandreruault.testplugin.template.utils.asKt
import com.github.alexandreruault.testplugin.template.utils.asXml
import com.github.alexandreruault.testplugin.template.utils.saveClass
import com.github.alexandreruault.testplugin.template.utils.saveXML

fun RecipeExecutor.standaloneFragmentTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    layoutName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = MyProjectManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneFragment(className = className, layountName = layoutName).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createPresentationLayoutXML().saveXML(pfm.getPath(),layoutName.asXml())

}

fun RecipeExecutor.standaloneViewModelTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    viewModelName: String
){
    val (projectData, _, _, manifestOut) = moduleData
    val project = MyProjectManagerListener.projectInstance ?: return
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
fun RecipeExecutor.standaloneActivityTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityName: String,
    layoutName: String
){
    val (projectData, _, _, manifestOut) = moduleData
    val project = MyProjectManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneActivity(packageName =packageName, activityName = activityName, layoutName = layoutName).saveClass(
        pfm.getPath(),
        packageName,
        activityName.asKt()
    )
    createPresentationLayoutXML().saveXML(pfm.getPath(),layoutName.asXml())


}

