package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database;

public class RutinaDBDef {
    //Nombre del esquema de Base de Datos
    public static final String DATABASE_NAME = "RutinaDB";
    //Version de la Base de Datos (Este parámetro es importante  )
    public static final int DATABASE_VERSION = 1;

    //Una clase estatica en la que se definen las propiedaes que determinan la tabla Notes
    // y sus 4 columnas
    public static class RUTINA {
        //Nombre de la tabla
        public static final String TABLE_NAME = "rutina";
        //Nombre de las Columnas que contiene la tabla
        public static final String ID_COL = "id";
        public static final String NOMBRE_COL = "nombre";
        public static final String DIA_COL = "dia";
        public static final String LIST_ENT_COL = "entrenamientos";

    }

    //Setencia SQL que permite crear la tabla Notes
    public static final String RUTINA_TABLE_CREATE =
            "CREATE TABLE " + RUTINA.TABLE_NAME + " (" +
                    RUTINA.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RUTINA.NOMBRE_COL + " TEXT, " +
                    RUTINA.DIA_COL + " TEXT, " +
                    RUTINA.LIST_ENT_COL + " TEXT);";

    //Setencia SQL que permite eliminar la tabla Notes
    public static final String RUTINA_TABLE_DROP = "DROP TABLE IF EXISTS " + RUTINA.TABLE_NAME;
}
