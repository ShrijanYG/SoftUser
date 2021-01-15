package com.shrijan.esoft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shrijan.esoft.adapter.FragmentAdapter
import com.shrijan.esoft.ui.home.HomeFragment
import com.shrijan.esoft.ui.dashboard.DashboardFragment
import com.shrijan.esoft.ui.notification.NotificationsFragment

class TabActivity : AppCompatActivity() {
    private lateinit var vp : ViewPager2
    private lateinit var tb : TabLayout
    private lateinit var titleAndFragment : MutableMap<String, Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        binding()
        initialize()
    }

    private fun binding()
    {
        vp = findViewById(R.id.vp)
        tb = findViewById(R.id.tabLay)
    }

    private fun initialize()
    {
        titleAndFragment = mutableMapOf("Home" to HomeFragment(),"Dashboard" to DashboardFragment(),"Notifications" to NotificationsFragment())
        val adapter = FragmentAdapter(supportFragmentManager,lifecycle,titleAndFragment.values.toMutableList())
        vp.adapter = adapter
        TabLayoutMediator(tb,vp){
                tab: TabLayout.Tab, position: Int ->
            tab.text = titleAndFragment.keys.toMutableList()[position]
        }.attach()
    }
}