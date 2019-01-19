package com.example.mg156.assignmen3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.google.gson.Gson

class MasterDetailsActivity : AppCompatActivity() , MasterFragment.OnFragmentInteractionListener {

    private var mTwoPane = false
    lateinit var movieList: List<MovieDataClass>
    lateinit var posterTable: MutableMap<String,Int>
    lateinit var mcontent: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_details)

        this.movieList = Gson().fromJson(movies , Array <MovieDataClass>::class.java ).asList()

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

        if (savedInstanceState == null) {
            mcontent = MasterFragment.newInstance(R.id.framentMasterLayout.toString())
        }

        if(findViewById<FrameLayout>(R.id.slaveContainer) != null){
            mTwoPane = true
            supportFragmentManager.beginTransaction().replace(R.id.masterContainer,mcontent,"Fragment Master").addToBackStack(null).commit()
            supportFragmentManager.beginTransaction().replace(R.id.slaveContainer,MovieFragment.newInstance(movieList[0],posterTable.get(movieList[0].title)!!)).addToBackStack(null).commit()
        }
        else{
            mTwoPane = false
            supportFragmentManager.beginTransaction().replace(R.id.masterContainer,mcontent,"Fragment Master").addToBackStack(null).commit()
        }
    }

    override fun onFragmentInteraction(v: View, index: Int){
        if (mTwoPane == true) {
            supportFragmentManager.beginTransaction().replace(R.id.slaveContainer, MovieFragment.newInstance(movieList[index],posterTable.get(movieList[index].title)!!)).addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.masterContainer, MovieFragment.newInstance(movieList[index],posterTable.get(movieList[index].title)!!)).addToBackStack(null).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "mcontent", mcontent)
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

}
