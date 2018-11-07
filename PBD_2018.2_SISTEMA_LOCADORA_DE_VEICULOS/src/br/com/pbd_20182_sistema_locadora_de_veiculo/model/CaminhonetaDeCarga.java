package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caminhoneta_de_carga")
public class CaminhonetaDeCarga extends Categoria{
    
    @Column(name = "potencia_do_motor", nullable = false)
    private double potenciaDoMotor;
    @Column(name = "distacia_entre_eixos", nullable = false)
    private double distanciaEntreEixos;
    @Column(name = "capacidade_de_carga", nullable = false)
    private double capacidadeDeCarga;
    @Column(name = "acionamento_de_embreagem", nullable = false)
    private String acionamentoDeEmbragem;
    @Column(length = 250)
    private String desempenho;
    @Column(name = "volume_de_abastecimento",nullable = false)
    private double volumeDeAbastecimento;

    public double getPotenciaDoMotor() {
        return potenciaDoMotor;
    }

    public void setPotenciaDoMotor(double potenciaDoMotor) {
        this.potenciaDoMotor = potenciaDoMotor;
    }

    public double getDistanciaEntreEixos() {
        return distanciaEntreEixos;
    }

    public void setDistanciaEntreEixos(double distanciaEntreEixos) {
        this.distanciaEntreEixos = distanciaEntreEixos;
    }

    public double getCapacidadeDeCarga() {
        return capacidadeDeCarga;
    }

    public void setCapacidadeDeCarga(double capacidadeDeCarga) {
        this.capacidadeDeCarga = capacidadeDeCarga;
    }

    public String getAcionamentoDeEmbragem() {
        return acionamentoDeEmbragem;
    }

    public void setAcionamentoDeEmbragem(String acionamentoDeEmbragem) {
        this.acionamentoDeEmbragem = acionamentoDeEmbragem;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }

    public double getVolumeDeAbastecimento() {
        return volumeDeAbastecimento;
    }

    public void setVolumeDeAbastecimento(double volumeDeAbastecimento) {
        this.volumeDeAbastecimento = volumeDeAbastecimento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.potenciaDoMotor) ^ (Double.doubleToLongBits(this.potenciaDoMotor) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.distanciaEntreEixos) ^ (Double.doubleToLongBits(this.distanciaEntreEixos) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.capacidadeDeCarga) ^ (Double.doubleToLongBits(this.capacidadeDeCarga) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.acionamentoDeEmbragem);
        hash = 71 * hash + Objects.hashCode(this.desempenho);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.volumeDeAbastecimento) ^ (Double.doubleToLongBits(this.volumeDeAbastecimento) >>> 32));
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
        final CaminhonetaDeCarga other = (CaminhonetaDeCarga) obj;
        if (Double.doubleToLongBits(this.potenciaDoMotor) != Double.doubleToLongBits(other.potenciaDoMotor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.distanciaEntreEixos) != Double.doubleToLongBits(other.distanciaEntreEixos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.capacidadeDeCarga) != Double.doubleToLongBits(other.capacidadeDeCarga)) {
            return false;
        }
        if (Double.doubleToLongBits(this.volumeDeAbastecimento) != Double.doubleToLongBits(other.volumeDeAbastecimento)) {
            return false;
        }
        if (!Objects.equals(this.acionamentoDeEmbragem, other.acionamentoDeEmbragem)) {
            return false;
        }
        if (!Objects.equals(this.desempenho, other.desempenho)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
