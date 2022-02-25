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
import app.ValorDeducaoInvalidoException;

// @RunWith(Parameterized.class)
public class OutrasDeducoes {

	private SimuladorIRPF simulador;
	
	@Before
	public void setup() {
		simulador = new SimuladorIRPF();
	}
	
//	Object [][] deducoes;
//	float valorEsperado;
//	
//	public Deducoes(Object [][] deducoes, float valorEsperado) {
//		this.deducoes = deducoes;
//		this.valorEsperado = valorEsperado;
//	}
//	
//	@Test
//	public void cadastroDeducoes() {
//		for (Object[] deducao : deducoes) {
//			simulador.cadastrarDeducao((String)deducao[0], (float)deducao[1]);
//		}
//		
//		assertEquals(valorEsperado, simulador.getTotalDeducao(), 0f);
//	}
//
//	@Parameters
//	public static Collection<Object[]> getParameters(){
//		Object[][] resposta = new Object[][] {
//			{new Object[][] {
//				{"Previdencia Privada", 500f}
//			}, 500f},
//			{new Object[][] {
//				{"Funpresp", 200f}
//			}, 200f},
//			{new Object[][] {
//				{"Carne", 150f},
//				{"Pensao Alimenticia", 300f}
//			}, 450f}
//		};
//		
//		return Arrays.asList(resposta);
//	}
	
	
	@Test
	public void testeCadastroUmaDeducao() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao("Previdencia Privada", 500f);
		assertEquals(500f, simulador.getTotalOutrasDeducoes(), 0f);
	}
	
	@Test
	public void testeCadastroOutraDeducao() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao("Funpresp", 200f);
		assertEquals(200f, simulador.getTotalOutrasDeducoes(), 0f);
	}

	@Test
	public void testeCadastroDuasDeducoes() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao("Previdencia Privada", 500f);
		simulador.cadastrarDeducao("Funpresp", 200f);
		assertEquals(700f, simulador.getTotalOutrasDeducoes(), 0f);
	}
	
	// Teste de descrição da dedução em branco
	@Test(expected = DescricaoEmBrancoException.class) 
	public void testeCadastroDescricaoDeducaoEmBranco() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao(null, 500f);
		assertEquals(500f, simulador.getTotalOutrasDeducoes(), 0f);
	}
	
	// Teste de valor da dedução igual a zero
	@Test(expected = ValorDeducaoInvalidoException.class)
	public void testeCadastroValorDeducaoZero() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao("Previdencia Privada", 0f);
		assertEquals(500f, simulador.getTotalOutrasDeducoes(), 0f);
	}
	
	// Teste de valor da dedução negativo
	@Test(expected = ValorDeducaoInvalidoException.class)
	public void testeCadastroValorDeducaoNegativo() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
		simulador.cadastrarDeducao("Previdencia Privada", -10f);
		assertEquals(500f, simulador.getTotalOutrasDeducoes(), 0f);
	}
	
}
