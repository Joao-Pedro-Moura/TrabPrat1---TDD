package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.NomeEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;

public class Dependentes {

private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	@Test
	public void testeCadastrarUmDependente() throws ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarDependente("joao", 170698f);
		assertEquals(189.59, simulador.getTotalDependentes(), 0.1f);
	}
	
	@Test
	public void testeCadastrarOutroDependente() throws ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarDependente("Marcos", 260898f);
		assertEquals(189.59, simulador.getTotalDependentes(), 0.1f);
	}
	
	@Test
	public void testeCadastrarDoisDependentes() throws ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarDependente("joao", 170698f);
		simulador.cadastrarDependente("Marcos", 260898f);
		assertEquals(379.18, simulador.getTotalDependentes(), 0.1f);
	}
	
}
