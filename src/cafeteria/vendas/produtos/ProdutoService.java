package cafeteria.vendas.produtos;

public class ProdutoService implements IProdutoService{
    @Override
    public Produto consulta(int id) {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Leite");
        produto.setPreco(52.40);
        produto.setMedida(UnidadeMedida.GARRAFA);


        return produto;
    }

    @Override
    public void salvar(Produto produto) {
        System.out.println(produto.getNome());
        System.out.println(produto.getPreco());
        System.out.println(produto.getMedida());
        System.out.println("Salvei");

    }

    @Override
    public void editar() {

    }
}
