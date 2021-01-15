package com.shrijan.esoft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shrijan.esoft.model.AuthenticateData
import com.shrijan.esoft.ui.home.HomeFragment
import com.shrijan.esoft.ui.dashboard.DashboardFragment
import com.shrijan.esoft.ui.notification.NotificationsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var tvUsername : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        tvUsername = findViewById(R.id.tvUsername)
        //  val navController = findNavController(R.id.linear)
        navView.setOnNavigationItemSelectedListener(){
            when(it.itemId)
            {
                R.id.navigation_home ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.linear, HomeFragment())
                        addToBackStack(null)
                        commit()
                    }
                }

                R.id.navigation_dashboard ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.linear, DashboardFragment())
                        addToBackStack(null)
                        commit()
                    }


                }

                R.id.navigation_notifications ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.linear, NotificationsFragment())
                        addToBackStack(null)
                        commit()

                    }
                }
            }

            true
        }
        var data: AuthenticateData? = intent.getParcelableExtra("admin")
        tvUsername.text = data!!.un
    }
}