import org.gradle.api.JavaVersion

plugins {
    id("com.android.application") version "8.2.2" // Make sure to use the correct version of the Android Gradle plugin
    id("com.google.gms.google-services") version "4.4.1" // Make sure to use the correct version of the Google Services plugin
}

android {
    namespace = "com.example.libby"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.libby"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0") // Make sure to use the correct version of Material Components
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation("com.google.firebase:firebase-auth:22.3.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth:22.3.1")
}

// Classpath dependency declaration should be within the buildscript block
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}

