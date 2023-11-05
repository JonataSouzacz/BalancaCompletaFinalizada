package org.example.classes;

import org.example.excecoes.ObjetoNulo;
import org.example.interfaces.IBalanca;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BalancaFizola implements IBalanca<Produto> {
    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        try (FileWriter writer = new FileWriter(pastaArquivoTxt + "/CADTXT.TXT")) {
            if (produtos.isEmpty()) {
                throw new ObjetoNulo("Lista nula");
            }
            for (Produto produto : produtos) {
                if (produto == null) {
                    throw new ObjetoNulo("Produto na balanca fizola nulo");
                }
                writer.write(String.format("%06d", produto.getCodigo()));
                writer.write(produto.getTipo() == 1 ? "P" : "U");
                writer.write(String.format("%-22s", produto.getDescricao()));
                writer.write(String.format("%07d", (int) (produto.getValor() * 100)));
                writer.write("000\n");
            }
        } catch (IOException | ObjetoNulo e) {
            System.out.println(e.getMessage());
        }
    }
}
