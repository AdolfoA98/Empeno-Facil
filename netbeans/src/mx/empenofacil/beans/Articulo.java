package mx.empenofacil.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Articulo {
    
    private Integer idarticulo;
    private Prenda2 prenda;
    private Empleado empleado;
    private LocalDateTime comercializacion;
    private BigDecimal precio;
    private BigDecimal descuento;
    private String codigodebarras;
    private String nombre;
    private String descripcion;
    private ItemCatalogo status;
    private Integer idEmpleado;

    public Articulo() {
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }


    public Prenda2 getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda2 prenda) {
        this.prenda = prenda;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getComercializacion() {
        return comercializacion;
    }

    public void setComercializacion(LocalDateTime comercializacion) {
        this.comercializacion = comercializacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getCodigodebarras() {
        return codigodebarras;
    }

    public void setCodigodebarras(String codigodebarras) {
        this.codigodebarras = codigodebarras;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ItemCatalogo getStatus() {
        return status;
    }

    public void setStatus(ItemCatalogo status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idarticulo=" + idarticulo + ", prenda=" + prenda + ", empleado=" + empleado + ", comercializacion=" + comercializacion + ", precio=" + precio + ", descuento=" + descuento + ", codigodebarras=" + codigodebarras + ", nombre=" + nombre + ", descripcion=" + descripcion + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idarticulo);
        hash = 53 * hash + Objects.hashCode(this.comercializacion);
        hash = 53 * hash + Objects.hashCode(this.precio);
        hash = 53 * hash + Objects.hashCode(this.descuento);
        hash = 53 * hash + Objects.hashCode(this.codigodebarras);
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Articulo other = (Articulo) obj;
        if (!Objects.equals(this.codigodebarras, other.codigodebarras)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.idarticulo, other.idarticulo)) {
            return false;
        }
        if (!Objects.equals(this.comercializacion, other.comercializacion)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        if (!Objects.equals(this.descuento, other.descuento)) {
            return false;
        }
        return true;
    }
}
