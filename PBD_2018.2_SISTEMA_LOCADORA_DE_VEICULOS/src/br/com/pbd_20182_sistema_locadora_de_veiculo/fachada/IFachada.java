/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.fachada;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
public interface IFachada {
   
    public void salvarCategoria(Categoria categoria)throws BusinessExpection,DAOException;
    public ArrayList<Categoria> listarTodosCategoria()throws DAOException;
    public Categoria buscarCategoriaPorId(int id)throws DAOException;
    public String buscarUltimoNomeCategoria()throws DAOException;
    public Categoria buscarCategoriaPorNome(String nome)throws DAOException;
    public ArrayList<Categoria> buscarPorBuscaCategoria(String busca)throws DAOException;
    
    
    public void salvarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga)throws BusinessExpection,DAOException;
    public ArrayList<CaminhonetaDeCarga> listarTodosCaminhonetaDeCarga()throws DAOException;
    public CaminhonetaDeCarga buscarPorIdCaminhonetaDeCarga(int id)throws DAOException;
    public String buscarUltimoNomeCaminhonetaDeCarga()throws DAOException;

    
    public void salvarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros)throws DAOException,DAOException;
    public ArrayList<CaminhonetaDePassageiros> listarTodosCaminhonetaDePassageiros()throws DAOException;
    public CaminhonetaDePassageiros buscarPorIdCaminhonetaDePassageiros(int id)throws DAOException;
    public void alterarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros)throws DAOException;
    public String buscarUltimoNomeCaminhonetaDePassageiros()throws DAOException;
    
    public void salvarEndereco(Endereco endereco)throws DAOException, BusinessExpection;
    public ArrayList<Endereco> listarTodosEndereco()throws DAOException;
    public Endereco buscarPorIdEndereco(int id)throws DAOException;
    
    
    public void salvarFilial(Filial filial)throws DAOException, BusinessExpection;
    public ArrayList<Filial> listarTodosFilial()throws DAOException;
    public Filial buscarPorIdFilial(int id)throws DAOException;
    public ArrayList<Filial> buscarPorBuscaFilial(String busca)throws DAOException;
    
    
    public void salvarFuncionario(Funcionario funcionario)throws DAOException,BusinessExpection;
    public ArrayList<Funcionario> listarTodosFuncionario()throws DAOException;
    public Funcionario buscarPorIdFuncionario(int id)throws DAOException;
    public ArrayList<Funcionario> buscarPorBuscaFuncionario(String busca)throws DAOException;
    
    public void salvarGeral(Geral geral)throws DAOException,BusinessExpection;
    public Geral buscarGeral()throws DAOException;
    
    public void salvarLocacao(Locacao locacao)throws DAOException,BusinessExpection;
    public ArrayList<Locacao> listarTodosLocacao()throws DAOException;
    public Locacao buscarPorIdLocacao(int id)throws DAOException;
    public Time procedureCalcularIntervaloDeAtraso(Calendar dataAtual, Integer id) throws DAOException ;
    
    
    
    public void salvarLog(Log log)throws BusinessExpection,DAOException;
    public ArrayList<Log> listarTodosLog()throws DAOException;
    public Log buscarPorIdLog(int id)throws DAOException;
    
    
    
    public void salvarPessoa(Pessoa pessoa)throws BusinessExpection,DAOException;
    public Pessoa buscarPorIdPessoa(int id)throws DAOException;
   
    public Pessoa buscarLoginPessoa(Pessoa pessoa) throws DAOException;
    public ArrayList<Pessoa> listarTodosPessoa() throws DAOException;
    public String buscarUltimoCodigoPessoa() throws DAOException;
    public ArrayList<Pessoa> buscarPorBuscaPessoa(String texto) throws DAOException;
    public String criptografarSenhaPessoa(String senha) throws DAOException;
    public int buscarUltimoIDPessoa() throws DAOException;
    
    public void salvarPessoaFisica(PessoaFisica pessoaFisica)throws BusinessExpection,DAOException;
    public ArrayList<PessoaFisica> listarTodosPessoaFisica()throws DAOException;
    public PessoaFisica buscarPorIdPessoaFisica(int id)throws DAOException;
    public ArrayList<PessoaFisica> buscarPorNomeLikePessoaFisica(String busca)throws DAOException;
   
    
    
    public void salvarPessoaJuridica(PessoaJuridica pessoaJuridica)throws BusinessExpection,DAOException;
    public ArrayList<PessoaJuridica> listarTodosPessoaJuridica()throws DAOException;
    public PessoaJuridica buscarPorIdPessoaJuridica(int id)throws DAOException;
    
    
    
    public void salvarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias)throws BusinessExpection,DAOException;
    public ArrayList<ReservaPessoasCategorias> listarTodosReservaPessoasCategorias()throws DAOException;
    public ReservaPessoasCategorias buscarPorIdReservaPessoasCategorias(int id)throws DAOException;
    public ArrayList<ReservaPessoasCategorias> buscarPorBuscaReservasPessoaCategoria(String busca) throws DAOException;
    
    public void salvarVeiculo(Veiculo veiculo)throws BusinessExpection,DAOException;
    public ArrayList<Veiculo> listarTodosVeiculo()throws DAOException;
    public Veiculo buscarPorIdVeiculo(int id)throws DAOException;
    
    public ArrayList<Veiculo> buscarVeiculoPorCategoria(Categoria categoria) throws DAOException;
    public ArrayList<Veiculo> buscarPorVeiculosIndisponiveisPorCategoria(Categoria categoria)throws DAOException;
    public ArrayList<Veiculo> buscarPorBuscaVeiculo(String busca)throws DAOException;
    
    
    
}
