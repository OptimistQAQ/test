package com.example.test.utils.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.example.test.bean.People;

/**
 * @author 65667
 */
public class DBAdapter {

	private static final String DB_NAME = "people.db";
	private static final String DB_TABLE = "peopleinfo";
	private static final int DB_VERSION = 1;
	 
	public static final String KEY_ID = "_id";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String HEIGHT = "height";
	
	private SQLiteDatabase db;
	private final Context context;
	private DBOpenHelper dbOpenHelper;
	
	public DBAdapter(Context _context) {
	    context = _context;
	  }

	  /** Close the database */
	  public void close() {
		  if (db != null){
			  db.close();
			  db = null;
		  }
		}

	  /** Open the database */
	  public void open() throws SQLiteException {  
		  //创建一个DatabaseHelper对象
		  dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
		  //只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者getWritableDatabase()方法才会调用onCreate()方法
		  try {
			  db = dbOpenHelper.getWritableDatabase();
		  }
		  catch (SQLiteException ex) {
			  db = dbOpenHelper.getReadableDatabase();
		  }	  
		}
	  
	
	  public long insert(People people) {
		//生成ContentValues对象
	    ContentValues newValues = new ContentValues();
	    //向该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和键的类型匹配
	    newValues.put(NAME, people.Name);
	    newValues.put(AGE, people.Age);
	    newValues.put(HEIGHT, people.Height);
	    
	    return db.insert(DB_TABLE, null, newValues);
	  }


	  public People[] queryAllData() { 
		  //参数2：查询的列名
		  //参数3：查询条件
		  //参数4：查询条件的参数
		  //参数5：分组
		  //参数6；分组结果的限制条件
		  //参数7：查询结果的排序
		  //返回值：查询结果表的游标
		  Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, NAME, AGE, HEIGHT}, 
				  null, null, null, null, null);
		  return ConvertToPeople(results);   
	  }
	  
	  public People[] queryOneData(long id) {  
		  Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, NAME, AGE, HEIGHT}, 
				  KEY_ID + "=" + id, null, null, null, null);
		  return ConvertToPeople(results);   
	  }
	  
	  private People[] ConvertToPeople(Cursor cursor){
		  int resultCounts = cursor.getCount();
		  if (resultCounts == 0 || !cursor.moveToFirst()){
			  return null;
		  }
		  People[] peoples = new People[resultCounts];
		  for (int i = 0 ; i<resultCounts; i++){
			  peoples[i] = new People();
			  peoples[i].ID = cursor.getInt(0);
			  peoples[i].Name = cursor.getString(cursor.getColumnIndex(NAME));
			  peoples[i].Age = cursor.getInt(cursor.getColumnIndex(AGE));
			  peoples[i].Height = cursor.getFloat(cursor.getColumnIndex(HEIGHT));
			  
			  cursor.moveToNext();//将游标向下移动一位
		  }	  
		  return peoples; 
	  }
	  
	  public long deleteAllData() {
		  return db.delete(DB_TABLE, null, null);
	  }

	  public long deleteOneData(long id) {
		  return db.delete(DB_TABLE,  KEY_ID + "=" + id, null);
	  }
 
	  //相当于执行SQL语句中的update语句
	  //update table_name SET XXCOL=XXX WHERE XXCOL=XX...
	  public long updateOneData(long id , People people){
		  ContentValues updateValues = new ContentValues();	  
		  updateValues.put(NAME, people.Name);
		  updateValues.put(AGE, people.Age);
		  updateValues.put(HEIGHT, people.Height);
		  //参数1：更新的表名
		  //参数2：ContentValues对象
		  //参数3：WHERE子句
		  //参数4：null表示更新整行
		  return db.update(DB_TABLE, updateValues,  KEY_ID + "=" + id, null);
	  }
	  
		/** 静态Helper类，用于建立、更新和打开数据库*/
	  //DBOpenHelper作为访问SQLite的助手类，提供两方面功能：
	  //（1）getReadableDatabase(),getWritableDatabase()可以获得SQLiteDatabase对象，通过该对象对数据库进行操作
	  //（2）提供onCreate()和onUpgrade()两个回调函数，允许我们在创建和升级数据库时，进行自己的操作
	  private static class DBOpenHelper extends SQLiteOpenHelper {
		  //在SQLiteOpenHelper的子类中必须有该构造函数
		  public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		    super(context, name, factory, version);
		  }

		  private static final String DB_CREATE = "create table " + 
		    DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
		    NAME+ " text not null, " + AGE+ " integer," + HEIGHT + " float);";

		  @Override
		  //该函数在第一次创建数据库时候执行，实际上是第一次得到SQLiteDatabase对象的时候，才会调用。
		  public void onCreate(SQLiteDatabase _db) {
		    _db.execSQL(DB_CREATE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {		    
		    _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
		    onCreate(_db);
		  }
		}
	}