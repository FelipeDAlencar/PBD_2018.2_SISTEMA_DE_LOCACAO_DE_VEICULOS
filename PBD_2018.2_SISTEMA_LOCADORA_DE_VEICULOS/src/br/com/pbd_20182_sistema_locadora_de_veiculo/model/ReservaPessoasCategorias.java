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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reservas_pessoa_categoria")
@SequenceGenerator(name = "reserva_sequence", sequenceName = "reserva_seq", initialValue = 1, allocationSize = 1)

public class ReservaPessoasCategorias implements Serializable, EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_sequence")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "data_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "valor_previsto")
    private double valorPrevisto;
   
    private boolean efetivada;
    
    private boolean ativo;

    @Override

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public double getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public boolean isEfetivada() {
        return efetivada;
    }

    public void setEfetivada(boolean status) {
        this.efetivada = status;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.categoria);
        hash = 97 * hash + Objects.hashCode(this.pessoa);
        hash = 97 * hash + Objects.hashCode(this.dataHora);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valorPrevisto) ^ (Double.doubleToLongBits(this.valorPrevisto) >>> 32));
        hash = 97 * hash + (this.efetivada ? 1 : 0);
        hash = 97 * hash + (this.ativo ? 1 : 0);
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
        final ReservaPessoasCategorias other = (ReservaPessoasCategorias) obj;
        if (Double.doubleToLongBits(this.valorPrevisto) != Double.doubleToLongBits(other.valorPrevisto)) {
            return false;
        }
        if (this.efetivada != other.efetivada) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.dataHora, other.dataHora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return categoria + " - " + pessoa + " - " + dataHora ;
    }
    
    
    

}
