/*
 *  Created by Azamov X ㋡ on 11/21/23, 2:02 AM
 *  Copyright (c) 2023 . All rights reserved.
 *  Last modified 11/21/23, 2:02 AM
 *
 *
 */

package com.azamovhudstc.graphqlanilist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.azamovhudstc.graphqlanilist.R
import com.azamovhudstc.graphqlanilist.application.App
import com.azamovhudstc.graphqlanilist.utils.hideSystemBars
import com.github.javiersantos.appupdater.enums.Display
import com.github.javiersantos.appupdater.enums.UpdateFrom
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        checkForUpdates()
    }
}

private fun checkForUpdates() {
    val appUpdater=     com.github.javiersantos.appupdater.AppUpdater(App.instance)
        .setUpdateFrom(UpdateFrom.GITHUB)
        .setGitHubUserAndRepo("professorDeveloper", "Kitsune-App")
        .showAppUpdated(true)
        .setButtonUpdate("Download New Version")
        .setDisplay(Display.SNACKBAR)
        .setDisplay(Display.SNACKBAR)

    appUpdater.start();

}
