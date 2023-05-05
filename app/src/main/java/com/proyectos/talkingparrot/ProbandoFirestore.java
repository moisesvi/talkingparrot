package com.proyectos.talkingparrot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ProbandoFirestore extends AppCompatActivity {

    FirebaseFirestore db;

    CollectionReference temasRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probando_firestore);
        Button btnCargar =  findViewById(R.id.btnFirestoreCargar);
        Button btnTraer = findViewById(R.id.btnTraerFirebase);


        db = FirebaseFirestore.getInstance();

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Crear un usuario y agregarlo a la colección "Usuarios"
                Map<String, Object> usuarioData = new HashMap<>();
                usuarioData.put("nombre", "Juan");
                usuarioData.put("correo", "juan@gmail.com");
                usuarioData.put("password", "********");
                db.collection("Usuarios").add(usuarioData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference usuarioRef) {
                        Map<String, Object> temaData = new HashMap<>();
                        temaData.put("titulo", "Cómo programar en Android");
                        temaData.put("contenido", "Este es un tutorial de programación en Android.");
                        temaData.put("traduccion", "How to program in Android");
                        temaData.put("dificultad", 2);
                        temaData.put("usuarioRef", usuarioRef); // Agregar una referencia al documento de usuario
                        temasRef = usuarioRef.collection("Temas");
                        temasRef.add(temaData);
                    }
                });

            }
        });

        btnTraer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temasRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot temasSnapshot = task.getResult();
                        for (QueryDocumentSnapshot temaSnapshot : temasSnapshot) {
                            String titulo = temaSnapshot.getString("titulo");
                            String contenido = temaSnapshot.getString("contenido");
                            String traduccion = temaSnapshot.getString("traduccion");
                            int dificultad = temaSnapshot.getLong("dificultad").intValue();
                            DocumentReference usuarioRef = temaSnapshot.getDocumentReference("usuarioRef");
                            Log.d("Firestore", "Tema: " + titulo + " (" + contenido + ") - Usuario: " + usuarioRef.getId());
                        }
                    } else {
                        Exception error = task.getException();
                        Log.e("Firestore", "Error al obtener los temas del usuario", error);
                    }
                });
            }
        });
    }
}