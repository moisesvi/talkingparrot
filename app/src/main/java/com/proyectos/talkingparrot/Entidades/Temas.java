package com.proyectos.talkingparrot.Entidades;

import com.google.firebase.firestore.DocumentReference;

public class Temas {
    String id;
    String titulo;
    String contenido;
    String traduccion;
    int dificultad;
    DocumentReference usuarioRef;

    public Temas() {
    }

    public Temas(String id, String titulo, String contenido, String traduccion, int dificultad, DocumentReference usuarioRef) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.traduccion = traduccion;
        this.dificultad = dificultad;
        this.usuarioRef = usuarioRef;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTraduccion() {
        return traduccion;
    }

    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public DocumentReference getUsuarioRef() {
        return usuarioRef;
    }

    public void setUsuarioRef(DocumentReference usuarioRef) {
        this.usuarioRef = usuarioRef;
    }

    @Override
    public String toString() {
        return "Temas{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", traduccion='" + traduccion + '\'' +
                ", dificultad=" + dificultad +
                ", usuarioRef=" + usuarioRef +
                '}';
    }
}
