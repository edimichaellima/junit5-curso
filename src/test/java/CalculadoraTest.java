import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculadoraTest {

	private Calculadora calc = new Calculadora();
	
	@Test
	public void testSomar() {
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(5, calc.soma(2, 3));
	}
	
	@Test
	public void assertivas() {
		Assertions.assertEquals("Casa", "Casa");
		Assertions.assertNotEquals("Casa", "casa");
		Assertions.assertTrue("casa".equalsIgnoreCase("CASA"));
		Assertions.assertTrue("Casa".endsWith("sa"));
		Assertions.assertTrue("Casa".startsWith("Ca"));
		Assertions.assertFalse("Casa".startsWith("ca"));
		
		List<String> s1 = new ArrayList<>();
		List<String> s2 = new ArrayList<>();
		List<String> s3 = null;
		
		Assertions.assertEquals(s1, s2);
		Assertions.assertSame(s1, s1);
		Assertions.assertNotEquals(s1, s3);
		Assertions.assertNull(s3);
		Assertions.assertNotNull(s1);
		//Assertions.fail("Falhou pelo motivo A");
	}
	
	@Test
	public void deveRetornarNumeroInteiroNaDivisao() {
		float resultado = calc.dividir(6, 2);
		Assertions.assertEquals(3, resultado);
	}
	
	@Test
	public void deveRetornarNumeroNegativoNaDivisao() {
		float resultado = calc.dividir(6, -2);
		Assertions.assertEquals(-3, resultado);
	}
	
	@Test
	public void deveRetornarNumeroDecimalNaDivisao() {
		float resultado = calc.dividir(10, 3);
		Assertions.assertEquals(3.3333332538604736, resultado);
		Assertions.assertEquals(3.33, resultado, 0.1);
	}
	
	@Test
	public void deveRetornarZeroComNumeradorZeroNaDivisao() {
		float resultado = calc.dividir(0, 2);
		Assertions.assertEquals(0, resultado);
	}
	
	@Test
	public void deveRetornarExcecaoQuandoDividirPorZero_Junit4() {
		try {
			float resultado = 10 / 0;
			Assertions.fail("Deveria ter sido lançado uma exceção na divisão");
		} catch (ArithmeticException e) {
			Assertions.assertEquals("/ by zero", e.getMessage());
		}
	}
	
	@Test
	public void deveRetornarExcecaoQuandoDividirPorZero_Junit5() {
		ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () ->{
			float resultado = 10 / 0;
		});
		Assertions.assertEquals("/ by zero", exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Teste1", "Teste2", "Teste 3"})
	public void testStrings(String param) {
		System.out.println(param);
		assertNotNull(param);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			"6, 2, 3",
			"6, -2, -3",
			"10, 3, 3.3333332538604736",
			"0, 2, 0"
	})
	public void deveDividirCorretamente(int num, int den, double res) {
		float resultado = calc.dividir(num, den);
		Assertions.assertEquals(res, resultado);
	}
	
}
