package di

import dagger.Module
import dagger.Provides
import data.local.dao.StudentDao
import domain.model.Student
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
interface DatabaseModule {

    companion object {

        @AppScope
        @Provides
        fun provideDatabase(): Realm {
            val configuration = RealmConfiguration.create(
                schema = setOf(
                    Student::class
                )
            )
            return Realm.open(configuration)
        }

        @AppScope
        @Provides
        fun provideStudentDao(realm: Realm): StudentDao {
            return StudentDao(realm)
        }
    }
}