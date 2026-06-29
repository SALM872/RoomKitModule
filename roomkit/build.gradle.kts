plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
    id("maven-publish")
}

android {
    namespace = "com.roomkit.module"
    compileSdk = 36
    defaultConfig { minSdk = 26 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    publishing { singleVariant("release") { withSourcesJar() } }
}

dependencies {
    api("androidx.room:room-runtime:2.7.2")
    api("androidx.room:room-ktx:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.SALM872"
                artifactId = "RoomKitModule"
                version = "1.1.0"
            }
        }
    }
}
