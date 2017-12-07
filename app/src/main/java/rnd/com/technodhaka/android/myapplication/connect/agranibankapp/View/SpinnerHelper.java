package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.View;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is responsible to produce Spinner skeleton
 *
 * @author Faisal Moahammad
 * @version 01
 *          Created by TD-Android on 12/6/2017.
 */
public class SpinnerHelper implements Parcelable {
    private int position;
    private String databaseId;
    private String databaseValue;

    public SpinnerHelper(int position, String databaseId, String databaseValue) {
        this.position = position;
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
    }

    public int getPosition() {
        return position;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public String getDatabaseValue() {
        return databaseValue;
    }


    private SpinnerHelper(Parcel parcel) {
        this.position = parcel.readInt();
        this.databaseId = parcel.readString();
        this.databaseValue = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(position);
        parcel.writeString(databaseId);
        parcel.writeString(databaseValue);
    }

    public static final Parcelable.Creator<SpinnerHelper> CREATOR = new
            Parcelable.Creator<SpinnerHelper>() {
                public SpinnerHelper createFromParcel(Parcel in) {
                    return new SpinnerHelper(in);
                }
                public SpinnerHelper[] newArray(int size) {
                    return new SpinnerHelper[size];
                }
            };
}
