plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
    // version retrofit
    val retroVersion = "2.9.0"

    /**
     * Librerias Utilizadas
     * */
    /**
     * RETROFIT
     * */
    implementation ("com.squareup.retrofit2:retrofit:$retroVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retroVersion")
    /**
     * PICASSO
     * */
    implementation("com.squareup.picasso:picasso:2.71828")
    /**
     * COROUTINES
     * */
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}