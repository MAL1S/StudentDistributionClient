package data.local.dao.base

import data.local.entity.base.Entity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow


abstract class Dao<T: Entity>(val realm: Realm) {

    inline fun <reified R: Entity> getAll(): Flow<ResultsChange<R>> {
        return realm.query(R::class).asFlow()
    }

    suspend fun insert(item: T) {
        realm.writeBlocking {
            copyToRealm(item, UpdatePolicy.ALL)
        }
    }

    suspend fun insert(items: List<T>) {
        items.forEach {
            insert(it)
        }
    }

    suspend inline fun <reified R: Entity> delete(item: T) {
        realm.writeBlocking {
            delete(this.query(R::class, "id == ${item.id}").find().first())
        }
    }

    suspend inline fun <reified R: Entity> deleteAll() {
        realm.writeBlocking {
            delete(this.query(R::class))
        }
    }
}

//abstract class Dao<T>(private val tableObject: Table) {
//    abstract suspend fun getAll(): List<T>
//    abstract suspend fun insert(item: T)
//    suspend fun insert(items: List<T>) {
//        newSuspendedTransaction {
//            items.forEach {
//                insert(it)
//            }
//        }
//    }
//
//    abstract suspend fun update(item: T)
//    suspend fun delete(item: T) {
//        newSuspendedTransaction {
//            tableObject.deleteWhere {
//                (this as Entity).id eq (item as Entity).id
//            }
//        }
//    }
//
//    suspend fun deleteAll() {
//        newSuspendedTransaction {
//            tableObject.deleteAll()
//        }
//    }
//    //protected abstract suspend fun insertOrUpdate(it: UpdateBuilder<Number>, item: T)
//}
//
//class BatchInsertUpdateOnDuplicate(table: Table, val onDupUpdate: List<Column<*>>) :
//    BatchInsertStatement(table, false) {
//    override fun prepareSQL(transaction: Transaction): String {
//        //println(onDupUpdate.joinToString { "${transaction.identity(it)}=${transaction.identity(it)}"})
//        val onUpdateSQL = if (onDupUpdate.isNotEmpty()) {
//            " ON CONFLICT(id) DO UPDATE SET " + onDupUpdate.joinToString {
//                "${transaction.identity(it)}=excluded.${
//                    transaction.identity(
//                        it
//                    )
//                }"
//            }
//        } else ""
//        return super.prepareSQL(transaction) + onUpdateSQL
//    }
//}
//
//fun <T : Table, E> T.batchInsertOnDuplicateKeyUpdate(
//    item: E,
//    onDupUpdateColumns: List<Column<*>>,
//    body: T.(BatchInsertUpdateOnDuplicate, E) -> Unit,
//) {
//    item?.let {
//        val insert = BatchInsertUpdateOnDuplicate(this, onDupUpdateColumns)
//
//        insert.addBatch()
//        body(insert, it)
//
//        TransactionManager.current().exec(insert)
//    }
//}