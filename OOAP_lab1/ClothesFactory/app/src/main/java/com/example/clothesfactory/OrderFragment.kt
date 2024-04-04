package com.example.clothesfactory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clothesfactory.abstractFactory.ClothFactory
import com.example.clothesfactory.cottonFactory.CottonClothFactory
import com.example.clothesfactory.databinding.FragmentOrderBinding
import com.example.clothesfactory.silkFactory.SilkClothFactory
import com.example.clothesfactory.woolFactory.WoolClothFactory

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    lateinit var factory: ClothFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnOrder.setOnClickListener {
            when(binding.materialCloth.checkedRadioButtonId){
                R.id.wool -> {
                    factory = WoolClothFactory()
                }
                R.id.silk -> {
                    factory = SilkClothFactory()
                }
                R.id.cotton -> {
                    factory = CottonClothFactory()
                }
            }

            val shirtCountText = binding.shirtCount.text.toString()
            if (shirtCountText.isNotEmpty() && shirtCountText.toInt() > 0) {
                binding.tvShirt.text =
                    "Рубашки в количестве: $shirtCountText шт, используют ${makeShirt(factory, shirtCountText.toInt())}"
            } else
                binding.tvShirt.text = ""

            val trousersCountText = binding.trousersCount.text.toString()
            if (trousersCountText.isNotEmpty() && trousersCountText.toInt() > 0) {
                binding.tvTrousers.text =
                    "Брюки в количестве: $trousersCountText шт, используют ${makeTrousers(factory, trousersCountText.toInt())}"
            } else
                binding.tvTrousers.text = ""

            val dressCountText = binding.dressCount.text.toString()
            if(dressCountText.isNotEmpty() && dressCountText.toInt() > 0){
                binding.tvDress.text =
                    "Платье в количестве: $dressCountText шт, используют ${makeDress(factory, dressCountText.toInt())}"
            } else
                binding.tvDress.text = ""
        }

        return view
    }

    fun makeShirt(factory: ClothFactory, count: Int): String{
        val shirt = factory.createShirt()
        return shirt.createShirt(count)
    }

    fun makeTrousers(factory: ClothFactory, count: Int): String{
        val trousers = factory.createTrousers()
        return trousers.createTrousers(count)
    }

    fun makeDress(factory: ClothFactory, count: Int): String{
        val dress = factory.createDress()
        return dress.createDress(count)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}