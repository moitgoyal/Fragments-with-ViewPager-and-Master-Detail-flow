package com.example.mg156.assignmen3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , FragmentFront.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,FragmentFront.newInstance(R.id.fragmentFrontLayout.toString()),"Fragment Front").commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            //val fragmentTransaction = supportFragmentManager.beginTransaction()
            //fragmentTransaction.replace(R.id.fragmentContainer, FragmentFront.newInstance(R.id.fragmentFrontLayout.toString()),"About Me")
            //fragmentTransaction.addToBackStack(null)
            //fragmentTransaction.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.view_pager) {
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.movie_details) {
            val intent = Intent(this, MasterDetailsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(v: View){
        when (v.id) {
            R.id.btnAboutMe -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentContainer, AboutMeFragment.newInstance(R.id.aboutMeLayout.toString()))
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.btnViewPager -> {
                val intent = Intent(this@MainActivity, ViewPagerActivity::class.java)
                startActivity(intent)
            }
            R.id.btnMovieDetails -> {
                val intent = Intent(this@MainActivity, MasterDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
