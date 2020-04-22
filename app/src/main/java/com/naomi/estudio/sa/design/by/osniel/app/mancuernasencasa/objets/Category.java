package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    String mName;

    public Category(String mName) {
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    protected Category(Parcel in) {
        mName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
    }

    @SuppressWarnings("unused")
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
