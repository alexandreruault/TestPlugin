package com.github.alexandreruault.testplugin.manager

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.github.alexandreruault.testplugin.manager.Path.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager

enum class Path(val type: Int) {
    APP(0);

    companion object {
        private val map = values().associateBy(Path::type)
        fun from(type: Int) = map[type]
    }
}

class ProjectFileManager(
    private val project: Project,
    private val moduleData: ModuleTemplateData,
    private val packageName: String
) {

    private val path: String = moduleData.srcDir.path.removeSuffix("/java/${packageName.replace(".","/")}")

    private val virtualFiles = ProjectRootManager.getInstance(project).contentRootsFromAllModules
    private val relativePaths = listOf(path)

    private var resources : Triple<Path, PsiDirectory?, String> = Triple(APP, null, path)


    fun getPath() = resources.second!!

    fun init(): Boolean {
        relativePaths.forEachIndexed { _, path ->
            virtualFile(virtualFiles, path)?.let {
                resources = resources.copy(second = PsiManager.getInstance(project).findDirectory(it))
            }
        }
        return true
    }

    private fun virtualFile(virtualFiles: Array<VirtualFile>, relativePath: String) =
        virtualFiles.firstOrNull { it.path.contains(relativePath) }
}