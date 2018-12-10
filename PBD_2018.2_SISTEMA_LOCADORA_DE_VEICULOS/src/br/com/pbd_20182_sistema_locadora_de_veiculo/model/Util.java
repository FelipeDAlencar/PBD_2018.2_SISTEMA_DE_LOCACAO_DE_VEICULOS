/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Felipe
 */
public abstract class Util {

    public static String[] ufs = {"AC",
        "AL",
        "AM",
        "AP",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MG",
        "MS",
        "MT",
        "PA",
        "PB",
        "PE",
        "PI",
        "PR",
        "RJ",
        "RN",
        "RO",
        "RR",
        "RS",
        "SC",
        "SE",
        "SP",
        "TO"
    };

    public static String gerarCodigoInterno(String nome, String ultimoCodigo) {
        int parteNumerica = Integer.parseInt(ultimoCodigo.substring(3));
        parteNumerica += 1;
        String codigo = nome.substring(0, 3) + String.format("%03d", parteNumerica);

        return codigo;
    }

    public static String gerarNomeCategoria(String ultimoNome, int tipo) {

        if (ultimoNome != null) {
            int parteNumerica = Integer.parseInt(ultimoNome.substring(2));
            parteNumerica += 1;

            String novoNome = ultimoNome.substring(0, 2) + String.valueOf(parteNumerica);

            return novoNome;

        } else {
            switch (tipo) {
                case 1:
                    return "CN1";
                case 2:
                    return "CC1";
                default:
                    return "CP1";
            }

        }

    }

    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);

    }

    public static Date converterStringEmDate(String dataTexto) {
        DateFormat f = DateFormat.getDateInstance();
        java.util.Date d;
        try {
            d = f.parse(dataTexto);
            return d;

        } catch (ParseException ex) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.ERROR, "Erro ao tentar converter data", "", "");

        }
        return null;
    }

    public static LocalDate converterDateEmLocalDate(Date d) {

        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static void tirarCaracteres(TextField textField) {
        if (!textField.getText().matches("\\d.-*")) {
            textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
            textField.positionCaret(textField.getText().length());
        }
    }

//    ArrayList<Veiculo> veiculos = fachada.buscarVeiculoPorCategoria(reservaPessoasCategorias.getCategoria());
//
//        if (veiculos.isEmpty()) {
//            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
//            alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Categoria não disponível",
//                    "Categoria não disponível, sua categoria vai ser "
//                    + "remanejada para uma categoria superior, porém com o mesmo valor.");
//
//            Categoria categoria = fachada.buscarCategoriaPorId(reservaPessoasCategorias.getCategoria().getId());
//
//            reservaPessoasCategorias.setCategoria(trocarDeCategoria(categoria));
//
//            validar(reservaPessoasCategorias);
//
//        } else {
//            veiculos.get(0).setDisponivel(false);
//            fachada.salvarVeiculo(veiculos.get(0));
//        }
//
//        if (errorMessage.length() != 0) {
//            throw new BusinessExpection("Atenção \n " + errorMessage);
//        }
//public Categoria trocarDeCategoria(Categoria categoria) throws DAOException, BusinessExpection {
//
//        fachada = Fachada.getInstance();
//
//        categoria.setDisponivel(false);
//        fachada.salvarCategoria(categoria);
//
//        int parteNumerica = Integer.parseInt(categoria.getNome().substring(2));
//        parteNumerica += 1;
//        String parteTexto = categoria.getNome().substring(0, 2);
//        String nomeCategoria = parteTexto + parteNumerica;
//
//        try {
//            categoria = fachada.buscarCategoriaPorNome(nomeCategoria);
//
//            return categoria;
//
//        } catch (DAOException e) {
//            throw new BusinessExpection("Nenhuma categoria disponível no momento!");
//        }
//
//    }
}
