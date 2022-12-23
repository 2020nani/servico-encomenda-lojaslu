package br.com.docesdalu.servicoencomenda.application.utils;

import br.com.docesdalu.servicoencomenda.core.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class CalculoValorEncomenda {

    public static Double valorTotalEncomenda(List<Pedido> pedidoList){
        return pedidoList
                .stream()
                .map(Pedido::getValorTotal)
                .collect(Collectors.toList())
                .stream()
                .reduce(0.00, Double::sum);
    }
}
