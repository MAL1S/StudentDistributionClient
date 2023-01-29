package data.local.dao

//class ProjectSpecialityDao @Inject constructor(
//    realm: Realm
//): Dao<ProjectSpecialty>(realm)

//object ProjectSpecialityDao: Dao<domain.model.ProjectSpeciality>(ProjectSpecialty) {
//
//    override suspend fun getAll(): List<domain.model.ProjectSpeciality> {
//        return newSuspendedTransaction {
//            val projectSpecialities = mutableListOf<domain.model.ProjectSpeciality>()
//            ProjectSpecialty.selectAll().forEach {
//                projectSpecialities.add(
//                    domain.model.ProjectSpeciality(
//                        id = it[ProjectSpecialty.id],
//                        projectId = it[ProjectSpecialty.projectId],
//                        specialityId = it[ProjectSpecialty.specialityId]
//                    )
//                )
//            }
//            projectSpecialities
//        }
//    }
//
//    override suspend fun insert(item: domain.model.ProjectSpeciality) {
//        newSuspendedTransaction {
//            ProjectSpecialty.batchInsertOnDuplicateKeyUpdate(
//                item,
//                listOf(ProjectSpecialty.projectId, ProjectSpecialty.specialityId)
//            ) { batch, projectSpeciality ->
//                batch[id] = projectSpeciality.id
//                batch[projectId] = projectSpeciality.projectId
//                batch[specialityId] = projectSpeciality.specialityId
//            }
//        }
//    }
//
//    override suspend fun update(item: domain.model.ProjectSpeciality) {
//        newSuspendedTransaction {
//            Project.update({ ProjectSpecialty.id eq item.id }) {
//                it[ProjectSpecialty.id] = item.id
//                it[ProjectSpecialty.projectId] = item.projectId
//                it[ProjectSpecialty.specialityId] = item.specialityId
//            }
//        }
//    }
//}