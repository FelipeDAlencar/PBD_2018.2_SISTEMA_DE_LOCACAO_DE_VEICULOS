/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.JPA;

import java.io.File;

/**
 *
 * @author Felipe
 */
public class SQLUtil {

    public static class Pessoa {

        public static final String SQL_BUSCA_LOGIN = "select p from Pessoa p where p.login = :login and p.senha = :senha";
        public static final String SQL_BUSCA_ULTIMO_CODIGO_PESSOA = "select MAX(codigo) from Pessoa";
        public static final String SQL_BUSCA_ULTIMO_ID_PESSOA = "select MAX(id) from Pessoa";

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT p FROM Pessoa p WHERE LOWER(p.nome) LIKE :nome or "
                + "                    LOWER(p.login) LIKE :login or LOWER(p.codigo) LIKE :codigo ";

        public static final String SQL_PROCEDURE_CRIPTOGRAFAR_SENHA = "criptografar_senha";

    }

    public static class Categoria {

        public static final String SQL_BUSCAR_CATEGORIA_POR_ULTIMO_NOME = "select max(nome) from Categoria where  nome like 'CN%'";
        public static final String SQL_BUSCAR_CATEGORIA_POR_NOME_DISPONIVEL = "select c from Categoria c where c.nome = :nome and disponivel = true";
        public static final String BUSCAR_POR_BUSCA = "select c from Categoria c where LOWER(c.nome) like :nome "
                + "or LOWER(c.descricacao) like :descricao or to_char(c.valor, '999D9') like :valor  ";
    }

    public static class CaminhonetaDeCarga {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_CARGA_ULTIMONOME = "select MAX(nome) from CaminhonetaDeCarga";

    }

    public static class CaminhonetaDePassageiros {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_PASSAGEIROS_ULTIMONOME = "select MAX(nome) from CaminhonetaDePassageiros";

    }

    public static class Veiculo {

        public static final String SQL_BUSCAR_VEICULO_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = true";
        public static final String SQL_BUSCAR_VEICULO_NAO_DISPONIVEIS_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = false";
        public static final String SQL_BUSCA_POR_BUSCA = "SELECT v FROM Veiculo v WHERE LOWER(v.modelo) LIKE :modelo or "
                + " LOWER(v.cor) LIKE :cor or LOWER(to_char(v.anoDoModelo, '999')) LIKE :anoDoModelo";

    }

    public static class Geral {

        public static final String SQL_BUSCAR_GERAL = "select p from Geral p where p.id = 1";
    }

    public static class Funcionario {

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT p FROM Funcionario p WHERE LOWER(p.nome) LIKE :nome or "
                + "                    LOWER(p.login) LIKE :login or LOWER(p.matricula) LIKE :matricula and ativo = true";

    }

    public static class Filial {

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT F\n"
                + "FROM Filial as F\n"
                + "INNER JOIN Endereco as E\n"
                + "                on F.endereco = E.id  where LOWER(F.nome) like :nome or LOWER(E.rua) like :rua and F.ativo = true ";

    }

}
