package com.rupesh.kotlinrxjavaex.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rupesh.kotlinrxjavaex.R
import com.rupesh.kotlinrxjavaex.databinding.ActivityMainBinding
import com.rupesh.kotlinrxjavaex.presentation.ui.features.movie.fragment.MovieContainerFragment
import com.rupesh.kotlinrxjavaex.presentation.ui.features.news.fragment.NewsContainerFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [AppCompatActivity] subclass.
 * The main Activity and the entry point of the App
 * @author Rupesh Mall
 * @since 1.0
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        replaceFragment(NewsContainerFragment())
        initBottomNavView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun initBottomNavView() {
        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.news -> {
                    replaceFragment(NewsContainerFragment())
                    true
                }

                R.id.movies -> {
                    replaceFragment(MovieContainerFragment())
                    true
                }

                else -> {
                    replaceFragment(NewsContainerFragment())
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_main, fragment)
        fragmentTransaction.commit()
    }
}