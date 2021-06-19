package com.example.projectdictionary.fragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.projectdictionary.R
import com.example.projectdictionary.databinding.FragmentEnglishQuizBinding
import com.example.projectdictionary.dataclass.WordData
import java.util.*
import kotlin.collections.ArrayList

class EnglishQuizFragment : Fragment() {
    lateinit var binding:FragmentEnglishQuizBinding
    lateinit var tts: TextToSpeech
    val data : ArrayList<WordData> = ArrayList()
    var isTtsReady = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEnglishQuizBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initTTS()
        randomWord()
        binding.nextWord.setOnClickListener {
            binding.answerImage.setImageResource(R.drawable.main_dog)
            randomWord()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun randomWord(){
        var random = Random()
        var num = random.nextInt(4030)
        var randomWord = data[num].meaning
        binding.showQuestion.text = randomWord
        binding.speakQuestion.setOnClickListener {
            if(isTtsReady)
                tts.speak(randomWord,TextToSpeech.QUEUE_ADD,null,null)
        }
        binding.submitAnswer.setOnClickListener {
            if(binding.enterAnswer.text.toString() == data[num].word){
                binding.answerImage.setImageResource(R.drawable.answer_right)
                Toast.makeText(context,"정답입니다",Toast.LENGTH_SHORT).show()
            }else{
                binding.answerImage.setImageResource(R.drawable.answer_wrong)
                Toast.makeText(context,"오답입니다",Toast.LENGTH_SHORT).show()
            }
            binding.enterAnswer.text.clear()
        }
    }
    private fun initTTS() {
        tts = TextToSpeech(context,TextToSpeech.OnInitListener {
            isTtsReady = true
            tts.language = Locale.KOREAN
        })
    }

    override fun onStop() {
        super.onStop()
        tts.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }

    private fun initData(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNext()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            data.add(WordData(word,meaning))
        }
        scan.close()
    }
}