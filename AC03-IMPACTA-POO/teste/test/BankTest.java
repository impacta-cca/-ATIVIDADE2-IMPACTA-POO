/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import AC03.Bank;
import AC03.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    @Test
    public void removeAccountNumberTest() {
        Bank banco = new Bank();
        banco.addAccount(new BankAccount(123,"567","Marcos Roberto",500.0));
        banco.addAccount(new BankAccount(145,"999","Maria Clara",480.0));
        banco.addAccount(new BankAccount(179,"555","Lucas Lima",120.0));
        banco.removeAccount(145);
       assertEquals(123,banco.getAccounts().get(0).getAccountNumber());
    }

    @Test
    public void removeAccountTest() {
        Bank banco = new Bank();
        banco.addAccount(new BankAccount(123,"567","Marcos Roberto",500.0));
        banco.addAccount(new BankAccount(145,"999","Maria Clara",480.0));
        banco.addAccount(new BankAccount(179,"555","Lucas Lima",120.0));
        BankAccount conta  = new BankAccount(145,"999","Maria Clara",480.0);
        banco.removeAccount(conta);
        assertEquals(123,banco.getAccounts().get(0).getAccountNumber());
    }

    @Test
    public void criarBancocomArquivoTest() {
        Bank banco = new Bank("entrada.txt");
        assertEquals(1011,banco.getAccounts().get(0).getAccountNumber());
    }

    @Test
    public void BancoOrdenaTest() {
        Bank banco = new Bank();
        banco.addAccount(new BankAccount(179,"555","Lucas Lima",120.0));
        banco.addAccount(new BankAccount(123,"567","Marcos Roberto",500.0));
        banco.addAccount(new BankAccount(145,"999","Maria Clara",480.0));
        banco.sort();
        assertEquals(123,banco.getAccounts().get(0).getAccountNumber());
    }

    @Test
    public void EscreveArquivoTest() {
        Bank banco = new Bank();
        banco.addAccount(new BankAccount(123,"567","Marcos Roberto",500.0));
        banco.addAccount(new BankAccount(145,"999","Maria Clara",480.0));
        banco.addAccount(new BankAccount(179,"555","Lucas Lima",120.0));
        banco.dump("saidaBank.txt");
    }

    @Test
    public void ChangeAccountTest() {
        Bank banco = new Bank();
        banco.addAccount(new BankAccount(123,"567","Marcos Roberto",500.0));
        banco.addAccount(new BankAccount(145,"999","Maria Clara",480.0));
        banco.addAccount(new BankAccount(179,"555","Lucas Lima",120.0));
        BankAccount novo = new BankAccount(179,"146","Lucas Santos",1500.0);
        banco.changeAccount(novo);
    }
}
