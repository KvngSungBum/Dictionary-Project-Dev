package com.example.projectdictionary.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectdictionary.adapter.NewsAdapter
import com.example.projectdictionary.databinding.FragmentNewsBinding
import com.example.projectdictionary.dataclass.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class NewsFragment : Fragment() {
    var binding: FragmentNewsBinding?=null
    val scope = CoroutineScope(Dispatchers.IO)
    lateinit var adapter: NewsAdapter
    val url ="http://www.koreaherald.com/common_prog/rssdisp.php?ct=020100000000.xml"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }
    fun getnews(){
        scope.launch {
            adapter.items.clear()
            val doc = Jsoup.connect(url).parser(Parser.xmlParser()).get()
            val headlines = doc.select("item")
            for(news in headlines){
                adapter.items.add(
                    NewsData(
                        news.select(
                            "title"
                        ).text(), news.select("link").text()
                    )
                )
            }
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
                binding!!.swipe.isRefreshing = false
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.swipe.setOnRefreshListener {
            binding!!.swipe.isRefreshing = true
            getnews()
        }
        binding!!.news.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding!!.news.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter =
            NewsAdapter(ArrayList<NewsData>())
        adapter.itemClickListener = object:
            NewsAdapter.OnItemClickListener {
            override fun OnItemClick(
                holder: NewsAdapter.MyViewHolder,
                view: View,
                data: NewsData,
                position: Int
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        binding!!.news.adapter = adapter
        getnews()
    }
}