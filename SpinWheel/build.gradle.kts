import com.android.build.gradle.internal.utils.createPublishingInfoForLibrary

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id ("maven-publish")
}

android {
    namespace = "com.inoobcoder.spinwheel"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles ("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
//publishing {
//    publications {
//        register<MavenPublication>("release") {
//            afterEvaluate {
//                from(components["release"])
//            }
//        }
//    }
//}

publishing {
    publications {
        // Register the MavenPublication for the release build
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.iNoob-coder"
            artifactId = "SpinMyWheel"
            version = "1.0"

            pom {
                name.set("Spin My Wheel")
                description.set("Spin Wheel Animation Library")
                url.set("https://github.com/iNoob-coder/SpinMyWheel")
            }
        }
    }

    // Repository configuration to publish to Maven Local
    repositories {
        mavenLocal()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}