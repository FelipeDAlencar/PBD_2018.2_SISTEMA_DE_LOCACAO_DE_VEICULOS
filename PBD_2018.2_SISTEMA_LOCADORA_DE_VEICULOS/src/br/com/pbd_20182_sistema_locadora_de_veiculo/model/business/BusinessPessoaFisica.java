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
    public void salvar(PessoaFisica pessoaFisica) throws BusinessExpection {
        if(validar(pessoaFisica)){
        
            try {
                dAOPessoaFisica.salvar(pessoaFisica);
            } catch (DAOException ex) {
                throw new BusinessExpection("Erro no bus");
            }
            
        }
    }

    @Override
    public ArrayList<PessoaFisica> listarTodos() throws BusinessExpection {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PessoaFisica buscarPorId(int id) throws BusinessExpection {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(PessoaFisica pessoaFisica) throws BusinessExpection {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean validar(PessoaFisica pessoaFisica) {
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

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alerta alerta = Alerta.getInstace(AlertType.NONE);
            alerta.alertar(AlertType.ERROR, "Erro", 
                    "Erro ao validar campos.", errorMessage);
           
            return false;
        }
    }
}