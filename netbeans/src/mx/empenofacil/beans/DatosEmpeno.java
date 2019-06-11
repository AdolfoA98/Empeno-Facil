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
public class DatosEmpeno {

    private Integer idempeno;
    private String nombreCliente;
    private String nombrePrenda;
    private Double interes;
    private Double total;

    public DatosEmpeno(Integer idempeno, String nombreCliente, String nombrePrenda, Double interes, Double total) {
        this.idempeno = idempeno;
        this.nombreCliente = nombreCliente;
        this.nombrePrenda = nombrePrenda;
        this.interes = interes;
        this.total = total;
    }

    public DatosEmpeno() {
    }

    public Integer getIdempeno() {
        return idempeno;
    }

    public void setIdempeno(Integer idempeno) {
        this.idempeno = idempeno;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombrePrenda() {
        return nombrePrenda;
    }

    public void setNombrePrenda(String nombrePrenda) {
        this.nombrePrenda = nombrePrenda;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}