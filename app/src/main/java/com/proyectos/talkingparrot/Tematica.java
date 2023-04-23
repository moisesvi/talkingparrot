package com.proyectos.talkingparrot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Tematica extends AppCompatActivity {

    private boolean isFragmentoVisible = true;
    FloatingActionButton fltBoton;
    Fragment frgAudio, frgChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tematica);
        frgChat = new FrgChtTematica();
        frgAudio = new FrgAudioTematica();

        getSupportFragmentManager().beginTransaction().add(R.id.frgContenedor, frgAudio).commit();

        fltBoton = findViewById(R.id.btnFloating);
        fltBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFragmentoVisible){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frgContenedor, frgChat).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frgContenedor, frgAudio).commit();
                }
                isFragmentoVisible = !isFragmentoVisible;
            }
        });
    }
}