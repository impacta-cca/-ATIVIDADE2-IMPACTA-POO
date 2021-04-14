/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AC03;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author anacris
 */
public class Bank {
    private ArrayList<BankAccount2> accounts; //vetor de contas

    public Bank() {
        accounts = new ArrayList<>(); //criando um vetor dinâmico indexado     
    }

    public Bank(String filename) {
        accounts = new ArrayList<>(); //criando um vetor dinâmico indexado 
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String linha = r.readLine();
            int n = Integer.parseInt(linha);
            String[] linhas;
            for (int i = 0; i < n; i++) {
                linha = r.readLine();
                linhas = linha.split("#");
                BankAccount2 conta = new BankAccount2(
                        Integer.parseInt(linhas[0]),
                        linhas[1],
                        linhas[2],
                        Double.parseDouble(linhas[3]));
                this.addAccount(conta);
            }
            r.close();

        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void addAccount(BankAccount2 bankAccount) {
        //Insere uma conta no vetor de contas
        accounts.add(bankAccount); //insere no final

    }

    public ArrayList<BankAccount2> getAccounts() {
        return accounts;
    }

    //Calcular o saldo total de todas as contas inseridas no banco
    public double getTotalBalance() {
        double soma = 0.0;
        for (BankAccount2 conta : accounts) {
            soma = soma + conta.getBalance();
        }
        return soma;
    }

    //Calcular o saldo total de todas as contas inseridas no banco
    public double getTotalBalance2() {
        double soma = 0.0;
        for (BankAccount2 conta : accounts) {
            soma = soma + conta.getBalance();
        }
        return soma;
    }

    //Devolve a conta vinculada a um número dado
    public BankAccount2 find(int accountNumber) {
        for (BankAccount2 conta : accounts)
            if (conta.getAccountNumber() == accountNumber)
                return conta; //Achei
        return null;//Não achei
    }

    //Devolve a conta com o maior saldo
    public BankAccount2 getMaximum() {
        BankAccount2 maior = accounts.get(0);
        for (int i = 1; i < accounts.size(); i++)
            if (accounts.get(i).getBalance() > maior.getBalance())
                maior = accounts.get(i);
        return maior;
    }

    //Devolve a conta com o maior saldo
    public BankAccount2 getMaximum2() {
        BankAccount2 maior = accounts.get(0);
        for (BankAccount2 conta : accounts) //percorre tudo, desde a posição 0
            if (conta.getBalance() > maior.getBalance())
                maior = conta;
        return maior;
    }

    //Devolve o número de contas com saldo igual ou superior a um limite
    public int count(double limit) {
        int cont = 0;
        for (BankAccount2 conta : accounts)
            if (conta.getBalance() >= limit)
                cont++;
        return cont;
    }

    // Remove usando número da conta
    public void removeAccount(int accountNumber) {
        for (BankAccount2 conta : this.accounts) {
            if (conta.getAccountNumber() == accountNumber) {
                int index = this.accounts.indexOf(conta);
                BankAccount2 removed = this.accounts.remove(index);
                System.out.println("Conta:" + removed.getAccountNumber() + "removida com sucesso");
                break;
            }
        }
    }

    // Remove usando isntância da conta
    public void removeAccount(BankAccount2 account) {
        for (BankAccount2 conta : this.accounts) {
            if (conta.equals(account)) {
                this.accounts.remove(conta);
                System.out.println("Conta:" + account.getAccountNumber() + "removida com sucesso");
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder acumuladora = new StringBuilder();
        for (BankAccount2 conta : this.getAccounts()) {
            acumuladora.append(conta.toString()).append("\n");
        }
        return acumuladora.toString();
    }


    // Ordena contas do banco a partir do número da conta
    public void sort() {
        for (int i = 1; i < accounts.size(); i++) {
            BankAccount2 aux = new BankAccount2(accounts.get(i).getAccountNumber(), accounts.get(i).getPassword(), accounts.get(i).getOwner(), accounts.get(i).getBalance());
            int j = i;
            while ((j > 0) && (accounts.get(j - 1).getAccountNumber() > aux.getAccountNumber())) {
                BankAccount2 s = accounts.get(j - 1);
                accounts.get(j).swap(s);
                j = j - 1;
            }
            accounts.get(j).swap(aux);
        }
    }


    // Recebe nome do arquivo e escreve atributos das contas
    public void dump(String filename) {
        try {
             FileWriter arq = new FileWriter(filename);
             PrintWriter out = new PrintWriter(arq);
             /*
             Conta número: <numero da conta>
             Senha: <senha da conta>
             Proprietário: <nome do proprietário>
             Saldo: <saldo da conta>
             */
            for(BankAccount2 conta : this.accounts){
                out.printf("Conta número: " + conta.getAccountNumber() +  "\n"+
                            "Senha: " + conta.getPassword()+ "\n"+
                            "Proprietário: "+ conta.getOwner()+"\n"+
                            "Saldo: " + conta.getBalance() +"\n");
                out.println();
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void ChangeAccount(BankAccount2 conta){
        for(BankAccount2 contas : this.accounts){
            if(contas.getAccountNumber() == conta.getAccountNumber()){
                contas.swap(conta);
                System.out.println("Alteração realizada com sucesso!");
                break;
            }
        }
    }
}



