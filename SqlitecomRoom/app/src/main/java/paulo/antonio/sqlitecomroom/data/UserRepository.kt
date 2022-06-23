package paulo.antonio.sqlitecomroom.data

class UserRepository (private  val userDao: UserDao){

    //Irá retornar uma lista de usuários do tipo LiveData
    val selectUsers = userDao.selectUsers()

    fun addUser(usuario: Usuario){
        userDao.addUser(usuario)
    }
}