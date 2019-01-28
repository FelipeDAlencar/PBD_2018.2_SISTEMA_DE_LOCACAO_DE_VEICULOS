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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Revisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewHistoricoPorVeiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewLocacaoPorPeriodo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewMotoristaPorLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewReservasPorPeriodo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoriaCaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessCategoriaCaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessEndereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessFilial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessFuncionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessGeral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessLog;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessPessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinessVeiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinnessContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinnessContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.BusinnessRevisao;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fachada implements IFachada {

    private static Fachada fachada;
    private BusinessCategoria businessCategoria;
    private BusinessCategoriaCaminhonetaDeCarga businessCategoriaCaminhonetaDeCarga;
    private BusinessCategoriaCaminhonetaDePassageiros businessCategoriaCaminhonetaDePassageiros;
    private BusinessEndereco businessEndereco;
    private BusinessFilial businessFilial;
    private BusinessFuncionario businessFuncionario;
    private BusinessGeral businessGeral;
    private BusinessLocacao businessLocacao;
    private BusinessPessoa businessPessoa;
    private BusinessPessoaFisica businessPessoaFisica;
    private BusinessPessoaJuridica businessPessoaJuridica;
    private BusinessReservaPessoaCategoria businessReservaPessoaCategoria;
    private BusinessVeiculo businessVeiculo;
    private BusinessLog businessLog;
    private BusinnessContaAPagar businnessContaAPagar;
    private BusinnessContaAReceber businnessContaAReceber;
    private BusinnessRevisao businnessRevisao;

    public Fachada() {

        businessCategoria = new BusinessCategoria();
        businessCategoriaCaminhonetaDeCarga = new BusinessCategoriaCaminhonetaDeCarga();
        businessCategoriaCaminhonetaDePassageiros = new BusinessCategoriaCaminhonetaDePassageiros();
        businessEndereco = new BusinessEndereco();
        businessFilial = new BusinessFilial();
        businessFuncionario = new BusinessFuncionario();
        businessGeral = new BusinessGeral();
        businessLog = new BusinessLog();
        businessLocacao = new BusinessLocacao();
        businessPessoa = new BusinessPessoa();
        businessPessoaFisica = new BusinessPessoaFisica();
        businessPessoaJuridica = new BusinessPessoaJuridica();
        businessReservaPessoaCategoria = new BusinessReservaPessoaCategoria();
        businessVeiculo = new BusinessVeiculo();
        businnessContaAPagar = new BusinnessContaAPagar();
        businnessContaAReceber = new BusinnessContaAReceber();
        businnessRevisao = new BusinnessRevisao();

    }

    public static Fachada getInstance() {

        if (fachada != null) {
            return fachada;
        }

        return fachada = new Fachada();
    }

    @Override
    public void salvarCategoria(Categoria categoria) throws BusinessExpection, DAOException {
        businessCategoria.salvar(categoria);
    }

    @Override
    public String buscarUltimoNomeCategoria() throws DAOException {
        return businessCategoria.buscarUltimoNomeCategoria();
    }

    @Override
    public ArrayList<Categoria> listarTodosCategoria() throws DAOException {
        return businessCategoria.listarTodos();
    }

    @Override
    public Categoria buscarCategoriaPorId(int id) throws DAOException {
        return businessCategoria.buscarPorId(id);
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nome) throws DAOException {
        return businessCategoria.buscarPorNome(nome);

    }

    @Override
    public ArrayList<Categoria> buscarPorBuscaCategoria(String busca) throws DAOException {
        return businessCategoria.buscarPorBusca(busca);
    }

    @Override
    public void salvarCaminhonetaDeCarga(CaminhonetaDeCarga caminhonetaDeCarga) throws DAOException, BusinessExpection {
        businessCategoriaCaminhonetaDeCarga.salvar(caminhonetaDeCarga);
    }

    @Override
    public String buscarUltimoNomeCaminhonetaDeCarga() throws DAOException {
        return businessCategoriaCaminhonetaDeCarga.buscarUltimoNomeCaminhonetaDeCarga();
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
    public void salvarCaminhonetaDePassageiros(CaminhonetaDePassageiros caminhonetaDePassageiros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscarUltimoNomeCaminhonetaDePassageiros() throws DAOException {
        return businessCategoriaCaminhonetaDePassageiros.buscarUltimoNomeCaminhonetaDePassageiros();
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
    public void salvarEndereco(Endereco endereco) throws DAOException, BusinessExpection {
        businessEndereco.salvar(endereco);
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
    public void salvarFilial(Filial filial) throws DAOException, BusinessExpection {
        businessFilial.salvar(filial);
    }

    @Override
    public ArrayList<Filial> listarTodosFilial() throws DAOException {
        return businessFilial.listarTodos();
    }

    @Override
    public Filial buscarPorIdFilial(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Filial> buscarPorBuscaFilial(String busca) throws DAOException {
        return businessFilial.buscarPorBusca(busca);
    }

    @Override
    public void salvarFuncionario(Funcionario funcionario) throws BusinessExpection, DAOException {
        businessFuncionario.salvar(funcionario);
    }

    @Override
    public ArrayList<Funcionario> listarTodosFuncionario() throws DAOException {
        return businessFuncionario.listarTodos();
    }

    @Override
    public Funcionario buscarPorIdFuncionario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> buscarPorBuscaFuncionario(String busca) throws DAOException {
        return businessFuncionario.buscarPorBusca(busca);
    }

    @Override
    public void salvarGeral(Geral geral) throws DAOException {
        businessGeral.salvar(geral);
    }

    @Override
    public Geral buscarGeral() throws DAOException {

        return businessGeral.buscarGeral();
    }

    @Override
    public void salvarLocacao(Locacao locacao) throws DAOException, BusinessExpection {
        businessLocacao.salvar(locacao);
    }

    @Override
    public ArrayList<Locacao> listarTodosLocacao() throws DAOException {
        return businessLocacao.listarTodos();
    }

    @Override
    public Locacao buscarPorIdLocacao(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Time procedureCalcularIntervaloDeAtraso(Calendar dataAtual, Integer id) throws DAOException {
        return businessLocacao.procedureCalcularIntervaloDeAtraso(dataAtual, id);
    }

    @Override
    public int calcularIdade(Integer id) throws DAOException {
        return businessLocacao.calcularIdade(id);
    }

    @Override
    public boolean verificarVencimentoCNH(Calendar dataIda, Calendar dataVolta, Integer id) throws DAOException {
        return businessLocacao.verificarVencimentoCNH(dataIda, dataVolta, id);
    }

    @Override
    public ArrayList<Locacao> buscarLocacaoPorCliente(Pessoa pessoa) throws DAOException {
        return businessLocacao.buscarLocacaoPorCliente(pessoa);
    }

    @Override
    public ArrayList<ViewMotoristaPorLocacao> buscarMotoristaPorLocacao(PessoaFisica pessoaFisica) throws DAOException {
        return businessLocacao.bsucarMotoristaPorLocacao(pessoaFisica);
    }

    @Override
    public ArrayList<ViewLocacaoPorPeriodo> buscarLocacaoPorPeriodo(Date dataIncial, Date dataFinal)
            throws DAOException {
        return businessLocacao.buscarLocacaoPorPeriodo(dataIncial, dataFinal);
    }

    @Override
    public void salvarLog(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Log> listarTodosLog() {
        return null;
    }

    @Override
    public Log buscarPorIdLog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarPessoa(Pessoa pessoa) throws DAOException, BusinessExpection {
        businessPessoa.salvar(pessoa);
    }

    @Override
    public ArrayList<Pessoa> listarTodosPessoa() throws DAOException {
        return businessPessoa.listarTodos();
    }

    @Override
    public Pessoa buscarLoginPessoa(Pessoa pessoa) throws DAOException {
        return businessPessoa.buscarLogin(pessoa);
    }

    @Override
    public String buscarUltimoCodigoPessoa() throws DAOException {
        return businessPessoa.buscarUltimoCodigo();
    }

    @Override
    public ArrayList<Pessoa> buscarPorBuscaPessoa(String texto) throws DAOException {
        return businessPessoa.buscarPorBusca(texto);
    }

    @Override
    public String criptografarSenhaPessoa(String senha) throws DAOException {
        return businessPessoa.criptografarSenha(senha);
    }

    @Override
    public int buscarUltimoIDPessoa() throws DAOException {
        return businessPessoa.buscarUltimoID();
    }

    @Override
    public Pessoa buscarPorIdPessoa(int id) throws DAOException {
        return businessPessoa.buscarPorId(id);
    }

    @Override
    public void salvarPessoaFisica(PessoaFisica pessoaFisica) throws BusinessExpection, DAOException {
        businessPessoaFisica.salvar(pessoaFisica);
    }

    @Override
    public ArrayList<PessoaFisica> listarTodosPessoaFisica() throws DAOException {
        return businessPessoaFisica.listarTodos();
    }

    @Override
    public PessoaFisica buscarPorIdPessoaFisica(int id) throws DAOException {
        return businessPessoaFisica.buscarPorId(id);
    }

    @Override
    public ArrayList<PessoaFisica> buscarPorNomeLikePessoaFisica(String busca) throws DAOException {
        return businessPessoaFisica.buscarPorNomeLike(busca);
    }

    @Override
    public void salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws BusinessExpection, DAOException {
        businessPessoaJuridica.salvar(pessoaJuridica);
    }

    @Override
    public ArrayList<PessoaJuridica> listarTodosPessoaJuridica() throws DAOException {
        return businessPessoaJuridica.listarTodos();
    }

    @Override
    public PessoaJuridica buscarPorIdPessoaJuridica(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias) throws BusinessExpection, DAOException {
        businessReservaPessoaCategoria.salvar(reservaPessoasCategorias);

    }

    @Override
    public ArrayList<ReservaPessoasCategorias> listarTodosReservaPessoasCategorias() throws DAOException {
        return businessReservaPessoaCategoria.listarTodos();
    }

    public ArrayList<ViewReservasPorPeriodo> buscarReservaPorPeriodo(Date dataIncial, Date dataFinal)
            throws DAOException {
        return businessReservaPessoaCategoria.buscarReservaPorPeriodo(dataIncial, dataFinal);
    }

    @Override
    public ReservaPessoasCategorias buscarPorIdReservaPessoasCategorias(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReservaPessoasCategorias> buscarPorBuscaReservasPessoaCategoria(String busca) throws DAOException {
        return businessReservaPessoaCategoria.buscarPorBusca(busca);
    }

    @Override
    public void salvarVeiculo(Veiculo veiculo) throws DAOException, BusinessExpection {
        businessVeiculo.salvar(veiculo);
    }

    @Override
    public ArrayList<Veiculo> listarTodosVeiculo() throws DAOException {

        return businessVeiculo.listarTodos();

    }

    @Override
    public Veiculo buscarPorIdVeiculo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Veiculo> buscarVeiculoPorCategoria(Categoria categoria) throws DAOException {
        return businessVeiculo.buscarPorCategoria(categoria);
    }

    @Override
    public ArrayList<Veiculo> buscarPorVeiculosIndisponiveisPorCategoria(Categoria categoria) throws DAOException {
        return businessVeiculo.buscarPorCategoriaVeiculosIndisponiveis(categoria);
    }

    @Override
    public ArrayList<Veiculo> buscarPorBuscaVeiculo(String busca) throws DAOException {
        return businessVeiculo.buscarPorBusca(busca);
    }

    @Override
    public ArrayList<ContaAPagar> listarTodasContasAPagar() throws DAOException {
        return businnessContaAPagar.listarTodasContasAPagar();
    }

    @Override
    public ArrayList<ContaAPagar> buscarContaAPagarPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {
        return businnessContaAPagar.buscarContaAPagarPorPeriodo(dataInicial, dataFinal);
    }

    @Override
    public void salvarContaAPagar(ContaAPagar contaAPagar) throws DAOException, BusinessExpection {
        businnessContaAPagar.salvarContaAPagar(contaAPagar);
    }

    @Override
    public void excluirContaAPagar(ContaAPagar contaAPagar) throws DAOException {
        businnessContaAPagar.excluirContaAPagar(contaAPagar);
    }

    @Override
    public ArrayList<ContaAPagar> buscarPorBuscaContaAPagar(String busca) throws DAOException {
        return businnessContaAPagar.buscarPorBuscaContaAPagar(busca);
    }

    @Override
    public void salvarContaAReceber(ContaAReceber contaAReceber) throws DAOException {
        businnessContaAReceber.salvarContaAReceber(contaAReceber);
    }

    @Override
    public void excluirContaAReceber(ContaAReceber contaAPagar) throws DAOException {
        businnessContaAReceber.excluirContaAReceber(contaAPagar);
    }

    @Override
    public ArrayList<ContaAReceber> listarTodasContasAReceber() throws DAOException {

        return businnessContaAReceber.listarTodasContasAReceber();
    }

    @Override
    public ArrayList<ContaAReceber> buscarContaAReceberPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {
        return businnessContaAReceber.buscarContaAReceberPorPeriodo(dataInicial, dataFinal);
    }

    @Override
    public ArrayList<ContaAReceber> buscarPorBuscaContaAReceber(String busca) throws DAOException {
        return businnessContaAReceber.buscarPorBuscaContaAReceber(busca);
    }

    @Override
    public ArrayList<ViewHistoricoPorVeiculo> revisoesPorVeiculo(Veiculo veiculo) throws DAOException {
        return businnessRevisao.revisoesPorVeiculo(veiculo);
    }

    @Override
    public void salvarRevisao(Revisao revisao) throws DAOException {
        businnessRevisao.salvar(revisao);
    }

    @Override
    public ArrayList<ViewHistoricoPorVeiculo> findAll() throws DAOException {
        return businnessRevisao.findAllRevisao();
    }

}
