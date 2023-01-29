//package data.local.entity
//
//import data.local.entity.base.Entity
//import io.realm.kotlin.types.annotations.PrimaryKey
//
////object Project: Table() {
////    val id = integer("id")
////    val createdAt = varchar("created_at", 20)
////    val updatedAt = varchar("updated_at", 20)
////    val title = varchar("title", 200)
////    val places = integer("places")
////    val goal = varchar("goal", 400)
////    val difficulty = integer("difficulty")
////    val dateStart = varchar("date_start", 20)
////    val dateEnd = varchar("date_end", 20)
////    val customer = varchar("customer", 100)
////    val additionalInf = varchar("additional_inf", 400)
////    val productResult = varchar("product_result", 400)
////    val studyResult = varchar("study_result", 400)
////    val supervisors = varchar("supervisors", 100)
////
////    override val primaryKey = PrimaryKey(id)
////}
//
//open class Project: Entity {
//    @PrimaryKey override var id: Int = 0
//    var title: String = ""
//    var places: Int = 0
//    var goal: String = ""
//    var difficulty: Int = 0
//    var customer: String = ""
//    var productResult = ""
//    var studyResult = ""
//}