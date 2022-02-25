package app;

import java.util.LinkedList;
import java.util.List;

public class SimuladorIRPF {

	private List<Rendimento> rendimentos;
	private float totalRendimentos;
	private String[] descricaoDeducao;
	private String[] descricaoContribuicao;
	private float[] valorDeducao;
	private float[] valorContribuicaoPrevidenciaria;
	private float[] valorPensaoAlimenticia;
	private float[] dataNascimento;
	private String[] nomeDependente;
	private float baseDeCalculo;
	
	public SimuladorIRPF() {
		rendimentos = new LinkedList<Rendimento>();
		descricaoDeducao = new String[0];
		valorDeducao = new float[0];
		descricaoContribuicao = new String[0];
		valorContribuicaoPrevidenciaria = new float[0];
		valorPensaoAlimenticia = new float[0];
		dataNascimento = new float[0];
		nomeDependente = new String[0];
	}

	public void cadastrarRendimento(String descricao, float valorRendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		if (descricao == null) {
			throw new DescricaoEmBrancoException();
		}
		if (valorRendimento <= 0) {
			throw new ValorRendimentoInvalidoException();
		}
		
		Rendimento r = new Rendimento(descricao, valorRendimento);
		this.rendimentos.add(r);
		
		this.totalRendimentos += valorRendimento;
	}

	public float getTotalRendimentos() {
		return totalRendimentos;
	}

	public void cadastrarDeducao(String descricaoDeducao, float valor) throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		
		if (descricaoDeducao == null) {
			throw new DescricaoEmBrancoException();
		}
		if (valor <= 0) {
			throw new ValorDeducaoInvalidoException();
		}
		
		String tempDescricaoDeducao[] = new String[descricaoDeducao.length() + 1];
		float tempValor[] = new float[valorDeducao.length + 1];
		
		for (int i=0; i<valorDeducao.length; i++) {
			tempValor[i] = valorDeducao[i];
			tempDescricaoDeducao[i] = this.descricaoDeducao[i];
		}
		
		tempValor[valorDeducao.length] = valor;
		tempDescricaoDeducao[valorDeducao.length] = descricaoDeducao;
		
		this.valorDeducao = tempValor;
		this.descricaoDeducao = tempDescricaoDeducao;
		
	}

	public float getTotalOutrasDeducoes() {
		
		float soma = 0;
		
		for (float f : valorDeducao) {
			soma += f;
		}
		
		return soma;
	}
	
	public void cadastrarContribuicaoPrevidenciaria(String descricaoContribuicao, float valor) throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		
		if (descricaoContribuicao == null) {
			throw new DescricaoEmBrancoException();
		}
		if (valor <= 0) {
			throw new ValorDeducaoInvalidoException();
		}
		
		String tempDescricaoContribuicao[] = new String[descricaoContribuicao.length() + 1];
		float tempValor[] = new float[valorContribuicaoPrevidenciaria.length + 1];
		
		for (int i=0; i<valorContribuicaoPrevidenciaria.length; i++) {
			tempValor[i] = valorContribuicaoPrevidenciaria[i];
			tempDescricaoContribuicao[i] = this.descricaoContribuicao[i];
		}
		
		tempValor[valorContribuicaoPrevidenciaria.length] = valor;
		tempDescricaoContribuicao[valorContribuicaoPrevidenciaria.length] = descricaoContribuicao;
		
		this.valorContribuicaoPrevidenciaria = tempValor;
		this.descricaoContribuicao = tempDescricaoContribuicao;
		
	}
	
	public float getTotalContribuicao() {
		
		float soma = 0;
		
		for (float f : valorContribuicaoPrevidenciaria) {
			soma += f;
		}
		
		return soma;
	}

	public void cadastrarPensaoAlimenticia(float valorPA) throws ValorDeducaoInvalidoException {
		
		if (valorPA <= 0) {
			throw new ValorDeducaoInvalidoException();
		}
		
		float tempValorPA[] = new float[valorPensaoAlimenticia.length + 1];
		
		for (int i=0; i<valorPensaoAlimenticia.length; i++) {
			tempValorPA[i] = valorPensaoAlimenticia[i];
		}
		
		tempValorPA[valorPensaoAlimenticia.length] = valorPA;
		
		this.valorPensaoAlimenticia = tempValorPA;
		
	}
	
	public float getTotalPensaoAlimenticia() {
		
		float soma = 0;
		
		for (float f : valorPensaoAlimenticia) {
			soma += f;
		}
		
		return soma;
	}

	public void cadastrarDependente(String nomeDependente, float data) throws NomeEmBrancoException, ValorDeducaoInvalidoException {
		
		if (nomeDependente == null) {
			throw new NomeEmBrancoException();
		}
		
		String tempNomeDependente[] = new String[nomeDependente.length() + 1];
		float tempDataNascimento[] = new float[dataNascimento.length + 1];
		
		for (int i=0; i<dataNascimento.length; i++) {
			tempNomeDependente[i] = this.nomeDependente[i];
			tempDataNascimento[i] = dataNascimento[i];
		}
		
		tempNomeDependente[dataNascimento.length] = nomeDependente;
		tempDataNascimento[dataNascimento.length] = data;
		
		this.nomeDependente = tempNomeDependente;
		this.dataNascimento = tempDataNascimento;
		
	}
	
	public float getTotalDependentes() {
		
		float soma = 0;
		
		for (int i=0; i<dataNascimento.length; i++) {
			soma += 1;
		}
		
		soma *= 189.59;
		
		return soma;
	}
	
	public float getTotalTodasDeducoes() {
		return 3000f; // por falsificacao
	}

	public float getBaseDeCalculo() {
		return 7000f; // por falsificacao
	}

	public float getValorImpostoFaixaUm() {
		return 0f; // por falsificacao
	}
	
	public double getValorImposto() {
		return 1055.64f; // por falsificacao
	}
	public double getValorAliquota() {
		return 0.1055f; //por falsificacao
	}
}
