package com.example.ex2ontouchlistenner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ArrayList<Stagiaire> stgs;

    Button btn1, btn2, btn3, btn4;
    EditText e1, e2;
    RadioGroup grp;
    ImageView image;

    int position = 0;
    float xInitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.idfirst);
        btn2 = findViewById(R.id.idprec);
        btn3 = findViewById(R.id.idnext);
        btn4 = findViewById(R.id.idlast);
        e1 = findViewById(R.id.idnom);
        e2 = findViewById(R.id.idprenom);
        grp = findViewById(R.id.grp);
        image = findViewById(R.id.im);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        stgs = new ArrayList<>();

        stgs.add(new Stagiaire("El Mouddene","Doae","Femme",R.drawable.image3));
        stgs.add(new Stagiaire("El Ayyachi","Eddouasse","Homme",R.drawable.image2));
        stgs.add(new Stagiaire("Karkour","Abdellah","Homme",R.drawable.image7));
        stgs.add(new Stagiaire("Akkallaa","Hafssa","Femme",R.drawable.image4));

        position = 0;
        afficheDonneesStagiaire();

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        xInitial = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float xFinal = motionEvent.getY();

                        if(xInitial<xFinal)
                            position--;
                        else if(xInitial>xFinal)
                            position ++;
                        afficheDonneesStagiaire();
                        break;
                }
                return true;
            }
        });
    }
    public void afficheDonneesStagiaire() {

        if (position < 0)
            position = stgs.size() - 1;
        else if (position >= stgs.size())
            position = 0;

        e1.setText(stgs.get(position).getNom());
        e2.setText(stgs.get(position).getPrenom());
        if (stgs.get(position).getSexe().equals("H"))
            grp.check(R.id.rdh);
        else
            grp.check(R.id.rdf);
        image.setImageResource(stgs.get(position).getImage());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idfirst:
                position = 0;
                break;
            case R.id.idprec:
                position--;
                break;
            case R.id.idnext:
                position++;
                break;
            case R.id.idlast:
                position = stgs.size() - 1;
                break;
        }
        afficheDonneesStagiaire();
    }
}