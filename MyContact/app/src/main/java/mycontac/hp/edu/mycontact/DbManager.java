package mycontac.hp.edu.mycontact;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by hanla on 2017-08-04.
 */

public class DbManager extends ContentProvider {

    private Context context;
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private final int DB_VERSION = 2;

    /* 컨텐츠 프로바이더로 변경 */
    @Override
    public boolean onCreate() {
        this.helper = new MyHelper(getContext(), "data.db", DB_VERSION);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor = this.helper.getReadableDatabase().query(TABLES.CONTACT.toString(), null, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) { //이건고급
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //아래와 다르게 contentValues 를 파라미터로 받는다. 바로넣어주면됨
        long id = this.helper.getWritableDatabase().insert(TABLES.CONTACT.toString(), null, contentValues);
        return uri.withAppendedPath(uri, id + "");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return this.helper.getWritableDatabase().delete(TABLES.CONTACT.toString(), selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        return this.helper.getWritableDatabase().update(TABLES.CONTACT.toString(), contentValues, selection, selectionArgs);
    }

    private enum TABLES {
        CONTACT("CONTACT");
        private String tableName;
        TABLES(String tableName) { this.tableName = tableName; }
        @Override public String toString() { return this.tableName; }
    }

    class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, /*데이터베이스 이름*/String name, int version) {
            super(context, name, null, version);
        }

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) { //처음실행
            Constants.printDebug(DbManager.this.context, "DbManager.MyHelper.onCreate()");
            sqLiteDatabase.execSQL("create table " + TABLES.CONTACT.toString() + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, tel TEXT NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //버전이 다른경우 실행
            Constants.printDebug(DbManager.this.context, "DbManager.MyHelper.onUpgrade()");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLES.CONTACT.toString());
            sqLiteDatabase.execSQL("create table " + TABLES.CONTACT.toString() + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, tel TEXT NOT NULL)");
        }
    }

    public DbManager() {
    }
    public DbManager(Context context) {
        this.context = context;
        Constants.printDebug(this.context, "DbManager.DbManager()");
        this.helper = new MyHelper(this.context, "data.db", this.DB_VERSION);
    }//END OF FUCNTION

    public void insert(String name, String tel) {
        Constants.printDebug(this.context, "DbManager.insert() : " + name + ", " + tel);
        this.db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("TEL", tel);
        this.db.insert(TABLES.CONTACT.toString(), null, cv);
    }//END OF FUCNTION

    public void update(int id, String name, String tel) {
        Constants.printDebug(this.context, "DbManager.update() : " + id + ", " + name + ", " + tel);
        this.db = this.helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("TEL", tel);
        this.db.update(TABLES.CONTACT.toString(), cv, "_id = " + id, null);
    }//END OF FUCNTION

    public void delete(int id) {
        Constants.printDebug(this.context, "DbManager.delete() : " + id);
        this.db = this.helper.getWritableDatabase();
        this.db.delete(TABLES.CONTACT.toString(), "_id = " + id, null);
    }//END OF FUCNTION

    public Cursor query() {
        Constants.printDebug(this.context, "DbManager.query()");
        this.db = this.helper.getReadableDatabase();
        Cursor cursor = this.db.query(TABLES.CONTACT.toString(), null, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }//END OF FUCNTION

    public Cursor query(String name) {
        Constants.printDebug(this.context, "DbManager.query() : " + name);

        return null;
    }//END OF FUCNTION

}//END OF CLASS
