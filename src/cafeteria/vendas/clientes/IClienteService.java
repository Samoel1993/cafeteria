package cafeteria.vendas.clientes;

import java.util.List;

public interface IClienteService {

    public Cliente consulta(String id);
    public void salva(Cliente cliente);
    public void editar();

}
