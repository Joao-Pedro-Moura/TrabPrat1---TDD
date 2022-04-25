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
	private float soma;
	private float dependentes;
	private float totalDeducoes;
	
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
		
		soma = 0;
		
		somarValorDeducoesParaVariavelSoma();
		
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
		
		soma = 0;
		
		somarValorTotalContribuicaoPrevidenciariaParaVariavelSoma();
		
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
		
		soma = 0;
		
		somarValorTotalPensaoAlimenticiaParaVariavelSoma();
		
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
		
		soma = 0;
		
		for (int i=0; i<dataNascimento.length; i++) {
			soma += 1;
		}
		
		soma *= 189.59;
		
		return soma;
	}
	
	public float getTotalTodasDeducoes() {
		inicializandoVariaveisComValorZero();
		
		somarValorDeducoesParaVariavelSoma();
		somarValorTotalContribuicaoPrevidenciariaParaVariavelSoma();
		somarValorTotalPensaoAlimenticiaParaVariavelSoma();
		obterQuantidadeDependentes();
		
		obterValorTotalDependentes();
		
		obterValorTotalTodasDeducoes();
		
		return totalDeducoes;
	}

	private void obterValorTotalTodasDeducoes() {
		totalDeducoes = dependentes + soma;
	}

	private void obterValorTotalDependentes() {
		dependentes *= 189.59;
	}

	private void obterQuantidadeDependentes() {
		for (int i=0; i<dataNascimento.length; i++) {
			dependentes += 1;
		}
	}

	private void somarValorTotalPensaoAlimenticiaParaVariavelSoma() {
		for (float f : valorPensaoAlimenticia) {
			soma += f;
		}
	}

	private void somarValorTotalContribuicaoPrevidenciariaParaVariavelSoma() {
		for (float f : valorContribuicaoPrevidenciaria) {
			soma += f;
		}
	}

	private void somarValorDeducoesParaVariavelSoma() {
		for (float f : valorDeducao) {
			soma += f;
		}
	}

	private void inicializandoVariaveisComValorZero() {
		soma = 0;
		dependentes = 0;
		totalDeducoes = 0;
	}

	public float getBaseDeCalculo() {
		baseDeCalculo = 0;
		
		baseDeCalculo = totalRendimentos - getTotalTodasDeducoes();
		
		return baseDeCalculo;
	}
	
	public float getValorImpostoFaixaUm() {
		return 0f; // valor sempre sera 0
	}
	
	public double getValorImposto() {
		double valorBase = getBaseDeCalculo();
		double imposto = 0;
		double faixa1 = 1903.98;
		double faixa2 = 922.67;
		double faixa3 = 924.40;
		double faixa4 = 913.63;
		
		if (valorBase < 1903.99) {
			return 0f;
		}
		else {
			if (valorBase > 2826.65) {
				imposto = imposto + faixa2*(7.5/100);
				if (valorBase > 3751.05) {
					imposto += 138.66;
					if (valorBase > 4664.68) {
						valorBase = valorBase - faixa1 - faixa2 - faixa3 - faixa4;
						valorBase *= 0.275;
						imposto = imposto + 205.5667 + valorBase; 
					}
					else {
						faixa4 = valorBase - faixa1 - faixa2 - faixa3;
						faixa4 *= 0.225;
						imposto = imposto + faixa4;
					}
				}
				else {
					faixa3 = valorBase - faixa1 - faixa2;
					faixa3 *= 0.15;
					imposto = imposto + faixa3;
				}
			}
			else {
				faixa2 -= faixa1;
				faixa2 *= 0.075;
				imposto = imposto + faixa2;
			}
		}
		return imposto;
	}

	public double getValorAliquota() {
		double imposto = getValorImposto();
		double rendimento = getTotalRendimentos();
		return imposto/rendimento;
	}
}
