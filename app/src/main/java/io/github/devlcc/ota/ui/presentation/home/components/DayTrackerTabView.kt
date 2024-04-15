package io.github.devlcc.ota.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.designsystem.theme.AndroidTaskTheme
import io.github.devlcc.ota.ui.designsystem.theme.PurpleAccent
import io.github.devlcc.ota.ui.designsystem.theme.TextTertiary

@Composable
fun DayTrackerTabView(
    day: ChallengeDayOfTheWeek,
    isSelected: Boolean,
) {

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) R.drawable.ic_day_tracker_on else R.drawable.ic_day_tracker_off
            ),
            tint = if (isSelected) PurpleAccent else TextTertiary,
            contentDescription = "Day Tracker ${day.value}"
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(
                id = when (day) {
                    ChallengeDayOfTheWeek.Monday -> R.string.home_day_tracker_monday
                    ChallengeDayOfTheWeek.Tuesday -> R.string.home_day_tracker_tuesday
                    ChallengeDayOfTheWeek.Wednesday -> R.string.home_day_tracker_wednesday
                    ChallengeDayOfTheWeek.Thursday -> R.string.home_day_tracker_thursday
                    ChallengeDayOfTheWeek.Friday -> R.string.home_day_tracker_friday
                    ChallengeDayOfTheWeek.Saturday -> R.string.home_day_tracker_saturday
                    ChallengeDayOfTheWeek.Sunday -> R.string.home_day_tracker_sunday
                }
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) PurpleAccent else TextTertiary,
        )
    }

}

@Composable
@Preview(heightDp = 62)
fun DayTrackerTabView_Preview_Selected() {
    AndroidTaskTheme {
        Surface {
            DayTrackerTabView(
                day = ChallengeDayOfTheWeek.entries.random(),
                isSelected = true,
            )
        }
    }
}

@Composable
@Preview(heightDp = 62)
fun DayTrackerTabView_Preview_UnSelected() {
    AndroidTaskTheme {
        Surface {
            DayTrackerTabView(
                day = ChallengeDayOfTheWeek.entries.random(),
                isSelected = false,
            )
        }
    }
}