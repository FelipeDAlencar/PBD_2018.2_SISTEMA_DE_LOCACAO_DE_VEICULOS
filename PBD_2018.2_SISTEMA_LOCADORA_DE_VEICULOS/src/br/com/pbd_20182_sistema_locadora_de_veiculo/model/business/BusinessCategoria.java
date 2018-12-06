/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoria;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessCategoria implements IBusinessCategoria {

    private DAOCategoria dAOCategoria;

    public BusinessCategoria() {
        dAOCategoria = new DAOCategoria();

    }

    @Override
    public void salvar(Categoria categoria) throws DAOException, BusinessExpection {

        validar(categoria);
        dAOCategoria.salvar(categoria);

    }

    @Override
    public ArrayList<Categoria> listarTodos() throws DAOException {
        return dAOCategoria.findAll();
    }

    @Override
    public Categoria buscarPorId(int id) throws DAOException {
        return dAOCategoria.findById(Categoria.class, id);
    }

    private boolean validar(Categoria categoria) throws BusinessExpection {

        if (categoria != null) {
            return true;
        } else {
            throw new BusinessExpection("ERRO DE TENTAR INSERIR!");
        }

    }

    @Override
    public String buscarUltimoNomeCategoria() {

        try {
            return dAOCategoria.buscarUltimoNome();
        } catch (DAOException ex) {
            Logger.getLogger(BusinessCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Categoria buscarPorNome(String nome) throws DAOException {
        return dAOCategoria.buscarPorNomeDisponivel(nome);
    }

    @Override
    public ArrayList<Categoria> buscarPorBusca(String busca) throws DAOException {
        return dAOCategoria.buscarPorBusca(busca);
    }

}
