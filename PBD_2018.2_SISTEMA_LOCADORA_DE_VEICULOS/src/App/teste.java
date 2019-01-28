/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewFilialPorEndereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewMotoristaPorLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContasAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOFilial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOFuncionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAORevisao;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

/**
 *
 * @author Felipe
 */
public class teste {

    public static void main(String[] args) throws DAOException, ParseException, JRException {

       DAOContasAPagar daocap = new DAOContasAPagar();
       
        System.err.println(daocap.buscarPorBuscaContaAPagar("200"));

//        System.err.println(format.parse(format.format(new Date())));
//
//        int input = 5418;// 1h : 30 min : 18 seg
//        int horas = input / 3600;
//        int minutos = (input - (horas * 3600)) / 60;
//        int segundos = input - (horas * 3600) - (minutos * 60);
//        System.out.printf("Horas: %d%nMinutos: %d%nSegundos: %d%n", horas, minutos, segundos);
        System.exit(0);

//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        Date date;
//        try {
//            date = formato.parse("20/11/2010");
//            System.err.println(formato.format(date));
//
//        } catch (ParseException ex) {
//            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
        DAOFuncionario daof = new DAOFuncionario();

        DAOPessoa daop = new DAOPessoa();
//        Funcionario funcionario = new Funcionario();
//        
//        funcionario.setAtivo(true);
//        funcionario.setCargo("Analista");
//        funcionario.setNome("Felipe");
//        //String UltimoCodigo = daof.buscarUltimoCodigo();
//        funcionario.setCodigo("Fel001");
//        funcionario.setLogin("felipe");
//        funcionario.setMatricula("123456");
//        funcionario.setSenha(funcionario.getMatricula());
//        funcionario.setSuperUsuario(true);
//        
//        Endereco endereco = new Endereco();
//        endereco.setBairro("Teste");
//        endereco.setCep("56580-000");
//        endereco.setCidade("Ibimirim");
//        endereco.setNumero(56);
//        endereco.setRua("Murilo Fagundes");
//        endereco.setUf("PE");
//        
//        
//        funcionario.setEndereco(endereco);
//        
//        daop.salvar(funcionario);
//        
//
//        System.err.println(daop.criptografarSenha("123456"));
//
//        try {
//            ProcessBuilder pb;
//            Process p;
//            pb = new ProcessBuilder("C:/Program Files/PostgreSQL/8.4/bin/pg_dump.exe ", "-i", "-h", "localhost", "-p", "5432", "-U", "seuusuario", "-F", "c", "-b", "-v", "-f", "C:\\Users\\MARK\\Desktop\\TesteBKP.sql", "teste");
//            pb.environment().put("PGPASSWORD", "suasenha");
//            pb.redirectErrorStream(true);
//            p = pb.start();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }

    }

}
