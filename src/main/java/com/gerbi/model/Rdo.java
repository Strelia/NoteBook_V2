package com.gerbi.model;

public class Rdo {

    private long idRdo;
    private String name;

    public Rdo() {
    }

    public long getIdRdo() {
        return idRdo;
    }

    public void setIdRdo(long idRdo) {
        this.idRdo = idRdo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rdo{" +
                "idRdo=" + idRdo +
                ", name='" + name + '\'' +
                '}';
    }
}
