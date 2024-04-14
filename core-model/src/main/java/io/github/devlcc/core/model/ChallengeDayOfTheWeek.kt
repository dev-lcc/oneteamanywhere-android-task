package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ChallengeDayOfTheWeek(val value: Int): Parcelable {
    Monday(0),
    Tuesday(1),
    Wednesday(2),
    Thursday(3),
    Friday(4),
    Saturday(5),
    Sunday(6),
}