package com.example.terrestrial.ui.recommendation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.terrestrial.data.response.QuestionItem
import com.example.terrestrial.databinding.ItemQuestionBinding

class QuestionAdapter(
    private val onAnswerSelected: (Int, String) -> Unit
) : ListAdapter<QuestionItem, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    private val selectedAnswers = mutableMapOf<Int, String>()

    fun getSelectedAnswers(): Map<Int, String> {
        return selectedAnswers
    }

    fun allQuestionsAnswered(): Boolean {
        return selectedAnswers.size == itemCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuestionBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(binding, onAnswerSelected, selectedAnswers)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = getItem(position)
        holder.bind(question, selectedAnswers[question.id] ?: "")
    }

    class QuestionViewHolder(
        private val binding: ItemQuestionBinding,
        private val onAnswerSelected: (Int, String) -> Unit,
        private val selectedAnswers: MutableMap<Int, String>
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.radioGroupOptions.setOnCheckedChangeListener { _, checkedId ->
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    val selectedRadioButton = binding.root.findViewById<RadioButton>(checkedId)
                    val answer = when (selectedRadioButton) {
                        binding.radioOption1 -> "A"
                        binding.radioOption2 -> "B"
                        binding.radioOption3 -> "C"
                        else -> ""
                    }

                    onAnswerSelected(position, answer)
                    Log.d("QuestionAdapter", "Jawaban terpilih: $position, $answer")

                    // Perbarui status jawaban terpilih
                    selectedAnswers[position] = answer
                }
            }
        }

        fun bind(question: QuestionItem, selectedAnswer: String) {
            binding.tvQuestion.text = question.queText
            binding.radioOption1.text = question.answer1
            binding.radioOption2.text = question.answer2
            binding.radioOption3.text = question.answer3

            // Perbarui status jawaban terpilih pada tampilan
            when (selectedAnswer) {
                "A" -> binding.radioOption1.isChecked = true
                "B" -> binding.radioOption2.isChecked = true
                "C" -> binding.radioOption3.isChecked = true
                else -> {
                    binding.radioOption1.isChecked = false
                    binding.radioOption2.isChecked = false
                    binding.radioOption3.isChecked = false
                }
            }
        }
    }

    private class QuestionDiffCallback : DiffUtil.ItemCallback<QuestionItem>() {
        override fun areItemsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
            return oldItem == newItem
        }
    }
}

