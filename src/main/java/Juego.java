import java.util.Scanner;
import java.util.Random;
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
        definirSpawn(mapa,"X");
        return mapa;
    }

    public void mostrarMapa(String[][] mapa) {
        for (String[] strings : mapa) {
            String linea = String.join(" ", strings);
            System.out.println(linea);
        }
    }

    public String[][] crearPersonaje(String[][] mapa) {
        String[][] personaje = crearMatriz(4, 2);
        String[][] posiciones = definirSpawn(mapa,"P");
        personaje[0][0] = "Vida: ";
        personaje[0][1] = "100";
        personaje[1][0] = "Ataque: ";
        personaje[1][1] = "15";
        personaje[2][0] = "PosicionX: ";
        personaje[2][1] = posiciones[1][0];
        personaje[3][0] = "PosicionY: ";
        personaje[3][1] = posiciones[0][0];

        return personaje;
    }

    public void mostrarStats(String[][] personaje) {
        for (String[] strings : personaje) {
            System.out.print(strings[0]);
            System.out.println(strings[1]);
        }
    }
    public String[][] crearEnemigo(String[][] mapa){
        String[][] enemigo = crearMatriz(4, 1);
        String[][] posiciones = definirSpawn(mapa,"E");
        enemigo[0][0] = posiciones[0][0];
        enemigo[1][0] = posiciones[1][0];
        enemigo[2][0] = "45";
        enemigo[3][0] = "10";

        return enemigo;
    }
    public String[][] crearCofre(String[][] mapa){
        String[][] cofre = crearMatriz(3,1);
        Random random = new Random();
        int prob;
        int vida;
        prob = random.nextInt(2);
        vida = random.nextInt(100);
        if(prob == 0) {
            vida = -1 *(vida);
            cofre[0][0] = Integer.toString(vida);
        }
        else{
            cofre[0][0] = Integer.toString(vida);
        }
        String [][] posiciones = definirSpawn(mapa,"C");
        cofre[1][0] = posiciones[0][0];
        cofre[2][0] = posiciones[1][0];
        return cofre;
    }
    public String[][] definirSpawn(String[][] mapa,String cosa){
        Random random = new Random();
        String[][] posiciones = crearMatriz(2,1);
        int posicionX;
        int posicionY;
        while(true){
            posicionX = random.nextInt(10);
            posicionY =  random.nextInt(10);
            if (mapa[posicionY][posicionX].equals(".")){
                mapa[posicionY][posicionX] = cosa;
                break;
            }
        }

        posiciones[0][0] = Integer.toString(posicionY);
        posiciones[1][0] = Integer.toString(posicionX);
        return posiciones;

    }
    public String leer(){
        Scanner teclado = new Scanner(System.in);
        return teclado.next();
    }
    public boolean movimiento(String[][] mapa, String[][] personaje,String accion){

        if(accion.equals("s")){
            if(mapa[Integer.parseInt(personaje[3][1])+1][Integer.parseInt(personaje[2][1])].equals(".")){
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = ".";
                personaje[3][1] = Integer.toString(Integer.parseInt(personaje[3][1])+1);
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = "P";
                return true;
            }
        }if(accion.equals("w")) {
            if (mapa[Integer.parseInt(personaje[3][1])-1][Integer.parseInt(personaje[2][1])].equals(".")) {
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = ".";
                personaje[3][1] = Integer.toString(Integer.parseInt(personaje[3][1])-1);
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = "P";
                return true;
            }
        }if(accion.equals("d")) {
            if (mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])+1].equals(".")) {
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = ".";
                personaje[2][1] = Integer.toString(Integer.parseInt(personaje[2][1])+1);
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = "P";
                return true;
            }
        }if(accion.equals("a")) {
            if (mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])-1].equals(".")) {
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = ".";
                personaje[2][1] = Integer.toString(Integer.parseInt(personaje[2][1])-1);
                mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = "P";
                return true;
            }
        }
        return false;
    }
    public String[][] atacarAlEnemigo(String[][] personaje,String[][] enemigo){
        enemigo[2][0] =Integer.toString(Integer.parseInt(enemigo[2][0])-Integer.parseInt(personaje[1][1]));
        return enemigo;
    }
    public String[][] atacarAlPersonaje(String[][] personaje,String[][] enemigo){
        personaje[0][1] = Integer.toString(Integer.parseInt(personaje[0][1])-Integer.parseInt(enemigo[3][0]));
        return personaje;
    }
    public String[][] huir(String[][] posicionAnterior,String[][] personaje,String[][] mapa){
        personaje[3][1] = posicionAnterior[0][0];
        personaje[2][1] = posicionAnterior[1][0];
        mapa[Integer.parseInt(personaje[3][1])][Integer.parseInt(personaje[2][1])] = "P";
        return personaje;
    }
    public String[][] combate(String[][] personaje, String[][] enemigo,String[][] posicionAnterior,String[][] mapa){
        try{
            while(true){
            mostrarMenuCombate(personaje,enemigo);
            String op = leer();
            if(Integer.parseInt(op) == 1){
                atacarAlEnemigo(personaje,enemigo);
                if(Integer.parseInt(enemigo[2][0])>0){
                    atacarAlPersonaje(personaje,enemigo);
                    if(Integer.parseInt(personaje[0][1])<=0){
                        System.out.println("....YOU DIED....");
                        break;
                    }
                }else{
                    System.out.println("El enemigo ha muerto....");
                    mapa = eliminarEnemigo(mapa,enemigo);
                    break;
                }
            }if(op.equals("2")){
                personaje = huir(posicionAnterior,personaje,mapa);
                break;
            }else{
               System.out.println("Ingrese una opcion valida...");
                }
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return personaje;
    }public String[][] eliminarEnemigo(String[][] mapa,String[][] enemigo){
        mapa[Integer.parseInt(enemigo[0][0])][Integer.parseInt(enemigo[1][0])] = ".";
        return mapa;
    }
    public String[][] abrirCofre(String[][] personaje,String[][] cofre,String[][] mapa){
        personaje[0][1] = Integer.toString(Integer.parseInt(personaje[0][1])+(Integer.parseInt(cofre[0][0])));
        eliminarCofre(mapa,cofre);
        if (Integer.parseInt(personaje[0][1]) == 0){
            System.out.println("....YOU DIED....");
            return personaje;
        }
        System.out.println("Luego de abrir el cofre tu vida es: "+personaje[0][1]);
        return personaje;
    }
    public String[][] eliminarCofre(String[][] mapa,String[][] cofre){
        mapa[Integer.parseInt(cofre[1][0])][Integer.parseInt(cofre[2][0])] = ".";
        return mapa;
    }public String[][] activarEvento(String[][] mapa,String[][] enemigo,String[][] cofre, String[][] personaje){
        String[][] posicionAnterior = crearMatriz(2,1);
        posicionAnterior[0][0] = personaje[3][1];
        posicionAnterior[1][0] = personaje[2][1];
        if(mapa[(Integer.parseInt(personaje[3][1])-1)][Integer.parseInt(personaje[2][1])].equals("E") || mapa[(Integer.parseInt(personaje[3][1])+1)][Integer.parseInt(personaje[2][1])].equals("E") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])-1)].equals("E") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])+1)].equals("E") ){
                combate(personaje,enemigo,posicionAnterior,mapa);

        }if(mapa[(Integer.parseInt(personaje[3][1])-1)][Integer.parseInt(personaje[2][1])].equals("C") || mapa[(Integer.parseInt(personaje[3][1])+1)][Integer.parseInt(personaje[2][1])].equals("C") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])-1)].equals("C") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])+1)].equals("C") ){
            while(true){
            System.out.println("Quieres abrir el cofre?(s/n)");
            String op = leer();
            if(op.equals("s")){
                abrirCofre(personaje,cofre,mapa);
                return mapa;
            }if(op.equals("n")){
                return mapa;
            }else{
                System.out.println("Ingrese una opcion valida...");
            }
        }
        }
        return mapa;
    }


    public boolean finJuego(String[][] mapa,String[][] personaje){
        if(Integer.parseInt(personaje[0][1])<= 0){
            return true;
        }
        if(mapa[(Integer.parseInt(personaje[3][1])-1)][Integer.parseInt(personaje[2][1])].equals("X") || mapa[(Integer.parseInt(personaje[3][1])+1)][Integer.parseInt(personaje[2][1])].equals("X") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])-1)].equals("X") || mapa[Integer.parseInt(personaje[3][1])][(Integer.parseInt(personaje[2][1])+1)].equals("X")){
                return true;
        }
        return false;
    }

    public void mostrarMenuCombate(String[][] personaje, String[][] enemigo){
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("Vida enemigo: "+enemigo[2][0]);
        System.out.println("                                 Vida personaje: "+personaje[0][1]);
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("1.Atacar");
        System.out.println("2.Huir");
        System.out.println("////////////////////////////////////////////////////");
    }
    public void menu(){
        String[][] mapa = crearMapa();
        String[][] personaje = crearPersonaje(mapa);
        String[][] enemigo = crearEnemigo(mapa);
        String[][] cofre = crearCofre(mapa);
        try{
        while(true){
            activarEvento(mapa,enemigo,cofre,personaje);
            if (!finJuego(mapa, personaje)) {
                mostrarMapa(mapa);
                System.out.print("Presiona w/a/s/d para moverte por el mapa:");
                String accion = leer();
                accion = accion.toLowerCase();
                System.out.println(accion);
                if (accion.equals("w") || accion.equals("s")|| accion.equals("d")|| accion.equals("a")) {
                    if (movimiento(mapa, personaje, accion)) {
                        System.out.println("Te haz movido de casilla...");
                    } else {
                        System.out.println("No puedes moverte hacia alla...");
                    }
                } else {
                    System.out.println("Utiliza las teclas mencionadas arriba....");
                }
            }else{
                break;
            }
        }
        System.out.println("El juego termino 🦖");
        }catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.menu();
    }




}
