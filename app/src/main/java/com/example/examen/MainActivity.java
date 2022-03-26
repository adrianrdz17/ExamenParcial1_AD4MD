package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itsxtt.patternlock.*;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    String save_pattern_key = "pattern_code";
    String final_pattern = "";
    PatternLockView patternLockView;



    private String getPatternString(ArrayList<Integer> ids){
        String resultado = "";
        for (Integer id: ids){
            resultado += ids.toString();
        }
        return resultado;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        final String save_pattern = Paper.book().read(save_pattern_key);

        if(save_pattern != null && !save_pattern.equals("null")  ){
            setContentView(R.layout.activity_principal);
            patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
            patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(@NonNull ArrayList<Integer> arrayList) {

                }

                @Override
                public boolean onComplete(@NonNull ArrayList<Integer> arrayList) {

                    boolean isCorrect = TextUtils.equals(save_pattern, getPatternString(arrayList));
                    String tip;

                    if (isCorrect){
                        Toast.makeText(MainActivity.this, "Patron correcto!\nAccediendo a la base de datos", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, UnlockedActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Patron incorrecto!\nIntente nuevamente.", Toast.LENGTH_SHORT).show();

                    }

                    return isCorrect;




                }

            });

            Button btnSave = findViewById(R.id.btnSave);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Paper.book().destroy();
                    Toast.makeText(MainActivity.this, "Configure su nuevo patron", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });



        } else {
            setContentView(R.layout.activity_main);
            patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);

            patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(@NonNull ArrayList<Integer> arrayList) {

                }

                @Override
                public boolean onComplete(@NonNull ArrayList<Integer> arrayList) {
                    final_pattern = getPatternString(arrayList);
                    return true;
                }
            });

            Button btnSave = (Button) findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Paper.book().write(save_pattern_key, final_pattern);
                    Toast.makeText(MainActivity.this,"Patron guardado correctament", Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                    startActivity(intent);
                }
            });
        }

    }

}