/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "log_pessoa_fisica")
@SequenceGenerator(name = "log_pessoa_fisica_sequencia", sequenceName = "log_pessoa_fisica_seq", initialValue = 1, allocationSize = 1)

public class LogPessoaFisica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_pessoa_fisica_sequencia")
    private Integer id;

    @Column(name = "cpf", length = 15, nullable = false, unique = true)
    private String CPF;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "data_vencimentocnh")
    @Temporal(TemporalType.DATE)
    private Date data_vencimentoCNH;

    private String identificacao;

    @Column(name = "numero_cnh", length = 11, nullable = false)
    private String numero_CNH;

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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getData_nascimento() {
        return dataNascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.dataNascimento = data_nascimento;
    }

    public Date getData_vencimentoCNH() {
        return data_vencimentoCNH;
    }

    public void setData_vencimentoCNH(Date data_vencimentoCNH) {
        this.data_vencimentoCNH = data_vencimentoCNH;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNumero_CNH() {
        return numero_CNH;
    }

    public void setNumero_CNH(String numero_CNH) {
        this.numero_CNH = numero_CNH;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.CPF);
        hash = 13 * hash + Objects.hashCode(this.dataNascimento);
        hash = 13 * hash + Objects.hashCode(this.data_vencimentoCNH);
        hash = 13 * hash + Objects.hashCode(this.identificacao);
        hash = 13 * hash + Objects.hashCode(this.numero_CNH);
        hash = 13 * hash + Objects.hashCode(this.nome);
        hash = 13 * hash + Objects.hashCode(this.codigo);
        hash = 13 * hash + Objects.hashCode(this.login);
        hash = 13 * hash + Objects.hashCode(this.senha);
        hash = 13 * hash + Objects.hashCode(this.endereco);
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
        final LogPessoaFisica other = (LogPessoaFisica) obj;
        if (!Objects.equals(this.CPF, other.CPF)) {
            return false;
        }
        if (!Objects.equals(this.identificacao, other.identificacao)) {
            return false;
        }
        if (!Objects.equals(this.numero_CNH, other.numero_CNH)) {
            return false;
        }
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
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        if (!Objects.equals(this.data_vencimentoCNH, other.data_vencimentoCNH)) {
            return false;
        }

        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }

}
