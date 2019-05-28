/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.beans;

import javafx.scene.image.Image;

/**
 *
 * @author adolf
 */
public class Foto {
    
    private Integer idfotografia;
    private Integer iddueno;
    private Image img;
    private String nota;
    
    public Foto(){}
    
    public Foto(Integer idfotografia, Integer iddueno, Image img, String nota){
        this.idfotografia = idfotografia;
        this.iddueno = iddueno;
        this.img = img;
        this.nota = nota;
    }

    /**
     * @return the idfotografia
     */
    public Integer getIdfotografia() {
        return idfotografia;
    }

    /**
     * @param idfotografia the idfotografia to set
     */
    public void setIdfotografia(Integer idfotografia) {
        this.idfotografia = idfotografia;
    }

    /**
     * @return the iddueno
     */
    public Integer getIddueno() {
        return iddueno;
    }

    /**
     * @param iddueno the iddueno to set
     */
    public void setIddueno(Integer iddueno) {
        this.iddueno = iddueno;
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }
    
}
