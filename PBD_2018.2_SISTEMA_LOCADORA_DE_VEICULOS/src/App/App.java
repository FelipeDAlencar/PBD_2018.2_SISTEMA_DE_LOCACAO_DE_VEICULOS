/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.controller.FXMLLoginController;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOGenerico;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaFisica;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
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

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        //new FXMLLoginController(primaryStage);

        DAOGenerico dao = new DAOGenerico();

//        Date data = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        System.out.println(sdf.format(data));
        //dao.salvar(pessoa);
//        PessoaFisica pessoaFisica = new PessoaFisica();
//        pessoaFisica.setCPF("000");
//        pessoaFisica.setNumero_CNH("111");
//        pessoaFisica.setNome("Felipe");
//        pessoaFisica.setCodigo("00");
//        pessoaFisica.setLogin("Felipe");
//        pessoaFisica.setSenha(pessoaFisica.getCPF());
//
//        dao.salvar(pessoaFisica);
//        Funcionario funcionario = new Funcionario();
//        funcionario.setLogin("Alisson");
//        funcionario.setSenha("123");
//        funcionario.setCodigo("000");
//        funcionario.setNome("Alisson");
//        funcionario.setCargo("Gerente");
//        funcionario.setSuperUsuario(true);
//        funcionario.setMatricula("01254");
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
//
    }

    public static void main(String[] args) {
        launch(args);
    }

}
