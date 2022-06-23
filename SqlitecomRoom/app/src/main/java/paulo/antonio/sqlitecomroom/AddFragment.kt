package paulo.antonio.sqlitecomroom

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import paulo.antonio.sqlitecomroom.data.Usuario
import paulo.antonio.sqlitecomroom.databinding.FragmentAddBinding
import java.lang.reflect.Array.get

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        binding.btnAdd.setOnClickListener {
            inserirNoBanco()
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

       return binding.root
    }

    fun verificarCampos(nome: String, sobrenome: String, idade: String): Boolean{
        return !(nome=="" || sobrenome=="" || idade=="")
    }

    fun inserirNoBanco(){
        val nome = binding.inputNome.text.toString()
        val sobrenome = binding.inputSobrenome.text.toString()
        val idade = binding.inputIdade.text.toString()

        if (verificarCampos(nome, sobrenome, idade)){
            val user = Usuario(0, nome, sobrenome, idade.toInt())
            mainViewModel.addUser(user)
            Toast.makeText(context, "Usuário Adicionado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(context, "Erro ao adicionar o usuário", Toast.LENGTH_LONG).show()
        }

    }

}