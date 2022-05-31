package com.application.todoapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDB extends RoomDatabase {
    //abstract for room db condition

    private static NoteDB instance;
    public abstract NoteDao noteDao();

    public static synchronized NoteDB getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), NoteDB.class, "notes_database") //my_notes
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
