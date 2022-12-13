package com.github.alexandreruault.testplugin.template.presentationlayer

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.alexandreruault.testplugin.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.alexandreruault.testplugin.manager.Path
import com.github.alexandreruault.testplugin.manager.ProjectFileManager
import com.github.alexandreruault.testplugin.manager.addPackageName
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationFragment
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationModule
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationSubComponent
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationViewModel
import com.github.alexandreruault.testplugin.template.presentationlayer.klass.createPresentationViewModelFactory
import com.github.alexandreruault.testplugin.template.utils.asKt
import com.github.alexandreruault.testplugin.template.utils.save

fun RecipeExecutor.fragmentPresentationLayerTemplate(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    viewModelName: String,
    subComponentName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return
    val diPackageName = "$packageName.di"
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createPresentationFragment(className = className, viewModelName = viewModelName,subComponentName= subComponentName).save(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createPresentationViewModel(presentationPackageName = packageName, viewModelName).save(
        pfm.getPath(),
        packageName,
        viewModelName.asKt()
    )
    createPresentationViewModelFactory(presentationPackageName = packageName, viewModelName).save(
        pfm.getPath(),
        packageName,
        "${viewModelName}Factory".asKt()
    )
    createPresentationSubComponent(
        presentationPackageName = packageName,
        injectionPackageName = diPackageName,
        viewModelName,
        subComponentName,
        className
    ).save(pfm.getPath(), diPackageName, subComponentName.asKt())

    createPresentationModule(
        presentationPackageName = packageName,
        injectionPackageName = diPackageName,
        viewModelName,
        subComponentName,
        className
    ).save(pfm.getPath(), diPackageName, "${viewModelName}Module".asKt())



    // createActivity(className = className, manifestOut = manifestOut, moduleData = moduleData)
    //     .save(pfm.dirOf(APP), packageName, "${className}Activity".asKt())
    //
    // createViewModel(className = className)
    //     .save(pfm.dirOf(APP), packageName, "${className}ViewModel".asKt())
    //
    // createPresentation(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.presentationPackageName, "${className}Presentation".asKt())
    //
    // createAction(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.presentationPackageName, "${className}Action".asKt())
    //
    // createMiddleware(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.presentationPackageName, "${className}Middleware".asKt())
    //
    // createReducer(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.presentationPackageName, "${className}Reducer".asKt())
    //
    // createDatasource(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.datasourcePackageName, "${className}Datasource".asKt())
    //
    // createRepository(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.repositoryPackageName, "${className}Repository".asKt())
    //
    // createIDatasource(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.repositoryPackageName, "I${className}Datasource".asKt())
    //
    // createUsecase(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.domainPackageName, "${className}Usecase".asKt())
    //
    // createIRepository(className = className)
    //     .save(pfm.dirOf(APP), PackageManager.domainPackageName, "I${className}Repository".asKt())
}