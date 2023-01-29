package domain.model

import data.local.entity.base.Entity
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Student(
    @PrimaryKey override var id: Int,
    var name: String,
    var group: String,
    var shouldDistribute: Boolean,
    var specialtyId: Int
): Entity(), RealmObject {

    constructor(): this(0, "", "", true, 0)
}
