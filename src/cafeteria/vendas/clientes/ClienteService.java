package cafeteria.vendas.clientes;

import java.util.ArrayList;
import java.util.List;

public class ClienteService implements IClienteService {
    @Override
    public Cliente consulta(String id) {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Samoel");
        cliente.setTelefone("48 9913203");

        System.out.println(id);
        return cliente;
    }

    @Override
    public void salva(Cliente cliente) {
        System.out.println(cliente.getNome());
        System.out.println(cliente.getTelefone());
        System.out.println("Salvei");
    }

    @Override
    public void editar() {
        System.out.println("salvei");

    }
}
