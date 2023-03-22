package ui.preview.widget

import domain.model.Department
import domain.model.Institute
import domain.model.Project

class ProjectFilterApplier(
    val projects: List<Project>,
    val institute: Institute,
    val department: Department
) {

    fun applyAndGet(): List<Project> {
        return projects.filter { proj->
            1==1
            //TODO: apply filters
        }
    }
}