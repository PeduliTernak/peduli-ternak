package com.example.peduliternak.view.fragment

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.peduliternak.R
import com.example.peduliternak.data.response.PredictResponse
import com.example.peduliternak.data.retrofit.ApiConfig
import com.example.peduliternak.databinding.FragmentCameraBinding
import com.example.peduliternak.databinding.FragmentPredictBinding
import com.example.peduliternak.view.ViewModelFactory
import com.example.peduliternak.view.getImageUri
import com.example.peduliternak.view.reduceFileImage
import com.example.peduliternak.view.uriToFile
import com.example.peduliternak.view.welcome.WelcomeActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var token = "token"
    private val viewModel by viewModels<CameraViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val name = arguments?.getString(ARG_USERNAME)
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        _binding = FragmentCameraBinding.bind(view)


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            } else {

                token = "Bearer ${user.token}"
                Log.d(ContentValues.TAG, "onCreate: $token")
                binding.galleryButton.setOnClickListener { startGallery() }
                binding.cameraButton.setOnClickListener { startCamera() }
                binding.uploadButton.setOnClickListener { uploadImage() }
            }
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireActivity())
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->

            val receivedList: String? = arguments?.getString("gejala")
            Log.d(TAG, "listGejala: $receivedList")
            val imageFile = uriToFile(uri, requireActivity()).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            //val description = "Ini adalah deksripsi gambar"
            showLoading(true)
            val requestBody = receivedList?.toRequestBody("text/plain".toMediaType())
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                requestImageFile
            )

            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService()
                    val successResponse =
                        requestBody?.let { apiService.uploadImage(token, multipartBody, it) }
                    showToast(successResponse?.prediction?.result.toString())
                    Log.d(ContentValues.TAG, "test predict: ${successResponse?.prediction?.result?.penyakit.toString()}")
//                    successResponse.enqueue(object : Callback<PredictionResponse> {
//                        override fun onResponse(
//                            call: Call<PredictionResponse>,
//                            response: Response<PredictionResponse>
//                        ) {
//
//                            if (response.isSuccessful) {
//                                Log.d(TAG, "onSuccess: ${response.message()}")
//                                showToast(response.message())
//                                //backToMainActivity()
//                            } else {
//                                Log.e(TAG, "onFailure1: ${response.message()}")
//                            }
//                        }
//
//                        override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
//
//                            Log.e(TAG, "onFailure2: ${t.message.toString()}")
//                        }
//                    })
                    showLoading(false)
                } catch (e: HttpException) {
//                    val errorBody = e.response()?.errorBody()?.string()
//                    val errorResponse = Gson().fromJson(errorBody, PredictResponse::class.java)
//                    showToast(errorResponse.message)
//                    showLoading(false)

                    when (e.code()) {
                        400 -> {
                            showToast("prediksi gagal : invalid request")
                        }
                        else -> {
                            showToast("prediksi gagal : unknown error")
                        }
                    }

                    showLoading(false)
                }
            }
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(fragment: Fragment): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[HistoryViewModel::class.java]
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CameraFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}