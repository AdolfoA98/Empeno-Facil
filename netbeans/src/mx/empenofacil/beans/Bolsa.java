/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import java.sql.Date;

/**
 *
 * @author adolf
 */
public class Bolsa {
    
    private int idbolsa;
    private double fondoinicial;
    private Date fecha;
    private double montofinal;

    public int getIdbolsa() {
        return idbolsa;
    }

    public void setIdbolsa(int idbolsa) {
        this.idbolsa = idbolsa;
    }

    public double getFondoinicial() {
        return fondoinicial;
    }

    public void setFondoinicial(double fondoinicial) {
        this.fondoinicial = fondoinicial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontofinal() {
        return montofinal;
    }

    public void setMontofinal(double montofinal) {
        this.montofinal = montofinal;
    }
}
