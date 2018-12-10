/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

/**
 *
 * @author Felipe
 */
public class teste {

    public static void main(String[] args) throws DAOException {
//        String nome = "CN1";
//        int parteNumerica = Integer.parseInt(nome.substring(2));
//        parteNumerica += 1;
//        String parteTexto = nome.substring(0,2);
//        String nomeCategoria = parteTexto + parteNumerica;
//        
//        System.err.println("parte numerica "  + parteNumerica);
//        System.err.println("parte texto" + parteTexto);
//        
//        System.err.println("Nome"  + nomeCategoria);

//        Fachada fachada = Fachada.getInstance();
//        
//        PessoaFisica pessoaFisica = fachada.buscarPorIdPessoaFisica(6);
//        
//        
//        System.err.println(pessoaFisica);
//        
//        System.err.println(Util.formatarData(pessoaFisica.getData_nascimento()));

        Fachada fachada = Fachada.getInstance();
        
        ArrayList<Veiculo> motorista = fachada.buscarPorBuscaVeiculo("Pál");
        
        System.err.println(motorista);
        
        
    }

}
