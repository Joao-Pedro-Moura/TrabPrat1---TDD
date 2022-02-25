package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;

public class ContribuicaoPrevidenciariaOficial {

	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	@Test
	public void testeCadastrarUmaContribuicao() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarContribuicaoPrevidenciaria("INSS", 1500f);
		assertEquals(1500f, simulador.getTotalContribuicao(), 0f);
	}
	
	@Test
	public void testeCadastrarOutraContribuicao() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarContribuicaoPrevidenciaria("Contracheque", 700f);
		assertEquals(700f, simulador.getTotalContribuicao(), 0f);
	}
	
	@Test
	public void testeCadastrarDuasContribuicoes() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarContribuicaoPrevidenciaria("INSS", 1500f);
		simulador.cadastrarContribuicaoPrevidenciaria("Contracheque", 700f);
		assertEquals(2200f, simulador.getTotalContribuicao(), 0f);
	}

}
