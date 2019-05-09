package mx.empenofacil.beans;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroListaNegra {
    
    private Integer idRegistroListaNegra;
    private Cliente cliente;
    private Empleado empleadoAgrega;
    private String motivoAgrega;
    private LocalDateTime fechaAgrega;
    private Empleado empleadoRetira;
    private String motivoRetira;
    private LocalDateTime fechaRetira;

    public RegistroListaNegra() {
    }
    
    public boolean estaActivo() {
        return fechaRetira == null;
    }

    public Integer getIdRegistroListaNegra() {
        return idRegistroListaNegra;
    }

    public void setIdRegistroListaNegra(Integer idRegistroListaNegra) {
        this.idRegistroListaNegra = idRegistroListaNegra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleadoAgrega() {
        return empleadoAgrega;
    }

    public void setEmpleadoAgrega(Empleado empleadoAgrega) {
        this.empleadoAgrega = empleadoAgrega;
    }

    public String getMotivoAgrega() {
        return motivoAgrega;
    }

    public void setMotivoAgrega(String motivoAgrega) {
        this.motivoAgrega = motivoAgrega;
    }

    public LocalDateTime getFechaAgrega() {
        return fechaAgrega;
    }

    public void setFechaAgrega(LocalDateTime fechaAgrega) {
        this.fechaAgrega = fechaAgrega;
    }

    public Empleado getEmpleadoRetira() {
        return empleadoRetira;
    }

    public void setEmpleadoRetira(Empleado empleadoRetira) {
        this.empleadoRetira = empleadoRetira;
    }

    public String getMotivoRetira() {
        return motivoRetira;
    }

    public void setMotivoRetira(String motivoRetira) {
        this.motivoRetira = motivoRetira;
    }

    public LocalDateTime getFechaRetira() {
        return fechaRetira;
    }

    public void setFechaRetira(LocalDateTime fechaRetira) {
        this.fechaRetira = fechaRetira;
    }

    @Override
    public String toString() {
        return "RegistroListaNegra{" + "idRegistroListaNegra=" + idRegistroListaNegra + ", cliente=" + cliente + ", empleadoAgrega=" + empleadoAgrega + ", motivoAgrega=" + motivoAgrega + ", fechaAgrega=" + fechaAgrega + ", empleadoRetira=" + empleadoRetira + ", motivoRetira=" + motivoRetira + ", fechaRetira=" + fechaRetira + '}';
    }
}
