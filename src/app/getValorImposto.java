package app;

public class getValorImposto {
	
	
	//Valores das constantes
	private static final float ALIQUOTA0 = 0f;
	private static final double ALIQUOTA015 = 0.15f;
	private static final double ALIQUOTA225 = 0.225f;
	private static final double ALIQUOTA275 = 0.275f;
	private static final double ALIQUOTA075 = 7.5/100f;
	private static final double LIMITESUPERIOR4AFAIXA = 4664.68f;
	private static final double LIMITESUPERIOR3AFAIXA = 3751.05f;
	private static final double LIMITESUPERIOR2AFAIXA = 2826.65f;
	private static final double LIMITESUPERIOR1AFAIXA = 1903.99f;
	private static final double VALORMAXIMOIMPOSTO4AFAIXA = 205.5667f;
	private static final double VALORMAXIMOIMPOSTO3AFAIXA = 138.66f;

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
		inicializandoFaixasComValoresMaximos();
		
		if (valorBase < LIMITESUPERIOR1AFAIXA) {
			return ALIQUOTA0;
		}
		else {
			if (valorBase > LIMITESUPERIOR2AFAIXA) {
				somarValorImpostoSegundaFaixaParaValorTotal();
				if (valorBase > LIMITESUPERIOR3AFAIXA) {
					somarValorImpostoTerceiraFaixaParaValorTotal();
					if (valorBase > LIMITESUPERIOR4AFAIXA) {
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
		imposto += VALORMAXIMOIMPOSTO3AFAIXA;
	}

	private void somarValorImpostoSegundaFaixaParaValorTotal() {
		imposto = imposto + faixa2*ALIQUOTA075;
	}

	private void obterValorImpostoAteQuintaFaixa() {
		valorBase = valorBase - faixa1 - faixa2 - faixa3 - faixa4;
		valorBase *= ALIQUOTA275;
		imposto = imposto + VALORMAXIMOIMPOSTO4AFAIXA + valorBase;
	}

	private void obterValorImpostoAteQuartaFaixa() {
		faixa4 = valorBase - faixa1 - faixa2 - faixa3;
		faixa4 *= ALIQUOTA225;
		imposto = imposto + faixa4;
	}

	private void obterValorImpostoAteSegundaFaixa() {
		faixa2 -= faixa1;
		faixa2 *= ALIQUOTA075;
		imposto = imposto + faixa2;
	}

	private void obterValorImpostoAteTerceiraFaixa() {
		faixa3 = valorBase - faixa1 - faixa2;
		faixa3 *= ALIQUOTA015;
		imposto = imposto + faixa3;
	}

	private void obterValorDaBaseDeCalculo() {
		valorBase = _fonte.getBaseDeCalculo();
	}

	private void inicializandoValorImposto() {
		imposto = 0;
	}

	private void inicializandoFaixasComValoresMaximos() {
		faixa1 = 1903.98;
		faixa2 = 922.67;
		faixa3 = 924.40;
		faixa4 = 913.63;
	}
	
}