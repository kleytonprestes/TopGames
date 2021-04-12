package kleyton.com.br.topgames.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameTop(
    var viewers: Int,
    var game: Game,
    var channels: Int
) : Parcelable
