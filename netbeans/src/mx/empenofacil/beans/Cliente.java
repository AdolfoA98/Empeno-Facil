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
public class Cliente {
    
    private Integer idcliente;
    private String nombres;
    private String apellidos;
    private String rfc;
    private String curp;
    private String identificacion;
    private Integer tipoidentificacion;
    private ItemCatalogo tipoIdentificacion;
    private Boolean enListaNegra;

    public ItemCatalogo getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(ItemCatalogo tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Cliente() {
    }
    
    public String getNombreCompleto() {
        return nombres.concat(" ").concat(apellidos);
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(Integer tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }
    
    public Boolean getEnListaNegra() {
        return enListaNegra;
    }

    public void setEnListaNegra(Boolean enListaNegra) {
        this.enListaNegra = enListaNegra;
    }
    
}
