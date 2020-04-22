package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.util.ArrayList;

public class RutinaDBHelper extends SQLiteOpenHelper {

    //Definimos in Contructor para Instanciar el Database Helper
    public RutinaDBHelper(Context context) {
        super(context, RutinaDBDef.DATABASE_NAME, null, RutinaDBDef.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas en la Base de datos
        db.execSQL(RutinaDBDef.RUTINA_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //El método onUpgrade se ejecuta cada vez que recompilamos e instalamos la app con un
        //nuevo numero de version de base de datos (DATABASE_VERSION), de tal mamera que en este
        // método lo que hacemos es:
        // 1. Con esta sentencia borramos la tabla "notes"
        db.execSQL(RutinaDBDef.RUTINA_TABLE_DROP);

        // 2. Con esta sentencia llamamos de nuevo al método onCreate para que se cree de nuevo
        //la tabla "notes" la cual seguramente al cambair de versión ha tenido modifciaciones
        // en cuanto a su estructura de columnas
        this.onCreate(db);
    }




    /*
    * OPERACIONES CRUD (Create, Read, Update, Delete)
    * A partir de aquí se definen los métodos para insertar, leer, actualizar y borrar registros de
    * la base de datos (BD)
    * */

    public void insertRutina(Rutina rutina){
        //Con este método insertamos las notas nuevas que el usuario vaya creando

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos un obejto de tipo ContentValues para agregar los pares
        // de Claves de Columna y Valor
        ContentValues values = new ContentValues();
        values.put(RutinaDBDef.RUTINA.NOMBRE_COL, rutina.getmName());
        values.put(RutinaDBDef.RUTINA.DIA_COL, rutina.getmDia());
        values.put(RutinaDBDef.RUTINA.LIST_ENT_COL, rutina.getmListEntrenamiento());


        // 3. Insertamos los datos en la tabla "notes"
        db.insert(RutinaDBDef.RUTINA.TABLE_NAME, null, values);

        // 4. Cerramos la conexión comn la BD
        db.close();
    }

    //Obtener uan Nota dado un ID
    public Rutina getRutinaById(int id){
        // Declaramos un objeto Note para instanciarlo con el resultado del query
        Rutina aRutina = null;

        // 1. Obtenemos una reference de la BD con permisos de lectura
        SQLiteDatabase db = this.getReadableDatabase();

        //Definimos un array con los nombres de las columnas que deseamos sacar
        String[] COLUMNS = {RutinaDBDef.RUTINA.ID_COL, RutinaDBDef.RUTINA.NOMBRE_COL, RutinaDBDef.RUTINA.DIA_COL, RutinaDBDef.RUTINA.LIST_ENT_COL};


        // 2. Contruimos el query
        Cursor cursor =
                db.query(RutinaDBDef.RUTINA.TABLE_NAME,  //Nomre de la tabla
                        COLUMNS, // b. Nombre de las Columnas
                        " id = ?", // c. Columnas de la clausula WHERE
                        new String[] { String.valueOf(id) }, // d. valores de las columnas de la clausula WHERE
                        null, // e. Clausula Group by
                        null, // f. Clausula having
                        null, // g. Clausula order by
                        null); // h. Limte de regsitros

        // 3. Si hemos obtenido algun resultado entonces sacamos el primero de ellos ya que se supone
        //que ha de existir un solo registro para un id
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. Contruimos el objeto Note
            aRutina = new Rutina(Integer.parseInt(cursor.getString(0))
                    ,cursor.getString(1)
                    ,cursor.getString(2)
                    ,cursor.getString(3));


        }


        // 5. Devolvemos le objeto Note
        return aRutina;
    }


    // Obtener todas las notas



    //Actualizar los datos en una nota
    public void updateRutina(Rutina rutina) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos el objeto ContentValues con las claves "columna"/valor
        // que se desean actualizar

        ContentValues values = new ContentValues();
        values.put(RutinaDBDef.RUTINA.NOMBRE_COL, rutina.getmName());
        values.put(RutinaDBDef.RUTINA.DIA_COL, rutina.getmDia());
        values.put(RutinaDBDef.RUTINA.LIST_ENT_COL, rutina.getmListEntrenamiento());

        // 3. Actualizamos el registro con el método update el cual devuelve la cantidad
        // de registros actualizados
        int i = db.update(RutinaDBDef.RUTINA.TABLE_NAME, //table
                values, // column/value
                RutinaDBDef.RUTINA.ID_COL+" = ?", // selections
                new String[] { String.valueOf(rutina.getmId()) }); //selection args

        // 4. Cerramos la conexión a la BD
        db.close();

        // Devolvemos la cantidad de registros actualizados
    }

    // Borrar una Nota
    public void deleteRutina(Rutina cuenta) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Borramos el registro
        db.delete(RutinaDBDef.RUTINA.TABLE_NAME,
                RutinaDBDef.RUTINA.ID_COL+" = ?",
                new String[] { String.valueOf(cuenta.getmId()) });

        // 3. Cerramos la conexión a la Bd
        db.close();
    }

    public ArrayList<Rutina> getAllRutinas() {
        //Instanciamos un Array para llenarlo con todos los objetos Notes que saquemos de la BD
        ArrayList<Rutina> rutinas = new ArrayList<>();

        // 1. Aramos un String con el query a ejecutar
        String query = "SELECT  * FROM " + RutinaDBDef.RUTINA.TABLE_NAME;

        // 2. Obtenemos una reference de la BD con permisos de escritura y ejecutamos el query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Iteramos entre cada uno de olos registros y agregarlos al array de Notas

        if (cursor.moveToFirst()) {
            do {
                Rutina aRutina = new Rutina(Integer.parseInt(cursor.getString(0))
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3));

                // Add book to books
                rutinas.add(aRutina);
            } while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();


        // Devolvemos las notas encontradas o un array vacio en caso de que no se encuentre nada
        return rutinas;
    }


}
