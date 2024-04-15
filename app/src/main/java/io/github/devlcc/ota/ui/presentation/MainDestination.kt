package io.github.devlcc.ota.ui.presentation

sealed class MainDestination(
    val path: String,
) {

    data object Home: MainDestination("home")
    data object MyAccount: MainDestination("myaccount")
    data object Journey: MainDestination("journey")
    data class ActivityDetail(val id: String): MainDestination(path) {
        // Use `argPath` for navigation route value when attempting to navigate
        val argPath: String
            get() = path.replace("{${Args.Id}}", id)

        object Args {
            const val Id = "id"
        }
        companion object {
            // Use `path` to represent this composable from navigation graph
            const val path = "activity/{${Args.Id}}"

            /**
             * Helper function to validate route pattern.
             * Accepted:
             * - activity/5txQ2uwDcLCC4uv0ChzSJp7FBMJel296NaotMcf3PwJ432uh72
             */
            fun validate(fromPath: String?): Boolean {
                fromPath ?: return false
                val regex = Regex("activity((/)*(.)+)*")
                return regex.matches(fromPath)
            }

            /**
             * Helper function create destination instance from path string.
             */
            fun newInstance(fromPath: String): ActivityDetail? {
                val paths = fromPath.split("/")
                val activityIdIndex = paths.lastIndexOf("activity") + 1

                return if(activityIdIndex < paths.size) {
                    val activityId = paths[activityIdIndex]
                    ActivityDetail(activityId)
                } else {
                    null
                }
            }
        }
    }

}