package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Create a new note.
    public void NewNote(View view)
    {
        Intent createNote = new Intent(this, CreateNote.class);
        startActivity(createNote);
    }

    // Show all notes.
    public void ShowNotes(View view)
    {
        Intent showNotes = new Intent(this, ShowNotes.class);
        startActivity(showNotes);
    }

    Button createNewNote, showAllNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNewNote = findViewById(R.id.createNewNote);
        showAllNotes = findViewById(R.id.showAllNotes);
    }
}