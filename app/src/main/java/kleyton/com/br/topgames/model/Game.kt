package kleyton.com.br.topgames.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

@Entity(tableName = "game")
class Game() : Parcelable {

    var giantbomb_id: Int = 0
    var popularity: Int = 0
    var name: String? = null

    @Embedded
    var logo: GameLogo? = null
    @Embedded

    var box: GameBox? = null

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "top_game_id")
    var id: Int = 0

    constructor(parcel: Parcel) : this() {
        giantbomb_id = parcel.readInt()
        popularity = parcel.readInt()
        name = parcel.readString()
        logo = parcel.readParcelable(GameLogo::class.java.classLoader)
        box = parcel.readParcelable(GameBox::class.java.classLoader)
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(giantbomb_id)
        parcel.writeInt(popularity)
        parcel.writeString(name)
        parcel.writeParcelable(logo, flags)
        parcel.writeParcelable(box, flags)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }


}
