package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database;

public class EntrenamientosDBDef {
    //Nombre del esquema de Base de Datos
    public static final String DATABASE_NAME = "EntrenamientosDB";
    //Version de la Base de Datos (Este parámetro es importante  )
    public static final int DATABASE_VERSION = 1;

    //Una clase estatica en la que se definen las propiedaes que determinan la tabla Notes
    // y sus 4 columnas
    public static class ENTRENAMIENTOS {
        //Nombre de la tabla
        public static final String TABLE_NAME = "rutina";
        //Nombre de las Columnas que contiene la tabla
        public static final String ID_COL_ENT = "id";
        public static final String NOMBRE_COL_ENT = "nombre";
        public static final String DESCRIPCION_COL_ENT = "descripcion";
        public static final String EXERSICE_COL_ENT = "exersice";
        public static final String IMAGE_COL_ENT = "image";
        public static final String LOGO_COL_ENT = "logo";

    }

    //Setencia SQL que permite crear la tabla Notes
    public static final String ENTRENAMIENTOS_TABLE_CREATE =
            "CREATE TABLE " + ENTRENAMIENTOS.TABLE_NAME + " (" +
                    ENTRENAMIENTOS.ID_COL_ENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ENTRENAMIENTOS.NOMBRE_COL_ENT + " TEXT, " +
                    ENTRENAMIENTOS.DESCRIPCION_COL_ENT + " TEXT, " +
                    ENTRENAMIENTOS.EXERSICE_COL_ENT + " TEXT, " +
                    ENTRENAMIENTOS.IMAGE_COL_ENT + " INTEGER, " +
                    ENTRENAMIENTOS.LOGO_COL_ENT + " INTEGER);";

    //Setencia SQL que permite eliminar la tabla Notes
    public static final String ENTRENAMIENTOS_TABLE_DROP = "DROP TABLE IF EXISTS " + ENTRENAMIENTOS.TABLE_NAME;
}
