/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public class BusinessReservaPessoaCategoria implements IBusinessRerservaPessoaCategoria {

    private DAOReservaPessoaCategoria dAOReservaPessoaCategoria;
    private Fachada fachada;

    public BusinessReservaPessoaCategoria() {
        dAOReservaPessoaCategoria = new DAOReservaPessoaCategoria();

    }

    @Override
    public void salvar(ReservaPessoasCategorias reservaPessoasCategorias) throws DAOException, BusinessExpection {

        validar(reservaPessoasCategorias);
        dAOReservaPessoaCategoria.salvar(reservaPessoasCategorias);

    }

    @Override
    public ArrayList<ReservaPessoasCategorias> listarTodos() {
        return dAOReservaPessoaCategoria.findAll();
    }

    @Override
    public ReservaPessoasCategorias buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(ReservaPessoasCategorias reservaPessoasCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validar(ReservaPessoasCategorias reservaPessoasCategorias) throws BusinessExpection, DAOException {
        fachada = Fachada.getInstance();
        Calendar dataHoraPrevista = Calendar.getInstance();
        dataHoraPrevista.setTime(new Date());
        double hora = Double.parseDouble(String.valueOf(dataHoraPrevista.get(Calendar.HOUR_OF_DAY)) + "."
                + String.valueOf(dataHoraPrevista.get(Calendar.MINUTE)));

        String errorMessage = "";
        
        
        
        
        System.err.println("Hora " + hora);
        if (hora < 8 || hora > 17) {

            errorMessage += "Horário não permitido \n";
        }

        ArrayList<Veiculo> veiculos = fachada.buscarVeiculoPorCategoria(reservaPessoasCategorias.getCategoria());

        if (veiculos.isEmpty()) {
            errorMessage += "Categoria não disponível, sua categoria vai ser "
                    + "remanejada para uma categoria superior, porém com o mesmo valor.";

        } else {
            veiculos.get(0).setDisponivel(false);
            fachada.salvarVeiculo(veiculos.get(0));
        }
        if (errorMessage.length() != 0) {
            throw new BusinessExpection("Atenção \n " + errorMessage);
        }

    }

}
