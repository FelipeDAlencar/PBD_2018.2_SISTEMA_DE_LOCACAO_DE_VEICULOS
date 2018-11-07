package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Funcionario extends Pessoa{
    
    @Column(nullable = false)
    private String cargo;
    @Column(name = "super_usuario")
    private boolean superUsuario;
    

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isSuperUsuario() {
        return superUsuario;
    }

    public void setSuperUsuario(boolean superUsuario) {
        this.superUsuario = superUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cargo);
        hash = 97 * hash + (this.superUsuario ? 1 : 0);
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
        final Funcionario other = (Funcionario) obj;
        if (this.superUsuario != other.superUsuario) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
