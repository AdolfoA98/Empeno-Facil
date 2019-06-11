/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import java.util.List;

/**
 *
 * @author Razer
 */
public class Venta {
    
    private Integer idventa;
    private Empleado empleado;
    private Cliente cliente;
    private TransaccionCaja transaccioncaja;
    private ItemCatalogo estatusVenta;
    private List<Articulo> articulos;

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public Venta() {
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TransaccionCaja getTransaccioncaja() {
        return transaccioncaja;
    }

    public void setTransaccioncaja(TransaccionCaja transaccioncaja) {
        this.transaccioncaja = transaccioncaja;
    }

    public ItemCatalogo getEstatusVenta() {
        return estatusVenta;
    }

    public void setEstatusVenta(ItemCatalogo estatusVenta) {
        this.estatusVenta = estatusVenta;
    }
    
    
    
}
