package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ShowNotes extends AppCompatActivity {

    // Declaration.
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {SQLDBHelper._ID, SQLDBHelper.TITLE, SQLDBHelper.CONTENT};

    final int[] to = new  int[] {R.id.id, R.id.title, R.id.text};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        // Initialisation.
        dbManager = new DBManager(this);

        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);

        listView.setEmptyView(findViewById(R.id.noNotesPresent));

        // Redirect to the specific note when clicked.
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_note, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // Set OnclickListener for the note in the list.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView noteID = view.findViewById(R.id.id);
                TextView noteHeading = view.findViewById(R.id.title);
                TextView noteText = view.findViewById(R.id.text);

                String ID = noteID.getText().toString();
                String heading = noteHeading.getText().toString();
                String text = noteText.getText().toString();

                Intent updateOrDelete = new Intent(getApplicationContext(), UpdateOrDelete.class);

                updateOrDelete.putExtra("id", ID);
                updateOrDelete.putExtra("heading", heading);
                updateOrDelete.putExtra("content", text);

                startActivity(updateOrDelete);
            }
        });
    }
}