package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "categoria_sequence", sequenceName = "categoria_seq", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Categoria implements Serializable, EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_sequence")
    private Integer id;
    @Column(nullable = false)
    private String descricacao;
    @Column(nullable = false)
    private double valor;
    @Column(nullable = false, length = 20)
    private String nome;
    @Column(name = "numero_de_portas", nullable = false)
    private int numeroDePortas;
    @Column(name = "numero_de_passageiros")
    private int numeroDePassageiros;
    @Column(name = "ar_condicionado")
    private boolean arCondicionado;
    private boolean mp3;
    private boolean dvd;
    @Column(name = "direcao_hidraulica")
    private boolean direcaoHidraulica;
    private boolean radio;
    @Column(name = "tipo_de_cambio")
    private boolean tipoDeCambio;
    @Column(name = "camera_de_re")
    private boolean cameraDeRe;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricacao() {
        return descricacao;
    }

    public void setDescricacao(String descricacao) {
        this.descricacao = descricacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDePortas() {
        return numeroDePortas;
    }

    public void setNumeroDePortas(int numeroDePortas) {
        this.numeroDePortas = numeroDePortas;
    }

    public int getNumeroDePassageiros() {
        return numeroDePassageiros;
    }

    public void setNumeroDePassageiros(int numeroDePassageiros) {
        this.numeroDePassageiros = numeroDePassageiros;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isMp3() {
        return mp3;
    }

    public void setMp3(boolean mp3) {
        this.mp3 = mp3;
    }

    public boolean isDvd() {
        return dvd;
    }

    public void setDvd(boolean dvd) {
        this.dvd = dvd;
    }

    public boolean isDirecaoHidraulica() {
        return direcaoHidraulica;
    }

    public void setDirecaoHidraulica(boolean direcaoHidraulica) {
        this.direcaoHidraulica = direcaoHidraulica;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public boolean isTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(boolean tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public boolean isCameraDeRe() {
        return cameraDeRe;
    }

    public void setCameraDeRe(boolean cameraDeRe) {
        this.cameraDeRe = cameraDeRe;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.descricacao);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + this.numeroDePortas;
        hash = 29 * hash + this.numeroDePassageiros;
        hash = 29 * hash + (this.arCondicionado ? 1 : 0);
        hash = 29 * hash + (this.mp3 ? 1 : 0);
        hash = 29 * hash + (this.dvd ? 1 : 0);
        hash = 29 * hash + (this.direcaoHidraulica ? 1 : 0);
        hash = 29 * hash + (this.radio ? 1 : 0);
        hash = 29 * hash + (this.tipoDeCambio ? 1 : 0);
        hash = 29 * hash + (this.cameraDeRe ? 1 : 0);

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
        final Categoria other = (Categoria) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.numeroDePortas != other.numeroDePortas) {
            return false;
        }
        if (this.numeroDePassageiros != other.numeroDePassageiros) {
            return false;
        }
        if (this.arCondicionado != other.arCondicionado) {
            return false;
        }
        if (this.mp3 != other.mp3) {
            return false;
        }
        if (this.dvd != other.dvd) {
            return false;
        }
        if (this.direcaoHidraulica != other.direcaoHidraulica) {
            return false;
        }
        if (this.radio != other.radio) {
            return false;
        }
        if (this.tipoDeCambio != other.tipoDeCambio) {
            return false;
        }
        if (this.cameraDeRe != other.cameraDeRe) {
            return false;
        }

        if (!Objects.equals(this.descricacao, other.descricacao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
