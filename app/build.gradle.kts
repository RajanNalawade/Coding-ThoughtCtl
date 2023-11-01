plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.codingthoughtctl"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.codingthoughtctl"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
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
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //recyclerView
    val recyclerViewVersion = "1.3.2"
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")

    //fragment
    val fragmentVersion = "1.6.1"
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    //Coroutines
    val coroutinesVersion = "1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("androidx.concurrent:concurrent-futures-ktx:1.1.0")

    // Hilt dependencies
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // Glide
    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    ksp("com.github.bumptech.glide:compiler:$glideVersion")

    //navigation graph
    var navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // provides us support to test coroutines. We get test scopes and dispatchers to run coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    // Mock web server
    //provides support for mocking of the server with which we can actually make api calls and get the output.
    val mockServerVersion = "4.10.0"
    testImplementation("com.squareup.okhttp3:mockwebserver:$mockServerVersion")

    // Google truth for assertion
    //provides support for better assertion and logging of results.
    testImplementation("com.google.truth:truth:1.1.3")
}