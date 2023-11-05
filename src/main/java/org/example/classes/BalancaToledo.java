package org.example.classes;

import org.example.excecoes.ObjetoNulo;
import org.example.interfaces.IBalanca;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BalancaToledo implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        try (FileWriter writer = new FileWriter(pastaArquivoTxt + "/ITENSMGV.TXT")) {
            if(produtos.isEmpty()){
                throw new ObjetoNulo("Lista nula");
            }
            for (Produto produto : produtos) {
                if(produto == null){
                    throw new ObjetoNulo("Produto na balanca toledo nulo");
                }
                StringBuilder sb = new StringBuilder();

                sb.append("01");
                sb.append(produto.getTipo());
                sb.append(String.format("%06d", produto.getCodigo()));
                sb.append(String.format("%06d", (int) (produto.getValor() * 100)));
                sb.append("000");
                sb.append(String.format("%-50s", produto.getDescricao()));
                sb.append("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000|01|");
                sb.append("                                                                      ");
                sb.append("0000000000000000000000000|0000|0||");

                writer.write(sb.toString() + "\n");
            }
        } catch (IOException | ObjetoNulo e) {
            System.out.println(e.getMessage());
        }
    }
}
