package com.csfu.cpsc41101.cs411assignment3.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class  PersistentObject {

    public abstract void insert(SQLiteDatabase db);
    public abstract void createTable(SQLiteDatabase db);
    public abstract void initFrom(SQLiteDatabase db, Cursor c);
}
