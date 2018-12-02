/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
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
    public void salvar(CaminhonetaDeCarga caminhonetaDeCarga) throws DAOException, BusinessExpection {

        validar(caminhonetaDeCarga);
        daoccdc.salvar(caminhonetaDeCarga);

    }

    @Override
    public ArrayList<CaminhonetaDeCarga> listarTodos() throws DAOException {

        return daoccdc.findAll();

    }

    @Override
    public CaminhonetaDeCarga buscarPorId(int id) throws DAOException {

        try {
            return daoccdc.findById(CaminhonetaDeCarga.class, id);
        } catch (DAOException ex) {
            Logger.getLogger(BusinessCategoriaCaminhonetaDeCarga.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void alterar(CaminhonetaDeCarga caminhonetaDeCarga) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscarUltimoNomeCaminhonetaDeCarga() throws DAOException {

        try {
            return daoccdc.buscarUltimoNome();
        } catch (DAOException ex) {
            Logger.getLogger(BusinessCategoriaCaminhonetaDeCarga.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void validar(CaminhonetaDeCarga caminhonetaDeCarga) throws BusinessExpection {

        if (!(caminhonetaDeCarga != null)) {

            throw new BusinessExpection("ERRO AO TENTAR INSERIR");

        }

    }

}
