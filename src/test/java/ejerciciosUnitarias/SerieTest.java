package ejerciciosUnitarias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

		List series = Arrays.asList(new Serie("Cuarta", 4, "", ""), new Serie("Segunda", 2, "", ""),
				new Serie("Primera", 1, "", ""), new Serie("Tercera", 3, "", ""));
		List aux = new ArrayList(series);
		
		try {
		    Collections.sort(series); // Forzamos el método
		} catch (ClassCastException e) {
		    System.err.println("Error al intentar ordenar la lista: " + e.getMessage());
		}

		/*
		 * La llamada al método sort provoca una excepción, porque la clase Serie no
		 * implementa la clase Comparable, por tanto aunque el método compareTo funcione
		 * no se está consiguiendo que tenga el comportamiento probablemente deseado
		 */

		assertThat("Las listas deberían tener un orden distinto", series, not(equalTo(aux)));

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

		// El método equals funciona, pero...

		assertThat("El método equals debería ser llamado indirectamente", serie,
				is(new Serie("Succession", "Jesse Armstrong")));

		/*
		 * Este test falla, porque el método equals no está sobreescribiendo al de la
		 * clase Object, y por tanto no se está consiguiendo que tenga el comportamiento
		 * probablemente deseado
		 */

	}

}
