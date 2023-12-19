package com.example.peduliternak.view.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.peduliternak.R
import com.example.peduliternak.data.question.QuestionRepository
import com.example.peduliternak.databinding.FragmentPredictBinding
import com.example.peduliternak.view.ViewModelFactory
import com.example.peduliternak.view.welcome.WelcomeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PredictFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PredictFragment : Fragment() {
    private var _binding: FragmentPredictBinding? = null
    private val binding get() = _binding!!
    private var token = "token"

    val questionRepository = QuestionRepository()
    val questions = questionRepository.questions

    val gejala: MutableList<Int> = MutableList(61) { 0 }
    private var currentQuestionIndex = 0
    private var maxQuestionIndex = 11

    var index = 0
    var iAnswer: IntArray = IntArray(15)



    private val viewModel by viewModels<PredictViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

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
        _binding = FragmentPredictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val name = arguments?.getString(ARG_USERNAME)
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        _binding = FragmentPredictBinding.bind(view)


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            } else {
                val PredictViewModel = obtainViewModel(this)
                val pertanyaan = binding.tvQuestion
                val buttonYes = binding.btnYes
                val buttonNo = binding.btnNo



                updateQuestion(0)

                // TODO 3: Perbaiki code dibawah ini, buttonClick(3,4) tidak pernah dieksekusi
//                buttonYes.setOnClickListener {
//                    gejala[52] = 1
////                    updateQuestion(2)
//                    updateQuestion(pertanyaan)


//
////                    if (pertanyaan.text == questions[2].text) {
////                        buttonClick(2,3)
////                    }
////
////                    if (pertanyaan.text == questions[3].text) {
////                        buttonClick(3,4)
////                    }
////
////                    if (pertanyaan.text == questions[4].text) {
////                        buttonClick(4,5)
////
////                    }
////                    if (pertanyaan.text == questions[5].text) {
////                        buttonClick(5,6)
////
////                    }
////                    if (pertanyaan.text == questions[6].text) {
////                        buttonClick(6,14)
////                    }
//                    when (pertanyaan.text) {
//                        questions[2].text -> buttonClick(2, 3)
//                        questions[3].text -> buttonClick(3, 4)
//                        questions[4].text -> buttonClick(4, 5)
//                        questions[5].text -> buttonClick(5, 6)
//                        questions[6].text -> buttonClick(6, 14)
//                    }


//                }

                buttonYes.setOnClickListener {
//                    gejala[currentQuestionIndex] = 1
                    checkAnswer(true)
                    nextQuestion(pertanyaan)
                }

                buttonNo.setOnClickListener {
//                    gejala[currentQuestionIndex] = 0
                    checkAnswer(false)
                    nextQuestion(pertanyaan)
                }
                Log.d(TAG, "gejala: $gejala")


            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(fragment: Fragment): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[HistoryViewModel::class.java]
    }

    private fun updateQuestion(index: Int) {
        val pertanyaan = binding.tvQuestion
        pertanyaan.text = questions[index].text
    }

    private fun buttonClick(index: Int, iQuestion: Int) {

        val buttonYes = binding.btnYes
        val buttonNo = binding.btnNo
        buttonYes.setOnClickListener {
            gejala[index] = 1
            updateQuestion(iQuestion)
            return@setOnClickListener
        }

        buttonNo.setOnClickListener {
            gejala[index] = 0
            updateQuestion(iQuestion)
            return@setOnClickListener
        }
    }

    private fun updateQuestion(questionTextView: TextView) {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.text
    }

    private fun checkAnswer(userAnswer: Boolean) {
//        val currentQuestion = questions[currentQuestionIndex]
//        val correctAnswer = currentQuestion.answer


        if (currentQuestionIndex < 11 && !userAnswer) {
            currentQuestionIndex++
        } else if (currentQuestionIndex == 0 && userAnswer) {
            iAnswer[0] = 2
            iAnswer[1] = 3
            iAnswer[2] = 4
            iAnswer[3] = 5
            iAnswer[4] = 6
            iAnswer[5] = 14
            iAnswer[6] = 0
            iAnswer[7] = 53
            iAnswer[8] = 54
            iAnswer[9] = 55
            iAnswer[10] = 56
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0
            gejala[52] = 1
            currentQuestionIndex = 11
            maxQuestionIndex = 22
        }  else if (currentQuestionIndex == 1 && userAnswer) {
            iAnswer[0] = 0
            iAnswer[1] = 2
            iAnswer[2] = 3
            iAnswer[3] = 4
            iAnswer[4] = 5
            iAnswer[5] = 6
            iAnswer[6] = 7
            iAnswer[7] = 0
            iAnswer[8] = 0
            iAnswer[9] = 0
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[13] = 1
            currentQuestionIndex = 22
            maxQuestionIndex = 29
        }  else if (currentQuestionIndex == 2 && userAnswer) {
            iAnswer[0] = 3
            iAnswer[1] = 6
            iAnswer[2] = 12
            iAnswer[3] = 26
            iAnswer[4] = 30
            iAnswer[5] = 31
            iAnswer[6] = 32
            iAnswer[7] = 37
            iAnswer[8] = 38
            iAnswer[9] = 46
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[1] = 1
            currentQuestionIndex = 29
            maxQuestionIndex = 39
        }  else if (currentQuestionIndex == 3 && userAnswer) {
            iAnswer[0] = 3
            iAnswer[1] = 4
            iAnswer[2] = 5
            iAnswer[3] = 7
            iAnswer[4] = 8
            iAnswer[5] = 16
            iAnswer[6] = 17
            iAnswer[7] = 21
            iAnswer[8] = 25
            iAnswer[9] = 27
            iAnswer[10] = 28
            iAnswer[11] = 29
            iAnswer[12] = 33
            iAnswer[13] = 43
            iAnswer[14] = 0

            gejala[18] = 1
            currentQuestionIndex = 39
            maxQuestionIndex = 53
        }  else if (currentQuestionIndex == 4 && userAnswer) {
            iAnswer[0] = 2
            iAnswer[1] = 3
            iAnswer[2] = 4
            iAnswer[3] = 5
            iAnswer[4] = 9
            iAnswer[5] = 11
            iAnswer[6] = 15
            iAnswer[7] = 16
            iAnswer[8] = 17
            iAnswer[9] = 21
            iAnswer[10] = 25
            iAnswer[11] = 33
            iAnswer[12] = 34
            iAnswer[13] = 35
            iAnswer[14] = 0

            gejala[10] = 1
            currentQuestionIndex = 53
            maxQuestionIndex = 67
        }  else if (currentQuestionIndex == 5 && userAnswer) {
            iAnswer[0] = 2
            iAnswer[1] = 3
            iAnswer[2] = 5
            iAnswer[3] = 6
            iAnswer[4] = 9
            iAnswer[5] = 20
            iAnswer[6] = 22
            iAnswer[7] = 23
            iAnswer[8] = 24
            iAnswer[9] = 36
            iAnswer[10] = 44
            iAnswer[11] = 45
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[22] = 1
            currentQuestionIndex = 67
            maxQuestionIndex = 79
        }  else if (currentQuestionIndex == 6 && userAnswer) {
            iAnswer[0] = 16
            iAnswer[1] = 17
            iAnswer[2] = 40
            iAnswer[3] = 41
            iAnswer[4] = 42
            iAnswer[5] = 0
            iAnswer[6] = 0
            iAnswer[7] = 0
            iAnswer[8] = 0
            iAnswer[9] = 0
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[39] = 1
            currentQuestionIndex = 79
            maxQuestionIndex = 84
        }  else if (currentQuestionIndex == 7 && userAnswer) {
            iAnswer[0] = 0
            iAnswer[1] = 2
            iAnswer[2] = 3
            iAnswer[3] = 4
            iAnswer[4] = 5
            iAnswer[5] = 7
            iAnswer[6] = 8
            iAnswer[7] = 11
            iAnswer[8] = 14
            iAnswer[9] = 19
            iAnswer[10] = 30
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[19] = 1
            currentQuestionIndex = 84
            maxQuestionIndex = 95
        }  else if (currentQuestionIndex == 8 && userAnswer) {
            iAnswer[0] = 0
            iAnswer[1] = 59
            iAnswer[2] = 60
            iAnswer[3] = 3
            iAnswer[4] = 0
            iAnswer[5] = 0
            iAnswer[6] = 0
            iAnswer[7] = 0
            iAnswer[8] = 0
            iAnswer[9] = 0
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[58] = 1
            currentQuestionIndex = 95
            maxQuestionIndex = 99
        }  else if (currentQuestionIndex == 9 && userAnswer) {
            iAnswer[0] = 28
            iAnswer[1] = 49
            iAnswer[2] = 50
            iAnswer[3] = 51
            iAnswer[4] = 0
            iAnswer[5] = 0
            iAnswer[6] = 0
            iAnswer[7] = 0
            iAnswer[8] = 0
            iAnswer[9] = 0
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[47] = 1
            currentQuestionIndex = 99
            maxQuestionIndex = 103
        }  else if (currentQuestionIndex == 10 && userAnswer) {
            iAnswer[0] = 6
            iAnswer[1] = 14
            iAnswer[2] = 20
            iAnswer[3] = 25
            iAnswer[4] = 0
            iAnswer[5] = 0
            iAnswer[6] = 0
            iAnswer[7] = 0
            iAnswer[8] = 0
            iAnswer[9] = 0
            iAnswer[10] = 0
            iAnswer[11] = 0
            iAnswer[12] = 0
            iAnswer[13] = 0
            iAnswer[14] = 0

            gejala[57] = 1
            currentQuestionIndex = 103
            maxQuestionIndex = 107
        } else if (currentQuestionIndex > 10) {
            gejala[iAnswer[index]] = 1
            Log.d(TAG, "checkAnswer: $index and ${iAnswer[index]}")
            index++
            currentQuestionIndex++
        }


        // Lakukan sesuatu dengan jawaban pengguna dan jawaban yang benar
        // Misalnya, tambahkan ke skor atau tampilkan pesan
    }

    private fun nextQuestion(questionTextView: TextView) {

        //currentQuestionIndex++

        // Cek apakah masih ada pertanyaan berikutnya
        if (currentQuestionIndex == maxQuestionIndex) {
            Log.d(TAG, "gejala: $gejala")
            val bundle = Bundle()
            bundle.putString("gejala", gejala.toString())

            val fragmentTujuan = CameraFragment()
            fragmentTujuan.arguments = bundle

            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container2, fragmentTujuan)
                ?.commit()
        }
        if (currentQuestionIndex < maxQuestionIndex) {
            updateQuestion(questionTextView)
        } else {
            // Semua pertanyaan sudah dijawab, lakukan sesuatu setelah kuis selesai
            // Misalnya, tampilkan skor akhir atau kembali ke halaman utama
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PredictFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PredictFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}