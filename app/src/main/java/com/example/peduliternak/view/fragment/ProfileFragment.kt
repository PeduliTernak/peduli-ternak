package com.example.peduliternak.view.fragment

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.peduliternak.R
import com.example.peduliternak.databinding.FragmentProfileBinding
import com.example.peduliternak.databinding.FragmentResultBinding
import com.example.peduliternak.view.ViewModelFactory
import com.example.peduliternak.view.welcome.WelcomeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var token = "token"
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val name = arguments?.getString(ARG_USERNAME)
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        _binding = FragmentProfileBinding.bind(view)


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            } else {
                val profileViewModel = obtainViewModel(this)
                token = "Bearer ${user.token}"

                profileViewModel.getUser(token).observe(viewLifecycleOwner) { user ->
                    Log.d(ContentValues.TAG, "predictList: $user")
                    binding.tvUsername.text = user.name.toString()
                    binding.tvNumber.text = user.noTelepon.toString()
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
                profileViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }

                binding.logout.setOnClickListener {
                    viewModel.logout()
                }

            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(fragment: Fragment): ProfileViewModel {
        val factory = ViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[ProfileViewModel::class.java]
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}