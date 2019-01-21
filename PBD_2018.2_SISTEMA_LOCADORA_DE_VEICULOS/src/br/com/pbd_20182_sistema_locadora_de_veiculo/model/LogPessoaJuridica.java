/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
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
@SequenceGenerator(name = "log_pessoa_juridica_sequencia", sequenceName = "log_pessoa_juridicapessoa_seq", initialValue = 1, allocationSize = 1)
@Table(name = "log_pessoa_juridica")
public class LogPessoaJuridica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_pessoa_juridica_sequencia")
    private Integer id;
    
    @Column(length = 30, nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(length = 50, nullable = false, unique = true)
    private String login;
    
    @Column(length = 50, nullable = false)
    private String senha;

    @Column(name = "cnpj", nullable = false, length = 20)
    private String CNPJ;
    
    @Column(name = "inscricao_estadual", nullable = false, length = 11)
    private String inscriçãoEstadual;
    
    @Column(name = "data_modificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataModificacao;
    
    @Column(name = "alteracao")
    private String alteracao;
    @OneToOne
    private Endereco endereco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.codigo);
        hash = 19 * hash + Objects.hashCode(this.login);
        hash = 19 * hash + Objects.hashCode(this.senha);
        hash = 19 * hash + Objects.hashCode(this.CNPJ);
        hash = 19 * hash + Objects.hashCode(this.inscriçãoEstadual);
        hash = 19 * hash + Objects.hashCode(this.dataModificacao);
        hash = 19 * hash + Objects.hashCode(this.alteracao);
        hash = 19 * hash + Objects.hashCode(this.endereco);
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
        final LogPessoaJuridica other = (LogPessoaJuridica) obj;
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
        if (!Objects.equals(this.CNPJ, other.CNPJ)) {
            return false;
        }
        if (!Objects.equals(this.inscriçãoEstadual, other.inscriçãoEstadual)) {
            return false;
        }
        if (!Objects.equals(this.alteracao, other.alteracao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }
    
    
   

}
