package com.example.test_for_children.Test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class TestBase {

    private final String NAME_BASE = "test.db";
    private final String TABLE_TEST = "table_test";

    private final String KEY_ID = "_id";
    private final String QUESTION = "question";
    private final String ANSWER_A = "answer_a";
    private final String ANSWER_B = "answer_b";
    private final String ANSWER_C = "answer_c";
    private final String ANSWER_D = "answer_d";
    private final String IS_ANSWER = "is_answer";

    public TestBase(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String _KEY_ID = KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";
        String _QUESTION = QUESTION + " TEXT";
        String _ANSWER_A = ANSWER_A + " TEXT";
        String _ANSWER_B = ANSWER_B + " TEXT";
        String _ANSWER_C = ANSWER_C + " TEXT";
        String _ANSWER_D = ANSWER_D + " TEXT";
        String _IS_ANSWER = IS_ANSWER + " INTEGER";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TEST
                + " ("+_KEY_ID+", "+_QUESTION+", "+_ANSWER_A+", "+_ANSWER_B+", "+_ANSWER_C+", "+_ANSWER_D+", "+_IS_ANSWER+")");
        db.close();
    }

    public boolean saveOnlyTest(Context context, OnlyTest onlyTest){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put(QUESTION, onlyTest.getQuestion());
        values.put(ANSWER_A, onlyTest.getAnswers()[0]);
        values.put(ANSWER_B, onlyTest.getAnswers()[1]);
        values.put(ANSWER_C, onlyTest.getAnswers()[2]);
        values.put(ANSWER_D, onlyTest.getAnswers()[3]);
        values.put(IS_ANSWER, onlyTest.getIsAnswer());
        long index = db.insert(TABLE_TEST, null, values);
        db.close();
        return index != -1;
    }

    public ArrayList<OnlyTest> getAllOnlyTest(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        ArrayList<OnlyTest> list = new ArrayList<>();

        if (query.moveToFirst()){
            do {
                OnlyTest onlyTest = new OnlyTest();
                onlyTest.setQuestion(query.getString(1));
                String[] answers = OnlyTest.emptyAnswers();
                answers[0] = query.getString(2);
                answers[1] = query.getString(3);
                answers[2] = query.getString(4);
                answers[3] = query.getString(5);
                onlyTest.setAnswers(answers);
                onlyTest.setIsAnswer(query.getInt(6));
                list.add(onlyTest);
            }while (query.moveToNext());
        }
        query.close();
        db.close();
        return list;
    }

    public OnlyTest getOnlyTest(Context context, int index){

        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        OnlyTest onlyTest = new OnlyTest();

        if (query.moveToPosition(index)) {
            onlyTest.setQuestion(query.getString(1));
            String[] answers = OnlyTest.emptyAnswers();
            answers[0] = query.getString(2);
            answers[1] = query.getString(3);
            answers[2] = query.getString(4);
            answers[3] = query.getString(5);
            onlyTest.setAnswers(answers);
            onlyTest.setIsAnswer(query.getInt(6));
        }

        query.close();
        db.close();

        return onlyTest;
    }

    public boolean editTest(Context context, OnlyTest onlyTest, int index){

        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        int indexUpdate = 0;

        if (query.moveToPosition(index)) {
            String value = query.getString(0);

            ContentValues values = new ContentValues();
            values.put(QUESTION, onlyTest.getQuestion());
            values.put(ANSWER_A, onlyTest.getAnswers()[0]);
            values.put(ANSWER_B, onlyTest.getAnswers()[1]);
            values.put(ANSWER_C, onlyTest.getAnswers()[2]);
            values.put(ANSWER_D, onlyTest.getAnswers()[3]);
            values.put(IS_ANSWER, onlyTest.getIsAnswer());

            indexUpdate = db.update(TABLE_TEST, values, KEY_ID + "=?", new String[] {value});

        }

        query.close();
        db.close();

        return indexUpdate == 1;
    }

    public boolean deliteOnlyTest(Context context, int index){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        int indexUpdate = 0;

        if (query.moveToPosition(index)) {
            String value = query.getString(0);
            indexUpdate = db.delete(TABLE_TEST, KEY_ID + "=?", new String[]{value});
        }

        return indexUpdate == 1;
    }

    public int getCountTest(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        int result = query.getCount();;
        query.close();
        db.close();
        return result;
    }

    public boolean deleteAllTest(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String sql = "SELECT * FROM " +TABLE_TEST+";";

        Cursor query = db.rawQuery(sql, null);

        int indexUpdate = 0;


        indexUpdate = db.delete(TABLE_TEST, null, null);

        return indexUpdate != -1;
    }

    /**
    public boolean delete(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_BASE, MODE_PRIVATE, null);
        String path = db.getPath();
        db.close();
        File file = new File(path);
        return SQLiteDatabase.deleteDatabase(file);
    }
     */
}
