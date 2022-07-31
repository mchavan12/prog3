package com.example.prog3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id = (EditText) findViewById(R.id.id);
        EditText name = (EditText) findViewById(R.id.name);

        Button add = (Button) findViewById(R.id.add);
        Button update = (Button) findViewById(R.id.update);
        Button delete = (Button) findViewById(R.id.delete);
        Button view = (Button) findViewById(R.id.view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                DB.addData(id1,name1);
                Toast.makeText(MainActivity.this, "Added Record", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                DB.updateData(id1,name1);
                Toast.makeText(MainActivity.this, "Updated Record", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                DB.deleteData(id1);
                Toast.makeText(MainActivity.this, "Deleted Record", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.viewData();

                StringBuilder builder = new StringBuilder();
                while (res.moveToNext())
                { builder.append("ID: "+res.getString(0)+"\n");
                  builder.append("Name: "+res.getString(1)+"\n\n"); }

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("User Data: ");
                builder1.setMessage(builder.toString());
                builder1.show();
            }
        });
    }
}