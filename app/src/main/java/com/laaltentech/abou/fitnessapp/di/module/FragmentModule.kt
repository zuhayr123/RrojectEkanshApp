package com.laaltentech.abou.fitnessapp.di.module

import com.laaltentech.abou.fitnessapp.bottomnav.owner.fragments.*
import com.laaltentech.abou.fitnessapp.cameraX.fragments.CameraClickFragment
import com.laaltentech.abou.fitnessapp.cameraX.fragments.ImageViewerFragment
import com.laaltentech.abou.fitnessapp.game.owner.fragments.GameInstructionsFragment
import com.laaltentech.abou.fitnessapp.login.owner.fragments.FragmentSplashScreen
import com.laaltentech.abou.fitnessapp.login.owner.fragments.Login
import com.laaltentech.abou.fitnessapp.login.owner.fragments.LoginOrSignupChoose
import com.laaltentech.abou.fitnessapp.login.owner.fragments.SignUp
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    //todo just an example to add fragments here
    @ContributesAndroidInjector
    abstract fun contributeFlickrMainFragment(): GameInstructionsFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginOrSignupChoose(): LoginOrSignupChoose

    @ContributesAndroidInjector
    abstract fun contributeLogin(): Login

    @ContributesAndroidInjector
    abstract fun contributeSignUp(): SignUp

    @ContributesAndroidInjector
    abstract fun contributeMeditateFragment(): MeditateFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraClickFragment(): CameraClickFragment

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment

    @ContributesAndroidInjector
    abstract fun contributeGlutesFragment(): GlutesFragment

    @ContributesAndroidInjector
    abstract fun contributeFullBodyWorkout(): FullBodyWorkout

    @ContributesAndroidInjector
    abstract fun contributeUpperBodyWorkout(): UpperBodyWorkout

    @ContributesAndroidInjector
    abstract fun contributeFragmentOthersWorkout(): FragmentOthersWorkout

    @ContributesAndroidInjector
    abstract fun contributeFragmentSubscribeCheck(): FragmentSubscribeCheck

    @ContributesAndroidInjector
    abstract fun contributeFragmentFlashLayoutBinding(): FragmentSplashScreen

    @ContributesAndroidInjector
    abstract fun contributeFragmentQuizLayoutBinding(): FragmentQuizLayout
}