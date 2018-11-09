/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.fachada;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Log;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IFachada {
    
    public void salvarCategoria(Categoria categoria);
    public ArrayList<Categoria> listarTodosCategoria();
    public Categoria buscarPorIdCategoria(int id);
    public void alterarCategoria(Categoria categoria);
    
    
    public void salvarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga);
    public ArrayList<CaminhonetaDeCarga> listarTodosCaminhonetaDeCarga();
    public CaminhonetaDeCarga buscarPorIdCaminhonetaDeCarga(int id);
    public void alterarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga);
    
    
    public void salvarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros);
    public ArrayList<CaminhonetaDePassageiros> listarTodosCaminhonetaDePassageiros();
    public CaminhonetaDePassageiros buscarPorIdCaminhonetaDePassageiros(int id);
    public void alterarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros);
    
    
    public void salvarEndereco(Endereco endereco);
    public ArrayList<Endereco> listarTodosEndereco();
    public Endereco buscarPorIdEndereco(int id);
    public void alterarEndereco(Endereco endereco);
    
    public void salvarFilial(Filial filial);
    public ArrayList<Filial> listarTodosFilial();
    public Filial buscarPorIdFilial(int id);
    public void alterarFilial(Filial filial);
    
    public void salvarFuncionario(Funcionario funcionario);
    public ArrayList<Funcionario> listarTodosFuncionario();
    public Funcionario buscarPorIdFuncionario(int id);
    public void alterarFuncionario(Funcionario funcionario);
    
    public void salvarGeral(Geral geral);
    public void alterarGeral(Geral geral);
    
    public void salvarLocacao(Locacao locacao);
    public ArrayList<Locacao> listarTodosLocacao();
    public Locacao buscarPorIdLocacao(int id);
    public void alterarLocacao(Locacao locacao);
    
    
    public void salvarLog(Log log);
    public ArrayList<Log> listarTodosLog();
    public Log buscarPorIdLog(int id);
    public void alterarLog(Log log);
    
    
    public void salvarPessoaFisica(PessoaFisica pessoaFisica);
    public ArrayList<PessoaFisica> listarTodosPessoaFisica();
    public PessoaFisica buscarPorIdPessoaFisica(int id);
    public void alterarPessoaFisica(PessoaFisica pessoaFisica);
    
    
    public void salvarPessoaJuridica(PessoaJuridica pessoaJuridica);
    public ArrayList<PessoaJuridica> listarTodosPessoaJuridica();
    public PessoaJuridica buscarPorIdPessoaJuridica(int id);
    public void alterarPessoaJuridica(PessoaJuridica pessoaJuridica);
    
    
    public void salvarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias);
    public ArrayList<ReservaPessoasCategorias> listarTodosReservaPessoasCategorias();
    public ReservaPessoasCategorias buscarPorIdReservaPessoasCategorias(int id);
    public void alterarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias);
    
    public void salvarVeiculo(Veiculo veiculo);
    public ArrayList<Veiculo> listarTodosVeiculo();
    public Veiculo buscarPorIdVeiculo(int id);
    public void alterarVeiculo(Veiculo veiculo);
}
