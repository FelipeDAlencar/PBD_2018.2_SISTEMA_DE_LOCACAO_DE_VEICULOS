/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Backup {

    private static final Logger log = Logger.getLogger(Backup.class.getName());

    public void executeCommand(final String command) throws IOException {

        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);

        BufferedReader br = null;

        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Retorno do comando = [" + line + "]");
            }
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }

}
