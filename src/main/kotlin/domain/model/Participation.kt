package domain.model

import domain.model.base.Entity
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Participation(
    @PrimaryKey override val id: Int,
    val studentId: Int,
    val projectId: Int,
    val priority: Int
): Entity(), RealmObject {

    constructor() : this(0, 0, 0, 0)
}
