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
        String[][] mapa = juego.crearMapa();
        juego.mostrarMapa(mapa);
        String[][] personaje = juego.crearPersonaje(mapa);
        assertEquals("100",personaje[0][1]);
        juego.mostrarStats(personaje);
        juego.mostrarMapa(mapa);

    }
    @Test
    void crearEnemigo(){
        String[][] mapa = juego.crearMapa();
        juego.mostrarMapa(mapa);
        String[][] enemigo = juego.crearEnemigo(mapa);
        assertEquals("10",enemigo[3][0]);
        juego.mostrarMapa(mapa);
    }
    @Test
    void crearCofre(){
        String[][] mapa = juego.crearMapa();
        juego.mostrarMapa(mapa);
        juego.crearCofre(mapa);
        juego.mostrarMapa(mapa);
    }
    @Test
    void atacarAlEnemigo(){
        String[][] mapa = juego.crearMapa();
        String[][] enemigo = juego.crearEnemigo(mapa);
        String[][] personaje = juego.crearPersonaje(mapa);
        enemigo = juego.atacarAlEnemigo(personaje,enemigo);
        assertEquals("30",enemigo[2][0]);
    }
    @Test
    void atacarAlPersonaje(){
        String[][] mapa = juego.crearMapa();
        String[][] enemigo = juego.crearEnemigo(mapa);
        String[][] personaje = juego.crearPersonaje(mapa);
        personaje = juego.atacarAlPersonaje(personaje,enemigo);
        assertEquals("90",personaje[0][1]);
    }
    @Test
    void menuCombate(){
        String[][] mapa = juego.crearMapa();
        String[][] enemigo = juego.crearEnemigo(mapa);
        String[][] personaje = juego.crearPersonaje(mapa);
        juego.mostrarMenuCombate(personaje,enemigo);
    }

}