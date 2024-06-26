
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //  -   -   Cosas de daggerhilt -   -
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    // -    -   -   -   -   -   -   -   -
    id("kotlin-parcelize")
}

android {
    namespace = "com.oimg.paseaasturias"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.oimg.paseaasturias"
        minSdk = 23
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

    /**
     * Activamos el View binding
     * */
    viewBinding{
        enable = true
    }


}

dependencies {
    /**
     * CONTROL DE VERSIONES
     * */
    val retroVersion = "2.9.0"  //  Retrofit
    val navVersion= "2.7.1"     //  Navigation
    val dagVersion = "2.48"     //  DaggerHilt
    val picassoVersion = "2.71828" // Picasso
    val coVersion = "1.3.6"     //  Coroutines
    val OSMVersion = "6.1.11"   //  OpenStreetMap
    val interceporVersion = "4.9.1" // Interceptor
    /**
     * Librerias Utilizadas
     * */
    /**
     * RETROFIT
     * */
    implementation ("com.squareup.retrofit2:retrofit:$retroVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retroVersion")
    /**
     * Interceptor
     * */
    implementation("com.squareup.okhttp3:logging-interceptor:$interceporVersion")
    /**
     * PICASSO
     * */
    implementation("com.squareup.picasso:picasso:$picassoVersion")
    /**
     * COROUTINES
     * */
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coVersion")
    /**
     * Navigation
     * */
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    /**
     * DaggerHilt
     * */
    implementation("com.google.dagger:hilt-android:$dagVersion")
    kapt("com.google.dagger:hilt-compiler:$dagVersion")
    /**
     * OpenStreetMap
     * */
    implementation("org.osmdroid:osmdroid-android:$OSMVersion")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}