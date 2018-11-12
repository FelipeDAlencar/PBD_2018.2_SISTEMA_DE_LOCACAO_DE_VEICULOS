/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javassist.expr.Instanceof;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
@SequenceGenerator(name = "pessoa_sequencia", sequenceName = "pessoa_seq", initialValue = 1, allocationSize = 1)
public class Pessoa implements EntidadeBase, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequencia")
    private Integer id;
    @Column(length = 30, nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String codigo;
    @Column(length = 50, nullable = false, unique = true)
    private String login;
    @Column(length = 50, nullable = false)
    private String senha;
    
    @OneToOne
    private Endereco endereco;
    
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<ReservaPessoasCategorias> reservaPessoasCategorias;
    
    
    
    
    
    
    

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<ReservaPessoasCategorias> getReservaPessoasCategorias() {
        return reservaPessoasCategorias;
    }

    public void setReservaPessoasCategorias(ArrayList<ReservaPessoasCategorias> reservaPessoasCategorias) {
        this.reservaPessoasCategorias = reservaPessoasCategorias;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setReservaPessoasCategorias(List<ReservaPessoasCategorias> reservaPessoasCategorias) {
        this.reservaPessoasCategorias = reservaPessoasCategorias;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.codigo);
        hash = 47 * hash + Objects.hashCode(this.login);
        hash = 47 * hash + Objects.hashCode(this.senha);
        hash = 47 * hash + Objects.hashCode(this.endereco);
        hash = 47 * hash + Objects.hashCode(this.reservaPessoasCategorias);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.reservaPessoasCategorias, other.reservaPessoasCategorias)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    

    
    
    

   
    
    
    
}
