package common.mapper

import domain.Department
import domain.model.Institute

fun Institute.makeString(): String {
    return this.name
}

fun Department.makeString(): String {
    return this.name
}