package paulo.antonio.sqlitecomroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

//Base de dados | Banco de dados
//exportSchema é utilizado para exportar o script para fora
@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{

        //Volatile, pois, ela ficara visivel
        //para todas as Threads a partir do momento que a base de dados for instanciada
        @Volatile
        private var INSTANCE: UserDataBase? = null

        //Verificando se já existe uma instancia do Banco de dados já criada
        //Se a condição for true irá retorna a instancia que já existe
        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): UserDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }//Metodo que será realizado dentro de uma corrotina de forma assincrona
            synchronized(this){
                //Instanciando o banco de dados pela primeira vez, caso a condição seja false
                val instance = Room.databaseBuilder(
                    //Contexto de onde vai ser criado o bd
                    context.applicationContext,
                    //Com base na classe UserDataBase convertendo
                    //em uma class java e inserindo um nome
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}