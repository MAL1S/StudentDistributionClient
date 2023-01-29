package domain.model

import domain.model.base.Entity
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Supervisor(
    @PrimaryKey override val id: Int,
    val name: String
): Entity(), RealmObject {

    constructor() : this(0, "")
}
