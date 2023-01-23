package data.local.dao

import data.local.dao.base.Dao
import data.local.dao.base.batchInsertOnDuplicateKeyUpdate
import data.local.entity.Specialty
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

object SpecialtyDao: Dao<domain.model.Specialty>(Specialty) {

    override suspend fun getAll(): List<domain.model.Specialty> {
        return newSuspendedTransaction {
            val specialities = mutableListOf<domain.model.Specialty>()
            Specialty.selectAll().forEach {
                specialities.add(
                    domain.model.Specialty(
                        id = it[Specialty.id],
                        name = it[Specialty.name]
                    )
                )
            }
            specialities
        }
    }

    override suspend fun insert(item: domain.model.Specialty) {
        newSuspendedTransaction {
            Specialty.batchInsertOnDuplicateKeyUpdate(
                item,
                listOf(Specialty.name)
            ) { batch, specialty ->
                batch[id] = specialty.id
                batch[name] = specialty.name
            }
        }
    }

    override suspend fun update(item: domain.model.Specialty) {
        newSuspendedTransaction {
            Specialty.update({ Specialty.id eq item.id }) {
                it[id] = item.id
                it[name] = item.name
            }
        }
    }
}