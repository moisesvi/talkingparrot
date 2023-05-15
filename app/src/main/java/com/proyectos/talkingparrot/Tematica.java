package com.proyectos.talkingparrot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyectos.talkingparrot.Data.Create;
import com.proyectos.talkingparrot.Data.Live;
import com.proyectos.talkingparrot.Data.Read;
import com.proyectos.talkingparrot.FrgTematica.FrgAudioTematica;
import com.proyectos.talkingparrot.FrgTematica.FrgChtTematica;

import org.w3c.dom.Text;

public class Tematica extends AppCompatActivity {

    private boolean isFragmentoVisible = true;
    FloatingActionButton fltBoton;
    Fragment frgAudio, frgChat;

    TextView titulo, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tematica);
        frgChat = new FrgChtTematica();
        frgAudio = new FrgAudioTematica();

        getSupportFragmentManager().beginTransaction().add(R.id.frgContenedor, frgAudio).commit();

        fltBoton = findViewById(R.id.btnFloating);
        titulo = findViewById(R.id.txtTitulo);
        contenido = findViewById(R.id.txtContenido);

        Read.readTemas(titulo, contenido);

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

                showDialog(copiedText);
            }
        });
    }

    private void showDialog(String copied) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        //aquí hacer el llamada a los elementos dialog.findBysjsj
        TextView textView = dialog.findViewById(R.id.txt_sheet);
        textView.setText(copied);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}