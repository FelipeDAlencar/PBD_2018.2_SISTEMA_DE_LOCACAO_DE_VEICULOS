/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caminhoneta_de_passageiros")
public class CaminhonetaDePassageiros extends Categoria{
    
    @Column(name = "cinto_de_seguranca")
    private boolean cintoDeSeguranca;
    @Column(name = "direcao_assistida")
    private boolean direcaoAssistida;
    @Column(name = "air_bag")
    private boolean airBag;
    @Column(name = "rodas_de_liga_leve")
    private boolean rodaDeLigaLeve;
    @Column(name = "controle_de_poluicao_do_ar")
    private boolean controleDePoluicaoDoAr;

    public boolean isCintoDeSeguranca() {
        return cintoDeSeguranca;
    }

    public void setCintoDeSeguranca(boolean cintoDeSeguranca) {
        this.cintoDeSeguranca = cintoDeSeguranca;
    }

    public boolean isDirecaoAssistida() {
        return direcaoAssistida;
    }

    public void setDirecaoAssistida(boolean direcaoAssistida) {
        this.direcaoAssistida = direcaoAssistida;
    }

    public boolean isAirBag() {
        return airBag;
    }

    public void setAirBag(boolean airBag) {
        this.airBag = airBag;
    }

    public boolean isRodaDeLigaLeve() {
        return rodaDeLigaLeve;
    }

    public void setRodaDeLigaLeve(boolean rodaDeLigaLeve) {
        this.rodaDeLigaLeve = rodaDeLigaLeve;
    }

    public boolean isControleDePoluicaoDoAr() {
        return controleDePoluicaoDoAr;
    }

    public void setControleDePoluicaoDoAr(boolean controleDePoluicaoDoAr) {
        this.controleDePoluicaoDoAr = controleDePoluicaoDoAr;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.cintoDeSeguranca ? 1 : 0);
        hash = 29 * hash + (this.direcaoAssistida ? 1 : 0);
        hash = 29 * hash + (this.airBag ? 1 : 0);
        hash = 29 * hash + (this.rodaDeLigaLeve ? 1 : 0);
        hash = 29 * hash + (this.controleDePoluicaoDoAr ? 1 : 0);
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
        final CaminhonetaDePassageiros other = (CaminhonetaDePassageiros) obj;
        if (this.cintoDeSeguranca != other.cintoDeSeguranca) {
            return false;
        }
        if (this.direcaoAssistida != other.direcaoAssistida) {
            return false;
        }
        if (this.airBag != other.airBag) {
            return false;
        }
        if (this.rodaDeLigaLeve != other.rodaDeLigaLeve) {
            return false;
        }
        if (this.controleDePoluicaoDoAr != other.controleDePoluicaoDoAr) {
            return false;
        }
        return true;
    }
    
    
}
