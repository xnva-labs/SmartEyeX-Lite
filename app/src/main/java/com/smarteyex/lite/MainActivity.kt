package com.smarteyex.lite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        
        setupNavigation()
    }
    
    private fun setupNavigation() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_chat -> { /* Handle chat */ }
                R.id.nav_camera -> { /* Handle camera */ }
                R.id.nav_memory -> { /* Handle memory */ }
                R.id.nav_profile -> { /* Handle profile */ }
                R.id.nav_journal -> { /* Handle journal */ }
                R.id.nav_reminder -> { /* Handle reminder */ }
                R.id.nav_settings -> { /* Handle settings */ }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}
