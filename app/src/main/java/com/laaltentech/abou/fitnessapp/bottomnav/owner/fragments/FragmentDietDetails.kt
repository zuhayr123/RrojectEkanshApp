package com.laaltentech.abou.fitnessapp.bottomnav.owner.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.laaltentech.abou.fitnessapp.R
import com.laaltentech.abou.fitnessapp.bottomnav.owner.activity.BottomMainNavActivity
import com.laaltentech.abou.fitnessapp.databinding.FragmentDietDetailsLayoutBinding
import com.laaltentech.abou.fitnessapp.databinding.UpperBodyWorkBinding
import com.laaltentech.abou.fitnessapp.di.Injectable
import com.laaltentech.abou.fitnessapp.util.AppExecutors
import com.laaltentech.abou.fitnessapp.util.CONSTANTS
import com.laaltentech.abou.fitnessapp.util.FragmentDataBindingComponent
import javax.inject.Inject

class FragmentDietDetails  : Fragment(), Injectable {
    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding: FragmentDietDetailsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diet_details_layout, container, false, dataBindingComponent)
        (activity as AppCompatActivity).supportActionBar!!.show()

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        (activity as (BottomMainNavActivity)).binding.navView.visibility = View.GONE

        val sharedPref = this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val isSub = sharedPref.getBoolean(CONSTANTS.IS_SUB, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
                val height = (binding.mainView.height - binding.parentView.height).toFloat()
                val perentagePosition = (i2.toFloat()/height) *100
                if(perentagePosition >0 && perentagePosition <100.5){
                    binding.animBack.progress = perentagePosition
                    binding.animBack.setMaxFrame(perentagePosition.toInt()+31)

                }
            }
        }
        super.onActivityCreated(savedInstanceState)
    }
}