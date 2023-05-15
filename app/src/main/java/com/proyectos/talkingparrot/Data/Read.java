package com.proyectos.talkingparrot.Data;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Read {
    public static void readTemas(TextView txt1, TextView txt2){
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
                    txt1.setText(titulo);
                    txt2.setText(contenido);
                }
            } else {
                Exception error = task.getException();
                Log.e("Firestore", "Error al obtener los temas del usuario", error);
            }
        });
    }
}
