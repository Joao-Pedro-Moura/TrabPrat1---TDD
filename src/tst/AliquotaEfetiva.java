package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.DescricaoEmBrancoException;
import app.NomeEmBrancoException;
import app.SimuladorIRPF;
import app.ValorDeducaoInvalidoException;
import app.ValorRendimentoInvalidoException;

public class AliquotaEfetiva {

	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	@Test
	public void testeValorAliquotaEfetiva() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 3000f);
		assertEquals(0.1055f, simulador.getValorAliquota(), 0.1f);
	}
	
	@Test
	public void testeValorOutraAliquotaEfetiva() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException, ValorDeducaoInvalidoException, NomeEmBrancoException {
		simulador.cadastrarRendimento("Salario", 10000f);
		simulador.cadastrarDeducao("Previdencia Privada", 5000f);
		assertEquals(0.0505f, simulador.getValorAliquota(), 0.1f);
	}

}
