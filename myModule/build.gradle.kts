import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.create("iosMain")

    val iosFrameworkName = "my_xcframework"
    val xcFramework = XCFramework(iosFrameworkName)

    iosX64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcFramework.add(this)
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcFramework.add(this)
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcFramework.add(this)
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val iosMain by getting {
            dependencies {
                dependsOn(commonMain)
            }
        }

        sourceSets["iosX64Main"].dependsOn(iosMain)
        sourceSets["iosArm64Main"].dependsOn(iosMain)
        sourceSets["iosSimulatorArm64Main"].dependsOn(iosMain)
    }

    tasks.register<Copy>("copyXCFramework") {
        from("build/XCFrameworks/debug")
        into("../MySwiftLibrary/artifacts")
    }

    tasks.findByName("assembleMy_xcframeworkXCFramework")!!.dependsOn("copyXCFramework")
}
