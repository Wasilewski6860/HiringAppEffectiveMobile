pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HiringAppEffectiveMobile"
include(":app")
include(":base")
include(":base-feature")
include(":network")
include(":uikit")
include(":transport")
include(":domain")
include(":database")
include(":main:feature")
include(":main:interactor")
include(":home:feature")
include(":home:interactor")
include(":search:feature")
include(":favourites:feature")
include(":feedback:feature")
include(":profile:feature")
include(":messages:feature")
include(":offers:common-feature")
include(":offers:common-interactor")
include(":vacancy:common-feature")
include(":vacancy:common-interactor")
