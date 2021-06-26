package com.laaltentech.abou.fitnessapp.bottomnav.owner.fragments

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.laaltentech.abou.fitnessapp.R
import com.laaltentech.abou.fitnessapp.bottomnav.data.QuizQuestionData
import com.laaltentech.abou.fitnessapp.bottomnav.owner.adapters.QuizPagerAdapter
import com.laaltentech.abou.fitnessapp.databinding.FragmentQuizBinding
import com.laaltentech.abou.fitnessapp.util.AppExecutors
import com.laaltentech.abou.fitnessapp.util.FragmentDataBindingComponent
import javax.inject.Inject

class FragmentQuizLayout : Fragment() {
    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding : FragmentQuizBinding

    lateinit var adapter : QuizPagerAdapter

    var arrayOfQuestions : ArrayList<QuizQuestionData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false, dataBindingComponent)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        binding.done.isEnabled = false

        createQuestions(title = "Body Frame", optionA = "Thin & Lean", optionB = "Medium", optionC = "Large")
        createQuestions(title = "Hair", optionA = "Dry & Brittle", optionB = "Normal,early greying,bald", optionC = "Thick & oily")
        createQuestions(title = "Skin", optionA = "Thin,dry & rough", optionB = "Smooth,oily & warm", optionC = "Thick,oily & cool")
        createQuestions(title = "Nose", optionA = "Uneven shape", optionB = "Long pointed", optionC = "Short rounded,button nose")
        createQuestions(title = "Lips", optionA = "Thin,dry & cracked", optionB = "Red,medium", optionC = "Smooth,pale & oily")
        createQuestions(title = "Teeth", optionA = "Stick out,big & thin gums", optionB = "Medium & tender gums", optionC = "White & strong gums")
        createQuestions(title = "Eyes", optionA = "Small,sunken & dry", optionB = "Bright,sharp & sensitive to light", optionC = "Big,calm & beautiful")
        createQuestions(title = "Nails", optionA = "Small,dry & brittle", optionB = "Medium,pink & sharp", optionC = "Thick,smooth & polished")
        createQuestions(title = "Chin", optionA = "Thin", optionB = "Tapering", optionC = "Round")
        createQuestions(title = "Neck", optionA = "Thin,slender", optionB = "Medium", optionC = "Thick,short")
        createQuestions(title = "Chest", optionA = "Flat", optionB = "Medium", optionC = "Expanded")
        createQuestions(title = "Hips", optionA = "Slender", optionB = "Moderate", optionC = "Big")
        createQuestions(title = "Joints", optionA = "Small & Cracking", optionB = "Moderate", optionC = "Large & lubricated")
        createQuestions(title = "Joints", optionA = "Small & Cracking", optionB = "Moderate", optionC = "Big")
        createQuestions(title = "Weight changes", optionA = "Easy to lose,hard to gain", optionB = "Easy to lose,easy to gain", optionC = "Hard to lose,easy to gain")
        createQuestions(title = "Digestion", optionA = "Irregular,gas", optionB = "Quick,causes acidity & burning", optionC = "Slow")
        createQuestions(title = "Taste preference", optionA = "Sweet,salty & sour", optionB = "Sweet,bitter & astringent", optionC = "Bitter,astringent & pungent")
        createQuestions(title = "Thirst", optionA = "Changeable", optionB = "Excess", optionC = "Scarce")
        createQuestions(title = "Bowel movements", optionA = "Dry,hard stools", optionB = "Soft,loose stools", optionC = "Thick,sticky stools")
        createQuestions(title = "Body temperature", optionA = "Generally cold", optionB = "Generally Hot", optionC = "Normal")
        createQuestions(title = "Physical activity", optionA = "Hyperactive", optionB = "Moderate", optionC = "Slow")
        createQuestions(title = "Mental activity", optionA = "Quick", optionB = "Moderate", optionC = "Calm,slow")
        createQuestions(title = "Sleep", optionA = "Interrupted & less", optionB = "Sound but little", optionC = "Deep & long")
        createQuestions(title = "Emotions", optionA = "Anxiety,fear", optionB = "Jealousy,anger,hate", optionC = "Greed,attachment,patience")
        createQuestions(title = "Communication", optionA = "Fast & unclear", optionB = "Sharp but good speaking skills", optionC = "Slow but firm")
        createQuestions(title = "Wealth", optionA = "Spends a lot without thinking much", optionB = "Spends on valuable things only", optionC = "Saves more & spends less")
        createQuestions(title = "Weather", optionA = "Dislikes cold", optionB = "Dislikes heat", optionC = "Dislikes rain")

        binding.answerCounter.text = "0/" + arrayOfQuestions.size.toString()
        binding.parentLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.constraintLayout2.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        adapter = QuizPagerAdapter(dataBindingComponent = DataBindingUtil.getDefaultComponent(), questions = arrayOfQuestions){
            when(it){
                "Action" ->{
                    if(answerCounter()<arrayOfQuestions.size){
                        binding.answerCounter.text = answerCounter().toString() + "/" + arrayOfQuestions.size.toString()

                        val h = Handler()
                        h.postDelayed({
                            moveNext()
                        }, 300)

                        Log.e("Writer", "This was called")
                    }

                    else{
                        binding.done.isEnabled = true
                        binding.answerCounter.visibility = View.GONE
                        binding.done.setImageResource(R.drawable.ic_correct)
                        binding.done.backgroundTintList = context?.resources?.getColorStateList(R.color.dot_inactive_screen2)
                    }
                }
            }
        }
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("onPageScrollState", "The state is $state")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                binding.progressBar.smoothProgress((((position+1)*100)/adapter.questions.size))
                binding.questionNumbers.text = (position+1).toString()
                binding.totalNumberOfQuestions.text = "/" + arrayOfQuestions.size.toString()

                Log.e("onPageScrolled", "The state is $position")
            }
            override fun onPageSelected(position: Int) {
                Log.e("onPageSelected", "The state is $position")
            }

        })

//        binding.done.setOnClickListener {
//            val action = AdapterQuizLayoutDynDirections.actionAdapterQuizLayoutDynToFragmentQuizResult()
//            findNavController().navigate(action)
//        }
        super.onActivityCreated(savedInstanceState)
    }

    private fun createQuestions(title:String, optionA: String, optionB: String, optionC: String){
        val questionData = QuizQuestionData()
        questionData.questionObservation = title
        questionData.optionKapha = optionA
        questionData.optionPitta = optionB
        questionData.optionVata = optionC

        arrayOfQuestions.add(questionData)
    }

    fun ProgressBar.smoothProgress(percent: Int){
        val animation = ObjectAnimator.ofInt(this, "progress", percent)
        animation.duration = 50
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    fun answerCounter() : Int{
        var answerCounter = 1
        adapter.questions.forEach {
            if(it.selectedOption != "0"){
                answerCounter += 1
            }
        }

        return answerCounter
    }

    fun moveNext() {
        //it doesn't matter if you're already in the last item
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }
}