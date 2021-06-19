package com.example.projectdictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectdictionary.R
import com.example.projectdictionary.databinding.FragmentHomeBinding
import com.example.projectdictionary.dataclass.WordData
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    var data:ArrayList<WordData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    //오늘의 단어 기능 구현
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initWord()
        var num = randomNum()
        binding.dailyWord1.text = data[num].word
        binding.dailyMeaning1.text = data[num].meaning
        var num2 = randomNum()
        binding.dailyWord2.text = data[num2].word
        binding.dailyMeaning2.text = data[num2].meaning
        var num3 = randomNum()
        binding.dailyWord3.text = data[num3].word
        binding.dailyMeaning3.text = data[num3].meaning

    }

    private fun initWord() {
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNext()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            data.add(WordData(word,meaning))
        }
        scan.close()
    }

    private fun randomNum():Int{
        var random = Random()
        var num = random.nextInt(4030)
        return num
    }
}