package paulo.antonio.sqlitecomroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Interface que realiza a requisição
@Dao
interface UserDao {

    //Informando que quando o usuário inserir dado dublicado a requisição irá ignorar
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    fun addUser(usuario: Usuario)

    //listando os usuário da tabela
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun selectUsers(): LiveData<List<Usuario>>
}