//package data.local.entity
//
//import data.local.entity.base.Entity
//import io.realm.kotlin.types.annotations.PrimaryKey
//
////object Student: Table() {
////    val id = integer("id")
////    val name = varchar("name", 100)
////    val group = varchar("group", 10)
////    var shouldDistribute = bool("should_distribute")
////    val specialtyId = integer("specialty_id").references(Specialty.id)
////
////    override val primaryKey = PrimaryKey(id)
////}
//
//open class Student: Entity {
//    @PrimaryKey override var id: Int = 0
//    val name: String = ""
//    val group: String = ""
//    var shouldDistribute: Boolean = true
//    val specialty: Specialty = Specialty()
//}