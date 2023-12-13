package com.example.peduliternak.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.peduliternak.R
import com.example.peduliternak.databinding.ActivityDrawerBinding
import com.example.peduliternak.view.fragment.HistoryFragment
import com.example.peduliternak.view.fragment.HomeFragment
import com.example.peduliternak.view.fragment.MapsFragment
import com.example.peduliternak.view.fragment.PredictFragment
import com.example.peduliternak.view.fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView


class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout,R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_maps -> openFragment(MapsFragment())
                R.id.bottom_scan -> openFragment(PredictFragment())
                R.id.bottom_quiz -> openFragment(HistoryFragment())
                R.id.bottom_profile -> openFragment(ProfileFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fab.setOnClickListener{
            Toast.makeText(this,"Opening Predict", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.bottom_home -> openFragment(HomeFragment())
            R.id.bottom_maps -> openFragment(MapsFragment())
            R.id.bottom_scan -> openFragment(PredictFragment())
            R.id.bottom_quiz -> openFragment(HistoryFragment())
            R.id.bottom_profile -> openFragment(ProfileFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager. beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container2, fragment)
        fragmentTransaction.commit()
    }
}