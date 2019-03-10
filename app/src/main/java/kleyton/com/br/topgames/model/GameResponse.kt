package kleyton.com.br.topgames.model

import android.os.Parcel
import android.os.Parcelable

class GameResponse() : Parcelable {

    var top: ArrayList<GameTop>? = null
    var total: Int = 0

    constructor(parcel: Parcel) : this() {
        total = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameResponse> {
        override fun createFromParcel(parcel: Parcel): GameResponse {
            return GameResponse(parcel)
        }

        override fun newArray(size: Int): Array<GameResponse?> {
            return arrayOfNulls(size)
        }
    }


}
