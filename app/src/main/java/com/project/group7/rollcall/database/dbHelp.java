package com.project.group7.rollcall.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;

import com.project.group7.rollcall.model.Daily;
import com.project.group7.rollcall.model.Date;
import com.project.group7.rollcall.model.Student;

import java.util.ArrayList;

public class dbHelp extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="roll.db";

    public static final String TABLE_DATE="date";
    public static final String COLUMN_DATE_ID="date_id";
    public static final String COLUMN_DATE_DATE="date";
    public static final String COLUMN_DATE_SUBJECTS="subjects";
    public static final String COLUMN_DATE_FOUND="found";


    public static final String TABLE_STUDENT="student";
    public static final String COLUMN_STUDENT_ID="student_id";
    public static final String COLUMN_STUDENT_ROLL="roll";
    public static final String COLUMN_STUDENT_NAME="name";
    public static final String COLUMN_STUDENT_YEAR="year";
    public static final String COLUMN_STUDENT_ADDRESS="address";
    public static final String COLUMN_STUDENT_PHONE="phone";

    public static final String TABLE_DAILY="daily";
    public static final String COLUMN_DAILY_ID="daily_id";
    public static final String COLUMN_DAILY_DATE="dailydate";
    public static final String COLUMN_DAILY_STUDENTS="dailystudents";
    public static final String COLUMN_DAILY_PRESENTS="dailypresents";
    public static final String COLUMN_DAILY_SUBJECT="dailysubject";
    public static final String COLUMN_DAILY_COUNT="dailycount";

    public dbHelp(@Nullable Context context, @Nullable String name,
                  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+ TABLE_DATE +"("+
                COLUMN_DATE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_DATE_DATE+" TEXT , "+
                COLUMN_DATE_SUBJECTS+" TEXT , "+
                COLUMN_DATE_FOUND+" TEXT "+
                ")";
        sqLiteDatabase.execSQL(query);

        String queryStudent="CREATE TABLE "+ TABLE_STUDENT +"("+
                COLUMN_STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_STUDENT_ROLL+" TEXT , "+
                COLUMN_STUDENT_NAME+" TEXT , "+
                COLUMN_STUDENT_YEAR+" TEXT , "+
                COLUMN_STUDENT_ADDRESS+" TEXT , "+
                COLUMN_STUDENT_PHONE+" TEXT "+
                ")";
        sqLiteDatabase.execSQL(queryStudent);

        String queryDaily="CREATE TABLE "+ TABLE_DAILY +"("+
                COLUMN_DAILY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_DAILY_DATE+" TEXT , "+
                COLUMN_DAILY_STUDENTS+" TEXT , "+
                COLUMN_DAILY_PRESENTS+" TEXT , "+
                COLUMN_DAILY_SUBJECT+" TEXT , "+
                COLUMN_DAILY_COUNT+" TEXT "+
                ")";
        sqLiteDatabase.execSQL(queryDaily);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILY);
        onCreate(sqLiteDatabase);
    }

    public void addDateData(Date date){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_DATE_DATE,date.getDate().toString());
        contentValues.put(COLUMN_DATE_SUBJECTS,date.getSubjects().toString());
        contentValues.put(COLUMN_DATE_FOUND,date.getFound().toString());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_DATE,null,contentValues);
        db.close();
    }

    public void addStudentData(Student students){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_STUDENT_ROLL,students.getRoll().toString());
        contentValues.put(COLUMN_STUDENT_NAME,students.getName().toString());
        contentValues.put(COLUMN_STUDENT_YEAR,students.getYear().toString());
        contentValues.put(COLUMN_STUDENT_ADDRESS,students.getAddress().toString());
        contentValues.put(COLUMN_STUDENT_PHONE,students.getPh().toString());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_STUDENT,null,contentValues);
        db.close();
    }

    public void addDailyData(Daily daily){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_DAILY_DATE,daily.getDate().toString());
        contentValues.put(COLUMN_DAILY_STUDENTS,daily.getStudents().toString());
        contentValues.put(COLUMN_DAILY_PRESENTS,daily.getPresents().toString());
        contentValues.put(COLUMN_DAILY_SUBJECT,daily.getSubject().toString());
        contentValues.put(COLUMN_DAILY_COUNT,daily.getCount().toString());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_DAILY,null,contentValues);
        db.close();
    }

    public Daily getDailyData(ArrayList<String> list){
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DAILY;
        Cursor cursor=db.rawQuery(query,null);
        Daily daily=null;
        if(cursor.moveToFirst())
        {
            do {
                if (list.get(0).equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))))&&
                        list.get(1).equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))))
                {
                    daily=new Daily();
                    daily.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                    daily.setDate(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                    daily.setStudents(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                    daily.setPresents(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                    daily.setSubject(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
                    daily.setCount(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
                }
            }while (cursor.moveToNext());
        }
        db.close();
        return daily;
    }

    public ArrayList<Daily> getAllDailyData(int month){
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DAILY;
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<Daily> dailies=new ArrayList<Daily>();
        Daily daily=null;
        if(cursor.moveToFirst())
        {
            do {
                    String date=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
                    Date d=getOneDateData(date);
                    date=d.getDate().substring(0,2);
                    if (month==Integer.parseInt(date)) {
                        daily = new Daily();
                        daily.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                        daily.setDate(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                        daily.setStudents(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                        daily.setPresents(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                        daily.setSubject(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
                        daily.setCount(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
                        dailies.add(daily);
                    }

            }while (cursor.moveToNext());
        }
        db.close();
        return dailies;
    }

    public Date getOneDateData(String id){
        Date date=new Date();

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DATE;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                if (id.equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))))){
                    date.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                    date.setDate(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                    date.setSubjects(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                    date.setFound(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                }
            }while (cursor.moveToNext());
        }
        db.close();

        return date;
    }

    public ArrayList<Date> getDateData(){
        ArrayList<Date> dateList=new ArrayList<Date>();
        Date date;

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DATE;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do
            {
                date=new Date();
                date.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                date.setDate(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                date.setSubjects(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                date.setFound(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                dateList.add(date);
            }while (cursor.moveToNext());
        }

        db.close();
        return dateList;
    }

    public ArrayList<Student> getStudentData(){
        ArrayList<Student> str=new ArrayList<Student>();

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_STUDENT;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do
            {
                Student stu=new Student();
                stu.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                stu.setRoll(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                stu.setName(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                stu.setYear(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                stu.setAddress(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
                stu.setPh(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
                str.add(stu);
            }while (cursor.moveToNext());
        }

        db.close();
        return str;
    }

    public Student getStudentDetailData(String id){
        Student stu=null;
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_STUDENT;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do{
                if (id.equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))))) {
                    stu = new Student();
                    stu.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                    stu.setRoll(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                    stu.setName(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                    stu.setYear(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
                    stu.setAddress(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
                    stu.setPh(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
                }
            }while (cursor.moveToNext());

        }
        db.close();
        return stu;
    }

    public String getStudentId(int id){
        String str=new String();
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_STUDENT;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToPosition(id))
        {
            str=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))).toString();
        }
        db.close();
        return str;
    }

    public void deleteStudent(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_STUDENT,COLUMN_STUDENT_ID + " = ? ",new String[]{id});
        db.close();
    }

    public void deleteDaily(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_DAILY,COLUMN_DAILY_ID + " = ? ",new String[]{id});
        db.close();
    }

    public void deleteDate(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_DATE,COLUMN_DATE_ID + " = ? ",new String[]{id});
        db.close();
    }


    public int updateStudentData(Student stu, String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_ROLL, stu.getRoll().toString());
        contentValues.put(COLUMN_STUDENT_NAME,stu.getName().toString());
        contentValues.put(COLUMN_STUDENT_YEAR, stu.getYear().toString());
        contentValues.put(COLUMN_STUDENT_ADDRESS, stu.getAddress().toString());
        contentValues.put(COLUMN_STUDENT_PHONE,stu.getPh().toString());

        int i=db.update(TABLE_STUDENT, contentValues, COLUMN_STUDENT_ID + " =  ? ",
                new String[] {id});
        db.close();
        return i;
    }

    public int updateDailyData(Daily daily)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_DAILY_DATE,daily.getDate().toString());
        contentValues.put(COLUMN_DAILY_STUDENTS,daily.getStudents().toString());
        contentValues.put(COLUMN_DAILY_PRESENTS,daily.getPresents().toString());
        contentValues.put(COLUMN_DAILY_SUBJECT,daily.getSubject().toString());
        contentValues.put(COLUMN_DAILY_COUNT,daily.getCount().toString());

        int i=db.update(TABLE_DAILY, contentValues, COLUMN_DAILY_ID + " =  ? ",
                new String[] {daily.getId()});
        db.close();
        return i;
    }

    public int updateDateData(Date date){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_DATE_SUBJECTS,date.getSubjects().toString());

        int i=db.update(TABLE_DATE, contentValues, COLUMN_DATE_ID + " =  ? ",
                new String[] {date.getId()});
        db.close();
        return i;
    }

    public Date checkDate(String date){
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DATE;
        Cursor cursor=db.rawQuery(query,null);
        Date checkDate=new Date();
        boolean found=false;

        if(cursor.moveToFirst())
        {
            do
            {
                if (date.equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))))
                {
                    checkDate.setId(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
                    checkDate.setDate(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                    checkDate.setSubjects(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                    found=true;
                }
            }while (cursor.moveToNext() && found==false);
        }

        if (found){
            checkDate.setFound("true");
        }
        else {
            checkDate.setFound("false");
        }

        db.close();
        return checkDate;
    }

    public boolean checkDateByDate(String date){
        boolean found=false;

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DATE;
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do
            {
                if (date.equals(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))))
                {
                    found=true;
                }
            }while (cursor.moveToNext() && found==false);
        }

        return found;
    }

}
