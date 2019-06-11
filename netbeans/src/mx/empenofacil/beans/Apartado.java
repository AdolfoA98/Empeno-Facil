/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import java.util.Date;

/**
 *
 * @author Rainbow Dash
 */
public class Apartado {
    private Integer idapartado;
    private Integer empleado;
    private Integer cliente;
    private Date fechalimite;
    private Integer estatusApartado;

    public Apartado(Integer idapartado, Integer empleado, Integer cliente, Date fechalimite, Integer estatusApartado) {
        this.idapartado = idapartado;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechalimite = fechalimite;
        this.estatusApartado = estatusApartado;
    }

    public Apartado() {
    }

    public Integer getIdapartado() {
        return idapartado;
    }

    public void setIdapartado(Integer idapartado) {
        this.idapartado = idapartado;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Date getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(Date fechalimite) {
        this.fechalimite = fechalimite;
    }

    public Integer getEstatusApartado() {
        return estatusApartado;
    }

    public void setEstatusApartado(Integer estatusApartado) {
        this.estatusApartado = estatusApartado;
    }
    
}
