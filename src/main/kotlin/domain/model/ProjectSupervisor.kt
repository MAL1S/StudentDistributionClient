package domain.model

import domain.model.base.Entity
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class ProjectSupervisor(
    @PrimaryKey override val id: Int,
    val projectId: Int,
    val supervisorId: Int
): Entity(), RealmObject {

    constructor() : this(0, 0, 0)
}
