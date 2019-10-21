package com.example.cookhelper.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.cookhelper.R
import com.example.cookhelper.navigation.products.ProductsFragment
import com.example.cookhelper.navigation.profile.HistoryFragment
import com.example.cookhelper.navigation.profile.ProfileFragment
import com.example.cookhelper.navigation.recipes.RecipesFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigation : AppCompatActivity() {

    lateinit var historyFragment: HistoryFragment
    lateinit var productsFragment: ProductsFragment
    lateinit var recipesFragment: RecipesFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)


        recipesFragment = RecipesFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, recipesFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_history -> {
                    historyFragment = if(supportFragmentManager.findFragmentByTag("1") != null){
                        supportFragmentManager.findFragmentByTag("1") as HistoryFragment
                    }else{
                        HistoryFragment()
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, historyFragment, "1")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_products -> {
                    productsFragment = if(supportFragmentManager.findFragmentByTag("2") != null){
                        supportFragmentManager.findFragmentByTag("2") as ProductsFragment
                    }else{
                        ProductsFragment()
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, productsFragment, "2")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_recipes -> {
                    recipesFragment = if(supportFragmentManager.findFragmentByTag("3") != null){
                        supportFragmentManager.findFragmentByTag("3") as RecipesFragment
                    }else{
                        RecipesFragment()
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, recipesFragment, "3")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_profile -> {
                    profileFragment = if(supportFragmentManager.findFragmentByTag("4") != null){
                        supportFragmentManager.findFragmentByTag("4") as ProfileFragment
                    }else{
                        ProfileFragment()
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, profileFragment, "4")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }



}