package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.NomeEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;
import app.ValorRendimentoInvalidoException;

public class ValorImposto {

	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	@Test
	public void testeValorImpostoFaixaUm() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 3000f);
		assertEquals(0f, simulador.getValorImpostoFaixaUm(), 0.1f);
	}
	
	@Test
	public void testeValorImposto() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 3000f);
		assertEquals(1055.64f, simulador.getValorImposto(), 0.1f);
	}
	
	@Test
	public void testeOutroValorImposto() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 5000f);
		assertEquals(505.64f, simulador.getValorImposto(), 0.1f);
	}
	
	@Test
	public void testeTerceiroValorImposto() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 1000f);
		simulador.cadastrarDeducao("Funpresp", 1200f);
		simulador.cadastrarContribuicaoPrevidenciaria("INSS", 800f);
		simulador.cadastrarContribuicaoPrevidenciaria("Contracheque", 1200f);
		simulador.cadastrarPensaoAlimenticia(1300f);
		simulador.cadastrarPensaoAlimenticia(120.82f);
		simulador.cadastrarDependente("joao", 170698f);
		simulador.cadastrarDependente("Marcos", 260898f);
		assertEquals(263.87f, simulador.getValorImposto(), 0.1f);
	}


}
