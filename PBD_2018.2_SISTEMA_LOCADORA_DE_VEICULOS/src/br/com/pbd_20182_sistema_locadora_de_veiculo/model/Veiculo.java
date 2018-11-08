/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "veiculo_sequencia", sequenceName = "veiculo_seq", initialValue = 1, allocationSize = 1)
public class Veiculo implements Serializable, EntidadeBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_sequencia")
    private Integer id;
    @Column(length = 50, nullable = false)
    private String cor;
    @Column(name = "quilometragem_atual", nullable = false)
    private double quilometragemAtual;
    @Column(name = "numero_chassi", nullable = false)
    private int numeroChassi;
    @Column(length = 70, nullable = false)
    private String modelo;
    @Column(name = "torque_do_motor")
    private double torqueDoMotor;
    @Column(name = "ano_de_fabricacao")
    private int anoDeFabricacao;
    @Column(name = "ano_do_modelo", nullable = false)
    private int anoDoModelo;
    @Column(length = 100)
    private String fabricante;
    @Column(length = 8, nullable = false)
    private String placa;
    @Column(name = "tipo_de_combustivel", length = 100)
    private String tipoDeCombustivel;
    @OneToOne
    private PessoaFisica pessoaFisica;
    @ManyToOne
    private Categoria categoria;
    
    
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(double quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public int getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(int numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getTorqueDoMotor() {
        return torqueDoMotor;
    }

    public void setTorqueDoMotor(double torqueDoMotor) {
        this.torqueDoMotor = torqueDoMotor;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public int getAnoDoModelo() {
        return anoDoModelo;
    }

    public void setAnoDoModelo(int anoDoModelo) {
        this.anoDoModelo = anoDoModelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }

    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.cor);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.quilometragemAtual) ^ (Double.doubleToLongBits(this.quilometragemAtual) >>> 32));
        hash = 79 * hash + this.numeroChassi;
        hash = 79 * hash + Objects.hashCode(this.modelo);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.torqueDoMotor) ^ (Double.doubleToLongBits(this.torqueDoMotor) >>> 32));
        hash = 79 * hash + this.anoDeFabricacao;
        hash = 79 * hash + this.anoDoModelo;
        hash = 79 * hash + Objects.hashCode(this.fabricante);
        hash = 79 * hash + Objects.hashCode(this.placa);
        hash = 79 * hash + Objects.hashCode(this.tipoDeCombustivel);
        hash = 79 * hash + Objects.hashCode(this.pessoaFisica);
        hash = 79 * hash + Objects.hashCode(this.categoria);
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
        final Veiculo other = (Veiculo) obj;
        if (Double.doubleToLongBits(this.quilometragemAtual) != Double.doubleToLongBits(other.quilometragemAtual)) {
            return false;
        }
        if (this.numeroChassi != other.numeroChassi) {
            return false;
        }
        if (Double.doubleToLongBits(this.torqueDoMotor) != Double.doubleToLongBits(other.torqueDoMotor)) {
            return false;
        }
        if (this.anoDeFabricacao != other.anoDeFabricacao) {
            return false;
        }
        if (this.anoDoModelo != other.anoDoModelo) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.fabricante, other.fabricante)) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.tipoDeCombustivel, other.tipoDeCombustivel)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.pessoaFisica, other.pessoaFisica)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
    
    
    
    

    
    
   
    
}
