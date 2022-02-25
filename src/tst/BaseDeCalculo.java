package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.NomeEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;
import app.ValorRendimentoInvalidoException;

public class BaseDeCalculo {
	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	

	@Test
	public void testeBaseDeCalculo() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException, ValorRendimentoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 3000f);
		assertEquals(7000f, simulador.getBaseDeCalculo(), 0.1f);
	}


}