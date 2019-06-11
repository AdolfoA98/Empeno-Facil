/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import java.util.Objects;

/**
 *
 * @author Rainbow Dash
 */
public class ItemCatalogo {
    
    private Integer iditemcatalogo;
    private String nombre;
    private Integer itemcatalogoSuperior;

    public ItemCatalogo() {
    }

    public Integer getIditemcatalogo() {
        return iditemcatalogo;
    }

    public void setIditemcatalogo(Integer iditemcatalogo) {
        this.iditemcatalogo = iditemcatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getItemcatalogoSuperior() {
        return itemcatalogoSuperior;
    }

    public void setItemcatalogoSuperior(Integer itemcatalogoSuperior) {
        this.itemcatalogoSuperior = itemcatalogoSuperior;
    }
     @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.iditemcatalogo);
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.itemcatalogoSuperior);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemCatalogo other = (ItemCatalogo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.iditemcatalogo, other.iditemcatalogo)) {
            return false;
        }
        if (!Objects.equals(this.itemcatalogoSuperior, other.itemcatalogoSuperior)) {
            return false;
        }
        return true;
    }
}
