package com.proyectos.talkingparrot.Data;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.proyectos.talkingparrot.Entidades.Temas;
import com.proyectos.talkingparrot.Entidades.Usuario;

import java.util.Random;

public class Create {
    static FirebaseFirestore db;

    static CollectionReference temasRef;

    public static void createTema(){

        db = FirebaseFirestore.getInstance();

        Usuario usuario = new Usuario("u"+new Random(), "admin@admin.com", "admin");

        //.add(usuarioData)
        db.collection("Usuarios").add(usuario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference usuarioRef) {

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
}
