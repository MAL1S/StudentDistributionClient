package common.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object DatabaseConnection {

    private var connectedDatabase: Database? = null

    fun connect() {
        if (connectedDatabase == null) {
            TransactionManager.manager.defaultIsolationLevel =
                Connection.TRANSACTION_SERIALIZABLE
            connectedDatabase = Database.connect(
                url = "jdbc:sqlite:database/StudentDistributionDB.db",
                driver = "org.sqlite.JDBC",
                user = "root",
                password = "root"
            )
        }
        transaction {
            addLogger(StdOutSqlLogger)
        }
    }
}