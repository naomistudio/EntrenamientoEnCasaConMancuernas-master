package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets;

import android.os.Parcel;
import android.os.Parcelable;

public class Rutina implements Parcelable {
    private int mId;
    private String mName;
    private String mDia;
    private String mListEntrenamiento;
    private int mCountEntrenamiento;
    public Rutina() {}

    public Rutina(int mId,String mName, String mDia, String mListEntrenamiento) {
        this.mId = mId;
        this.mName = mName;
        this.mDia = mDia;
        this.mListEntrenamiento = mListEntrenamiento;
    }

    public Rutina(String mName, String mDia, String mListEntrenamiento) {
        this.mName = mName;
        this.mDia = mDia;
        this.mListEntrenamiento = mListEntrenamiento;
    }

    public String getmListEntrenamiento() {
        return mListEntrenamiento;
    }

    public void setmListEntrenamiento(String mListEntrenamiento) {
        this.mListEntrenamiento = mListEntrenamiento;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmCountEntrenamiento() {
        return mCountEntrenamiento;
    }

    public void setmCountEntrenamiento(int mCountEntrenamiento) {
        this.mCountEntrenamiento = mCountEntrenamiento;
    }

    public String getmName() {
        return mName;
    }

    public String getmDiaText() {
        String nRutina = this.mDia;
        switch (this.mDia){
            case "Lunes":
                nRutina = "Lun";
                break;
            case "Martes":
                nRutina = "Mar";
                break;
            case "Miercoles":
                nRutina = "Mie";
                break;
            case "Jueves":
                nRutina = "Jue";
                break;
            case "Viernes":
                nRutina = "Vie";
                break;
            case "Sabado":
                nRutina = "Sab";
                break;
            case "Domingo":
                nRutina = "Dom";
                break;
        }

        return nRutina;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDia() {

        return mDia;
    }

    public void setmDia(String mDia) {
        this.mDia = mDia;
    }

    protected Rutina(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDia = in.readString();
        mCountEntrenamiento = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mDia);
        dest.writeInt(mCountEntrenamiento);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Rutina> CREATOR = new Parcelable.Creator<Rutina>() {
        @Override
        public Rutina createFromParcel(Parcel in) {
            return new Rutina(in);
        }

        @Override
        public Rutina[] newArray(int size) {
            return new Rutina[size];
        }
    };
}
