package com.laaltentech.abou.fitnessapp.bottomnav.owner.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laaltentech.abou.fitnessapp.R
import com.laaltentech.abou.fitnessapp.databinding.ActivityBottomMainLayoutBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BottomMainNavActivity : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var binding: ActivityBottomMainLayoutBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_main_layout)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_meditate, R.id.navigation_profile
            )
        )

        binding.contact.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=$+91 9044280425"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Log.e("Layout", "this layout was  created")
    }
}