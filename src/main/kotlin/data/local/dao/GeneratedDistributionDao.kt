package data.local.dao

//object GeneratedDistributionDao: Dao<domain.model.GeneratedDistribution>(GeneratedDistribution) {
//
//    override suspend fun getAll(): List<domain.model.GeneratedDistribution> {
//        return newSuspendedTransaction {
//            val generatedDistributions = mutableListOf<domain.model.GeneratedDistribution>()
//            GeneratedDistribution.selectAll().forEach {
//                generatedDistributions.add(
//                    domain.model.GeneratedDistribution(
//                        id = it[GeneratedDistribution.id],
//                        projects = it[GeneratedDistribution.projects],
//                        participations = it[GeneratedDistribution.participations]
//                    )
//                )
//            }
//            generatedDistributions
//        }
//    }
//
//    override suspend fun insert(item: domain.model.GeneratedDistribution) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun update(item: domain.model.GeneratedDistribution) {
//        TODO("Not yet implemented")
//    }
//}