package com.example.loginscreen;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Message {

    private View v;
    private Boolean aBoolean;
    private String texto;


    public Message() {
    }

    public Message(View v, Boolean aBoolean, String texto) {
        Snackbar snackbar = Snackbar.make(v, texto, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(aBoolean == true ? Color.GREEN : Color.RED);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void Print(View v, Boolean aBoolean, String texto) {
        Snackbar snackbar = Snackbar.make(v, texto, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(aBoolean == true ? Color.GREEN : Color.RED);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
