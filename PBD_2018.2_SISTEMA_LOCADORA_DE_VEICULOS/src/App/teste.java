/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

/**
 *
 * @author Felipe
 */
public class teste {

    public static void main(String[] args) throws DAOException {

        Fachada fachada = Fachada.getInstance();
        
        
        Categoria categoria = new Categoria();
        categoria.setId(1);
        
        //Veiculo v = fachada.buscarVeiculoPorCategoria(categoria);
        
        
        

    }

}
