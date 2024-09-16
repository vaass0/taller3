import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {
    Juego juego = new Juego();
    @Test
    void crearMapa() {
        String[][] mapa = juego.crearMapa();
        juego.mostrarMapa(mapa);
    }
    @Test
    void crearPersonaje(){
        String[][] personaje = juego.crearPersonaje("ElPollas","0","0");
        juego.mostrarStats(personaje);
    }
}