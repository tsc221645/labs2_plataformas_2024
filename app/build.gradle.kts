plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.uvg.ana.app1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uvg.ana.app1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core y Lifecycle KTX
    implementation(libs.androidx.core.ktx) // Última versión 1.12.0
    implementation(libs.androidx.lifecycle.runtime.ktx) // Última versión 2.6.2

    // Activity Compose
    implementation(libs.androidx.activity.compose) // Última versión 1.8.0

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom)) // Última versión 2024.01.00

    // UI y Herramientas de Jetpack Compose
    implementation(libs.androidx.ui) // Última versión 1.6.0
    implementation(libs.androidx.ui.graphics) // Última versión 1.6.0
    implementation(libs.androidx.ui.tooling.preview) // Última versión 1.6.0

    // Material 3 (Material You)
    implementation(libs.androidx.material3)
    //implementation(libs.androidx.material3.android) // Última versión 1.2.0

    // Testing
    testImplementation(libs.junit) // Última versión 4.13.2
    androidTestImplementation(libs.androidx.junit) // Última versión 1.1.6
    androidTestImplementation(libs.androidx.espresso.core) // Última versión 3.6.0

    // Test de UI para Jetpack Compose
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Última versión 2024.01.00
    androidTestImplementation(libs.androidx.ui.test.junit4) // Última versión 1.6.0

    // Herramientas de desarrollo y pruebas
    debugImplementation(libs.androidx.ui.tooling) // Última versión 1.6.0
    debugImplementation(libs.androidx.ui.test.manifest) // Última versión 1.6.0

    // Dependencia de Jetpack Compose Navigation
    implementation(libs.androidx.compose.navigation) // Última versión 2.8.0

    // Dependencia para la serialización de JSON con KotlinX
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Dependencia de Coil para cargar imágenes en Compose
    implementation("io.coil-kt:coil-compose:2.4.0") // Última versión de Coil

    implementation ("androidx.compose.material:material-icons-extended:<version>")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.room:room-runtime:2.5.0")

}
