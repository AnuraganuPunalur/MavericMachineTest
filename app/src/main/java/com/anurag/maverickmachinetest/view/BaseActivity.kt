package com.anurag.maverickmachinetest.view

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun doInitialSetup()
}