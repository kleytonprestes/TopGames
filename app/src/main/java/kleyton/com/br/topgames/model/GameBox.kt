package kleyton.com.br.topgames.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

class GameBox() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "game_box_id")
    var id = 0

    @ColumnInfo(name = "game_box_small")
    var small: String? = null

    @ColumnInfo(name = "game_box_template")
    var template: String? = null

    @ColumnInfo(name = "game_box_large")
    var large: String? = null

    @ColumnInfo(name = "game_box_medium")
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

    companion object CREATOR : Parcelable.Creator<GameBox> {
        override fun createFromParcel(parcel: Parcel): GameBox {
            return GameBox(parcel)
        }

        override fun newArray(size: Int): Array<GameBox?> {
            return arrayOfNulls(size)
        }
    }


}
