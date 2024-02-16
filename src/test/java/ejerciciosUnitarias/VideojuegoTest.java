package ejerciciosUnitarias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

		/*
		 * AVISO: La clase Serie no implementa la clase Comparable, por tanto, este
		 * método no sería utilizado por Collections.sort.
		 */

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
		
		assertThat("El método equals debería considerar que los videojuegos son diferentes",
				videojuego.equals(new Videojuego("Hearthstone", "Blizzard")), is(false));

		/*
		 * AVISO: Este método no está sobreescribiendo al de la clase Object, por tanto,
		 * no será utilizado por Java para considerar equivalencia de objetos.
		 */

	}
}
