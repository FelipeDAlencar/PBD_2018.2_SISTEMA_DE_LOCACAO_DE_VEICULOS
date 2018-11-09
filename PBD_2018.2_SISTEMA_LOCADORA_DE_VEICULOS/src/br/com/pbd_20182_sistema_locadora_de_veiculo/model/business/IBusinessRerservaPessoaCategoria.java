/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessRerservaPessoaCategoria {
    
    public void salvar(ReservaPessoasCategorias reservaPessoasCategorias);
    public ArrayList<ReservaPessoasCategorias> listarTodos();
    public ReservaPessoasCategorias buscarPorId(int id);
    public void alterar(ReservaPessoasCategorias reservaPessoasCategorias);
    
}
