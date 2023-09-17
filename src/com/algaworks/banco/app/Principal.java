package com.algaworks.banco.app;

import com.algaworks.banco.modelo.*;
import com.algaworks.banco.modelo.atm.CaixaEletronico;
import com.algaworks.banco.modelo.excecao.SaldoInsuficenteException;
import com.algaworks.banco.modelo.pagamento.Boleto;
import com.algaworks.banco.modelo.pagamento.DocumentoPagavel;
import com.algaworks.banco.modelo.pagamento.Holerite;

import java.math.BigDecimal;

public class Principal {
    public static void main(String[] args) {

        Pessoa titular1 = new Pessoa();
        titular1.setNome("João da Silva");
        titular1.setDocumento("12312312311");
        titular1.setRendimentoAnual(new BigDecimal("15000"));
        titular1.setTipo(TipoPessoa.JURIDICA);

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Maria Abadia");
        titular2.setDocumento("222333444555");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaInvestimento minhaConta = new ContaInvestimento(titular1, 123, 987);
        ContaEspecial suaConta = new ContaEspecial(titular2, 222, 333, new BigDecimal("1000"));

        try {


            minhaConta.depositar(new BigDecimal("100000"));
            minhaConta.sacar(new BigDecimal("1000"));

            suaConta.depositar(new BigDecimal("15000"));
            suaConta.sacar(new BigDecimal("1000"));
            suaConta.debitarTarifaMensal();

            Boleto boletoEscola = new Boleto(titular2, new BigDecimal("35000"));
            Holerite salarioFuncionario = new Holerite(titular2, new BigDecimal("100"), 160);

            caixaEletronico.pagar(boletoEscola, minhaConta);
            caixaEletronico.pagar(salarioFuncionario, minhaConta);

            caixaEletronico.estornarPagamento(boletoEscola, minhaConta);


            boletoEscola.imprimirRecibo();
            System.out.println();
            salarioFuncionario.imprimirRecibo();
            System.out.println();
        }catch (SaldoInsuficenteException e){
            System.out.println("Erro ao executar operação na conta: "+e.getMessage());
        }

        caixaEletronico.imprimirSaldo(suaConta);
        System.out.println();
        caixaEletronico.imprimirSaldo(minhaConta);

    }
}
