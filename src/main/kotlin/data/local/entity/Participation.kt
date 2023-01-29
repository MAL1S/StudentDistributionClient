//package data.local.entity
//
//import data.local.entity.base.Entity
//import io.realm.kotlin.types.annotations.PrimaryKey
//
////object Participation: Table() {
////    val id = integer("id")
////    val studentId = integer("student_id").references(Student.id)
////    val projectId = integer("project_id").references(Project.id)
////    val priority = integer("priority")
////
////    override val primaryKey = PrimaryKey(id)
////}
//
//open class Participation: Entity() {
//    @PrimaryKey override var id: Int = 0
//    var student: Student = Student()
//    var project: Project = Project()
//    var priority: Int = 0
//}