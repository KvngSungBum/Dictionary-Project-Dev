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
import com.example.projectdictionary.R
import com.example.projectdictionary.adapter.DictionaryAdapter
import com.example.projectdictionary.databinding.FragmentDictionaryBinding
import com.example.projectdictionary.dataclass.WordData
import com.example.projectdictionary.dataclass.myDictionary
import io.realm.Realm
import java.util.*
import kotlin.collections.ArrayList


class DictionaryFragment : Fragment() {
    val realm = Realm.getDefaultInstance()
    var data :ArrayList<WordData> = ArrayList()
    lateinit var binding:FragmentDictionaryBinding
    lateinit var recyclerView:RecyclerView
    lateinit var adapter: DictionaryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initData()
        binding = FragmentDictionaryBinding.inflate(layoutInflater,container,false)
        recyclerView = binding.recyclerTextView
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        adapter = DictionaryAdapter(data)
        recyclerView.adapter = adapter
        val simpleCallBack = object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                TODO("Not yet implemented")
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                realm.beginTransaction()

                val newItem = realm.createObject(myDictionary::class.java)
                newItem.word = data[viewHolder.adapterPosition].word.toString()
                newItem.meaning = data[viewHolder.adapterPosition].meaning.toString()

                realm.commitTransaction()
                Toast.makeText(context,"추가되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    private fun initData() {
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNext()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            data.add(WordData(word,meaning))
        }
        scan.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}