package mx.empenofacil.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Empeño2 {
    
    private Integer idEmpeño;
    private Empleado empleado;
    private Cliente cliente;
    private TransaccionCaja transaccionCaja;
    private List<Prenda2> prendas;
    private LocalDateTime fecha;
    private LocalDateTime fechaLimite;
    private LocalDateTime fechaExtendida;
    private BigDecimal interes;
    private BigDecimal almacenaje;
    private String nombreCotitular;
    private Boolean extendido;
    private Boolean vencido;
    private Boolean cancelado;
    private Boolean comercializado;

    public Empeño2() {
    }

    public Integer getIdEmpeño() {
        return idEmpeño;
    }

    public void setIdEmpeño(Integer idEmpeño) {
        this.idEmpeño = idEmpeño;
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

    public TransaccionCaja getTransaccionCaja() {
        return transaccionCaja;
    }

    public void setTransaccionCaja(TransaccionCaja transaccionCaja) {
        this.transaccionCaja = transaccionCaja;
    }

    public List<Prenda2> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Prenda2> prendas) {
        this.prendas = prendas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDateTime getFechaExtendida() {
        return fechaExtendida;
    }

    public void setFechaExtendida(LocalDateTime fechaExtendida) {
        this.fechaExtendida = fechaExtendida;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getAlmacenaje() {
        return almacenaje;
    }

    public void setAlmacenaje(BigDecimal almacenaje) {
        this.almacenaje = almacenaje;
    }

    public String getNombreCotitular() {
        return nombreCotitular;
    }

    public void setNombreCotitular(String nombreCotitular) {
        this.nombreCotitular = nombreCotitular;
    }

    public Boolean getExtendido() {
        return extendido;
    }

    public void setExtendido(Boolean extendido) {
        this.extendido = extendido;
    }

    public Boolean getVencido() {
        return vencido;
    }

    public void setVencido(Boolean vencido) {
        this.vencido = vencido;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Boolean getComercializado() {
        return comercializado;
    }

    public void setComercializado(Boolean comercializado) {
        this.comercializado = comercializado;
    }

    @Override
    public String toString() {
        return "Empe\u00f1o{" + "idEmpe\u00f1o=" + idEmpeño + ", empleado=" + empleado + ", cliente=" + cliente + ", transaccionCaja=" + transaccionCaja + ", prendas=" + prendas + ", fecha=" + fecha + ", fechaLimite=" + fechaLimite + ", fechaExtendida=" + fechaExtendida + ", interes=" + interes + ", almacenaje=" + almacenaje + ", nombreCotitular=" + nombreCotitular + ", extendido=" + extendido + ", vencido=" + vencido + ", cancelado=" + cancelado + ", comercializado=" + comercializado + '}';
    }
}
