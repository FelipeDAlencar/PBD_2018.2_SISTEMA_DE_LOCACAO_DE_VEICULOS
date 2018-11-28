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
                + "                    LOWER(p.login) LIKE :login or LOWER(p.codigo) LIKE :codigo";

        public static final String SQL_PROCEDURE_CRIPTOGRAFAR_SENHA = "criptografar_senha";

    }

    public static class Categoria {

        public static final String SQL_BUSCAR_CATEGORIA_POR_ULTIMO_NOME = "select MAX(nome) from Categoria";
        public static final String SQL_BUSCAR_CATEGORIA_POR_NOME_DISPONIVEL = "select c from Categoria c where c.nome = :nome and disponivel = true";

    }

    public static class CaminhonetaDeCarga {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_CARGA_ULTIMONOME = "select MAX(nome) from CaminhonetaDeCarga";

    }

    public static class CaminhonetaDePassageiros {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_PASSAGEIROS_ULTIMONOME = "select MAX(nome) from CaminhonetaDePassageiros";

    }

    public static class Veiculo {

        public static final String SQL_BUSCAR_VEICULO_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = true";
        public static final String SQL_BUSCAR_VEICULO_NÃO_DISPONIVEIS_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = false";
    
    }

}
