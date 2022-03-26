package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText etName;
    Button btnDeleteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etName = findViewById(R.id.etDeleteName);
        btnDeleteName = findViewById(R.id.btnDeleteName);


        SQLiteDatabase sqld;
        AdminSQLiteOpenHelper asqop = new AdminSQLiteOpenHelper(this, "DbNombres", null, 1);
        sqld = asqop.getWritableDatabase();

        btnDeleteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String name = etName.getText().toString();
//                sqld.execSQL("DELETE FROM nombres WHERE nombre = " + name);
                Toast.makeText(DeleteActivity.this, "Funcion aun no implementada :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}