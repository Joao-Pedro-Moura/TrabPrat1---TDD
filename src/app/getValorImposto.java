package app;

public class getValorImposto {
	
	//Refencia para objeto fonte
	SimuladorIRPF _fonte;
	
	//Para cada variavel temporaria um atributo
	double valorBase;
	double imposto;
	double faixa1;
	double faixa2;
	double faixa3;
	double faixa4;
	
	public getValorImposto(SimuladorIRPF _fonte){
		this._fonte = _fonte;
	}
	
	double computar() {
		obterValorDaBaseDeCalculo();
		inicializandoValorImposto();
		inicializandoValoresDasFaixas();
		
		if (valorBase < 1903.99) {
			return 0f;
		}
		else {
			if (valorBase > 2826.65) {
				somarValorImpostoSegundaFaixaParaValorTotal();
				if (valorBase > 3751.05) {
					somarValorImpostoTerceiraFaixaParaValorTotal();
					if (valorBase > 4664.68) {
						obterValorImpostoAteQuintaFaixa(); 
					}
					else {
						obterValorImpostoAteQuartaFaixa();
					}
				}
				else {
					obterValorImpostoAteTerceiraFaixa();
				}
			}
			else {
				obterValorImpostoAteSegundaFaixa();
			}
		}
		return imposto;
	}

	private void somarValorImpostoTerceiraFaixaParaValorTotal() {
		imposto += 138.66;
	}

	private void somarValorImpostoSegundaFaixaParaValorTotal() {
		imposto = imposto + faixa2*(7.5/100);
	}

	private void obterValorImpostoAteQuintaFaixa() {
		valorBase = valorBase - faixa1 - faixa2 - faixa3 - faixa4;
		valorBase *= 0.275;
		imposto = imposto + 205.5667 + valorBase;
	}

	private void obterValorImpostoAteQuartaFaixa() {
		faixa4 = valorBase - faixa1 - faixa2 - faixa3;
		faixa4 *= 0.225;
		imposto = imposto + faixa4;
	}

	private void obterValorImpostoAteSegundaFaixa() {
		faixa2 -= faixa1;
		faixa2 *= 0.075;
		imposto = imposto + faixa2;
	}

	private void obterValorImpostoAteTerceiraFaixa() {
		faixa3 = valorBase - faixa1 - faixa2;
		faixa3 *= 0.15;
		imposto = imposto + faixa3;
	}

	private void obterValorDaBaseDeCalculo() {
		valorBase = _fonte.getBaseDeCalculo();
	}

	private void inicializandoValorImposto() {
		imposto = 0;
	}

	private void inicializandoValoresDasFaixas() {
		faixa1 = 1903.98;
		faixa2 = 922.67;
		faixa3 = 924.40;
		faixa4 = 913.63;
	}
	
}