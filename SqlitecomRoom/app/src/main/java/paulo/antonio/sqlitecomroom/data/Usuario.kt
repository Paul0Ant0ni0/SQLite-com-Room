package paulo.antonio.sqlitecomroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Referenciado a tabela e o nome dela
@Entity(tableName = "user_table")
class Usuario(

    //Gerando id de forma automatica
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var nome: String,
    var sobrenome: String,
    var idade: Int
) {

}