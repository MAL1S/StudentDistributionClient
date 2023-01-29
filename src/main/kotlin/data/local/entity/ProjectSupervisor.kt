//package data.local.entity
//
//import data.local.entity.base.Entity
//import io.realm.kotlin.types.annotations.PrimaryKey
//
////object ProjectSupervisor: Table() {
////    val id = integer("id")
////    val projectId = integer("project_id").references(Project.id)
////    val supervisorId = integer("supervisor_id").references(Supervisor.id)
////
////    override val primaryKey = PrimaryKey(id)
////}
//
//open class ProjectSupervisor: Entity{
//    @PrimaryKey override var id: Int = 0
//    var project: Project = Project()
//    var supervisor: Supervisor = Supervisor()
//}