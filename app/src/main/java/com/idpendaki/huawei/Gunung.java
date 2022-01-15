package com.idpendaki.huawei;

import android.os.Parcel;
import android.os.Parcelable;

public class Gunung implements Parcelable {

    public String strNamaGunung = null;
    public String strImageGunung = null;
    public String strLokasiGunung = null;
    public String strDeskripsi = null;
    public String strJalurPendakian = null;
    public String strInfoGunung = null;
    public double strLat = 0.0;
    public double strLong = 0.0;

    public Gunung() {
    }

    protected Gunung(Parcel in) {
        strNamaGunung = in.readString();
        strImageGunung = in.readString();
        strLokasiGunung = in.readString();
        strDeskripsi = in.readString();
        strJalurPendakian = in.readString();
        strInfoGunung = in.readString();
        strLat = in.readDouble();
        strLong = in.readDouble();
    }

    public static final Creator<Gunung> CREATOR = new Creator<Gunung>() {
        @Override
        public Gunung createFromParcel(Parcel in) {
            return new Gunung(in);
        }

        @Override
        public Gunung[] newArray(int size) {
            return new Gunung[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strNamaGunung);
        parcel.writeString(strImageGunung);
        parcel.writeString(strLokasiGunung);
        parcel.writeString(strDeskripsi);
        parcel.writeString(strJalurPendakian);
        parcel.writeString(strInfoGunung);
        parcel.writeDouble(strLat);
        parcel.writeDouble(strLong);
    }
}
