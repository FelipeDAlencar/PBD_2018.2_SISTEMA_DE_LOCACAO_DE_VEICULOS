/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public class BusinessPessoaJuridica implements IBusinessPessoaJuridica {

    private DAOPessoaJuridica dAOPessoaJuridica;

    public BusinessPessoaJuridica() {
        dAOPessoaJuridica = new DAOPessoaJuridica();
    }

    @Override
    public void salvar(PessoaJuridica pessoaJuridica) throws BusinessExpection, DAOException {

        validar(pessoaJuridica);
        dAOPessoaJuridica.salvar(pessoaJuridica);

    }

    @Override
    public ArrayList<PessoaJuridica> listarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PessoaJuridica buscarPorId(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(PessoaJuridica pessoaJuridica) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void validar(PessoaJuridica pessoaJuridica) throws BusinessExpection {
        String errorMessage = "";

        if (pessoaJuridica.getNome() == null || pessoaJuridica.getNome().length() == 0) {
            errorMessage += "Nome inválido\n";
        }
        if (pessoaJuridica.getCNPJ() == null || pessoaJuridica.getCNPJ().length() == 0) {
            errorMessage += "CNPJ inválido\n";
        }
        if (pessoaJuridica.getLogin() == null || pessoaJuridica.getLogin().length() == 0) {
            errorMessage += "Login inválido\n";
        }
        if (pessoaJuridica.getSenha() == null || pessoaJuridica.getSenha().length() == 0) {
            errorMessage += "Senha inválido\n";
        }
        if (pessoaJuridica.getInscriçãoEstadual() == null || pessoaJuridica.getInscriçãoEstadual().length() == 0) {
            errorMessage += "Numero de incrição estadual inválido\n";
        }

        if (errorMessage.length() != 0) {
            throw new BusinessExpection(errorMessage);
        }
    }

}
