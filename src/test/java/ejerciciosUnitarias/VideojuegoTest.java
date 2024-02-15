package ejerciciosUnitarias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VideojuegoTest {

	Videojuego videojuego;

	@BeforeEach
	public void init() {
		videojuego = new Videojuego();
	}

	@Test
	public void testCompareTo() {

		assertThat("El videojuego de prueba debería ser igual que el auxiliar", videojuego.compareTo(new Videojuego()),
				is(Videojuego.IGUAL));

		videojuego.setHorasEstimadas(50);
		assertThat("El videojuego de prueba debería ser menor que el auxiliar", videojuego.compareTo(new Videojuego()),
				is(Videojuego.MENOR));

		videojuego.setHorasEstimadas(150);
		assertThat("El videojuego de prueba debería ser mayor que el auxiliar", videojuego.compareTo(new Videojuego()),
				is(Videojuego.MAYOR));

		List videojuegos = Arrays.asList(new Videojuego("Cuarto", 40, "", ""), new Videojuego("Segundo", 20, "", ""),
				new Videojuego("Primero", 10, "", ""), new Videojuego("Tercero", 30, "", ""));
		List aux = new ArrayList(videojuegos);

		try {
			Collections.sort(videojuegos); // Forzamos el método
		} catch (ClassCastException e) {
			System.err.println("Error al intentar ordenar la lista: " + e.getMessage());
		}

		/*
		 * La llamada al método sort provoca una excepción, porque la clase Videojuego no
		 * implementa la clase Comparable, por tanto aunque el método compareTo funcione
		 * no se está consiguiendo que tenga el comportamiento probablemente deseado
		 */

		assertThat("Las listas deberían tener un orden distinto", videojuegos, not(equalTo(aux)));
	}

	@Test
	public void testToString() {
		videojuego.setTitulo("Kenshi");
		videojuego.setHorasEstimadas(83);
		videojuego.setGenero("RTS/RPG");
		videojuego.setcompañia("Lo-Fi Games");

		String esperado = "Informacion del videojuego: \n" + "\tTitulo: Kenshi\n" + "\tHoras estimadas: 83\n"
				+ "\tGenero: RTS/RPG\n" + "\tcompañia: Lo-Fi Games";

		assertThat("El resultado del método toString no es el esperado", videojuego.toString(), equalTo(esperado));
	}

	@Test
	public void testEquals() {
		videojuego.setTitulo("Football Manager 2024");
		videojuego.setcompañia("Sports Interactive");

		assertThat("El método equals debería considerar que los videojuegos son iguales",
				videojuego.equals(new Videojuego("Football Manager 2024", "Sports Interactive")), is(true));

		// El método equals funciona, pero...
		assertThat("El método equals debería ser llamado indirectamente", videojuego,
				is(new Videojuego("Football Manager 2024", "Sports Interactive")));
		/*
		 * Este test falla, porque el método equals no está sobreescribiendo al de la
		 * clase Object, y por tanto no se está consiguiendo que tenga el comportamiento
		 * probablemente deseado
		 */
	}
}
