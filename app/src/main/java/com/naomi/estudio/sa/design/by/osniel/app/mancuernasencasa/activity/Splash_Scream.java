package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.R;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.database.EntrenamientosDBHelper;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;

import java.util.ArrayList;
import java.util.Arrays;


public class Splash_Scream extends AppCompatActivity  {

    ProgressBar progressBar;
    EntrenamientosDBHelper entrenamientosDBHelper;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_scream);
        entrenamientosDBHelper = new EntrenamientosDBHelper(this);
        progressBar = findViewById(R.id.progressBarIdSplashScream);
        starAnimatorPogress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(entrenamientosDBHelper.getAllEntrenamientos().isEmpty()){

                    for (Entrenamiento entrenamiento : AllExercise()){
                        i++;
                        entrenamientosDBHelper.insertEntrenamiento(entrenamiento);
                    }

                }



                Toast.makeText(Splash_Scream.this, String.valueOf(entrenamientosDBHelper.getAllEntrenamientos().size()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Splash_Scream.this, MancuernasEnCasaActivity.class);
                startActivity(intent);
                finish();

            }
        },500);

    }

    public void starAnimatorPogress(){

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressBar,"progress",0,100);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }

    /*private void bringMainActivityToTop(User id) {
        Intent intent = new Intent(Splash_Scream.this, MainActivity.class);
        intent.putExtra("userCuenta", id);
        intent.setAction(MainActivity.ACTION_LOGIN_USER);
        startActivity(intent);
        finish();
    }*/
    private ArrayList<Entrenamiento> AllExercise() {

        return new ArrayList<>(Arrays.asList(
                new Entrenamiento( "Prensa de Pecho en Banco", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos",R.drawable.img_biceps_exercise),
                new Entrenamiento( "Prensa de Pecho en Banco Inclinado", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos",R.drawable.img_biceps_exercise),
                new Entrenamiento( "Prensa de Pecho en Banco Inclinado Empuñadura Neutral", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos",R.drawable.img_biceps_exercise),
                new Entrenamiento( "Pullover Brazos Flexionados", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos", R.drawable.img_biceps_exercise),
                new Entrenamiento( "Pullover Brazos Rectos ", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos",R.drawable.img_biceps_exercise),
                new Entrenamiento("Apertura Inclinada", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos", R.drawable.img_biceps_exercise),
                new Entrenamiento("Apertura Recostado", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Pechos",R.drawable.img_biceps_exercise),
                new Entrenamiento("Extencion De Triceps A Dos Brazos", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Extencion De Triceps A Un Brazo", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ", "Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Extencion De Triceps Sentado", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento( "Prensa En Banco Para Triceps", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Extenciones de Triceps Acostado", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Patada de Burro De Triceps", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Extencion De Triceps Inclinado A Un Brazos", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ", "Triceps" ,R.drawable.img_triceps_exercise),
                new Entrenamiento("Flexiones de Biceps Una a la Vez", "adfsdfssgdfgfg jvmgvmghvhj jhhgjhvj jhggjhvjh jmgvgjv jvjhvhjvjhvjhvjhv j jgvjhvjh jhg gjhggjhggj jhhggv jgvhghg jhg jhhggjhgjhgv jhgjhgv jhhvjhvh jhvjhvbjhvjb jhg jh bvjhbv jhb  jh bvjhbvjhb hgjhg j  kjgbhjb hghgjhg kjgjhhv,jhv jhhgjhj jjgljhg jjggjhg jg kgfhgf k yyfh gf hgv sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" , R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Alternadas", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" ,R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Martillo", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" ,R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Sentado", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" ,R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Inclinado ", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ", "Biceps" ,R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Inclinado Alternadas", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ", "Biceps" ,R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Concentradas", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" , R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps Interior", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ", "Biceps" , R.drawable.image_flexiones_biceps_de_pie),
                new Entrenamiento("Flexiones de Biceps", "adfsdfssgdfgfg sdgfsdfg dfgdfg dfgddfgdfg ","Biceps" , R.drawable.image_flexiones_biceps_de_pie)
        ));

    }
}
