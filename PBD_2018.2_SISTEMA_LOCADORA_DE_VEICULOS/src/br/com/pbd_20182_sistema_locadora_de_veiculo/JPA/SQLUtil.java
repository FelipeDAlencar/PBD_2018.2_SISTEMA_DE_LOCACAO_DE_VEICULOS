/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.JPA;

/**
 *
 * @author Felipe
 */
public class SQLUtil {

    public static class Pessoa {

        public static final String SQL_BUSCA_LOGIN = "select p from Pessoa p where p.login = :login and p.senha = :senha";
        public static final String SQL_BUSCA_ULTIMOCODIGO = "select MAX(codigo) from Pessoa";
        public static final String SQL_BUSCAPORBUSCA = "SELECT p FROM Pessoa p WHERE LOWER(p.nome) LIKE :nome or " +
"                    LOWER(p.login) LIKE :login or LOWER(p.codigo) LIKE :codigo";
        
    }
    
    
    

}
