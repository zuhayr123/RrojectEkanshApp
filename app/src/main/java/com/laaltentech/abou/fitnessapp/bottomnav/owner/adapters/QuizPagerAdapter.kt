package com.laaltentech.abou.fitnessapp.bottomnav.owner.adapters

import android.animation.LayoutTransition
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.laaltentech.abou.fitnessapp.R
import com.laaltentech.abou.fitnessapp.bottomnav.data.QuizQuestionData
import com.laaltentech.abou.fitnessapp.databinding.AdapterQuizLayoutBinding

class QuizPagerAdapter(private val dataBindingComponent: DataBindingComponent?, var questions : ArrayList<QuizQuestionData>, private val callback: ((action:String)->Unit)?):
    PagerAdapter(){

    override fun getCount(): Int = questions.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil
            .inflate<AdapterQuizLayoutBinding>(
                LayoutInflater.from(container.context),
                R.layout.adapter_quiz_layout,
                container,
                false,
                dataBindingComponent
            )

        when(questions[position].selectedOption){
            VATA ->{
                selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = true, position = position, selection = VATA)
                selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = false, position = position, selection = PITTA)
                selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = false, position = position, selection = KAPHA)
            }

            PITTA -> {
                selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = false, position = position, selection = VATA)
                selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = true, position = position, selection = PITTA)
                selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = false, position = position, selection = KAPHA)
            }

            KAPHA -> {
                selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = false, position = position, selection = VATA)
                selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = false, position = position, selection = PITTA)
                selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = true, position = position, selection = KAPHA)
            }
        }

        binding.materialCardViewA.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.materialCardViewB.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.materialCardViewC.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.layoutLinear.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.constraintA.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.constraintB.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.constraintC.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        container.addView(binding.root)
        binding.textData.text = questions[position].questionObservation
        binding.optionA.text = questions[position].optionVata
        binding.optionB.text = questions[position].optionPitta
        binding.optionC.text = questions[position].optionKapha

        binding.materialCardViewA.setOnClickListener {
            callback?.invoke("Action")
            selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = true, position = position, selection = VATA)
            selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = false, position = position, selection = PITTA)
            selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = false, position = position, selection = KAPHA)
        }

        binding.materialCardViewB.setOnClickListener {
            callback?.invoke("Action")
            selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = false, position = position, selection = VATA)
            selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = true, position = position, selection = PITTA)
            selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = false, position = position, selection = KAPHA)
        }

        binding.materialCardViewC.setOnClickListener{
            callback?.invoke("Action")
            selectItem(container = container, view = binding.materialCardViewA, imageView = binding.optionAArrow, textView = binding.optionA, select = false, position = position, selection = VATA)
            selectItem(container = container, view = binding.materialCardViewB, imageView = binding.optionBArrow, textView = binding.optionB, select = false, position = position, selection = PITTA)
            selectItem(container = container, view = binding.materialCardViewC, imageView = binding.optionCArrow, textView = binding.optionC, select = true, position = position, selection = KAPHA)
        }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    fun selectItem(container: ViewGroup, view: View, textView: TextView, imageView: ImageView, select: Boolean, position: Int, selection : String){
        if(select){
            questions[position].selectedOption = selection
            view.backgroundTintList = container.context.resources.getColorStateList(R.color.dot_inactive_screen2)
            imageView.setImageResource(R.drawable.ic_correct)
            val params: ViewGroup.LayoutParams = view.layoutParams
            params.height = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, container.context.resources.displayMetrics)).toInt()
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        }

        else{
            view.backgroundTintList = container.context.resources.getColorStateList(R.color.colorExtraPrimaryDark)
            imageView.setImageResource(R.drawable.ic_forward)
            val params: ViewGroup.LayoutParams = view.layoutParams
            params.height = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72f, container.context.resources.displayMetrics)).toInt()
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        }
    }

    companion object{
        var VATA = "vata"
        var PITTA = "pitta"
        var KAPHA = "kapha"
    }
}