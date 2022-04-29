plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.maximapps.maxim_weather"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            type = "String",
            name = "OpenWeatherApiKey",
            value = project.property("open_weather_api_key") as String
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    //Google Play Services
    implementation("com.google.android.gms:play-services-location:19.0.1")

    //FastAdapter
    implementation("com.mikepenz:fastadapter:5.6.0")
    implementation("com.mikepenz:fastadapter-extensions-diff:5.6.0")
    implementation("com.mikepenz:fastadapter-extensions-binding:5.6.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    //ViewBindingPropertyDelegate
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Dagger
    implementation("com.google.dagger:dagger:2.40.5")
    implementation("com.google.dagger:dagger-android-support:2.40.5")
    kapt("com.google.dagger:dagger-compiler:2.40.5")
    kapt("com.google.dagger:dagger-android-processor:2.40.5")

    //Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.1")

    testImplementation("junit:junit:4.13.2")

    //MocKK
    testImplementation("io.mockk:mockk:1.12.2")

    //Coroutines Test Package
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}