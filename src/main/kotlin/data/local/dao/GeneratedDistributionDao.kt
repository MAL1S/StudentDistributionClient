package data.local.dao

import data.local.dao.base.Dao



//object GeneratedDistributionDao: Dao<domain.model.GeneratedDistribution>(GeneratedDistribution) {
//
//    override suspend fun getAll(): List<domain.model.GeneratedDistribution> {
//        return newSuspendedTransaction {
//            val generatedDistributions = mutableListOf<domain.model.GeneratedDistribution>()
//            val typeProject: Type = object : TypeToken<List<domain.model.Project>>() {}.type
//            val typeParticipation: Type = object : TypeToken<List<domain.model.Participation>>() {}.type
//
//            GeneratedDistribution.selectAll().forEach {
//                generatedDistributions.add(
//                    domain.model.GeneratedDistribution(
//                        id = it[GeneratedDistribution.id],
//                        projects = Gson().fromJson<List<domain.model.Project>>(it[GeneratedDistribution.projects], typeProject),
//                        participations = Gson().fromJson<List<domain.model.Participation>>(it[GeneratedDistribution.participations], typeParticipation)
//                    )
//                )
//            }
//            generatedDistributions
//        }
//    }
//
//    override suspend fun insert(item: domain.model.GeneratedDistribution) {
//        newSuspendedTransaction {
//            GeneratedDistribution.insert {
//                it[id] = item.id
//                it[projects] = Gson().toJson(item.projects)
//                it[participations] = Gson().toJson(item.participations)
//            }
//        }
//    }
//
//    override suspend fun update(item: domain.model.GeneratedDistribution) {
//        newSuspendedTransaction {
//            GeneratedDistribution.update({ GeneratedDistribution.id eq item.id }) {
//                it[id] = item.id
//                it[projects] = Gson().toJson(item.projects)
//                it[participations] = Gson().toJson(item.participations)
//            }
//        }
//    }
//}