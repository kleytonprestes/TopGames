package kleyton.com.br.topgames.model

import android.os.Parcel
import android.os.Parcelable

class GameTop() : Parcelable {

    var viewers: Int = 0
    var game: Game? = null
    var channels: Int = 0

    constructor(parcel: Parcel) : this() {
        viewers = parcel.readInt()
        game = parcel.readParcelable(Game::class.java.classLoader)
        channels = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(viewers)
        parcel.writeParcelable(game, flags)
        parcel.writeInt(channels)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameTop> {
        override fun createFromParcel(parcel: Parcel): GameTop {
            return GameTop(parcel)
        }

        override fun newArray(size: Int): Array<GameTop?> {
            return arrayOfNulls(size)
        }
    }


}
