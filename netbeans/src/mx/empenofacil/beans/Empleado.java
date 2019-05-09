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
public class Empleado {
    private Integer idempleado;
    private Integer sucursal;
    private String numpersonal;
    private String nombres;
    private String apellidos;
    private String contrasenahash;
    private Integer tipoempleado;
    private String nombreusuario;

    public Empleado() {
    }
    
    public String getNombreCompleto() {
        return nombres.concat(" ").concat(apellidos);
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public String getNumpersonal() {
        return numpersonal;
    }

    public void setNumpersonal(String numpersonal) {
        this.numpersonal = numpersonal;
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

    public String getContrasenahash() {
        return contrasenahash;
    }

    public void setContrasenahash(String contrasenahash) {
        this.contrasenahash = contrasenahash;
    }

    public Integer getTipoempleado() {
        return tipoempleado;
    }

    public void setTipoempleado(Integer tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    
}
