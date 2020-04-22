package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.util.ArrayList;

public class EntrenamientosDBHelper extends SQLiteOpenHelper {

    //Definimos in Contructor para Instanciar el Database Helper
    public EntrenamientosDBHelper(Context context) {
        super(context, EntrenamientosDBDef.DATABASE_NAME, null, EntrenamientosDBDef.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas en la Base de datos
        db.execSQL(EntrenamientosDBDef.ENTRENAMIENTOS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //El método onUpgrade se ejecuta cada vez que recompilamos e instalamos la app con un
        //nuevo numero de version de base de datos (DATABASE_VERSION), de tal mamera que en este
        // método lo que hacemos es:
        // 1. Con esta sentencia borramos la tabla "notes"
        db.execSQL(EntrenamientosDBDef.ENTRENAMIENTOS_TABLE_DROP);

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

    public void insertEntrenamiento(Entrenamiento entrenamiento){
        //Con este método insertamos las notas nuevas que el usuario vaya creando

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos un obejto de tipo ContentValues para agregar los pares
        // de Claves de Columna y Valor
        ContentValues values = new ContentValues();
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.NOMBRE_COL_ENT, entrenamiento.getmName());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.DESCRIPCION_COL_ENT, entrenamiento.getmDescripcion());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.EXERSICE_COL_ENT, entrenamiento.getmExersice());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.IMAGE_COL_ENT, entrenamiento.getmImage());
        //values.put(EntrenamientosDBDef.ENTRENAMIENTOS.LOGO_COL_ENT, rutina.getmName());


        // 3. Insertamos los datos en la tabla "notes"
        db.insert(EntrenamientosDBDef.ENTRENAMIENTOS.TABLE_NAME, null, values);

        // 4. Cerramos la conexión comn la BD
        db.close();
    }

    //Obtener uan Nota dado un ID
    public Entrenamiento getEntrenamientoById(int id){
        // Declaramos un objeto Note para instanciarlo con el resultado del query
        Entrenamiento aEntrenamiento = null;

        // 1. Obtenemos una reference de la BD con permisos de lectura
        SQLiteDatabase db = this.getReadableDatabase();

        //Definimos un array con los nombres de las columnas que deseamos sacar
        String[] COLUMNS = {EntrenamientosDBDef.ENTRENAMIENTOS.ID_COL_ENT, EntrenamientosDBDef.ENTRENAMIENTOS.NOMBRE_COL_ENT, EntrenamientosDBDef.ENTRENAMIENTOS.DESCRIPCION_COL_ENT, EntrenamientosDBDef.ENTRENAMIENTOS.IMAGE_COL_ENT};


        // 2. Contruimos el query
        Cursor cursor =
                db.query(EntrenamientosDBDef.ENTRENAMIENTOS.TABLE_NAME,  //Nomre de la tabla
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
            aEntrenamiento = new Entrenamiento(Integer.parseInt(cursor.getString(0))
                    ,cursor.getString(1)
                    ,cursor.getString(2)
                    ,cursor.getString(3)
                    ,Integer.parseInt(cursor.getString(4)));


        }


        // 5. Devolvemos le objeto Note
        return aEntrenamiento;
    }


    // Obtener todas las notas



    //Actualizar los datos en una nota
    public void updateEntrenamiento(Entrenamiento entrenamiento) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos el objeto ContentValues con las claves "columna"/valor
        // que se desean actualizar

        ContentValues values = new ContentValues();
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.NOMBRE_COL_ENT, entrenamiento.getmName());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.DESCRIPCION_COL_ENT, entrenamiento.getmDescripcion());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.EXERSICE_COL_ENT, entrenamiento.getmExersice());
        values.put(EntrenamientosDBDef.ENTRENAMIENTOS.IMAGE_COL_ENT, entrenamiento.getmImage());
        //values.put(EntrenamientosDBDef.ENTRENAMIENTOS.LOGO_COL_ENT, rutina.getmName());

        // 3. Actualizamos el registro con el método update el cual devuelve la cantidad
        // de registros actualizados
        int i = db.update(EntrenamientosDBDef.ENTRENAMIENTOS.TABLE_NAME, //table
                values, // column/value
                EntrenamientosDBDef.ENTRENAMIENTOS.ID_COL_ENT+" = ?", // selections
                new String[] { String.valueOf(entrenamiento.getmId()) }); //selection args

        // 4. Cerramos la conexión a la BD
        db.close();

        // Devolvemos la cantidad de registros actualizados
    }

    // Borrar una Nota
    public void deleteEntrenamiento(Entrenamiento entrenamiento) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Borramos el registro
        db.delete(EntrenamientosDBDef.ENTRENAMIENTOS.TABLE_NAME,
                EntrenamientosDBDef.ENTRENAMIENTOS.ID_COL_ENT+" = ?",
                new String[] { String.valueOf(entrenamiento.getmId()) });

        // 3. Cerramos la conexión a la Bd
        db.close();
    }

    public ArrayList<Entrenamiento> getAllEntrenamientos() {
        //Instanciamos un Array para llenarlo con todos los objetos Notes que saquemos de la BD
        ArrayList<Entrenamiento> entrenamientos = new ArrayList<>();
        // 1. Aramos un String con el query a ejecutar
        String query = "SELECT  * FROM " + EntrenamientosDBDef.ENTRENAMIENTOS.TABLE_NAME;

        // 2. Obtenemos una reference de la BD con permisos de escritura y ejecutamos el query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Iteramos entre cada uno de olos registros y agregarlos al array de Notas

        if (cursor.moveToFirst()) {
            do {
                // Add book to books
                entrenamientos.add(new Entrenamiento(Integer.parseInt(cursor.getString(0))
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3)
                        ,Integer.parseInt(cursor.getString(4))));
            } while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();
        // Devolvemos las notas encontradas o un array vacio en caso de que no se encuentre nada
        return entrenamientos;
    }


}
