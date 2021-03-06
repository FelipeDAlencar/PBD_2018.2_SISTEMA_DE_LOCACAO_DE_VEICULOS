/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Felipe
 */
public abstract class Util {

    private static Fachada fachada = Fachada.getInstance();
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

        if (ultimoCodigo.length() != 0) {
            int parteNumerica = Integer.parseInt(ultimoCodigo.substring(3));
            parteNumerica += 1;
            String codigo = nome.substring(0, 3) + String.format("%03d", parteNumerica);
            return codigo;
        }

        return nome.substring(0, 3) + String.format("%03d", "1");
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

    public static String removerCaracteres(String textField) {

        textField = textField.replaceAll("\\.", "");
        textField = textField.replaceAll("-", "");
        textField = textField.replaceAll("/", "");
        return textField;
    }

    public static double calcularQuantidadeDeHorasAtrasadas(Locacao locacao) throws DAOException {

        for (Locacao l : ThreadDeVerificacaoDeLocacoes.locacaosEmAtraso) {
            if (locacao.getId() == l.getId()) {

                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                Time intervalo = fachada.procedureCalcularIntervaloDeAtraso(c, locacao.getId());
                c.setTime(intervalo);

                int qtdHoras = c.get(Calendar.HOUR);
                double valor = 0;
                System.err.println("Aqui " + qtdHoras);

                if (qtdHoras > 1 && qtdHoras % 4 != 0) {
                    int diariasCompletas = (qtdHoras / 4);//2
                    double valorDiariasCompletas = diariasCompletas * ThreadDeVerificacaoDeLocacoes.VALOR_DIARIA;
                    double resto = (ThreadDeVerificacaoDeLocacoes.VALOR_DIARIA / 4) * (qtdHoras % 4);//

                    valor = ((valorDiariasCompletas + resto) + locacao.getValor());
                } else {

                    double valorDiarias = (qtdHoras / 4) * ThreadDeVerificacaoDeLocacoes.VALOR_DIARIA;

                    valor = (valorDiarias + locacao.getValor());
                }

                return valor;
            }
        }
        return 0;
    }

    public static Date converterLocalDateEmDate(LocalDate localDate) {
        Date s = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return s;

    }
    public static void gerarRelatorio(InputStream caminho, ArrayList<? extends Object> lista) throws JRException{
        
        JasperDesign desenho = JRXmlLoader.load(caminho);

        JasperReport jasperReport = JasperCompileManager.compileReport(desenho);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(lista));

        //false pq n�o deixa feixar a 'aplica��o principal
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
        
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
