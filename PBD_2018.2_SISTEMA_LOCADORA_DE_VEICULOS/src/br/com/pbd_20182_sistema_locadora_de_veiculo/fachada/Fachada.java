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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoriaCaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoriaCaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessEndereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessFilial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessFuncionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessGeral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessLog;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessPessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessVeiculo;
import java.util.ArrayList;

public class Fachada implements IFachada{
    private static Fachada fachada;
    private BusinessCategoria businessCategoria;
    private BusinessCategoriaCaminhonetaDeCarga businessCategoriaCaminhonetaDeCarga;
    private BusinessCategoriaCaminhonetaDePassageiros businessCategoriaCaminhonetaDePassageiros;
    private BusinessEndereco businessEndereco;
    private BusinessFilial businessFilial;
    private BusinessFuncionario businessFuncionario;
    private BusinessGeral businessGeral;
    private BusinessLocacao businessLocacao;
    private BusinessPessoaFisica businessPessoaFisica;
    private BusinessPessoaJuridica businessPessoaJuridica;
    private BusinessReservaPessoaCategoria businessReservaPessoaCategoria;
    private BusinessVeiculo businessVeiculo;
    private BusinessLog businessLog;
  
    
    public Fachada() {
        
        businessCategoria =  new BusinessCategoria();
        businessCategoriaCaminhonetaDeCarga = new BusinessCategoriaCaminhonetaDeCarga();
        businessCategoriaCaminhonetaDePassageiros =  new BusinessCategoriaCaminhonetaDePassageiros();
        businessEndereco = new BusinessEndereco();
        businessFilial = new BusinessFilial();
        businessFuncionario = new BusinessFuncionario();
        businessGeral = new BusinessGeral();
        businessLog =  new BusinessLog();
        businessPessoaFisica = new BusinessPessoaFisica();
        businessPessoaJuridica = new BusinessPessoaJuridica();
        businessReservaPessoaCategoria = new BusinessReservaPessoaCategoria();
        businessVeiculo = new BusinessVeiculo();
        
        
    }
    
    public static Fachada getInstance(){
        
        if(fachada != null){
            return fachada;
        }
        
        return fachada = new Fachada();
    }

    @Override
    public void salvarCategoria(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Categoria> listarTodosCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria buscarPorIdCategoria(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CaminhonetaDeCarga> listarTodosCaminhonetaDeCarga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CaminhonetaDeCarga buscarPorIdCaminhonetaDeCarga(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CaminhonetaDePassageiros> listarTodosCaminhonetaDePassageiros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CaminhonetaDePassageiros buscarPorIdCaminhonetaDePassageiros(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarEndereco(Endereco endereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Endereco> listarTodosEndereco() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco buscarPorIdEndereco(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarEndereco(Endereco endereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarFilial(Filial filial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Filial> listarTodosFilial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filial buscarPorIdFilial(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarFilial(Filial filial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> listarTodosFuncionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario buscarPorIdFuncionario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarGeral(Geral geral) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarGeral(Geral geral) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarLocacao(Locacao locacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Locacao> listarTodosLocacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Locacao buscarPorIdLocacao(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarLocacao(Locacao locacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarLog(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Log> listarTodosLog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Log buscarPorIdLog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarLog(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessExpection {
        businessPessoaFisica.salvar(pessoaFisica);
    }

    @Override
    public ArrayList<PessoaFisica> listarTodosPessoaFisica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PessoaFisica buscarPorIdPessoaFisica(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarPessoaFisica(PessoaFisica pessoaFisica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessExpection {
        businessPessoaJuridica.salvar(pessoaJuridica);
    }

    @Override
    public ArrayList<PessoaJuridica> listarTodosPessoaJuridica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PessoaJuridica buscarPorIdPessoaJuridica(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReservaPessoasCategorias> listarTodosReservaPessoasCategorias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservaPessoasCategorias buscarPorIdReservaPessoasCategorias(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarVeiculo(Veiculo veiculo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Veiculo> listarTodosVeiculo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Veiculo buscarPorIdVeiculo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarVeiculo(Veiculo veiculo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
