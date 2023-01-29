package domain.model

import com.google.gson.annotations.SerializedName
import domain.model.base.Entity
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Project(
    @PrimaryKey override val id: Int,
    val title: String,
    val places: Int,
    var freePlaces: Int = places,
    val goal: String?,
    val difficulty: Int,
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("date_end") val dateEnd: String,
    val customer: String?,
    @SerializedName("product_result") val productResult: String,
    @SerializedName("study_result") val studyResult: String,
    val supervisors: String,
) : Entity(), RealmObject {

    constructor() : this(
        0,
        "",
        0,
        0,
        null,
        0,
        "",
        "",
        null,
        "",
        "",
        ""
    )
}
