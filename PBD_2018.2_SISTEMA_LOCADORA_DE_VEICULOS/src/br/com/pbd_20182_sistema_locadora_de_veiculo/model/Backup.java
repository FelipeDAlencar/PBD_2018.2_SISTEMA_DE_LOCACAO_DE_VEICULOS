package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class Backup implements Initializable {

    private static final String caminhoBackup = "C:/BKP";
    private static final String senha = "81540106";
    private static final String nomeBanco = "PBD_2018.2_SISTEMA_LOCADORA_DE_VEICULOS";
    private static final String caminhoDump = "C:\\Program Files\\PostgreSQL\\10\\bin\\pg_dump.exe";

    public Backup() {

    }

    public static void realizaBackup() throws IOException, InterruptedException {
        final List<String> comandos = new ArrayList<>();

        List<String> lista = new ArrayList<>();
        File diretorio = new File(caminhoBackup);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        File fList[] = diretorio.listFiles();

        if (fList.length == 0) {
            //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\9.4\\bin\\pg_dump.exe"); //cecom
            //comandos.add("C:\\Arquivos de programas\\PostgreSQL\\9.2\\bin\\pg_dump.exe"); 
            comandos.add(caminhoDump);

            //comandos.add("-i");      
            comandos.add("-h");
            comandos.add("localhost");
            //comandos.add("192.168.0.25");
            comandos.add("-p");
            comandos.add("5432");
            comandos.add("-U");
            comandos.add("postgres");
            comandos.add("-F");
            comandos.add("c");
            comandos.add("-b");
            comandos.add("-v");
            comandos.add("-f");

            //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+(Character.getNumericValue(recebe)+1)+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add(caminhoBackup + "\\" + 1 + " " + getDateTime(new Date()) + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add(nomeBanco);
            ProcessBuilder pb = new ProcessBuilder(comandos);

            pb.environment().put("PGPASSWORD", senha);

            try {
                final Process process = pb.start();

                final BufferedReader r = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    line = r.readLine();
                }
                r.close();

                process.waitFor();
                process.destroy();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Backup Realizado com sucesso.");
                alert.setTitle("Backup");
                alert.setHeaderText("Backup");
                alert.show();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        } else {

            for (int i = 0; i < fList.length; i++) {
                //JOptionPane.showMessageDialog(null,fList[i].getName());
                lista.add(fList[i].getName());
            }

            char ultimoBack = Collections.max(lista).charAt(0);
            //JOptionPane.showMessageDialog(null,Character.getNumericValue(recebe)); 

            comandos.add(caminhoDump);

            //comandos.add("-i");      
            comandos.add("-h");
            comandos.add("localhost");
            comandos.add("-p");
            comandos.add("5432");
            comandos.add("-U");
            comandos.add("postgres");
            comandos.add("-F");
            comandos.add("c");
            comandos.add("-b");
            comandos.add("-v");
            comandos.add("-f");

            //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+(Character.getNumericValue(recebe)+1)+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add(caminhoBackup + "\\" + (Character.getNumericValue(ultimoBack) + 1) + " " + getDateTime(new Date()) + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add(nomeBanco);
            ProcessBuilder pb = new ProcessBuilder(comandos);

            pb.environment().put("PGPASSWORD", senha);

            try {
                final Process process = pb.start();

                final BufferedReader r = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    line = r.readLine();
                }
                r.close();

                process.waitFor();
                process.destroy();
                removerBackup();
                System.err.println("Fez Back");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void removerBackup() {
        List<String> lista = new ArrayList<String>(9);

        File diretorio = new File(caminhoBackup);
        File fList[] = diretorio.listFiles();

        if (fList.length != 0 && fList.length > 8) {
            //JOptionPane.showMessageDialog(null,"Numero de arquivos no diretorio : " + fList.length );
            for (File fList1 : fList) {
                //JOptionPane.showMessageDialog(null,fList[i].getName());
                lista.add(fList1.getName());
            }

            //lista.remove(Collections.min(lista)); //remove o menor da array 
            //lista.remove(Collections.max(lista)); //remove o maior da array 
            String x = Collections.min(lista);
            lista.remove(Collections.min(lista)); //remove o menor da array 

            String nome = caminhoBackup + "/" + x;
            File f = new File(nome);
            f.delete();
            //JOptionPane.showMessageDialog(null,"removida "+lista);
            //char inicial = 0;
            //for ( int i = 0; i < lista.size(); i++ ){
            //inicial = lista.get(i).charAt(0); //pega o primeiro número da array
            //lista.get(i)
            //JOptionPane.showMessageDialog(null,inicial);
            //}
            int m = 0;
            String recebe;
            String result;
            while (m < lista.size()) {
                //recebe=JOptionPane.showInputDialog("Digite uma frase: ");    
                recebe = lista.get(m);

                result = recebe.replace(" ", " ").substring(1); //remove o primeiro número da string recebe
                //String result=recebe.replace(" ", "-").substring(1, recebe.length()-1);

                m++;
                result = m + result;
                // Arquivo ou diretório com nome antigo
                File file = new File(caminhoBackup + "/" + recebe);
                //// Arquivo ou diretório com novo nome
                File file2 = new File(caminhoBackup + "/" + result);
                // Renomeando arquivo ou diretório
                boolean success = file.renameTo(file2);
                if (!success) {
                    JOptionPane.showMessageDialog(null, "Falha no momento de renomear");
                }

            }

        }

    }

    private static String getDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");

        return dateFormat.format(date);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
