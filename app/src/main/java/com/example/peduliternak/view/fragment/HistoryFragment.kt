package com.example.peduliternak.view.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peduliternak.R
import com.example.peduliternak.data.response.PredictionsItem
import com.example.peduliternak.databinding.FragmentHistoryBinding
import com.example.peduliternak.view.HistoryAdapter
import com.example.peduliternak.view.ViewModelFactory
import com.example.peduliternak.view.welcome.WelcomeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private var token = "token"

    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
////        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root

//        val layoutManager = LinearLayoutManager(requireActivity())
//        binding.rvListHistory.layoutManager = layoutManager

//        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
//        binding.rvListHistory.addItemDecoration(itemDecoration)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val name = arguments?.getString(ARG_USERNAME)
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        _binding = FragmentHistoryBinding.bind(view)


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            } else {
                val historyViewModel = obtainViewModel(this)
                //val mainViewModel = obtainViewModel(this@HistoryActivity)

                token = "Bearer ${user.token}"

                historyViewModel.getPredict(token).observe(viewLifecycleOwner) { predictList ->
                    Log.d(TAG, "predictList: $predictList")
                    setHistory(predictList)
                    //adapter.submitData(lifecycle, predictList)
//                    binding.rvListStory.adapter = adapter.withLoadStateFooter(
//                        footer = LoadingStateAdapter {
//                            adapter.retry()
//                        }
//                    )
                }
//                adapter.setOnItemClickCallback(object : StoryAdapter.OnItemClickCallback {
//                    override fun onItemClicked(data: ListStoryItem) {
//                        Intent(this@MainActivity, DetailActivity::class.java).also {
//                            it.putExtra(DetailActivity.ID, data.id)
//                            startActivity(it)
//                        }
//                    }
//                })
                historyViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }
            }
        }
    }

    private fun setHistory(listUsers: List<PredictionsItem>) {
        val adapter = HistoryAdapter()
        adapter.submitList(listUsers)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvListHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvListHistory.addItemDecoration(itemDecoration)
        binding.rvListHistory.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(fragment: Fragment): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[HistoryViewModel::class.java]
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HistoryFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HistoryFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}