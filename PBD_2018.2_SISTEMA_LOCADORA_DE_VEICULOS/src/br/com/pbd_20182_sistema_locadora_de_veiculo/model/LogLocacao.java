/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Entity
@SequenceGenerator(name = "log_locacao_sequencia", sequenceName = "log_locacao_seq", initialValue = 1, allocationSize = 1)
@Table(name = "log_locacao")
public class LogLocacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_locacao_sequencia")
    private Integer id;
    
    @Column(nullable = false)
    private boolean finalizada;
    
    private double valor;
    
    @Column(name = "km_inicial_veiculo", nullable = false)
    private double KmInicialDoVeiculo;
    
    @Column(name = "metade_primeira_diaria", nullable = false)
    private double metadeDaPrimeiraDiaria;
    
    @Column(name = "km_final_veiculo")
    private double kmFinalVeiculo;
    
    @Column(name = "taxa_combustivel")
    private double taxaCombustivel;
    
    @Column(name = "taxa_higienizacao")
    private double taxaHigienizacao;
    
    @Column(name = "data_ida", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataIda;
    
    @Column(name = "data_volta")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataVolta;
    
    @Column(name = "km_livre")
    private boolean kmLivre;
    
    @Column()
    private boolean ativo;
    
    @Column(name = "data_modificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataModificacao;
    
    @Column(name = "alteracao")
    private String alteracao;
    
    @OneToOne
    private Filial pontoDeEntregea;

    @OneToOne
    private Pessoa cliente;

    @OneToOne
    private PessoaFisica motorista;

    @OneToOne
    private Veiculo veiculo;

    @OneToOne
    private Locacao locacao;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getKmInicialDoVeiculo() {
        return KmInicialDoVeiculo;
    }

    public void setKmInicialDoVeiculo(double KmInicialDoVeiculo) {
        this.KmInicialDoVeiculo = KmInicialDoVeiculo;
    }

    public double getMetadeDaPrimeiraDiaria() {
        return metadeDaPrimeiraDiaria;
    }

    public void setMetadeDaPrimeiraDiaria(double metadeDaPrimeiraDiaria) {
        this.metadeDaPrimeiraDiaria = metadeDaPrimeiraDiaria;
    }

    public double getKmFinalVeiculo() {
        return kmFinalVeiculo;
    }

    public void setKmFinalVeiculo(double kmFinalVeiculo) {
        this.kmFinalVeiculo = kmFinalVeiculo;
    }

    public double getTaxaCombustivel() {
        return taxaCombustivel;
    }

    public void setTaxaCombustivel(double taxaCombustivel) {
        this.taxaCombustivel = taxaCombustivel;
    }

    public double getTaxaHigienizacao() {
        return taxaHigienizacao;
    }

    public void setTaxaHigienizacao(double taxaHigienizacao) {
        this.taxaHigienizacao = taxaHigienizacao;
    }

    public Calendar getDataIda() {
        return dataIda;
    }

    public void setDataIda(Calendar dataIda) {
        this.dataIda = dataIda;
    }

    public Calendar getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(Calendar dataVolta) {
        this.dataVolta = dataVolta;
    }

    public boolean isKmLivre() {
        return kmLivre;
    }

    public void setKmLivre(boolean kmLivre) {
        this.kmLivre = kmLivre;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Filial getPontoDeEntregea() {
        return pontoDeEntregea;
    }

    public void setPontoDeEntregea(Filial pontoDeEntregea) {
        this.pontoDeEntregea = pontoDeEntregea;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public PessoaFisica getMotorista() {
        return motorista;
    }

    public void setMotorista(PessoaFisica motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Calendar getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Calendar dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + (this.finalizada ? 1 : 0);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.KmInicialDoVeiculo) ^ (Double.doubleToLongBits(this.KmInicialDoVeiculo) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.metadeDaPrimeiraDiaria) ^ (Double.doubleToLongBits(this.metadeDaPrimeiraDiaria) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.kmFinalVeiculo) ^ (Double.doubleToLongBits(this.kmFinalVeiculo) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taxaCombustivel) ^ (Double.doubleToLongBits(this.taxaCombustivel) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taxaHigienizacao) ^ (Double.doubleToLongBits(this.taxaHigienizacao) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.dataIda);
        hash = 97 * hash + Objects.hashCode(this.dataVolta);
        hash = 97 * hash + (this.kmLivre ? 1 : 0);
        hash = 97 * hash + (this.ativo ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.dataModificacao);
        hash = 97 * hash + Objects.hashCode(this.alteracao);
        hash = 97 * hash + Objects.hashCode(this.pontoDeEntregea);
        hash = 97 * hash + Objects.hashCode(this.cliente);
        hash = 97 * hash + Objects.hashCode(this.motorista);
        hash = 97 * hash + Objects.hashCode(this.veiculo);
        hash = 97 * hash + Objects.hashCode(this.locacao);
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
        final LogLocacao other = (LogLocacao) obj;
        if (this.finalizada != other.finalizada) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.KmInicialDoVeiculo) != Double.doubleToLongBits(other.KmInicialDoVeiculo)) {
            return false;
        }
        if (Double.doubleToLongBits(this.metadeDaPrimeiraDiaria) != Double.doubleToLongBits(other.metadeDaPrimeiraDiaria)) {
            return false;
        }
        if (Double.doubleToLongBits(this.kmFinalVeiculo) != Double.doubleToLongBits(other.kmFinalVeiculo)) {
            return false;
        }
        if (Double.doubleToLongBits(this.taxaCombustivel) != Double.doubleToLongBits(other.taxaCombustivel)) {
            return false;
        }
        if (Double.doubleToLongBits(this.taxaHigienizacao) != Double.doubleToLongBits(other.taxaHigienizacao)) {
            return false;
        }
        if (this.kmLivre != other.kmLivre) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.alteracao, other.alteracao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataIda, other.dataIda)) {
            return false;
        }
        if (!Objects.equals(this.dataVolta, other.dataVolta)) {
            return false;
        }
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) {
            return false;
        }
        if (!Objects.equals(this.pontoDeEntregea, other.pontoDeEntregea)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.motorista, other.motorista)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.locacao, other.locacao)) {
            return false;
        }
        return true;
    }
    
    
   
    

}
