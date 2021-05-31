package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNote extends AppCompatActivity implements View.OnClickListener
{
    // Declaring variables.
    EditText noteTitle, noteText;
    Button saveNote;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        // Initialising variables.
        noteTitle = findViewById(R.id.noteTitle);
        noteText = findViewById(R.id.noteText);
        saveNote = findViewById(R.id.saveNote);

        dbManager = new DBManager(this);
        dbManager.open();

        saveNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.saveNote:
                final String title = noteTitle.getText().toString();
                final  String text = noteText.getText().toString();

                dbManager.insert(title,text);

                // When redirecting to the list of notes if the activity being launched is already running in the current task,
                // then instead of launching a new instance of that activity, all of the other activities on top of it will be closed
                // and this Intent will be delivered to the (now on top) old activity as a new Intent.
                Intent showAllNotes = new Intent(CreateNote.this, ShowNotes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(showAllNotes);
                finish();
                break;
        }
    }
}