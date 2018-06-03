package com.example.aaron.nutrisportbetaversion.Objetos;

/**
 * Created by aaron on 09/05/2018.
 */

public class Entrenamiento {
    String dia;
    String tipo_entrenamiento;
    int time_duration;
    String observaciones;
    String key;
    String uid;
    String creadoBy;

    public Entrenamiento() {
    }

    public Entrenamiento(String dia, String tipo_entrenamiento, int time_duration, String observaciones, String key, String uid, String creadoBy) {
        this.dia = dia;
        this.tipo_entrenamiento = tipo_entrenamiento;
        this.time_duration = time_duration;
        this.observaciones = observaciones;
        this.key = key;
        this.uid = uid;
        this.creadoBy = creadoBy;
    }

    public String getDia() {return dia;}

    public void setDia(String dia) {this.dia = dia;}

    public String getTipo_entrenamiento() {return tipo_entrenamiento;}

    public void setTipo_entrenamiento(String tipo_entrenamiento) {this.tipo_entrenamiento = tipo_entrenamiento;}

    public int getTime_duration() {return time_duration;}

    public void setTime_duration(int time_duration) {this.time_duration = time_duration;}

    public String getObservaciones() {return observaciones;}

    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getUid() {return uid;}

    public void setUid(String uid) {this.uid = uid;}

    public String getCreadoBy() {return creadoBy;}

    public void setCreadoBy(String creadoBy) {this.creadoBy = creadoBy;}
}
