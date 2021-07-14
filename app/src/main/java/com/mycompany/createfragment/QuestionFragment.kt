package com.mycompany.createfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.mycompany.createfragment.databinding.FragmentLoginBinding
import com.mycompany.createfragment.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding

    //Question Bank
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "what is MCO stand for ?",
            answers = listOf(
                "Movement Control Order",
                "Multiple Choice Order",
                "More Coffee Order "
            )
        ),
        Question(
            text = "What is FMCO?",
            answers = listOf(
                "Full Movement Control Oder",
                "Fun Movement Control Oder",
                "ForeveMovement Control Oder"
            )
        )
    )

    //Set Optional for Radio Group
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()

        answers.shuffle()

        binding.tvQuestion.text = currentQuestion.text
        binding.rb1.text = answers[0]
        binding.rb2.text = answers[1]
        binding.rb3.text = answers[2]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question,container, false)

        setQuestion()

        binding.btnNext.setOnClickListener() {
            //Check Answer
            val checkedId = binding.rgQuestion.checkedRadioButtonId

            if (checkedId != -1) {
                var answerIndex = 0

                when (checkedId) {
                    R.id.rb1 -> answerIndex = 0
                    R.id.rb2 -> answerIndex = 1
                    R.id.rb3 -> answerIndex = 2
                }

                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    score += 1
                }

                if (questionIndex < 1) {

                    questionIndex += 1
                    binding.rgQuestion.clearCheck()
                    setQuestion()

                } else {
                    // todo:: navigate to thankyou fragment
                    Navigation.findNavController(it)
                        .navigate(R.id.action_questionFragment_to_thankyouFragment)
                }

            } else {
                Toast.makeText(context, "please select answer", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

}