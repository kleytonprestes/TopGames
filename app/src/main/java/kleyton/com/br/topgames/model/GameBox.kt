package kleyton.com.br.topgames.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameBox(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "game_box_id")
    var id: Int?,

    @ColumnInfo(name = "game_box_small")
    var small: String?,

    @ColumnInfo(name = "game_box_template")
    var template: String?,

    @ColumnInfo(name = "game_box_large")
    var large: String?,

    @ColumnInfo(name = "game_box_medium")
    var medium: String?

) : Parcelable


