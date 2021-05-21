package br.myhome.cc;

import javax.swing.*;
import java.util.Date;

public class ControleDasOperacao {

    private Operacao[] listaDeOperacoes = new Operacao[9999]; // as operações são gravadas em uma lista do  tipo Operações
    int contadorDeOperacoa = 0;// variavel para o controle da lista

    // metodo para cadastrar uma nova operação
    public void cadastrarNovaOperacao(){

        Operacao operacao = new Operacao();// intancia um novo objeto Operação para criação da operação

        operacao.cadastrarOperacao();// chama o metodo de cadastro da operação

        listaDeOperacoes[contadorDeOperacoa] = operacao;//adiciona a operação na lista de operações

        contadorDeOperacoa++;// adiciona + 1 a variavel de controle

    }

    // metodos para mostrar o relatorio geral
    private void mostrarRelatorio(){

        String listaParaORelatorio = ""; // variavel do tipo String para gardar as informações das operações

        for (int i = 0; i<contadorDeOperacoa;i++){ // laço para percorrer a lista de operações
            listaParaORelatorio += listaDeOperacoes[i].obterNomeDoCliente()+" "+
                          listaDeOperacoes[i].obeterValorDaOperacaoEmDolar()+" "+
            listaDeOperacoes[i].obterDataDaOperacao()+"\n"; // adiciona as informações na String criada em cima
        }

        JOptionPane.showMessageDialog(null,listaParaORelatorio);// mostra o relatorio para o usuario
    }

    // metodos para mostrar o relatorio geral com o filtro de nome
    private void mostrarRelatorio(String nome){

        String listaParaORelatorio = "";// variavel do tipo String para gardar as informações das operações

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações

            if (listaDeOperacoes[i].obterNomeDoCliente().equals(nome)) {//contição para verificar o nome do clente da opração
                listaParaORelatorio += listaDeOperacoes[i].obterNomeDoCliente() + " " +
                        listaDeOperacoes[i].obeterValorDaOperacaoEmDolar() + " " +
                        listaDeOperacoes[i].obterDataDaOperacao() + "\n";
            }
        }

        JOptionPane.showMessageDialog(null,listaParaORelatorio);// mostra o relatorio para o usuario

    }

    // metodos para mostrar o relatorio geral com o filtro de data
    private void mostrarRelatorio(Date inicio, Date fim){

        String listaParaORelatorio = "";// variavel do tipo String para gardar as informações das operações

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações

            if (listaDeOperacoes[i].obterDataDaOperacao().after(inicio) &&
                         listaDeOperacoes[i].obterDataDaOperacao().before(fim)) {//condição para verificar a data da operação
                listaParaORelatorio += listaDeOperacoes[i].obterNomeDoCliente() + " " +
                        listaDeOperacoes[i].obeterValorDaOperacaoEmDolar() + " " +
                        listaDeOperacoes[i].obterDataDaOperacao() + "\n";
            }

        }

        JOptionPane.showMessageDialog(null,listaParaORelatorio);// mostra o relatorio para o usuario

    }

    // metodo para gerar o relatorio geral
    public void gerarRelatorioGeral(){

        int escolha;

      escolha =  JOptionPane.showConfirmDialog(null,"Deseja aplicar filtros no relatorio?"
                                             ,"",JOptionPane.YES_NO_OPTION);// pergunta para o usuario se ele
                                                                                 // quer aplicar filtro

      if (escolha == -1){ // se o usuario  apertou para sair ele fecha o metodo
          return;
      }
      if (escolha == 0){// se o usuario escolheu sim;

          Object[] opcaoDeFiltro = {"Nome", "Data"};// cria as opções de filtro

          String escolhaDefiltro;
          escolhaDefiltro = (String) JOptionPane.showInputDialog(null,"Escolha uma opção de filtro","",
                                               JOptionPane.DEFAULT_OPTION, null,opcaoDeFiltro,opcaoDeFiltro[0]);// pergunta qual filtro
                                                                                                 // o usuario quer aplicar

         if (opcaoDeFiltro == null){ //tratamento para caso vor um cancelar ou fechar

              return;
          }else if (escolhaDefiltro == "Nome"){// caso o usuario escolha pelo mone do cliente

             String nomeParaOFiltro = null;

             nomeParaOFiltro = JOptionPane.showInputDialog(null,"Digite o nome do cliente para o filtro");// pergunta o mone do cliente

             mostrarRelatorio(nomeParaOFiltro); // chama o metodo para mostrar o relatorio

          }else if (escolhaDefiltro == "Data"){ // caso o usuario escolha pela data da operação

             String dataInicioParaOfiltro = null;
             String dataFimParaOFiltro = null;

             dataInicioParaOfiltro = JOptionPane.showInputDialog(null,"Digite a data de inicio para o filtro\n" +
                                                                  "Exemplo: 01/01/21 (mm/dd/yy) "); // pergunta a data de inicio

             Date inicio = new Date(dataInicioParaOfiltro);

             dataFimParaOFiltro = JOptionPane.showInputDialog(null,"Digite a data do fim para o filtro\n" +
                     "Exemplo: 12/01/21 (mm/dd/yy)");// pergunta a data do fim

             Date fim = new Date(dataFimParaOFiltro);

             mostrarRelatorio(inicio,fim);// chama o metodo para mostrar o relatorio para o usuario

          }
         }else {//caso a opção de não filtrar
          mostrarRelatorio();// chama o metodo para mostrar o relatorio para  o usuario
      }

    }

    // metodo para mostrar o valor total das operaçoes
    private void mostrarValoTotal(){

        double valorTotal = 0.0;// variavel para calcular o valor total

        Date dataInicio = listaDeOperacoes[0].obterDataDaOperacao();// aloca a data da operaçõa mais velha
        Date dataDoFim = listaDeOperacoes[contadorDeOperacoa-1].obterDataDaOperacao();// aloca a data da operaçõa mais nova

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações
            valorTotal += listaDeOperacoes[i].obeterValorDaOperacaoEmDolar();// soma o valor ja contido na variavel
                                                                             // valorTotal com o valor da operação
        }

        JOptionPane.showMessageDialog(null,"O valor total da operação de "+dataInicio+" até "+dataDoFim+
                                       " é de: "+valorTotal+" Dolar");//mostra as informações ao usuario
    }

    // metodo para mostrar o valor total das operaçoes em um periodo de tempo epecifico
    private void mostrarValoTotal(Date inicio, Date fim){

        double valorTotal = 0.0;// variavel para calcular o valor total

        Date dataInicio = inicio; // aloca o data inicial para aplicar o filtro
        Date dataDoFim = fim;// aloca o data final para aplicar o filtro

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações

            if (listaDeOperacoes[i].obterDataDaOperacao().after(inicio) &&
                           listaDeOperacoes[i].obterDataDaOperacao().before(fim)) {//condição para verificar a data da operação
                valorTotal += listaDeOperacoes[i].obeterValorDaOperacaoEmDolar();
            }

        }

        JOptionPane.showMessageDialog(null,"O valor total da operação de "+dataInicio+" até "+dataDoFim+
                " é de: "+valorTotal+" Dolar");//mostra as informações ao usuario

    }

    // metodo para gerar o relatorio do valor total
    public void geralRelatorioDeValorTotal(){

        int escolha;

        escolha =  JOptionPane.showConfirmDialog(null,"Deseja aplicar filtros de data no relatorio?"
                ,"",JOptionPane.YES_NO_OPTION); // pergunta para o usuario se ele quer aplicar filtro

        if (escolha == -1){//caso cancelar ou fechar
            return;
        }
        if (escolha == 0){// caso sim

            // aloca as variaveis das datas para o filtro
            String dataInicioParaOfiltro = null;
            String dataFimParaOFiltro = null;


                // pede as datas para o usuario
                dataInicioParaOfiltro = JOptionPane.showInputDialog(null,"Digite a data de inicio para o filtro\n" +
                        "Exemplo: 01/01/21 (mm/dd/yy) ");

                Date inicio = new Date(dataInicioParaOfiltro);

                dataFimParaOFiltro = JOptionPane.showInputDialog(null,"Digite a data do fim para o filtro\n" +
                        "Exemplo: 12/01/21 (mm/dd/yy)");

                Date fim = new Date(dataFimParaOFiltro);

                // mostra o relatorio para o usuario
                mostrarValoTotal(inicio, fim);

        }else {// caso escolha em não filtrar

            // mostra o relatorio para o ususario
            mostrarValoTotal();
        }

    }

    // metodo para mostrar o valor total das taxas das operaçoes
    private void mostrarValoTotalDasTaxa(){

        double valorTotal = 0.0;// variavel para calcular o valor total das taxas

        Date dataInicio = listaDeOperacoes[0].obterDataDaOperacao();// aloca a data da operaçõa mais velha
        Date dataDoFim = listaDeOperacoes[contadorDeOperacoa-1].obterDataDaOperacao();// aloca a data da operaçõa mais nova

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações
            valorTotal += listaDeOperacoes[i].obeterValorDaTaxaEmDolar();// soma o valor ja contido na variavel
                                                                         // valorTotal com o valor da taxa da operação
        }

        JOptionPane.showMessageDialog(null,"O valor total das  taxas do "+dataInicio+" até "+dataDoFim+
                " é de: "+valorTotal+" Dolar");//mostra as informações ao usuario

    }

    // metodo para mostrar o valor total das taxas das operaçoes , num periodo espcifico de tempo
    private void mostrarValoTotalDasTaxa(Date inicio, Date fim){

        double valorTotal = 0.0;// variavel para calcular o valor total das taxas

        Date dataInicio = inicio; // aloca o data inicial para aplicar o filtro
        Date dataDoFim = fim;// aloca o data final para aplicar o filtro

        for (int i = 0; i<contadorDeOperacoa;i++){// laço para percorrer a lista de operações

            if (listaDeOperacoes[i].obterDataDaOperacao().after(inicio) &&
                    listaDeOperacoes[i].obterDataDaOperacao().before(fim)) {//condição para verificar a data da operação
                valorTotal += listaDeOperacoes[i].obeterValorDaTaxaEmDolar();
            }

        }

        JOptionPane.showMessageDialog(null,"O valor total da operação de "+dataInicio+" até "+dataDoFim+
                " é de: "+valorTotal+" Dolar");//mostra as informações ao usuario

    }

    // metodo para gerar o relatorio do valor total
    public void geralRelatorioDeValorDasTaxas(){

        // aloca as variaveis das datas para o filtro
        String dataInicioParaOfiltro = null;
        String dataFimParaOFiltro = null;

        int escolha;

        escolha =  JOptionPane.showConfirmDialog(null,"Deseja aplicar filtros de data no relatorio?"
                ,"",JOptionPane.YES_NO_OPTION);// pergunta para o usuario se ele quer aplicar filtro

        if (escolha == -1){//caso cancelar ou fechar
            return;
        }
        if (escolha == 0){// caso sim

            // pede as datas para o usuario
            dataInicioParaOfiltro = JOptionPane.showInputDialog(null,"Digite a data de inicio para o filtro\n" +
                    "Exemplo: 01/01/21 (mm/dd/yy) ");

            Date inicio = new Date(dataInicioParaOfiltro);

            dataFimParaOFiltro = JOptionPane.showInputDialog(null,"Digite a data do fim para o filtro\n" +
                    "Exemplo: 12/01/21 (mm/dd/yy)");

            Date fim = new Date(dataFimParaOFiltro);

            // mostra o relatorio para o usuario
            mostrarValoTotalDasTaxa(inicio, fim);


        }else {

            // mostra o relatorio para o usuario
            mostrarValoTotalDasTaxa();
        }





    }

}
