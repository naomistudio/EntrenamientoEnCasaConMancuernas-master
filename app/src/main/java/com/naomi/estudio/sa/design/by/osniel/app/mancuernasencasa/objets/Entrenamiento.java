package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets;

import android.os.Parcel;
import android.os.Parcelable;

public class Entrenamiento implements Parcelable {
  private int mId;
  private String mName;
  private String mDescripcion;
  private String mExersice;
  private int mLogo;
  private int mImage;

  public Entrenamiento(int mId, String mName, String mDescripcion, String mExersice, int mImage) {
    this.mId = mId;
    this.mName = mName;
    this.mDescripcion = mDescripcion;
    this.mExersice = mExersice;
    this.mImage = mImage;
  }

  public Entrenamiento(String mName, String mDescripcion, String mExersice, int mImage) {
    this.mName = mName;
    this.mDescripcion = mDescripcion;
    this.mExersice = mExersice;
    this.mImage = mImage;
  }

  public void setmId(int mId) {
    this.mId = mId;
  }

  public void setmName(String mName) {
    this.mName = mName;
  }

  public void setmDescripcion(String mDescripcion) {
    this.mDescripcion = mDescripcion;
  }

  public String getmExersice() {
    return mExersice;
  }

  public void setmExersice(String mExersice) {
    this.mExersice = mExersice;
  }

  public void setmLogo(int mLogo) {
    this.mLogo = mLogo;
  }

  public void setmImage(int mImage) {
    this.mImage = mImage;
  }

  public int getmId() {
    return mId;
  }

  public String getmName() {
    return mName;
  }

  public String getmDescripcion() {
    return mDescripcion;
  }

  public int getmLogo() {
    return mLogo;
  }

  public int getmImage() {
    return mImage;
  }

  protected Entrenamiento(Parcel in) {
    mId = in.readInt();
    mName = in.readString();
    mDescripcion = in.readString();
    mExersice = in.readString();
    mLogo = in.readInt();
    mImage = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(mId);
    dest.writeString(mName);
    dest.writeString(mDescripcion);
    dest.writeString(mExersice);
    dest.writeInt(mLogo);
    dest.writeInt(mImage);
  }

  @SuppressWarnings("unused")
  public static final Creator<Entrenamiento> CREATOR = new Creator<Entrenamiento>() {
    @Override
    public Entrenamiento createFromParcel(Parcel in) {
      return new Entrenamiento(in);
    }

    @Override
    public Entrenamiento[] newArray(int size) {
      return new Entrenamiento[size];
    }
  };
}