package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UnlockedActivity extends AppCompatActivity {

    Button btnCreate, btnDelete, btnExit;
    TextView tvNames, tvValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocked);

        btnCreate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnDelete);
        btnExit = findViewById(R.id.btnExit);

        tvNames = findViewById(R.id.tv_names);
        tvValues = findViewById(R.id.tv_values);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnlockedActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnlockedActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnlockedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SQLiteDatabase sqld;
        AdminSQLiteOpenHelper asqop = new AdminSQLiteOpenHelper(this, "DbNombres", null, 1);
        sqld = asqop.getWritableDatabase();

        Cursor c = sqld.rawQuery("SELECT nombre, valor FROM nombres", null);
        tvNames.setText("");
        tvValues.setText("");

        String name, value;

        if (c.moveToFirst()){
            do {
                name = c.getString(0);
                value = c.getString(1);

                tvNames.append(name +"\n");
                tvValues.append(value +"\n");

            }while (c.moveToNext());
        }
    }
}