/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Log;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IDAOLog {
    
     public ArrayList<Log> findAll();
}
