/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoriaCaminhonetaDePassageiros;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessCategoriaCaminhonetaDePassageiros implements IBusinessCategoriaCaminhonetaDePassageiros{

    private DAOCategoriaCaminhonetaDePassageiros daoccdp;

    public BusinessCategoriaCaminhonetaDePassageiros() {
        
        daoccdp = new DAOCategoriaCaminhonetaDePassageiros();
    
    }
    
    
    
    
    @Override
    public void salvar(CaminhonetaDePassageiros caminhonetaDePassageiros) {
        
        try {
            daoccdp.salvar(caminhonetaDePassageiros);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public ArrayList<CaminhonetaDePassageiros> listarTodos() {
        return daoccdp.findAll();
    }

    @Override
    public CaminhonetaDePassageiros buscarPorId(int id) {
        try {
            return daoccdp.findById(CaminhonetaDePassageiros.class, id);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public void alterar(CaminhonetaDePassageiros caminhonetaDePassageiros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscarUltimoNomeCaminhonetaDePassageiros() {
        
        try {
            return daoccdp.buscarUltimoNome();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        
        return null;
        
    }
    
}
