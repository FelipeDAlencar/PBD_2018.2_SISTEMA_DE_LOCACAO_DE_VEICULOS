/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Felipe
 */
public class minhaData extends Date{

    @Override
    public String toString() {
        return Util.formatarData(this);
    }
    
}
