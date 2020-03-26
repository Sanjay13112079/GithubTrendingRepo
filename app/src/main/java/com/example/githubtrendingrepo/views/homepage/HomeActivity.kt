package com.example.githubtrendingrepo.views.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.githubtrendingrepo.CustomFragmentStatePagerAdapter
import com.example.githubtrendingrepo.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    lateinit var mPagerStateAdapter: CustomFragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }


    fun initViews() {
        setupBottomNav()
        setupViewPager()
    }


    fun setupBottomNav() {

        bottonNav.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item?.itemId) {
                    R.id.menu_repo -> {

                        viewPager.currentItem=0
                        item?.isChecked=true

                    }

                    R.id.menu_develoeprs -> {

                        viewPager.currentItem=1
                        item?.isChecked=true

                    }
                }

                return false

            }
        })

    }


    fun setupViewPager()
    {
        mPagerStateAdapter=CustomFragmentStatePagerAdapter(supportFragmentManager)
        viewPager.adapter=mPagerStateAdapter
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                bottonNav.menu.getItem(position).isChecked=true
            }
        })

    }

}
