package br.myhome.cc;

import javax.swing.*;
import java.util.Date;


public class Operacao extends Moedas {

    private String nomeDoCliente;
    private String moedaDeOringem;
    private String moedeDeDestino;
    private Date dataDeOperacao;
    private double valorDeOringin;
    private double valorConvertido;
    private double taxaCobrada;

    public void cadastrarOperacao() { // metodo cadastra as informações da operação na classe

        nomeDoCliente = JOptionPane.showInputDialog("Digite o nome do clinte:"); // pede o nome do cliente ao usuario



        if (nomeDoCliente == null) { // verifica se o usuario clicou no cancelar, fechar a janela ou não digitou nada
            return;// caso sim ele finaliza a função
        }

        Object[] opcaoMoeda = {"BRL", "USD", "EUR"}; // foi criado um vetor do tipo Object que contem as opções
                                                     // de moedas de origen para converção

        moedaDeOringem = (String) JOptionPane.showInputDialog(null, "Escolha a moeda de origem: ", "Moeda de origem",
                JOptionPane.INFORMATION_MESSAGE, null, opcaoMoeda, opcaoMoeda[0]);// mostras as opções de moedas e
                                                                                       // pede para o usuario escolher uma.

        if (moedaDeOringem == null) {// verifica se o usuario clicou no cancelar ou fechar a janela
            return;// caso sim ele finaliza a função
        }


        Object[] opcaoMoeda2 = new Object[2];// Foi criado um vetor do tipo Object para alocar as outras duas moedas restantes
                                            // para assim o usuario escolher uma delas

        switch (moedaDeOringem) {//verificamos a moeda de origem para saber quais moedas não foram utilizadas como origem
                                 // e esscrevemos essas moedas no vetor criado acima.
            case "BRL":
                opcaoMoeda2[0] = "USD";
                opcaoMoeda2[1] = "EUR";
                break;
            case "USD":
                opcaoMoeda2[0] = "BRL";
                opcaoMoeda2[1] = "EUR";
                break;
            case "EUR":
                opcaoMoeda2[0] = "USD";
                opcaoMoeda2[1] = "BRL";
                break;
        }


        moedeDeDestino = (String) JOptionPane.showInputDialog(null, "Escolha a moeda de destino: ", "Moeda de destino",
                JOptionPane.INFORMATION_MESSAGE, null, opcaoMoeda2, opcaoMoeda2[0]);// mostramos as opções de moedas
                                                                          // de destino e pedimos para selecionar uma

        if (moedeDeDestino == null) {// verifica se o usuario clicou no cancelar ou fechar a janela
            return;// caso sim ele finaliza a função
        }

        dataDeOperacao = new Date(System.currentTimeMillis());// pegamos a data do sistema e alocamos na variavel
                                                              // responsavel por gardar tal informação

        valorDeOringin = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor de origem")); // pede para o usuario
                                                                     // entrar com o valor a ser convertido

        taxaCobrada = valorDeOringin * 0.1;//calcula a taxa cobrada e salva na variavel responsavel

        valorConvertido = converterMoeda(moedaDeOringem, moedeDeDestino, valorDeOringin - taxaCobrada);
                                            // converte o valor menos a taxa cobrada para a moeda de destino.


        JOptionPane.showMessageDialog(null, "Cliente: " + nomeDoCliente + "\nMoeda de origem: " + moedaDeOringem
                + "\nMoeda de destino: " + moedeDeDestino + "\nValor de origem: " + valorDeOringin +
                "\nTaxa cobrada: " + taxaCobrada + "\nValor convertido: " + valorConvertido +
                "\nData da operação: " + dataDeOperacao);// mostra as informações ao usuario


    }

    public double obeterValorDaOperacaoEmDolar() {//metodo para obter o valor total da operação, por motivos de
                                                  // evitar que os relatorios saião com tipos de moedas diferentes
                                                 // o metado converte tudo em dolar

        if (moedaDeOringem == "USD") {//condiçoes para verificar a moeda de oringem e retornnar o valor correto
            return valorDeOringin;
        } else if (moedaDeOringem == "BRL") {
            return valorDeOringin * 0.19;
        } else {
            return valorDeOringin * 1.21;
        }

    }

    public double obeterValorDaTaxaEmDolar() {//metodo para obter o valor total da taxa da operação, por motivos de
                                          // evitar que os relatorios saião com tipos de moedas diferentes
                                         // o metado converte tudo em dolar

        if (moedaDeOringem == "USD") {//condiçoes para verificar a moeda de oringem e retornnar o valor correto
            return taxaCobrada;
        } else if (moedaDeOringem == "BRL") {
            return taxaCobrada * 0.19;
        } else {
            return taxaCobrada * 1.21;
        }
    }

    public Date obterDataDaOperacao() { // metodo para obtemos a data que ocorreu a operação

        return dataDeOperacao;
    }

    public String obterNomeDoCliente(){// retorna o nome do cliente da operação
        return nomeDoCliente;
    }

}
