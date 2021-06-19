package com.example.projectdictionary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.projectdictionary.R
import com.example.projectdictionary.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())

        var view = findViewById<BottomNavigationView>(R.id.bottom_Navigation_View)

        view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home ->{
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_words ->{
                    replaceFragment(WordMainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_quiz ->{
                    replaceFragment(EnglishQuizFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_translator ->{
                    replaceFragment(TranslateFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_news ->{
                    replaceFragment(NewsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else->{
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout_main_fragment,fragment)
        fragmentTransaction.commit()
    }

    fun fragTofrag(fragment:Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framlayout_main_word,fragment)
        fragmentTransaction.addToBackStack(null)
        //fragmentTransaction.replace(R.id.framelayout_word_main_fragment,fragment)
        fragmentTransaction.commit()
    }
}