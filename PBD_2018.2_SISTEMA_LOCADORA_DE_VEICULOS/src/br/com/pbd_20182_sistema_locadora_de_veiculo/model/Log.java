/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "log_sequencia", sequenceName = "log_seq", initialValue = 1, allocationSize = 1)
public class Log implements Serializable, EntidadeBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_sequencia")
    private Integer id;
    @Column(name = "quem_modificou", length = 200)
    private String quemModificou;
    @Column(name = "como_era")
    private String comoEra;
    @Column(name = "como_esta")
    private String comoEsta;
    @Column(name = "data_de_modificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataDeModificacao;
    
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuemModificou() {
        return quemModificou;
    }

    public void setQuemModificou(String quemModificou) {
        this.quemModificou = quemModificou;
    }

    public String getComoEra() {
        return comoEra;
    }

    public void setComoEra(String comoEra) {
        this.comoEra = comoEra;
    }

    public String getComoEsta() {
        return comoEsta;
    }

    public void setComoEsta(String comoEsta) {
        this.comoEsta = comoEsta;
    }

    public Calendar getDataDeModificacao() {
        return dataDeModificacao;
    }

    public void setDataDeModificacao(Calendar dataDeModificacao) {
        this.dataDeModificacao = dataDeModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.quemModificou);
        hash = 59 * hash + Objects.hashCode(this.comoEra);
        hash = 59 * hash + Objects.hashCode(this.comoEsta);
        hash = 59 * hash + Objects.hashCode(this.dataDeModificacao);
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
        final Log other = (Log) obj;
        if (!Objects.equals(this.quemModificou, other.quemModificou)) {
            return false;
        }
        if (!Objects.equals(this.comoEra, other.comoEra)) {
            return false;
        }
        if (!Objects.equals(this.comoEsta, other.comoEsta)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataDeModificacao, other.dataDeModificacao)) {
            return false;
        }
        return true;
    }   
    
}
