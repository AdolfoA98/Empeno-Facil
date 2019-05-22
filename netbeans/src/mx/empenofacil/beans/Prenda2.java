package mx.empenofacil.beans;

import java.math.BigDecimal;

public class Prenda2 {
    
    private Integer idPrenda;
    private ItemCatalogo tipo;
    private ItemCatalogo categoria;
    private BigDecimal avaluo;
    private BigDecimal montoPrestado;
    private String nombre;
    private String descripcion;
    private BigDecimal peso;
    private Boolean comercializado;

    public Prenda2() {
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public ItemCatalogo getTipo() {
        return tipo;
    }

    public void setTipo(ItemCatalogo tipo) {
        this.tipo = tipo;
    }

    public ItemCatalogo getCategoria() {
        return categoria;
    }

    public void setCategoria(ItemCatalogo categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(BigDecimal avaluo) {
        this.avaluo = avaluo;
    }

    public BigDecimal getMontoPrestado() {
        return montoPrestado;
    }

    public void setMontoPrestado(BigDecimal montoPrestado) {
        this.montoPrestado = montoPrestado;
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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Boolean getComercializado() {
        return comercializado;
    }

    public void setComercializado(Boolean comercializado) {
        this.comercializado = comercializado;
    }

    @Override
    public String toString() {
        return "Prenda{" + "idPrenda=" + idPrenda + ", tipo=" + tipo + ", categoria=" + categoria + ", avaluo=" + avaluo + ", montoPrestado=" + montoPrestado + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso=" + peso + ", comercializado=" + comercializado + '}';
    }
    
}
