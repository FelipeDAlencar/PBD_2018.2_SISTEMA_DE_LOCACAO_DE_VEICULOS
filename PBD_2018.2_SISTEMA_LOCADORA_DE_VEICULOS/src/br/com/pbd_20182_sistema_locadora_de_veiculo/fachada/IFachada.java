/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.fachada;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Log;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
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
    
    public void salvarCategoria(Categoria categoria)throws BusinessExpection;
    public ArrayList<Categoria> listarTodosCategoria()throws BusinessExpection;
    public Categoria buscarPorIdCategoria(int id)throws BusinessExpection;
    public void alterarCategoria(Categoria categoria)throws BusinessExpection;
    public String buscarUltimoNomeCategoria();
    
    
    public void salvarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga)throws BusinessExpection;
    public ArrayList<CaminhonetaDeCarga> listarTodosCaminhonetaDeCarga()throws BusinessExpection;
    public CaminhonetaDeCarga buscarPorIdCaminhonetaDeCarga(int id)throws BusinessExpection;
    public void alterarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga)throws BusinessExpection;
    public String buscarUltimoNomeCaminhonetaDeCarga();

    
    public void salvarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros)throws BusinessExpection;
    public ArrayList<CaminhonetaDePassageiros> listarTodosCaminhonetaDePassageiros()throws BusinessExpection;
    public CaminhonetaDePassageiros buscarPorIdCaminhonetaDePassageiros(int id)throws BusinessExpection;
    public void alterarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros)throws BusinessExpection;
    public String buscarUltimoNomeCaminhonetaDePassageiros();
    
    public void salvarEndereco(Endereco endereco)throws BusinessExpection;
    public ArrayList<Endereco> listarTodosEndereco()throws BusinessExpection;
    public Endereco buscarPorIdEndereco(int id)throws BusinessExpection;
    public void alterarEndereco(Endereco endereco)throws BusinessExpection;
    
    public void salvarFilial(Filial filial)throws BusinessExpection;
    public ArrayList<Filial> listarTodosFilial()throws BusinessExpection;
    public Filial buscarPorIdFilial(int id)throws BusinessExpection;
    public void alterarFilial(Filial filial)throws BusinessExpection;
    
    public void salvarFuncionario(Funcionario funcionario)throws BusinessExpection;
    public ArrayList<Funcionario> listarTodosFuncionario()throws BusinessExpection;
    public Funcionario buscarPorIdFuncionario(int id)throws BusinessExpection;
    public void alterarFuncionario(Funcionario funcionario)throws BusinessExpection;
    
    public void salvarGeral(Geral geral)throws BusinessExpection;
    public void alterarGeral(Geral geral)throws BusinessExpection;
    
    public void salvarLocacao(Locacao locacao)throws BusinessExpection;
    public ArrayList<Locacao> listarTodosLocacao()throws BusinessExpection;
    public Locacao buscarPorIdLocacao(int id)throws BusinessExpection;
    public void alterarLocacao(Locacao locacao)throws BusinessExpection;
    
    
    public void salvarLog(Log log)throws BusinessExpection;
    public ArrayList<Log> listarTodosLog()throws BusinessExpection;
    public Log buscarPorIdLog(int id)throws BusinessExpection;
    public void alterarLog(Log log)throws BusinessExpection;
    
    
    public void salvarPessoa(Pessoa pessoa)throws BusinessExpection;
    public ArrayList<Pessoa> listarTodosPessoa()throws BusinessExpection;
    public Pessoa buscarPorIdPessoa(int id)throws BusinessExpection;
    public void alterarPessoa(PessoaFisica pessoaFisica)throws BusinessExpection;
    
    public void salvarPessoaFisica(PessoaFisica pessoaFisica)throws BusinessExpection;
    public ArrayList<PessoaFisica> listarTodosPessoaFisica()throws BusinessExpection;
    public PessoaFisica buscarPorIdPessoaFisica(int id)throws BusinessExpection;
    public void alterarPessoaFisica(PessoaFisica pessoaFisica)throws BusinessExpection;
    
    
    public void salvarPessoaJuridica(PessoaJuridica pessoaJuridica)throws BusinessExpection;
    public ArrayList<PessoaJuridica> listarTodosPessoaJuridica()throws BusinessExpection;
    public PessoaJuridica buscarPorIdPessoaJuridica(int id)throws BusinessExpection;
    public void alterarPessoaJuridica(PessoaJuridica pessoaJuridica)throws BusinessExpection;
    
    
    public void salvarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias)throws BusinessExpection;
    public ArrayList<ReservaPessoasCategorias> listarTodosReservaPessoasCategorias()throws BusinessExpection;
    public ReservaPessoasCategorias buscarPorIdReservaPessoasCategorias(int id)throws BusinessExpection;
    public void alterarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias)throws BusinessExpection;
    
    public void salvarVeiculo(Veiculo veiculo)throws BusinessExpection;
    public ArrayList<Veiculo> listarTodosVeiculo()throws BusinessExpection;
    public Veiculo buscarPorIdVeiculo(int id)throws BusinessExpection;
    public void alterarVeiculo(Veiculo veiculo)throws BusinessExpection;
}
