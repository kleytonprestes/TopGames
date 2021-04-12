package kleyton.com.br.topgames.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "game")
@Parcelize
data class Game(
    var giantbomb_id: Int,
    var popularity: Int,
    var name: String?,
    @Embedded var logo: GameLogo?,
    @Embedded
    var box: GameBox?,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "top_game_id")
    var _id: Int?

) : Parcelable
