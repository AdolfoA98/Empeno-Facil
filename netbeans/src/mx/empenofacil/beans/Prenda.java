/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

/**
 *
 * @author adolf
 */
public class Prenda {
    
    private Integer idprenda;
    private Integer empeno;
    private Integer tipo;
    private Integer categoria;
    private Double avaluo;
    private Double montoprestado;
    private String nombre;
    private String descripcion;
    private Double peso;
    private Boolean comercializado;

    /**
     * @return the idprenda
     */
    public Integer getIdprenda() {
        return idprenda;
    }

    /**
     * @param idprenda the idprenda to set
     */
    public void setIdprenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    /**
     * @return the empeno
     */
    public Integer getEmpeno() {
        return empeno;
    }

    /**
     * @param empeno the empeno to set
     */
    public void setEmpeno(Integer empeno) {
        this.empeno = empeno;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the categoria
     */
    public Integer getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the avaluo
     */
    public Double getAvaluo() {
        return avaluo;
    }

    /**
     * @param avaluo the avaluo to set
     */
    public void setAvaluo(Double avaluo) {
        this.avaluo = avaluo;
    }

    /**
     * @return the montoprestado
     */
    public Double getMontoprestado() {
        return montoprestado;
    }

    /**
     * @param montoprestado the montoprestado to set
     */
    public void setMontoprestado(Double montoprestado) {
        this.montoprestado = montoprestado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    public Boolean getComercializado() {
        return comercializado;
    }

    public void setComercializado(Boolean comercializado) {
        this.comercializado = comercializado;
    }
    
    
}
