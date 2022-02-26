package tst;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.DescricaoEmBrancoException;
import app.SimuladorIRPF;
import app.ValorRendimentoInvalidoException;

@RunWith(Parameterized.class)
public class CadastroRendimentosTeste {

	SimuladorIRPF simulador;
	private Object[][] rendimentos;
	private float valorEsperado;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
	public CadastroRendimentosTeste(Object[][] rendimentos, float valorEsperado) {
		this.rendimentos = rendimentos;
		this.valorEsperado = valorEsperado;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		Object[][] respostas = new Object[][] {
			{new Object[][] {
				{"Salario", 10000f},
			}, 10000f},
			{new Object[][] {
				{"Salario", 8000f}, 
				{"Aluguel", 2000f}
			}, 10000f}, 
			{new Object[][] {
				{"Salario", 7000f}, 
				{"Aluguel", 2000f},
				{"Dividen", 1000f}
			}, 10000f}
		};
		
		return Arrays.asList(respostas);
	}
	
	
	@Test
	public void testCadastroRendimentos() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		for (Object[] o : rendimentos) {
			simulador.cadastrarRendimento((String)o[0], (float)o[1]);
		}
		assertEquals(valorEsperado, simulador.getTotalRendimentos(), 0f);
	}
	
	
	@Test(expected = DescricaoEmBrancoException.class)
	public void testeCadastraRendimentoDescricaoEmBranco() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		simulador.cadastrarRendimento(null, 10000f);
		assertEquals(10000f, simulador.getTotalRendimentos(), 0f);
	}
	
	@Test (expected = ValorRendimentoInvalidoException.class)
	public void testeCadastraRendimentoValorZero() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		simulador.cadastrarRendimento("Salario", 0f);
		assertEquals(10000f, simulador.getTotalRendimentos(), 0f);
	}
	
	@Test (expected = ValorRendimentoInvalidoException.class)
	public void testeCadastraRendimentoValorNegativo() throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		simulador.cadastrarRendimento("Salario", -10f);
		assertEquals(10000f, simulador.getTotalRendimentos(), 0f);
	}

}
