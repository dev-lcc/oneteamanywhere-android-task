package io.github.devlcc.ota.ui.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.designsystem.theme.AndroidTaskTheme
import io.github.devlcc.ota.ui.designsystem.theme.TextTertiary
import java.util.UUID
import kotlin.random.Random

const val ChallengeActivityItemViewSize = 160
internal const val ChallengeActivityIconSize = 120

@Composable
fun ChallengeActivityItemView(
    modifier: Modifier,
    item: ChallengeActivity,
    onTapChallengeActivity: ((ChallengeActivity) -> Unit) = {},
) {

    //
    // Root Content
    //
    Column(
        modifier = modifier
            .padding(12.dp)
            .clickable { onTapChallengeActivity(item) },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        //
        // Activity Icon and Status
        //
        Box {
            Image(
                modifier = Modifier
                    .size(ChallengeActivityIconSize.dp)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(TextTertiary)
                ,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_challenge_chapter),
                contentDescription = "Activity ${item.id ?: ""}",
            )

            if(item.state == ChallengeActivity.State.Completed) {
                Image(
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.TopEnd),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_checkmark),
                    contentDescription = "Activity State ${item.state?.value ?: ""}",
                )
            }

        }   // END:: Box ~ Activity Icon and Status

        // Activity Title
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = item.title ?: "---",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
        )

    }   // END:: Column ~ Root Content

}

@Preview(widthDp = 160, heightDp = 160)
@Composable
fun ChallengeActivityItemView_Preview_NotSet() {
    AndroidTaskTheme {
        Surface {
            ChallengeActivityItemView(
                modifier = Modifier,
                item = run {
                    val randomIndex = Random.nextInt(10)
                    val activityId = UUID.randomUUID().toString()
                    val iconIndex = Random.nextInt(FakeIconData.Icons.size)
                    ChallengeActivity(
                        id = activityId,
                        challengeId = activityId.substring(0..(activityId.length/2)),
                        type = ChallengeActivity.Type.entries.random(),
                        title = "Activity Title #$randomIndex",
                        description = "This is some description for activity #$randomIndex",
                        state = ChallengeActivity.State.NotSet,
                        icon = FakeIconData.Icons[iconIndex],
                        lockedIcon = FakeIconData.LockedIcons[iconIndex],
                    )
                }
            )
        }
    }
}

@Preview(widthDp = 160, heightDp = 160)
@Composable
fun ChallengeActivityItemView_Preview_Completed() {
    AndroidTaskTheme {
        Surface {
            ChallengeActivityItemView(
                modifier = Modifier,
                item = run {
                    val randomIndex = Random.nextInt(10)
                    val activityId = UUID.randomUUID().toString()
                    val iconIndex = Random.nextInt(FakeIconData.Icons.size)
                    ChallengeActivity(
                        id = activityId,
                        challengeId = activityId.substring(0..(activityId.length/2)),
                        type = ChallengeActivity.Type.entries.random(),
                        title = "Activity Title #$randomIndex",
                        description = "This is some description for activity #$randomIndex",
                        state = ChallengeActivity.State.Completed,
                        icon = FakeIconData.Icons[iconIndex],
                        lockedIcon = FakeIconData.LockedIcons[iconIndex],
                    )
                }
            )
        }
    }
}

internal object FakeIconData {
    val Icons = listOf(
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/7qfuLW6KOLr5wARa6y1iiJ/d9fe08d9680ebe8fa1d02b056e9d9f61/Chapter_05__Lesson_02__State_Active.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/yexAVzAgoVKQnFNPcS57M/db312214000929b2238273c2e7c1ee44/Chapter_02__Lesson_01__State_Active__1_.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/4gLO4SNpkWwF8t0RFRPTC/34bdc756deee0b3e089d4c7248fec8b5/Chapter_03__Lesson_02__State_Active.pdf",
                contentType = "application/pdf",
            ),
        ),
    )
    val LockedIcons = listOf(
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/1aknm1E9St7J2mIKPeerI8/e937194308275460c2facda7d09cf9e7/Chapter_01__Lesson_02__State_Locked.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/60XAuyMCfdyX8vz3uMwPTW/97811784b538551493416bdc6b279f85/Chapter_05__Lesson_02__State_Locked.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/6t2elPAgkiDVcO2FBTkEil/3eb231b2de977dd18d714207a894560d/Chapter_02__Lesson_01__State_Locked__1_.pdf",
                contentType = "application/pdf",
            ),
        ),
        ChallengeActivity.Icon(
            file = ChallengeActivity.Icon.File(
                url = "//assets.ctfassets.net/37k4ti9zbz4t/31thfjGB33eySuuaqMaZj7/e5694a459af89744c17377313b43cb96/Chapter_03__Lesson_02__State_Locked.pdf",
                contentType = "application/pdf",
            ),
        ),
    )
}