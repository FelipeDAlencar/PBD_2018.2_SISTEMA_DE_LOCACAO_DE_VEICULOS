/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.JPA;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class SQLUtil {

    public static String USUARIO_POSTGRES = "postgres";
    public static String SENHA_POSTGRES = "81540106";
    public static String URL_POSTGRES = "jdbc:postgresql://localhost:5432/PBD_2018.2_SISTEMA_LOCADORA_DE_VEICULOS";

    public static class Pessoa {

        public static final String SQL_BUSCA_LOGIN = "select p from Pessoa p where p.login = :login and p.senha = :senha";
        public static final String SQL_BUSCA_ULTIMO_CODIGO_PESSOA = "select MAX(codigo) from Pessoa";
        public static final String SQL_BUSCA_ULTIMO_ID_PESSOA = "select MAX(id) from Pessoa";

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT p FROM Pessoa p WHERE LOWER(p.nome) LIKE :nome or "
                + "                    LOWER(p.login) LIKE :login or LOWER(p.codigo) LIKE :codigo ";

        public static final String SQL_PROCEDURE_CRIPTOGRAFAR_SENHA = "criptografar_senha";

    }

    public static class PessoaFisica {

        public static final String SQL_BUSCAR_NOME_LIKE = ""
                + "select p from PessoaFisica p where lower(p.nome) like :busca";

        public static final String SQL_BUSCAR_POR_CPF = ""
                + "select p from PessoaFisica p where lower(p.CPF) = :cpf";

    }

    public static class PessoaJuridica {

        public static final String SQL_BUSCAR_POR_CNPJ = ""
                + "select p from PessoaJuridica p where lower(p.CNPJ) = :cnpj";

    }

    public static class Categoria {

        public static final String SQL_BUSCAR_CATEGORIA_POR_ULTIMO_NOME = "select max(nome) from Categoria where  nome like 'CN%'";
        public static final String SQL_BUSCAR_CATEGORIA_POR_NOME = "select c from Categoria c where c.nome = :nome and ativo = true";
        public static final String BUSCAR_POR_BUSCA = "select c from Categoria c where LOWER(c.nome) like :nome "
                + "or LOWER(c.descricacao) like :descricao or to_char(c.valor, '999D9') like :valor  ";
    }

    public static class CaminhonetaDeCarga {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_CARGA_ULTIMONOME = "select MAX(nome) from CaminhonetaDeCarga";

    }

    public static class CaminhonetaDePassageiros {

        public static final String SQL_BUSCAR_CAMINHONETA_DE_PASSAGEIROS_ULTIMONOME = "select MAX(nome) from CaminhonetaDePassageiros";

    }

    public static class Veiculo {

        public static final String SQL_BUSCAR_VEICULO_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = true";
        public static final String SQL_BUSCAR_VEICULO_NAO_DISPONIVEIS_POR_CATEGORIA = "select v from Veiculo v where v.categoria = :categoria and v.disponivel = false";
        public static final String SQL_BUSCA_POR_BUSCA = "SELECT v FROM Veiculo v WHERE LOWER(v.modelo) LIKE :modelo or "
                + " LOWER(v.cor) LIKE :cor or LOWER(to_char(v.anoDoModelo, '999')) LIKE :anoDoModelo";

    }

    public static class Geral {

        public static final String SQL_BUSCAR_GERAL = "select p from Geral p where p.id = 1";
    }

    public static class Funcionario {

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT p FROM Funcionario p WHERE LOWER(p.nome) LIKE :nome or "
                + "                    LOWER(p.login) LIKE :login or LOWER(p.matricula) LIKE :matricula and ativo = true";

    }

    public static class Filial {

        public static final String SQL_BUSCA_POR_BUSCA = "SELECT F\n"
                + "FROM Filial as F\n"
                + "INNER JOIN Endereco as E\n"
                + "                on F.endereco = E.id  where LOWER(F.nome) like :nome or LOWER(E.rua) like :rua and F.ativo = true ";

    }

    public static class Reservas {

        public static String SQL_BUSCAR_POR_BUSCA = "select r from ReservaPessoasCategorias r INNER JOIN Pessoa p ON r.pessoa = p.id\n"
                + "INNER JOIN Categoria c ON r.categoria = c.id WHERE r.ativo = true and LOWER(p.nome) like :NomeCliente or LOWER(c.nome) like :nomeCategoria  or to_char(r.dataHora, 'dd/MM/yyyy') like :dataHora ";
   
        public static String SQL_BUSCAR_RESERVA_POR_PERIODO = "select v  from ViewReservasPorPeriodo v  where dataReserva between :dataInicial and :dataFinal";
    }
    
    public static class Revisao{
        public static String SQL_BUSCAR_REVISOES_POR_VEICULO = "select v from ViewHistoricoPorVeiculo v where veiculoId = :veiculo_id";
                public static String SQL_BUSCAR_REVISOES = "select v from ViewHistoricoPorVeiculo v ";

    }
    public static class Locacao {

        public static final String SQL_PROCEDURE_CALCULAR_INTERVALO_DE_ATRASO = "calculardiferencadedatalocacao";
        public static final String SQL_PROCEDURE_Verificar_Vencimento_CNH = "verificarvencimentocnh";
        public static final String SQL_PROCEDURE_CALCULAR_IDADE = "calcularidade";
        public static final String SQL_BUSCAR_LOCACAO_POR_CLIENTE = "select l from Locacao l where l.cliente = :idcliente ";
        public static final String SQL_BUSCAR_LOCACAO_POR_MOTORISTA = "select v  from ViewMotoristaPorLocacao v  where motoId = :id";
        public static final String SQL_BUSCAR_LOCACAO_POR_PERIODO = "select v  from ViewLocacaoPorPeriodo v  where dataIda between :dataInicial and :dataFinal";
    }
    
    public static class ContaAPagar{
        public static  String  SQL_BUSCAR_CONTA_A_PAGAR_POR_PERIODO = "select v  from ContaAPagar v  where v.dataVencimento between :dataInicial and :dataFinal";
        
    }
    
    public static class ContaAReceber{
        public static  String  SQL_BUSCAR_CONTA_A_RECEBER_POR_PERIODO = "select v  from ContaAReceber v  where v.dataRecebimento between :dataInicial and :dataFinal";
    }

    public static final String NOME_BD_CONEXAO_POSTGRES = "POSTGRES";

    private static Connection conexao = null;

    public static synchronized Connection getConnectionInstance(String bd) {

        try {
            if (conexao == null || conexao.isClosed()) {
                switch (bd) {
                    case NOME_BD_CONEXAO_POSTGRES:
                        conexao = (Connection) DriverManager.getConnection(SQLUtil.URL_POSTGRES, SQLUtil.USUARIO_POSTGRES,
                                SQLUtil.SENHA_POSTGRES);
                        break;

                    default:
                        break;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conexao;

    }
}
