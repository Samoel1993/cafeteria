package cafeteria.vendas;

import cafeteria.vendas.clientes.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private List<ItemVenda> itens = new ArrayList<>();
    private double valorTotal;

    public Venda(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
