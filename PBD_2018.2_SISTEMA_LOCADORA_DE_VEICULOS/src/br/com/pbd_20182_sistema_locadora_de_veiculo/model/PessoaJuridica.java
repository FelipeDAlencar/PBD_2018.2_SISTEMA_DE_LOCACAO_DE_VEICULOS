/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa{
    @Column(name = "cnpj", nullable = false, length = 14)
    private String CNPJ;
    @Column(name = "inscricao_estadual", nullable = false, length = 9)
    private String inscriçãoEstadual;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getInscriçãoEstadual() {
        return inscriçãoEstadual;
    }

    public void setInscriçãoEstadual(String inscriçãoEstadual) {
        this.inscriçãoEstadual = inscriçãoEstadual;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.CNPJ);
        hash = 71 * hash + Objects.hashCode(this.inscriçãoEstadual);
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
        final PessoaJuridica other = (PessoaJuridica) obj;
        if (!Objects.equals(this.CNPJ, other.CNPJ)) {
            return false;
        }
        if (!Objects.equals(this.inscriçãoEstadual, other.inscriçãoEstadual)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
