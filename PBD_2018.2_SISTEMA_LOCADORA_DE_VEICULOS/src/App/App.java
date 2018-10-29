/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.dao.DAOGenerico;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.io.IOException;
import java.util.Calendar;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Pane root;root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLLogin.fxml"));     
        Pane root;root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVboxTelaPrincipal.fxml"));     
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        DAOGenerico dao = new DAOGenerico();
        
//        PessoaFisica pessoa = new PessoaFisica();
//        pessoa.setCodigo("p2");
//        pessoa.setNome("Alissim");
//        pessoa.setData_nascimento(Calendar.getInstance());
//        pessoa.setData_vencimentoCNH(Calendar.getInstance());
//        pessoa.setIdentificacao("BBBB");
//        pessoa.setNumero_CNH("321");
//        pessoa.setCPF("0000000");
//        
//        
//        dao.salvar(pessoa);
  
        
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
