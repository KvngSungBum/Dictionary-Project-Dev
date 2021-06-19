package com.example.projectdictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdictionary.adapter.WordAdapter
import com.example.projectdictionary.databinding.FragmentMyWordBinding
import com.example.projectdictionary.dataclass.WordData
import com.example.projectdictionary.dataclass.myDictionary
import io.realm.Realm
import io.realm.kotlin.where

class myWordFragment : Fragment() {
    val realm = Realm.getDefaultInstance()
    lateinit var binding:FragmentMyWordBinding
    var myword: ArrayList<WordData> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter2: WordAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val realmResult = realm.where<myDictionary>()
            .findAll()
        var addedWord :String=""
        var addedMeaning: String =""
        for(i in 0..realmResult.size-1){
            addedWord = realmResult.get(i)?.word.toString()
            addedMeaning = realmResult.get(i)?.meaning.toString()
            myword.add(WordData(addedWord,addedMeaning))
        }

        binding = FragmentMyWordBinding.inflate(inflater,container,false)
        recyclerView = binding.recyclerTextView2
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        adapter2 = WordAdapter(myword)
        recyclerView.adapter = adapter2
        val simpleCallBack = object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                realm.beginTransaction()
                val deleteItem = realm.where<myDictionary>().equalTo("word",myword[viewHolder.adapterPosition].word).findFirst()!!
                deleteItem.deleteFromRealm()

                realm.commitTransaction()
                Toast.makeText(context,"제거되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}