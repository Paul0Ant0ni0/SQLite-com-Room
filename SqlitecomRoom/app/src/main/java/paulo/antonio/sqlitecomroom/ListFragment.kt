package paulo.antonio.sqlitecomroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import paulo.antonio.sqlitecomroom.adapter.UserAdapter
import paulo.antonio.sqlitecomroom.databinding.FragmentAddBinding
import paulo.antonio.sqlitecomroom.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //instanciando o recyclerView
        val adapter = UserAdapter()
        binding.recyclerUsers.layoutManager = LinearLayoutManager(context)
        binding.recyclerUsers.adapter = adapter
        binding.recyclerUsers.setHasFixedSize(true)

        //Retornando a listagem de usuários
        mainViewModel.selectUser.observe(viewLifecycleOwner){ response ->
                adapter.setList(response)

        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }


}