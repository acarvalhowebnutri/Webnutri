package br.com.projetowebnutri.dell.teste250416;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 12/11/2015.
 */
public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login4.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
   /* public static final String DATABASE_CREATE ="CREATE TABLE LOGIN " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " USERNAME           TEXT    NOT NULL, " +
            " PASSWORD            INT     NOT NULL, " +
            " IDADE        CHAR(50), " +
            " PESO         REAL)";*/
     public static final String DATABASE_CREATE = "create table " + "LOGIN" + "( " + "ID" + " integer primary key autoincrement," + " USERNAME  text," + " PASSWORD text," + " IDADE text," + " PESO text " + ");";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    private SQLiteDatabase writableDatabase;



    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password, String idade, String peso) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("IDADE", idade);
        newValues.put("PESO", peso);


        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password, String idade, String peso) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("IDADE", idade);
        updatedValues.put("PESO", peso);


        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }

    public List<Usuario> buscar(String userName) {

        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"id", "nome", "senha"};
        writableDatabase = dbHelper.getWritableDatabase();
        SQLiteDatabase db = writableDatabase;
        Cursor cursor = db.query("usuario", colunas, null, null, null, null, "nome ASC");


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Usuario u = new Usuario();
                u.setNome(cursor.getString(0));
                u.setSenha(cursor.getString(1));
            } while (cursor.moveToNext());

        }
        return (list);


    }

    /*
    public Usuario read(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();

        }

        Usuario user = new Usuario();
        cursor.moveToFirst();
        user.setNome(cursor.getString(1));
        user.setSenha(cursor.getString(2));
        user.setPeso(cursor.getString(3));
        user.setIdade(cursor.getString(4));


        cursor.close();
        return user;
    }
*/
    public Usuario read(String userName) {
       Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null, null);


      /*  String[] colunas = new String[]{"USERNAME", "PASSWORD", "PESO", "IDADE"};
        writableDatabase = dbHelper.getWritableDatabase();
        SQLiteDatabase db = writableDatabase;
        Cursor cursor = db.query("LOGIN", colunas, null, null, null, null, "userName ASC");
*/
        Usuario user = new Usuario();

        if (cursor != null && cursor.moveToFirst()) {

            try {

                user.setNome(cursor.getString(1));
                user.setSenha(cursor.getString(2));
                user.setIdade(cursor.getString(3));
                user.setPeso(cursor.getString(4));



            } catch (Exception erro) {
            } finally {
                if ((cursor != null && !cursor.isClosed())) {
                    cursor.close();
                }
            }
        }



        return user;
    }

}
