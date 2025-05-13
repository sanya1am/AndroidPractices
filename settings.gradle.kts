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

rootProject.name = "ConsecutivePractices"
include(":app")
include(":core")
include(":uikit")
include(":feature")
include(":feature:movies")
include(":feature:profile")
include(":feature:favorites")
include(":feature:movies:api")
include(":feature:movies:impl")
