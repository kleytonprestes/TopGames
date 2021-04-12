package kleyton.com.br.topgames.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameLogo(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "game_logo_id")
    var id: Int?,
    var small: String?,
    var template: String?,
    var large: String?,
    var medium: String?,
) : Parcelable
