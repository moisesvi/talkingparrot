package com.proyectos.talkingparrot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyectos.talkingparrot.FrgTematica.FrgAudioTematica;
import com.proyectos.talkingparrot.FrgTematica.FrgChtTematica;

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

        //Permite que el texto sea seleccionable
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipData clipData = clipboard.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                String copiedText = item.getText().toString();

                Toast.makeText(Tematica.this, copiedText, Toast.LENGTH_SHORT).show();

            }
        });
    }
}