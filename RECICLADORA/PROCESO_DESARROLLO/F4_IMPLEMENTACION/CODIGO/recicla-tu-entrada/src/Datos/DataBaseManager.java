package Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	public static final String TABLE_NAME="usuario";
	
	public static final String CN_ID="idUsuario";
	public static final String CN_NAME="nombre";
	public static final String CN_LNAME="apellidos";
	public static final String CN_BIDATE="fechaNacimiento";
	public static final String CN_HUELLA="huella";
	//
	public static final String CREATETABLE="create table "+TABLE_NAME+" ( "+
			CN_ID+" integer primary key autoincrement, "+
			CN_NAME+" text ot null, "+
			CN_LNAME+" text not null, "+
			CN_BIDATE+" date not null, "+
			CN_HUELLA+" blob null);";
	private DbHelper helper;
	private static SQLiteDatabase db;
	
	public DataBaseManager(Context context){
		 helper =new DbHelper(context);
		 db=helper.getWritableDatabase();
		 
	}
	
	public static ContentValues generarContentValues(String nombre,String apellidos,String FNacimiento){
		ContentValues valores = new ContentValues();
		valores.put(CN_NAME,nombre);
		valores.put(CN_LNAME,apellidos);
		valores.put(CN_BIDATE,FNacimiento);
		return valores;
	}
	
	
	
	
	public void insertar(String nombre,String apellidos,String FNacimiento){
		db.insert(TABLE_NAME, null,generarContentValues(nombre, apellidos,FNacimiento));
		
	}
	
}
