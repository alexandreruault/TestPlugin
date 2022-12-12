package com.github.alexandreruault.testplugin.services

import com.intellij.openapi.project.Project
import com.github.alexandreruault.testplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }

    /**
     * Chosen by fair dice roll, guaranteed to be random.
     */
}
