import br.myhome.cc.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ControleDasOperacao operacao = new ControleDasOperacao();// inicia um novo objeto Comprole de operação

        Object[] opcaoDeOperacoes = {"Cadastrar nova operacao", "Gerar relatorio geral","Geral relatorio de valor total",
                                      "Geral relatorio de valor das taxas"};// opções para o gerenciamento das operaçoes


        String escolha; // variavel para amazernar a escolha do usuario

        while (true){// loop para ficar executando o programa


            //pedir para o usuario escolher qual ação fazer
            escolha = (String) JOptionPane.showInputDialog(null,"Escolha uma opção: ","Gerenciador de Oprações",
                                           JOptionPane.INFORMATION_MESSAGE,null,opcaoDeOperacoes,opcaoDeOperacoes[0]);

            if (escolha == null){//caso o usuario feche o programa
                return;
            }

            switch (escolha){// verificar a escolha do usuario

                case "Cadastrar nova operacao":
                    operacao.cadastrarNovaOperacao();
                    break;
                case "Gerar relatorio geral":
                    operacao.gerarRelatorioGeral();
                    break;
                case  "Geral relatorio de valor total":
                    operacao.geralRelatorioDeValorTotal();
                    break;
                case "Geral relatorio de valor das taxas":
                    operacao.geralRelatorioDeValorDasTaxas();
                    break;
            }

        }

    }

}
