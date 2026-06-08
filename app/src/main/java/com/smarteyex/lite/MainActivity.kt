package com.smarteyex.lite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.smarteyex.lite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        
        setupNavigation()
    }
    
    private fun setupNavigation() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_chat -> { /* Chat */ }
                R.id.nav_camera -> { /* Camera */ }
                R.id.nav_memory -> { /* Memory */ }
                R.id.nav_profile -> { /* Profile */ }
                R.id.nav_journal -> { /* Journal */ }
                R.id.nav_reminder -> { /* Reminder */ }
                R.id.nav_settings -> { /* Settings */ }
            }
            drawerLayout.closeDrawers()
            true
        }
        
        binding.menuButton.setOnClickListener {
            drawerLayout.open()
        }
    }
}
