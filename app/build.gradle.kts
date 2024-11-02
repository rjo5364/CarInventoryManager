plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "edu.psu.sweng888.carinventorymanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.psu.sweng888.carinventorymanager"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3") // Use the latest version
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

    // Image loading library
    implementation("com.github.bumptech.glide:glide:4.12.0") // Glide for image loading
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")



    // Firebase dependencies
    implementation(libs.firebase.auth) // Firebase Authentication
    implementation(libs.firebase.firestore) // Firebase Firestore

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}