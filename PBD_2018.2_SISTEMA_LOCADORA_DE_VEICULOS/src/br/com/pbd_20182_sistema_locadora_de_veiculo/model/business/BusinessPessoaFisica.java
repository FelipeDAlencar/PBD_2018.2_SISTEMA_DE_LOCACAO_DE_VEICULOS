/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Felipe
 */
public class BusinessPessoaFisica implements IBusinessPessoaFisica {

    private DAOPessoaFisica dAOPessoaFisica;

    public BusinessPessoaFisica() {
        dAOPessoaFisica = new DAOPessoaFisica();

    }

    @Override
    public void salvar(PessoaFisica pessoaFisica) throws DAOException, BusinessExpection {

        validar(pessoaFisica);

        dAOPessoaFisica.salvar(pessoaFisica);

    }

    @Override
    public ArrayList<PessoaFisica> listarTodos() throws DAOException {
        return dAOPessoaFisica.findAll();
    }

    @Override
    public PessoaFisica buscarPorId(int id) throws DAOException {
        return dAOPessoaFisica.findById(PessoaFisica.class, id);
    }

    @Override
    public void alterar(PessoaFisica pessoaFisica) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean validar(PessoaFisica pessoaFisica) throws BusinessExpection, DAOException {
        String errorMessage = "";

        if (pessoaFisica.getNome() == null || pessoaFisica.getNome().length() == 0) {
            errorMessage += "Nome inválido\n";
        }
        if (pessoaFisica.getCPF() == null || pessoaFisica.getCPF().length() == 0) {
            errorMessage += "CPF inválido\n";
        }
        if (pessoaFisica.getLogin() == null || pessoaFisica.getLogin().length() == 0) {
            errorMessage += "Login inválido\n";
        }
        if (pessoaFisica.getSenha() == null || pessoaFisica.getSenha().length() == 0) {
            errorMessage += "Senha inválido\n";
        }
        if (pessoaFisica.getNumero_CNH() == null || pessoaFisica.getNumero_CNH().length() == 0) {
            errorMessage += "Numero de CNH inválido\n";
        }
        if (pessoaFisica.getData_nascimento() == null) {
            errorMessage += "Data de nascimento invalida inválido\n";
        }
        if (pessoaFisica.getData_vencimentoCNH() == null) {
            errorMessage += "Data de vencimento inválido\n";
        }

        if (verificarCPF(pessoaFisica)) {
            errorMessage = "CPF já consta no sistema. Por favor, informe um válido";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            throw new BusinessExpection("ERRO AO TENTAR INSERIR" + errorMessage);
        }
    }

    public boolean verificarCPF(PessoaFisica pessoaFisica) throws DAOException {

        PessoaFisica pessoa = dAOPessoaFisica.buscarPorCPF(pessoaFisica.getCPF());

        if (!(pessoa != null)) {

            return false;
        }

        return true;
    }

    @Override
    public ArrayList<PessoaFisica> buscarPorNomeLike(String busca) throws DAOException {
        return dAOPessoaFisica.buscarPorNomeLike(busca);
    }
}
