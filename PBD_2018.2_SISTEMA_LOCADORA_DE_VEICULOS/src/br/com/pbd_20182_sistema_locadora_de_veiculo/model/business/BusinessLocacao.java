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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public class BusinessLocacao implements IBusinessLocacao {

    private DAOLocacao dAOLocacao;

    public BusinessLocacao() {
        dAOLocacao = new DAOLocacao();
    }

    @Override
    public void salvar(Locacao locacao) throws DAOException, BusinessExpection {
        validar(locacao);
        dAOLocacao.salvar(locacao);
    }

    @Override
    public ArrayList<Locacao> listarTodos() throws DAOException {
        return dAOLocacao.findAll();
    }

    @Override
    public Locacao buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Locacao locacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Time procedureCalcularIntervaloDeAtraso(Calendar dataAtual, Integer id) throws DAOException {
        return dAOLocacao.procedureCalcularIntervaloDeAtraso(dataAtual, id);
    }

    @Override
    public boolean verificarVencimentoCNH(Calendar dataIda, Calendar dataVolta, Integer id) throws DAOException {
        return dAOLocacao.verificarVencimentoCNH(dataIda, dataVolta, id);
    }

    @Override
    public int calcularIdade(Integer id) throws DAOException {
        return dAOLocacao.calcularIdade(id);
    }

    private void validar(Locacao locacao) throws BusinessExpection, DAOException {
        String errorMessage = "";
        Fachada fachada = Fachada.getInstance();

        Calendar dataVoltaTem = Calendar.getInstance();
        Calendar dataIdaTemp = Calendar.getInstance();
        Calendar dataAtual = Calendar.getInstance();

        dataIdaTemp.setTime(locacao.getDataVolta().getTime());
        dataVoltaTem.setTime(locacao.getDataIda().getTime());
        dataAtual.setTime(new Date());

        dataIdaTemp.set(Calendar.HOUR_OF_DAY, 0);
        dataIdaTemp.set(Calendar.MINUTE, 0);
        dataIdaTemp.set(Calendar.SECOND, 0);
        dataIdaTemp.set(Calendar.MILLISECOND, 0);

        dataVoltaTem.set(Calendar.HOUR_OF_DAY, 0);
        dataVoltaTem.set(Calendar.MINUTE, 0);
        dataVoltaTem.set(Calendar.SECOND, 0);
        dataVoltaTem.set(Calendar.MILLISECOND, 0);

        dataAtual.set(Calendar.HOUR_OF_DAY, 0);
        dataAtual.set(Calendar.MINUTE, 0);
        dataAtual.set(Calendar.SECOND, 0);
        dataAtual.set(Calendar.MILLISECOND, 0);

        ArrayList<Veiculo> veiculos = fachada.buscarVeiculoPorCategoria(locacao.getVeiculo().getCategoria());

        if (!(locacao.getCliente() != null)) {
            errorMessage += "Por favor, selecione o cliente.";
        }

        if (!(locacao.getVeiculo() != null)) {
            errorMessage += "Por favor, selecione o veículo.";

        }
        if (!(locacao.getMotorista() != null)) {
            errorMessage += "Por favor, selecione o Motorista.";
        }

        if (!(locacao.getDataIda() != null)) {
            errorMessage += "Por favor, selecione a uma data de ida válida.";
        }
        if (dataIdaTemp.compareTo(dataAtual) < 0 || dataVoltaTem.compareTo(dataAtual) < 0) {
            errorMessage += "Datas não podem ser antes do dia de hoje. ";
        }

//        if (locacao.getDataIda().compareTo(locacao.getDataVolta()) <= 0) {
//            errorMessage += "Data tem que ter no mínimo um intervalo de um dia. ";
//        }
        if (!(locacao.getDataVolta() != null)) {
            errorMessage += "Por favor, selecione a uma data de volta válida.";
        }

        if (locacao.getKmInicialDoVeiculo() < 0) {
            errorMessage = "Informe o km inicial do veículo.";
        }
        if (locacao.getMetadeDaPrimeiraDiaria() <= 0) {
            errorMessage = "Informe o valor da primeira diária.";
        }

        if (!fachada.verificarVencimentoCNH(locacao.getDataIda(),
                locacao.getDataVolta(), locacao.getMotorista().getId())) {
            errorMessage = "Habilitação do motorista não pode vencer dentro do prazo  de locação.";

        }

        if (fachada.calcularIdade(locacao.getMotorista().getId()) < 21) {
            errorMessage = "O motorista deve ter idade igual ou superior a 20 anos.";
        }
        if (veiculos.isEmpty()) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.INFORMATION);
            alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Categoria não disponível",
                    "Categoria não disponível, sua categoria vai ser "
                    + "remanejada para uma categoria superior, porém com o mesmo valor.");

            locacao.getVeiculo().setCategoria(trocarDeCategoria(locacao.getVeiculo().getCategoria()));
            validar(locacao);

        } else {

            locacao.setVeiculo(veiculos.get(0));
            veiculos.get(0).setDisponivel(false);
            fachada.salvarVeiculo(veiculos.get(0));
        }
        if (errorMessage.length() != 0) {
            throw new BusinessExpection(errorMessage);
        }

    }

    public Categoria trocarDeCategoria(Categoria categoria) throws DAOException, BusinessExpection {

        Fachada fachada = Fachada.getInstance();

        int parteNumerica = Integer.parseInt(categoria.getNome().substring(2));
        parteNumerica += 1;
        String parteTexto = categoria.getNome().substring(0, 2);
        String nomeCategoria = parteTexto + parteNumerica;
        try {

            categoria = fachada.buscarCategoriaPorNome(nomeCategoria);
            System.err.println("ID" + categoria.getId());

            return categoria;

        } catch (DAOException e) {
            throw new BusinessExpection("Nenhuma categoria disponível no momento!");
        }

    }

}
