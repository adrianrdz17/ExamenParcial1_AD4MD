package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    Button btnCreate, btnBack;
    EditText etName, etValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        btnCreate = findViewById(R.id.btnCreateAction);
        btnBack = findViewById(R.id.btnBack);

        etName = findViewById(R.id.etName);
        etValue = findViewById(R.id.etValue);

        SQLiteDatabase sqld;
        AdminSQLiteOpenHelper asqop = new AdminSQLiteOpenHelper(this, "DbNombres", null, 1);
        sqld = asqop.getWritableDatabase();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateActivity.this, UnlockedActivity.class);
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String value = etValue.getText().toString();

                if (name.equals("") || value.equals("")){
                    Toast.makeText(CreateActivity.this, "Registro invalido\nPor favor, rellene los campos",Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues cv = new ContentValues();
                    cv.put("nombre", name);
                    cv.put("valor", value);
                    sqld.insert("nombres", null, cv);

                    Toast.makeText(CreateActivity.this, "Registro creado", Toast.LENGTH_SHORT).show();

                    etValue.setText("");
                    etName.setText("");


                }



            }
        });
    }
}