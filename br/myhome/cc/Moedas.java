package br.myhome.cc;

public class Moedas {// classe responsavel por fazer as converções

    public double converterMoeda(String moedaOrigin, String moedaDestino, double valor) { // nesse metodo passamos 3 parametros
                                                                                          // o primeiro sendo a moedada de origem
                                                                                          // o segundo e da moeda de destino e
                                                                                          // por ultimo o valor que sera convertido

        if (moedaOrigin == "BRL") { // no primeiro teste de condições, testa o primeiro paramentro  para descobrir qual
                                    // operação deve seguir
            if (moedaDestino == "USD") {// no teste condicional mais interno verificamos o segundo parametro, para assim
                                        // descobrir a moeda de destino e fazer a operação corretamente
                return valor * 0.19;// retornamos o valor vezes a contação da moeda de destino;
            } else {
                return valor * 0.16;
            }
        } else if (moedaOrigin == "USD") {
            if (moedaDestino == "BRL") {
                return valor * 5.22;
            } else {
                return valor * 0.82;
            }
        } else {
            if (moedaDestino == "BRL") {
                return valor * 6.34;
            } else {
                return valor * 1.21;
            }
        }
    }

}
