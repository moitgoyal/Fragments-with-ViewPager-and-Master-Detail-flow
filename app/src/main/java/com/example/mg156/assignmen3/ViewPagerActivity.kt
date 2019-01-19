package com.example.mg156.assignmen3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_view_pager.*
import java.util.*

class ViewPagerActivity : AppCompatActivity() {

    lateinit var movieList: List<MovieDataClass>
    lateinit var posterTable: MutableMap<String,Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        activity_view_pager.adapter = MoviePagerAdapter(supportFragmentManager)
        activity_view_pager.currentItem = 4

        activity_view_pager.setPageTransformer (false , MyPageTransformer())

        tabs.setupWithViewPager(activity_view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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


    private class MyPageTransformer : ViewPager.PageTransformer {
        private val MIN_SCALE = 0.85F
        private val MIN_ALPHA = 0.5F

        override fun transformPage (p0: View , p1: Float ) {
            val pageW = p0.width
            val pageH = p0.height

            if (p1 < -1) { // way off - screen to the left !
                p0.alpha = 0f
                }
            else if (p1 <= 0) { // [-1 0]
                val scaleFactor = Math . max ( MIN_SCALE , 1
                        -Math . abs (p1))
                val verMargin = pageH * (1- scaleFactor )/2
                val horMargin = pageW * (1- scaleFactor )/2

                if (p1 < 0)
                    p0.translationX = horMargin - verMargin /2
                else
                    p0.translationX = verMargin /2 - horMargin

                    p0.scaleX = scaleFactor
                    p0.scaleY = scaleFactor
                    p0. alpha = MIN_ALPHA + ( scaleFactor - MIN_SCALE ) /(1
                        - MIN_SCALE ) *(1 - MIN_ALPHA )
                    }
                else { // (1, + infinity
                p0.alpha = 0F
                }
            }

        }

    inner class MoviePagerAdapter ( fragmentManager : FragmentManager ) : FragmentStatePagerAdapter ( fragmentManager ) {
        private var movieList = Gson().fromJson(movies , Array <MovieDataClass>::class.java ).asList()
        private var posterTable : MutableMap <String,Int> = mutableMapOf ()
        private var count = movieList.size
        //private var genreTable : MutableMap <Int , String > = mutableMapOf ()
        init {
            posterTable = mutableMapOf()
            posterTable["Fight Club"] = R.drawable.fight_club
            posterTable["Forrest Gump"] = R.drawable.forrest_gump
            posterTable["The Godfather"] = R.drawable.godfather
            posterTable["The Godfather: Part II"] = R.drawable.godfather_2
            posterTable["Psycho"] = R.drawable.psycho
            posterTable["Pulp Fiction"] = R.drawable.pulp_fiction
            posterTable["Schindler's List"] = R.drawable.schindler_list
            posterTable["The Shawshank Redemption"] = R.drawable.shawshank_redemption
            posterTable["The Dark Knight"] = R.drawable.the_dark_knight
            posterTable["The Green Mile"] = R.drawable.the_green_mile

        }
        override fun getItem (position: Int ): Fragment {
            return MovieFragment.newInstance(movieList[position] as MovieDataClass, posterTable.get(movieList[position].title)!!)
        }
        override fun getCount (): Int {
            return count
        }
        override fun getPageTitle ( position : Int ): CharSequence ? {
            val l = Locale.getDefault()
            val movie = movieList[position] as MovieDataClass
            val title = movie.title as String
            return title.toUpperCase(l)
        }
    }
}
