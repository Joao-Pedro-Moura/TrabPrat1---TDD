package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;

public class PensaoAlimenticia {

	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	@Test
	public void testeCadastrarUmaPensao() throws ValorDeducaoInvalidoException {
		simulador.cadastrarPensaoAlimenticia(300f);
		assertEquals(300f, simulador.getTotalPensaoAlimenticia(), 0f);
	}
	
	@Test
	public void testeCadastrarOutraPensao() throws ValorDeducaoInvalidoException {
		simulador.cadastrarPensaoAlimenticia(100f);
		assertEquals(100f, simulador.getTotalPensaoAlimenticia(), 0f);
	}
	
	@Test
	public void testeCadastrarDuasContribuicoes() throws ValorDeducaoInvalidoException {
		simulador.cadastrarPensaoAlimenticia(300f);
		simulador.cadastrarPensaoAlimenticia(100f);
		assertEquals(400f, simulador.getTotalPensaoAlimenticia(), 0f);
	}
}
