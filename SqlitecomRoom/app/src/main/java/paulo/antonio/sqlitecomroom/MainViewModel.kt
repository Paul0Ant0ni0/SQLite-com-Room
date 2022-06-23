package paulo.antonio.sqlitecomroom

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import paulo.antonio.sqlitecomroom.data.UserDataBase
import paulo.antonio.sqlitecomroom.data.UserRepository
import paulo.antonio.sqlitecomroom.data.Usuario
//Utilizar a MainViewModel só nos fragments e adctivitys utiliza construtor (context: Context),
//mas caso utiliza-la na aplicação inteira, utiliza construtor (application: Application)
// : AndroidViewModel(application)
class MainViewModel (application: Application) : AndroidViewModel(application) {

    val selectUser: LiveData<List<Usuario>>
    val repository: UserRepository

    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
        selectUser = repository.selectUsers
    }

    fun addUser(usuario: Usuario){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(usuario)
        }
    }
}