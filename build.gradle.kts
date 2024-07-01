// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}


buildscript {
    extra.apply{
        set("kotlin_version", "1.3.61")
        set("room_version", "2.6.1")
        set("work_version", "2.9.0")
    }
}
