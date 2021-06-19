package com.example.projectdictionary.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectdictionary.activity.MainActivity
import com.example.projectdictionary.databinding.FragmentWordMainBinding

class WordMainFragment : Fragment() {
    var mainActivity:MainActivity?=null
    lateinit var binding: FragmentWordMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWordMainBinding.inflate(layoutInflater,container,false)
        mainActivity!!.fragTofrag(DictionaryFragment())
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.button.setOnClickListener{
            mainActivity!!.fragTofrag(DictionaryFragment())
        }
        binding.button2.setOnClickListener {
            mainActivity!!.fragTofrag(myWordFragment())
        }
    }
}