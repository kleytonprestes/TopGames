package kleyton.com.br.topgames.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

class GameLogo() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "game_logo_id")
    var id = 0

    var small: String? = null
    var template: String? = null
    var large: String? = null
    var medium: String? = null

    constructor(parcel: Parcel) : this() {
        small = parcel.readString()
        template = parcel.readString()
        large = parcel.readString()
        medium = parcel.readString()
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(small)
        parcel.writeString(template)
        parcel.writeString(large)
        parcel.writeString(medium)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameLogo> {
        override fun createFromParcel(parcel: Parcel): GameLogo {
            return GameLogo(parcel)
        }

        override fun newArray(size: Int): Array<GameLogo?> {
            return arrayOfNulls(size)
        }
    }
}
