package com.laaltentech.abou.fitnessapp.bottomnav.owner.fragments

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.laaltentech.abou.fitnessapp.R
import com.laaltentech.abou.fitnessapp.databinding.FragmentHomeBinding
import com.laaltentech.abou.fitnessapp.di.Injectable
import com.laaltentech.abou.fitnessapp.util.AppExecutors
import com.laaltentech.abou.fitnessapp.util.CONSTANTS
import com.laaltentech.abou.fitnessapp.util.FragmentDataBindingComponent
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {
    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false, dataBindingComponent)
        (activity as AppCompatActivity).supportActionBar!!.show()

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val sharedPref = this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val firstName = sharedPref.getString(CONSTANTS.FIRST_NAME, "")
        if(!firstName.isNullOrEmpty()){
            binding.textView.text = "Welcome $firstName!!"
        }

        binding.testClick.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToFragmentQuizLayout()
            findNavController().navigate(action)
        }

        binding.glutesClick.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToGlutesFragment()
            findNavController().navigate(action)
        }

        binding.fullBodyWorkout.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToFullBodyWorkout()
            findNavController().navigate(action)
        }

        binding.upperBodyWork.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToUpperBodyWorkout()
            findNavController().navigate(action)
        }

        binding.others.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToFragmentOthersWorkout()
            findNavController().navigate(action)
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu_source, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.log_out -> {
                showConfirmation("Show")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun <T> showConfirmation(vararg params: T) {
        BottomSheetCustomDialog(title = "Confirmation",
            message = "Are you sure you want to Logout?",
            positiveActionTitle = "Yes",
            context = requireContext(),
            args = *arrayOf(params),
            actionPositive = this::performAction
        ).show()
    }

    private fun <T> performAction(vararg param: T) {
        val h = Handler()

        Toast.makeText(context, "Successfully logged out ", Toast.LENGTH_LONG ).show()

        h.postDelayed(Runnable {
            (context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .clearApplicationUserData() // note: it has a return value!
        }, 1000)
    }
}