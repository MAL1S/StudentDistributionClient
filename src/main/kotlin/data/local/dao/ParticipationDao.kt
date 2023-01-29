package data.local.dao

//class ParticipationDao @Inject constructor(
//    realm: Realm
//): Dao<Participation>(realm)

//object ParticipationDao: Dao<domain.model.Participation>(Participation) {
//
//    override suspend fun getAll(): List<domain.model.Participation> {
//        return newSuspendedTransaction {
//            val participations = mutableListOf<domain.model.Participation>()
//            Participation.selectAll().forEach {
//                participations.add(
//                    domain.model.Participation(
//                        id = it[Participation.id],
//                        studentId = it[Participation.studentId],
//                        projectId = it[Participation.projectId],
//                        priority = it[Participation.priority],
//                    )
//                )
//            }
//            participations
//        }
//    }
//
//    override suspend fun insert(item: domain.model.Participation) {
//        newSuspendedTransaction {
//            Participation.batchInsertOnDuplicateKeyUpdate(
//                item,
//                listOf(
//                    Participation.studentId,
//                    Participation.projectId,
//                    Participation.priority
//                )
//            ) { batch, participation ->
//                batch[id] = participation.id
//                batch[studentId] = participation.studentId
//                batch[projectId] = participation.projectId
//                batch[priority] = participation.priority
//            }
//        }
//    }
//
//    override suspend fun update(item: domain.model.Participation) {
//        newSuspendedTransaction {
//            Participation.update({ Participation.id eq item.id }) {
//                it[id] = item.id
//                it[studentId] = item.studentId
//                it[projectId] = item.projectId
//                it[priority] = item.priority
//            }
//        }
//    }
//}