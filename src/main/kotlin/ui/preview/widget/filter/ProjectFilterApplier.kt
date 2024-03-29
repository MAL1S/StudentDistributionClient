package ui.preview.widget.filter

import domain.model.Department
import domain.model.Institute
import domain.model.Project

object ProjectFilterApplier {

    fun applyAndGet(
        projects: List<Project>,
        institute: Institute?,
        department: Department?
    ): List<Project> {
        if (institute == null) return projects
        else if (department == null) {
            return projects.filter { proj ->
                proj.department?.institute?.id == institute.id
            }
        }
        return projects.filter { proj->
            proj.department?.id == department.id
        }
    }
}