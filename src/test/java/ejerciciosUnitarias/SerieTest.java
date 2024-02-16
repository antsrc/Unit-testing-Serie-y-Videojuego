package ejerciciosUnitarias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SerieTest {

	Serie serie;

	@BeforeEach
	public void init() {
		serie = new Serie();
	}

	@Test
	public void testCompareTo() {

		assertThat("La serie de prueba debería ser igual que la auxiliar", serie.compareTo(new Serie()),
				is(Serie.IGUAL));

		serie.setnumeroTemporadas(1);
		assertThat("La serie de prueba debería ser menor que la auxiliar", serie.compareTo(new Serie()),
				is(Serie.MENOR));

		serie.setnumeroTemporadas(5);
		assertThat("La serie de prueba debería ser mayor que la auxiliar", serie.compareTo(new Serie()),
				is(Serie.MAYOR));

		/*
		 * AVISO: La clase Serie no implementa la clase Comparable, por tanto, este
		 * método no sería utilizado por Collections.sort.
		 */

	}

	@Test
	public void testToString() {

		serie.setTitulo("The Sopranos");
		serie.setnumeroTemporadas(6);
		serie.setGenero("drama");
		serie.setcreador("David Chase");

		String esperado = "Informacion de la Serie: \n" + "\tTitulo: The Sopranos" + "\n" + "\tNumero de temporadas: 6"
				+ "\n" + "\tGenero: drama" + "\n" + "\tCreador: David Chase";

		assertThat("El resultado del método toString no es el esperado", serie.toString(), equalTo(esperado));

	}

	@Test
	public void testEquals() {

		serie.setTitulo("Succession");
		serie.setcreador("Jesse Armstrong");

		assertThat("El método equals debería considerar que las series son iguales",
				serie.equals(new Serie("Succession", "Jesse Armstrong")), is(true));

		assertThat("El método equals debería considerar que las series son diferentes",
				serie.equals(new Serie("True Detective", "Nic Pizzolatto")), is(false));

		/*
		 * AVISO: Este método no está sobreescribiendo al de la clase Object, por tanto,
		 * no será utilizado por Java para considerar equivalencia de objetos.
		 */

	}

}
