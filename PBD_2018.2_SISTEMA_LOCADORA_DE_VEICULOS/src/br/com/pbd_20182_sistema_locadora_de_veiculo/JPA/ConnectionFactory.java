/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.connection;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Felipe
 */
public class ConnectionFactory {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
    private static ConnectionFactory connectionFactory;
    
    
    public static ConnectionFactory getInstance(){
        if(connectionFactory != null){
            return connectionFactory;
        }
        
        return connectionFactory = new ConnectionFactory();
    }
    
    public EntityManager getConnection(){
        return emf.createEntityManager();
    }
    
}
