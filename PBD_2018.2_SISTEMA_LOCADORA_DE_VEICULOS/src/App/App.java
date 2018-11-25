/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.controller.FXMLLoginController;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Backup;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOGenerico;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOFuncionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.minhaData;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class App extends Application implements Runnable {

    private Thread thread;
    private Fachada fachada;

    @Override
    public void start(Stage primaryStage) throws IOException, DAOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLLogin.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return new FXMLLoginController(primaryStage);
            }
        });
        Pane root = loader.load();

        //Pane root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVboxTelaPrincipal.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("LOCADORA DE VEÍCULOS PAJEÚ");

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        //new FXMLLoginController(primaryStage);

        DAOGenerico dao = new DAOGenerico();

//        Date data = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        System.out.println(sdf.format(data));
        //dao.salvar(pessoa);
//        PessoaJuridica pessoaJuridica = new PessoaJuridica();
//        pessoaJuridica.setCNPJ("123456");
//        pessoaJuridica.setInscriçãoEstadual("00005");
//        pessoaJuridica.setNome("Abimael");
//        pessoaJuridica.setCodigo("001");
//        pessoaJuridica.setLogin("Abi");
//        pessoaJuridica.setSenha(pessoaJuridica.getCNPJ());
//        dao.salvar(pessoaJuridica);
//        Funcionario funcionario = new Funcionario();
//        funcionario.setLogin("Igor");
//        funcionario.setSenha("123");
//        funcionario.setCodigo("8080");
//        funcionario.setNome("igo");
//        funcionario.setCargo("Funcionario");
//        funcionario.setSuperUsuario(false);
//        funcionario.setMatricula("01888");
//        dao.salvar(funcionario);
//
//        Pessoa p =  (Pessoa) dao.findById(Pessoa.class, 4);
//
//        DAOPessoa dAOPessoa = new DAOPessoa();
//
//        Funcionario funcionario1 = new Funcionario();
//        funcionario1.setLogin("Alisim");
//        funcionario1.setSenha("852");
//        funcionario1.setCodigo("000");
//        funcionario1.setNome("Alisson");
//        funcionario1.setCargo("Gerente");
//
//        try {
//            if (dAOPessoa.buscarLogin(p) != null) {
//                System.out.println("Logou");
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Erro ao logar");
//        }
//
//        Pessoa.verificarTipoUsuarioLogin(p);
//        DAOPessoa dAOPessoa = new DAOPessoa();
//        String ultimoCodigo = dAOPessoa.buscarUltimoCodigo();
//       System.out.println(Util.gerarCodigoInterno("Felipe", "luc112"));
//        Categoria categoria =  new Categoria();
//        categoria.setDescricacao("Tem isso, aquilo, e tals");
//        categoria.setValor(500);
//        categoria.setNome("P1");
//        categoria.setNumeroDePortas(4);
//        categoria.setArCondicionado(true);
//        categoria.setMp3(true);
//        categoria.setNumeroDePassageiros(5);
//        categoria.setDvd(true);
//        categoria.setDirecaoHidraulica(true);
//        categoria.setRadio(true);
//        categoria.setTipoDeCambio(true);
//        categoria.setCameraDeRe(true);
//        
//        
//                  CODIGO DE INSERÇÃO DE RESERVA    
//        DAOCategoria dAOCategoria = new DAOCategoria();
//        DAOFuncionario dAOFuncionario = new DAOFuncionario();
//        Funcionario funcionario = dAOFuncionario.findAll().get(0);
//        Categoria categoria = dAOCategoria.findAll().get(0);
//        DAOPessoaFisica dAOPessoaFisica = new DAOPessoaFisica();
//        PessoaFisica pessoaFisica = dAOPessoaFisica.findAll().get(0);
//        ReservaPessoasCategorias reservaPessoasCategorias = new ReservaPessoasCategorias();
//        reservaPessoasCategorias.setCategoria(categoria);
//        reservaPessoasCategorias.setDataHora(Calendar.getInstance());
//        reservaPessoasCategorias.setPessoa(pessoaFisica);
//        double valorAMais = 10;
//        reservaPessoasCategorias.setValorPrevisto(categoria.getValor() + valorAMais);
//        DAOReservaPessoaCategoria dAOReservaPessoaCategoria = new DAOReservaPessoaCategoria();
//        dAOReservaPessoaCategoria.salvar(reservaPessoasCategorias);
        //System.out.println(Util.gerarNomeCategoria("CP1", 1));
//        String teste = "CP1";
//
//        System.out.println("esse 1"+teste.substring(0, 0));
//        System.out.println("esse 2"+teste.substring(0, 1));
//        System.out.println("esse 3"+teste.substring(0, 2));
//        PessoaJuridica pessoaJuridica = new PessoaJuridica();
//        pessoaJuridica.setCNPJ("123456");
//        pessoaJuridica.setInscriçãoEstadual("00005");
//        pessoaJuridica.setNome("Jonas");
//        pessoaJuridica.setCodigo("001");
//        pessoaJuridica.setLogin("jonas");
//        pessoaJuridica.setSenha(pessoaJuridica.getCNPJ());
//        dao.salvar(pessoaJuridica);
        //new Backup().executeCommand("ls ~");
        addNotify();
        
        fachada = Fachada.getInstance();
        
        ReservaPessoasCategorias reserva = fachada.listarTodosReservaPessoasCategorias().get(0);
        
        
        System.err.println("Data objeto antes: " + reserva.getDataHora());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reserva.getDataHora());
        calendar.add(Calendar.MINUTE, 60);

        System.err.println("Data objeto add + 60 min: " + calendar.getTime());
        System.err.println("Data Atual: " + new Date());

        if (calendar.after(new Date())) {
            System.out.println("Venceu");
        }
        

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addNotify() {
        fachada = Fachada.getInstance();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        
//        try {
//
//            while (true) {
//                ArrayList<ReservaPessoasCategorias> reservas = fachada.listarTodosReservaPessoasCategorias();
//                System.err.println("Entrou");
//                for(ReservaPessoasCategorias reserva: reservas){
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(reserva.getDataHora());
//                    calendar.add(Calendar.MINUTE, 60);
//                    
//                    System.err.println(calendar.getTime());
//                    System.err.println(new Date());
//                    
//                    if(calendar.after(new Date())){
//                        System.out.println("Venceu");
//                    }
//                    
//                    
//                    
//                    
//                }
//                
//                Thread.sleep(1000);
//            }
//
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }

    }

}
