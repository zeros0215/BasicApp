plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.study.basicapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.study.basicapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.fragment:fragment-ktx:1.5.7")
    implementation ("androidx.activity:activity-ktx:1.7.1")

    // Room components
    //val room_version = "2.6.1"
    val room_version = rootProject.extra["room_version"]
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // retrofit and okhttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.0")

    //val hilt_version = "2.46.1"
    val hilt_version = rootProject.extra["hilt_version"]
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    //push message
    implementation("com.google.firebase:firebase-messaging:23.0.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))

    //val work_version = "2.9.0"
    val work_version = rootProject.extra["work_version"]
    // (Java only)
    implementation("androidx.work:work-runtime:$work_version")
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")
    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:$work_version")
    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:$work_version")
    // optional - Test helpers
    androidTestImplementation("androidx.work:work-testing:$work_version")
    // optional - Multiprocess support
    implementation("androidx.work:work-multiprocess:$work_version")

}