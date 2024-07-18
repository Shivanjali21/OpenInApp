plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.devtoolsKSP)
    alias(libs.plugins.navSafeArgs)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.practice.openinapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.practice.openinapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    }
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //SplashApi
    implementation(libs.androidx.core.splashscreen)
    //Navigation Components
    implementation(libs.navFragmentKtx)
    implementation(libs.navUiKtx)
    // Coroutines
    implementation(libs.ktCoroutineAndroid)
    //Glide
    implementation(libs.glide)
    //ViewModel
    implementation(libs.lifeCycleVMKt)
    // LiveData
    implementation(libs.lifeCycleLiveData)
    implementation(libs.lifeCycleRuntimekt)
    //Retrofit
    implementation(libs.squareRetrofit)
    implementation(libs.converterGson)
    implementation (libs.logging.interceptor)
    //hilt
    implementation (libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    kapt(libs.androidx.hilt.compiler)
    //MP Chart
    implementation(libs.mpandroidchart)
}