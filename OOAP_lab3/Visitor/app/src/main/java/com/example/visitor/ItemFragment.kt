package com.example.visitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.visitor.databinding.FragmentItemBinding
import com.example.visitor.items.FastFood
import com.example.visitor.items.Order
import com.example.visitor.items.Salad
import com.example.visitor.visitors.Calories
import com.example.visitor.visitors.Ingredient
import com.example.visitor.visitors.Rating

class ItemFragment : Fragment() {
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    var text = "Вы заказали: "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        val view = binding.root

        val order = arguments?.getSerializable("order") as Order
        for (food in order.foods) {
            if (food is Salad) {
                text += food.name + "\n"
            }
            if (food is FastFood) {
                text += food.name + "\n"
            }
        }

        binding.orderText.text = text

        binding.btnIngredients.setOnClickListener{
            val ingredientVisitor = Ingredient()

            for (food in order.foods){
                food.accept(ingredientVisitor)
            }
            binding.description.text = ingredientVisitor.ingredientsText
        }

        binding.btnCalories.setOnClickListener{
            val caloriesVisitor = Calories()

            for (food in order.foods){
                food.accept(caloriesVisitor)
            }
            binding.description.text = caloriesVisitor.ingredientText
        }

        binding.btnRating.setOnClickListener{
            val ratingVisitor = Rating()

            for (food in order.foods){
                food.accept(ratingVisitor)
            }
            binding.description.text = ratingVisitor.textRating
        }

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}