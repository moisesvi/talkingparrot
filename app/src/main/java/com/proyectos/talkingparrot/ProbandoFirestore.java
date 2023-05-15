package com.proyectos.talkingparrot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.proyectos.talkingparrot.Data.Live;
import com.proyectos.talkingparrot.Entidades.Temas;
import com.proyectos.talkingparrot.Entidades.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
                //Map<String, Object> usuarioData = new HashMap<>();
                //usuarioData.put("nombre", "Juan");
                //usuarioData.put("correo", "juan@gmail.com");
                //usuarioData.put("password", "********");

                Usuario usuario = new Usuario("u"+new Random(), "admin@admin.com", "admin");

                //.add(usuarioData)
                db.collection("Usuarios").add(usuario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference usuarioRef) {
                        //Map<String, Object> temaData = new HashMap<>();
                        //temaData.put("titulo", "Cómo programar en Android");
                        //temaData.put("contenido", "Este es un tutorial de programación en Android.");
                        //temaData.put("traduccion", "How to program in Android");
                        //temaData.put("dificultad", 2);
                        //temaData.put("usuarioRef", usuarioRef); // Agregar una referencia al documento de usuario
                        //temasRef = usuarioRef.collection("Temas");
                        //temasRef.add(temaData);

                        Temas tema = new Temas("t"+new Random(), "My day", "First, I wake up. Then, I get dressed. I walk to school. I do not ride a bike. I do not ride the bus. I like to go to school. It rains. I do not like rain. I eat lunch. I eat a sandwich and an apple.\n" +
                                "\n" +
                                "I play outside. I like to play. I read a book. I like to read books. I walk home. I do not like walking home. My mother cooks soup for dinner. The soup is hot. Then, I go to bed. I do not like to go to bed.","Primero, me despierto. Luego, me visto. Voy andando al colegio. No voy en bicicleta. No voy en autobús. Me gusta ir a la escuela. Llueve. No me gusta la lluvia. Yo almuerzo. Como un bocadillo y una manzana.\n" +
                                "\n" +
                                "Juego fuera. Me gusta jugar. Leo un libro. Me gusta leer libros. Vuelvo a casa andando. No me gusta volver a casa andando. Mi madre prepara sopa para cenar. La sopa está caliente. Luego me voy a la cama. No me gusta irme a la cama.", 0, usuarioRef);
                        temasRef = usuarioRef.collection("Temas");
                        temasRef.add(tema);
                        Live.TEMAS.setUsuarioRef(usuarioRef);
                    }
                });

            }
        });

        btnTraer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //temasRef.get()
                Live.TEMAS.getUsuarioRef().collection("Temas").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot temasSnapshot = task.getResult();
                        for (QueryDocumentSnapshot temaSnapshot : temasSnapshot) {
                            String titulo = temaSnapshot.getString("titulo");
                            String contenido = temaSnapshot.getString("contenido");
                            String traduccion = temaSnapshot.getString("traduccion");
                            int dificultad = temaSnapshot.getLong("dificultad").intValue();
                            DocumentReference usuarioRef = temaSnapshot.getDocumentReference("usuarioRef");
                            Log.d("Firestore", "Tema: " + titulo + " (" + contenido + ") - Usuario: " + usuarioRef.getId());
                            Live.TEMAS.setTitulo(titulo);
                            Live.TEMAS.setContenido(contenido);
                            Live.TEMAS.setTraduccion(traduccion);
                            Live.TEMAS.setDificultad(dificultad);
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