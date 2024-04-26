package com.example.visitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.visitor.databinding.FragmentCategoryBinding
import com.example.visitor.items.FastFood
import com.example.visitor.items.Order
import com.example.visitor.items.Salad

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val order = Order()
    private var ingredient: MutableList<String> = mutableListOf()
    private var ingredientCalories: MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnOrder.setOnClickListener {
            if(binding.category1.isChecked) {
                ingredient.clear()
                ingredientCalories.clear()

                ingredient.addAll(listOf(
                    "Куриное Филе",
                    "Понировачные Сухари",
                    "Масло подсолнечное",
                    "Секретные специи" ))

                ingredientCalories.addAll(listOf(
                    174,
                    76,
                    168,
                    43
                ))

                order.foods.add(FastFood(
                    "Густрипсы",
                    4.9,
                    ingredient,
                    ingredientCalories,
                    12
                ))
            }

            if(binding.category2.isChecked){
                ingredient.clear()
                ingredientCalories.clear()

                ingredient.addAll(listOf(
                    "Куриное Ножки",
                    "Понировачные Сухари",
                    "Масло подсолнечное",
                    "Секретные специи" ))

                ingredientCalories.addAll(listOf(
                    189,
                    76,
                    168,
                    43
                ))

                order.foods.add(FastFood(
                    "Ножки Саламанки",
                    4.8,
                    ingredient,
                    ingredientCalories,
                    6
                ))
            }

            if(binding.category3.isChecked) {
                ingredient.clear()
                ingredientCalories.clear()

                ingredient.addAll(listOf(
                    "Куриное Филе",
                    "Помидоры",
                    "Сыр Пармезан",
                    "Секретная Заправка",
                ))

                ingredientCalories.addAll(listOf(
                    183,
                    46,
                    145,
                    210,
                ))

                order.foods.add(Salad(
                    "Салат МакГилл",
                    4.6,
                    ingredient,
                    ingredientCalories,
                    300
                ))
            }

            if(binding.category4.isChecked) {
                ingredient.clear()
                ingredientCalories.clear()

                ingredient.addAll(listOf(
                    "Куриное Филе",
                    "Помидоры",
                    "Сыр Пармезан",
                    "Секретная Заправка",
                ))

                ingredientCalories.addAll(listOf(
                    183,
                    46,
                    145,
                    210,
                ))

                order.foods.add(Salad(
                    "Салат Белый",
                    5.0,
                    ingredient,
                    ingredientCalories,
                    280
                ))
            }

            val bundle = Bundle().apply {
                putSerializable("order", order)
            }
            view?.findNavController()?.navigate(R.id.action_categoryFragment_to_itemFragment, bundle)
        }
        return view
    }
}