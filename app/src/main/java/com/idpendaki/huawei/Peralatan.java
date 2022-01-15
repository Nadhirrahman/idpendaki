package com.idpendaki.huawei;

import android.os.Parcel;
import android.os.Parcelable;

public class Peralatan implements Parcelable {

    public String strNamaPeralatan = null;
    public String strImagePeralatan = null;
    public String strTipePeralatan = null;
    public String strDeskripsiPeralatan = null;
    public String strTipsPeralatan = null;

    public Peralatan(){}

    protected Peralatan(Parcel in) {
        strNamaPeralatan = in.readString();
        strImagePeralatan = in.readString();
        strTipePeralatan = in.readString();
        strDeskripsiPeralatan = in.readString();
        strTipsPeralatan = in.readString();
    }

    public static final Creator<Peralatan> CREATOR = new Creator<Peralatan>() {
        @Override
        public Peralatan createFromParcel(Parcel in) {
            return new Peralatan(in);
        }

        @Override
        public Peralatan[] newArray(int size) {
            return new Peralatan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strNamaPeralatan);
        parcel.writeString(strImagePeralatan);
        parcel.writeString(strTipePeralatan);
        parcel.writeString(strDeskripsiPeralatan);
        parcel.writeString(strTipsPeralatan);
    }
}
