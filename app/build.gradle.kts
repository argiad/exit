plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

android {
    namespace = "com.steegler.exit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.steegler.exit"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] =
                    "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    // Hilt
    hilt()

    // ROOM
    room()

    // Android Core and UI
    androidX()

    // Compose
    implementation(platform(Dependencies.composeBOM))
    compose()
    composeDebug()

    // Navigation
    implementation(Dependencies.navigationCompose)

    // Serialization
    implementation(Dependencies.kotlinSerialization)


    // TESTS
    androidTestImplementation(platform(Dependencies.composeBOM))
    tests()
}

kapt {
    correctErrorTypes = true
}
