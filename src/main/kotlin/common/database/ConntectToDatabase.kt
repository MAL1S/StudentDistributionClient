package common.database

import org.jetbrains.exposed.sql.Database

object DatabaseConnection {

    private var connectedDatabase: Database? = null

    fun connect() {
//        if (connectedDatabase == null) {
//            TransactionManager.manager.defaultIsolationLevel =
//                Connection.TRANSACTION_SERIALIZABLE
//            connectedDatabase = Database.connect(
//                url = "jdbc:sqlite:database/StudentDistributionDB.db",
//                driver = "org.sqlite.JDBC",
//                user = "root",
//                password = "root"
//            )
//        }
//        runBlocking {
//            newSuspendedTransaction {
//                addLogger(StdOutSqlLogger)
//                SchemaUtils.create(
//                    data.local.entity.Specialty,
//                    data.local.entity.Student,
//                    data.local.entity.Project,
//                    data.local.entity.Participation,
//                    data.local.entity.Supervisor,
//                    data.local.entity.ProjectSupervisor,
//                    data.local.entity.ProjectSpecialty,
//                    data.local.entity.GeneratedDistribution,
//                )
//            }
//        }
    }
}