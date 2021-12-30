package com.example.devoir_libre.affichage;
public class affichage {

    String module;
    Float Note;

    public affichage() {
    }
    public affichage(String module, Float note) {
        this.module = module;
        Note = note;
    }

    public String getModule() {
        return module;
    }

    public Float getNote() {
        return Note;
    }

    @Override
    public String toString() {
        return "Resultat{" + "module='" + module + '\'' + ", Note=" + Note + '}';
    }
}
