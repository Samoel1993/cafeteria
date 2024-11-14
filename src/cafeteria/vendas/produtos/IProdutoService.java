package cafeteria.vendas.produtos;

public interface IProdutoService {
    public Produto consulta(int id);
    public void salvar(Produto produto);
    public void editar();

}
