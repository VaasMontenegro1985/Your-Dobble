package com.vasya.numberlistapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    private var numbers = emptyList<Int>()

    fun updateNumbers(newNumbers: List<Int>) {
        numbers = newNumbers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number_card, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(numbers[position])
    }

    override fun getItemCount(): Int = numbers.size

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numberText: TextView = itemView.findViewById(R.id.numberText)

        fun bind(number: Int) {
            numberText.text = number.toString()

            val backgroundColor = if (number % 2 == 0) {
                ContextCompat.getColor(itemView.context, R.color.even_color)
            } else {
                ContextCompat.getColor(itemView.context, R.color.odd_color)
            }

            numberText.setBackgroundColor(backgroundColor)
        }
    }
}