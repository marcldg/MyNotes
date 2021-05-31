package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateOrDelete extends AppCompatActivity implements View.OnClickListener
{
    // Declaring variables.
    EditText editTitle, editText;
    Button updateNote, deleteNote;

    private  long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_or_delete);

        // Initialising variables.
        editTitle = findViewById(R.id.editTitle);
        editText = findViewById(R.id.editText);
        updateNote = findViewById(R.id.updateNote);
        deleteNote = findViewById(R.id.deleteNote);

        dbManager = new DBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("heading");
        String text = intent.getStringExtra("content");

        _id = Long.parseLong(id);
        editTitle.setText(title);
        editText.setText(text);

        updateNote.setOnClickListener(this);
        deleteNote.setOnClickListener(this);
    }

    // Method to return to Show Notes activity.
    public void showNotes()
    {
        Intent showNotes = new Intent(getApplicationContext(), ShowNotes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(showNotes);
    }

    // Handle the button clicks.
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.updateNote:
                String title = editTitle.getText().toString();
                String text = editText.getText().toString();
                dbManager.update(_id, title,text);
                showNotes();
                break;
            case R.id.deleteNote:
                dbManager.delete(_id);
                showNotes();
                break;
        }
    }
}