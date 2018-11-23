/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoriaCaminhonetaDeCarga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessCategoriaCaminhonetaDeCarga implements IBusinessCategoriaCaminhonetaDeCarga {

    private DAOCategoriaCaminhonetaDeCarga daoccdc;

    public BusinessCategoriaCaminhonetaDeCarga() {

        daoccdc = new DAOCategoriaCaminhonetaDeCarga();
    }

    @Override
    public void salvar(CaminhonetaDeCarga caminhonetaDeCarga) {
        try {
            daoccdc.salvar(caminhonetaDeCarga);
        } catch (DAOException ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public ArrayList<CaminhonetaDeCarga> listarTodos() {

        return daoccdc.findAll();

    }

    @Override
    public CaminhonetaDeCarga buscarPorId(int id) {

        try {
            return daoccdc.findById(CaminhonetaDeCarga.class, id);
        } catch (DAOException ex) {
            Logger.getLogger(BusinessCategoriaCaminhonetaDeCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }

    @Override
    public void alterar(CaminhonetaDeCarga caminhonetaDeCarga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscarUltimoNomeCaminhonetaDeCarga() {

        try {
            return daoccdc.buscarUltimoNome();
        } catch (DAOException ex) {
            Logger.getLogger(BusinessCategoriaCaminhonetaDeCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return null;
    }

}
