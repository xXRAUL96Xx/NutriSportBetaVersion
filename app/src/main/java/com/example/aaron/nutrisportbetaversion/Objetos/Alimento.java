package com.example.aaron.nutrisportbetaversion.Objetos;

/**
 * Created by aaron on 06/05/2018.
 */

public class Alimento {
    String producto;
    float proteinas;
    float hidratos;
    float grasas;
    float energia;
    float fibra;
    String key;
    String uid;
    String creadoBy;

    public Alimento(){
    }

    public Alimento(String producto, float proteinas, float hidratos, float grasas, float energia, float fibra, String key, String uid, String creadoBy) {
        this.producto = producto;
        this.proteinas = proteinas;
        this.hidratos = hidratos;
        this.grasas = grasas;
        this.energia = energia;
        this.fibra = fibra;
        this.key = key;
        this.uid = uid;
        this.creadoBy = creadoBy;
    }

    public String getProducto() {return producto;}

    public void setProducto(String producto) {this.producto = producto;}

    public float getProteinas() {return proteinas;}

    public void setProteinas(float proteinas) {this.proteinas = proteinas;}

    public float getHidratos() {return hidratos;}

    public void setHidratos(float hidratos) {this.hidratos = hidratos;}

    public float getGrasas() {return grasas;}

    public void setGrasas(float grasas) {this.grasas = grasas;}

    public float getEnergia() {return energia;}

    public void setEnergia(float energia) {this.energia = energia;}

    public float getFibra() {return fibra;}

    public void setFibra(float fibra) {this.fibra = fibra;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getUid() {return uid;}

    public void setUid(String uid) {this.uid = uid;}

    public String getCreadoBy() {return creadoBy;}

    public void setCreadoBy(String creadoBy) {this.creadoBy = creadoBy;}
}