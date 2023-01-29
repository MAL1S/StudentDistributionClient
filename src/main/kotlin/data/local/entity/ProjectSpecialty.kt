//package data.local.entity
//
//import data.local.entity.base.Entity
//import io.realm.kotlin.types.annotations.PrimaryKey
//
////object ProjectSpecialty: Table() {
////    val id = integer("id")
////    val projectId = integer("project_id").references(Project.id)
////    val specialityId = integer("speciality_id").references(Specialty.id)
////
////    override val primaryKey = PrimaryKey(id)
////}
//
//open class ProjectSpecialty: Entity {
//    @PrimaryKey
//    override var id: Int = 0
//    var project: Project = Project()
//    var specialty: Specialty = Specialty()
//}