/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessCategoriaCaminhonetaDeCarga {
    public void salvar(CaminhonetaDeCarga caminhonetaDeCarga);
    public ArrayList<CaminhonetaDeCarga> listarTodos();
    public CaminhonetaDeCarga buscarPorId(int id);
    public void alterar(CaminhonetaDeCarga caminhonetaDeCarga);
}
