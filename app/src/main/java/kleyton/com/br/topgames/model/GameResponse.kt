package kleyton.com.br.topgames.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResponse(
    var top: ArrayList<GameTop>,
    var total: Int
) : Parcelable

