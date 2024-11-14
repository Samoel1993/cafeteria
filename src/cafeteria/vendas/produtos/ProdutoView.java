package cafeteria.vendas.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProdutoView extends JInternalFrame {

	private static final String TITULO = "Cadastro de Produto";

	private static final int POSICAO_X_INICIAL = 60;
	private static final int POSICAO_Y_INICIAL = 60;

	private static final int LARGURA = 580;
	private static final int ALTURA = 300;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField nome;
	private JComboBox<UnidadeMedida> medida;
	private JFormattedTextField preco;
	private JTextField estoque;
	private JCheckBox temEstoque;

	private JButton btSalvar;
	private JButton btVoltar;
	private JButton btNovoProduto;
	private JButton btPesquisar;

	private IProdutoService service = null;

	/**
	 * Cria a janela do CRUD do produto
	 */
	public ProdutoView(IProdutoService service) {
		this.service = service;

		setClosable(true);
		setIconifiable(true);
		setSize(LARGURA, ALTURA);
		setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
		setTitle(TITULO);
		getContentPane().setLayout(null);

		JLabel lbId = new JLabel("ID:");
		lbId.setBounds(31, 40, 60, 17);
		getContentPane().add(lbId);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(109, 38, 114, 21);
		getContentPane().add(id);
		id.setColumns(10);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(31, 73, 60, 17);
		getContentPane().add(lbNome);

		nome = new JTextField();
		nome.setBounds(109, 71, 430, 21);
		getContentPane().add(nome);
		nome.setColumns(10);

		JLabel lbMedida = new JLabel("Medida:");
		lbMedida.setBounds(31, 106, 60, 17);
		getContentPane().add(lbMedida);

        medida = new JComboBox<>(UnidadeMedida.values());
		medida.setBounds(109, 104, 114, 21);
		getContentPane().add(medida);

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickSalvar();
			}
		});
		btSalvar.setBounds(434, 229, 105, 27);
		getContentPane().add(btSalvar);

		btVoltar = new JButton("Cancelar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickVoltar();
			}
		});
		btVoltar.setBounds(322, 229, 105, 27);
		getContentPane().add(btVoltar);

		btNovoProduto = new JButton("Novo Produto");
		btNovoProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickIncluirNovoProduto();
			}
		});
		btNovoProduto.setBounds(400, 35, 139, 27);
		getContentPane().add(btNovoProduto);

		btPesquisar = new JButton("Teste");
		btPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisar();
			}
		});
		btPesquisar.setBounds(235, 35, 96, 27);
		getContentPane().add(btPesquisar);

		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setBounds(31, 136, 60, 17);
		getContentPane().add(lbPreco);

		JLabel lbEstoque = new JLabel("Estoque:");
		lbEstoque.setBounds(31, 205, 60, 17);
		getContentPane().add(lbEstoque);

		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		preco = new JFormattedTextField(numberFormat);
		preco.setHorizontalAlignment(SwingConstants.RIGHT);
		preco.setBounds(109, 137, 114, 21);
		getContentPane().add(preco);
		preco.setColumns(10);

		estoque = new JTextField();
		estoque.setHorizontalAlignment(SwingConstants.RIGHT);
		estoque.setBounds(109, 203, 114, 21);
		getContentPane().add(estoque);
		estoque.setColumns(10);

		temEstoque = new JCheckBox("Tem estoque?");
		temEstoque.setBounds(26, 166, 114, 25);
		getContentPane().add(temEstoque);
		temEstoque.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					estoque.setEnabled(true);
				}else{
					estoque.setEnabled(false);
					estoque.setText(null);
				}
			}
		});
	}

	/**
	 * Prepara o frame para a ação de consultar
	 */
	public void setupConsultar() {
		// configura os botões de ação
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(false);
		medida.setEnabled(false);
		preco.setEnabled(false);
		temEstoque.setEnabled(false);
		estoque.setEnabled(false);
	}

	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickPesquisar() {
		Produto produto = service.consulta(Integer.parseInt(id.getText()));
		nome.setText(produto.getNome());
		preco.setText(Double.toString(produto.getPreco()));
		medida.setSelectedItem(produto.getMedida());

		id.setEnabled(false);
		btPesquisar.setEnabled(false);
		btNovoProduto.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);
		temEstoque.setEnabled(true);
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);


		System.out.println("==> onClickPesquisar");
	}

	/**
	 * Executa as tarefas para preparar a interface para a inclusão de um novo
	 * produto
	 */
	protected void onClickIncluirNovoProduto() {
		nome.setEnabled(true);
		preco.setEnabled(true);
		medida.setEnabled(true);
		temEstoque.setEnabled(true);
		btVoltar.setEnabled(true);
		btSalvar.setEnabled(true);
		id.setEnabled(false);

	}


	/**
	 * Executa as tarefas para voltar a inclusão de um produto
	 */
	protected void onClickVoltar() {
		nome.setText(null);
		preco.setText(null);

	}

	/**
	 * Executa as tarefas para salvar a inclusão de um novo produto
	 */
	protected void onClickSalvar() {
		Produto produto = new Produto();
		UnidadeMedida m = (UnidadeMedida) medida.getSelectedItem();
		produto.setNome(nome.getText());
		produto.setPreco(((Number) preco.getValue()).doubleValue());
		produto.setMedida(m);
		service.salvar(produto);

		System.out.println("==> onClickSalvar");
	}
}
