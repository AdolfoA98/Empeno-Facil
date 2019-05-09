package mx.empenofacil.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionCaja {
    
    private Integer idTransaccionCaja;
    private Sucursal sucursal;
    private TransaccionCaja cancelacion;
    private BigDecimal monto;
    private BigDecimal balanceCaja;
    private LocalDateTime fechaHora;
    private String descripcion;

    public Integer getIdTransaccionCaja() {
        return idTransaccionCaja;
    }

    public void setIdTransaccionCaja(Integer idTransaccionCaja) {
        this.idTransaccionCaja = idTransaccionCaja;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TransaccionCaja getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(TransaccionCaja cancelacion) {
        this.cancelacion = cancelacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getBalanceCaja() {
        return balanceCaja;
    }

    public void setBalanceCaja(BigDecimal balanceCaja) {
        this.balanceCaja = balanceCaja;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TransaccionCaja{" + "idTransaccionCaja=" + idTransaccionCaja + ", sucursal=" + sucursal + ", cancelacion=" + cancelacion + ", monto=" + monto + ", balanceCaja=" + balanceCaja + ", fechaHora=" + fechaHora + ", descripcion=" + descripcion + '}';
    }
    
}
