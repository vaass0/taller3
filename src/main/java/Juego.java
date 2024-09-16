import java.util.Scanner;

public class Juego {
    public String[][] crearMatriz(int filas, int columnas) {
        String[][] matriz = new String[filas][columnas];
        return matriz;
    }

    public String[][] crearMapa() {
        String[][] mapa = crearMatriz(10, 10);
        for (int i = 0; i < mapa.length; i++) {
            mapa[i][0] = "#";
            mapa[0][i] = "#";
            mapa[9][i] = "#";
            mapa[i][9] = "#";
        }
        int comienzo = 1;
        int tope = mapa.length - 2;
        for (int i = 1; i < (mapa.length - 1); i++) {

            if (i % 2 == 0) {
                if (comienzo == 1) {
                    for (int j = 1; j < (tope); j++) {
                        if (j % 2 == 0) {
                            mapa[i][j] = "#";
                        }
                    }
                    comienzo++;
                    tope++;
                } else {
                    for (int j = 2; j < (tope); j++) {
                        if (j % 2 == 0) {
                            mapa[i][j] = "#";
                        }
                    }
                    comienzo--;
                    tope--;
                }
            }
        }
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                if (mapa[i][j] != "#") {
                    mapa[i][j] = ".";
                }
            }
        }

        return mapa;
    }

    public void mostrarMapa(String[][] mapa) {
        for (String[] strings : mapa) {
            String linea = String.join(" ", strings);
            System.out.println(linea);
        }
    }

    public String[][] crearPersonaje(String nombre,String posicionX,String posicionY) {
        String[][] personaje = crearMatriz(4, 2);
        personaje[0][0] = "Nombre: ";
        personaje[0][1] = nombre;
        personaje[1][0] = "Vida: ";
        personaje[1][1] = "100";
        personaje[2][0] = "PosicionX: ";
        personaje[2][1] = posicionX;
        personaje[3][0] = "PosicionY: ";
        personaje[3][1] = posicionY;

        return personaje;
    }

    public void mostrarStats(String[][] personaje) {
        for (String[] strings : personaje) {
            System.out.print(strings[0]);
            System.out.println(strings[1]);
        }
    }

    public String leer(){
        Scanner teclado = new Scanner(System.in);
        return teclado.next();
    }
}
