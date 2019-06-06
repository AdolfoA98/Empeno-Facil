/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author adolf
 */
public class Empeno {
    
    private Integer idempeno;
    private Integer empleado;
    private Integer cliente;
    private Integer transaccioncaja;
    private Date fecha;
    private Date fechalimite;
    private Date fechaextendida;
    private Double interes;
    private Double almacenaje;
    private String nombrecotitular;
    private Integer estatusEmpeno;
    private List<Prenda> prendas;
    private Boolean extendido;
    private Boolean vencido;
    private Boolean cancelado;
    private Boolean comercializado;

    /**
     * @return the idempeno
     */
    public Integer getIdempeno() {
        return idempeno;
    }

    /**
     * @param idempeno the idempeno to set
     */
    public void setIdempeno(Integer idempeno) {
        this.idempeno = idempeno;
    }

    /**
     * @return the empleado
     */
    public Integer getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the cliente
     */
    public Integer getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the transaccioncaja
     */
    public Integer getTransaccioncaja() {
        return transaccioncaja;
    }

    /**
     * @param transaccioncaja the transaccioncaja to set
     */
    public void setTransaccioncaja(Integer transaccioncaja) {
        this.transaccioncaja = transaccioncaja;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the fechalimite
     */
    public Date getFechalimite() {
        return fechalimite;
    }

    /**
     * @param fechalimite the fechalimite to set
     */
    public void setFechalimite(Date fechalimite) {
        this.fechalimite = fechalimite;
    }

    /**
     * @return the fechaextendida
     */
    public Date getFechaextendida() {
        return fechaextendida;
    }

    /**
     * @param fechaextendida the fechaextendida to set
     */
    public void setFechaextendida(Date fechaextendida) {
        this.fechaextendida = fechaextendida;
    }

    /**
     * @return the interes
     */
    public Double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(Double interes) {
        this.interes = interes;
    }

    /**
     * @return the almacenaje
     */
    public Double getAlmacenaje() {
        return almacenaje;
    }

    /**
     * @param almacenaje the almacenaje to set
     */
    public void setAlmacenaje(Double almacenaje) {
        this.almacenaje = almacenaje;
    }

    /**
     * @return the nombrecotitular
     */
    public String getNombrecotitular() {
        return nombrecotitular;
    }

    /**
     * @param nombrecotitular the nombrecotitular to set
     */
    public void setNombrecotitular(String nombrecotitular) {
        this.nombrecotitular = nombrecotitular;
    }
    
    public List<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    /**
     * @return the extendido
     */
    public Boolean getExtendido() {
        return extendido;
    }

    /**
     * @return the vencido
     */
    public Boolean getVencido() {
        return vencido;
    }

    /**
     * @param vencido the vencido to set
     */
    public void setVencido(Boolean vencido) {
        this.vencido = vencido;
    }

    /**
     * @return the cancelado
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * @return the comercializado
     */
    public Boolean getComercializado() {
        return comercializado;
    }

    /**
     * @param extendido the extendido to set
     */
    public void setExtendido(Boolean extendido) {
        this.extendido = extendido;
    }

    /**
     * @param cancelado the cancelado to set
     */
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    /**
     * @param comercializado the comercializado to set
     */
    public void setComercializado(Boolean comercializado) {
        this.comercializado = comercializado;
    }

    /**
     * @return the statusEmpeno
     */
    public Integer getEstatusEmpeno() {
        return estatusEmpeno;
    }

    /**
     * @param estatusEmpeno the statusEmpeno to set
     */
    public void setEstatusEmpeno(Integer estatusEmpeno) {
        this.estatusEmpeno = estatusEmpeno;
    }
    
}
