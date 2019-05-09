/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

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
    
}
