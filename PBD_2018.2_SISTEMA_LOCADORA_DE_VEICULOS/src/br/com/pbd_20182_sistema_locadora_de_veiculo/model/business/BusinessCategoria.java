/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoria;
import java.util.ArrayList;

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
    public void salvar(Categoria categoria) throws BusinessExpection {
        if(validar(categoria)){
            salvar(categoria);
        }
    }

    @Override
    public ArrayList<Categoria> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean validar(Categoria categoria) {
       return true;
    }

}
