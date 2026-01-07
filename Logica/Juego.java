package Logica;

import Interfaz.MiniJuego.MinijuegoInterfaz;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.*;
import javax.swing.*;

import Lib.Convert;

public class Juego {
    private static Juego instance;
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;
    private Partida partidaActual;
    private ArrayList<MiniJuego> miniJuegos;




    private Juego() {
        this.titulo = "Juego";
        this.version = "0.5";
        this.partidas = new LinkedList<Partida>();
        this.miniJuegos = new ArrayList<>();

        this.partidaActual = null;
        hacerMinijuegos();
    }



    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(LinkedList<Partida> partidas) {
        this.partidas = partidas;
    }

    public void agregarPartida(Partida p) {
        partidas.add(p);
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    public Partida obtenerPartida(String id) {
        Iterator<Partida> IP = partidas.iterator();
        boolean salida = false;
        Partida p = null;
        while (IP.hasNext() && !salida) {
            Partida par = IP.next();
            if (par.getIdPartida().equals(id)) {
                salida = true;
                p = par;
            }
        }
        return p;
    }

    public boolean crearNuevaPartida(String idPartida, String nombreJugador) {
        // Verificar si ya existe una partida con ese ID
        boolean salida = false;
        if (existePartida(idPartida)) {
            System.out.println("Ya existe una partida con ID: " + idPartida);
        }

        else
        {
            //  Creo la nueva partida
            Partida nuevaPartida = new Partida();
            nuevaPartida.setIdPartida(idPartida);

            //  Configuro a el jugador
            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Agregar a la lista de partidas
            partidas.add(nuevaPartida);

            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Cargar como partida actual (la que esta clonada)
            setPartidaActual(nuevaPartida.clone());


            System.out.println("Nueva partida creada y cargada: " + idPartida);
            salida = true;
        }
        return salida;
    }

    public void eliminarPartida(String id)
    {
        boolean salida = false;
        Iterator<Partida> IP = partidas.iterator();
        while (IP.hasNext() && !salida)
        {
            Partida p = IP.next();
            if (p.getIdPartida().equals(id))
            {
                IP.remove();
                salida = true;
            }
        }
    }
    public boolean existePartida(String id)
    {
        boolean salida = false;
        if (!partidas.isEmpty())
        {
            Iterator<Partida> IP = partidas.iterator();
            while (IP.hasNext() && !salida)
            {
                Partida p = IP.next();
                if (p.getIdPartida().equals(id))
                {
                    salida = true;
                }
            }
        }
        return salida;
    }

    public void iniciarJuego()
    {
        // Se necesita que se invoque el metodo obtenerPartida para poder obtener en que partida esta el usuario.
        // y pasarle el Id de la partida como parametro

    }
    public void cargarPartida()
    {

    }
    /*public void guardarPartida()
    {

    }*/

    public ArrayList<MiniJuego> getMiniJuegos() {
        return miniJuegos;
    }

    public void setMiniJuegos(ArrayList<MiniJuego> miniJuegos) {
        this.miniJuegos = miniJuegos;
    }

    public void aggMinijuego(MiniJuego miniJuego){
        miniJuegos.add(miniJuego);
    }
    public MiniJuego getMinijuego(int numero){
        return miniJuegos.get(numero);
    }
    public void hacerMinijuegos(){
        ImageIcon escCrimen = new ImageIcon("DatosAuxiliares/Minijuego/Escena del Crimen.png");
        MiniJuego escenaDelCrimen = new MiniJuego("Escena del crimen", escCrimen);
        ObjetoEscenario ob1 = new ObjetoEscenario("Laptop", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.332F, 0, 0.103F, 0.2F, false, "");
        ObjetoEscenario ob2 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.3F, 0, 0.028F, 0.14F, false, "");
        ObjetoEscenario ob3 = new ObjetoEscenario("Sombrero", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.037F, 0.04F, 0.065F, 0.1F, false, "");
        ObjetoEscenario ob4 = new ObjetoEscenario("Gafas", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.324F, 0.75F, 0.07F, 0.055F, false, "");
        ObjetoEscenario ob5 = new ObjetoEscenario("Sandwich", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.45F, 0.18F, 0.05F, 0.07F, false, "");
        ObjetoEscenario ob6 = new ObjetoEscenario("Telefono", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.312F, 0.177F, 0.04F, 0.07F, false, "");
        ObjetoEscenario ob7 = new ObjetoEscenario("Taza de café", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.71F, 0.465F, 0.033F, 0.064F, false, "");
        ObjetoEscenario ob8 = new ObjetoEscenario("Copa", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.45F, 0, 0.03F, 0.125F, false, "");
        ObjetoEscenario ob9 = new ObjetoEscenario("Anillo", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.583F, 0.158F, 0.02F, 0.035F, false, "");
        ObjetoEscenario ob10 = new ObjetoEscenario("Reloj de bolsillo", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.0349F, 0.35F, 0.028F, 0.077F, false, "");
        ObjetoEscenario ob11 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.562F, 0.716F, 0.07F, 0.13F, false, "");
        ObjetoEscenario ob12 = new ObjetoEscenario("Memoria USB", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.528F, 0.63F, 0.025F, 0.04F, false, "");
        ObjetoEscenario ob13 = new ObjetoEscenario("Grabadora de voz", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.519F, 0.237F, 0.05F, 0.05F, false, "");
        ObjetoEscenario ob14 = new ObjetoEscenario("Pluma", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.445F, 0.132F, 0.0162F, 0.107F, false, "");
        ObjetoEscenario ob15 = new ObjetoEscenario("Calculadora", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.49F, 0.72F, 0.062F, 0.125F, false, "");
        ObjetoEscenario ob16 = new ObjetoEscenario("Charco de sangre", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.18F, 0.62F, 0.22F, 0.29F, false, "");
        ObjetoEscenario ob17 = new ObjetoEscenario("Planta", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.09F, 0.09F, 0.04F, 0.117F, false, "");
        ObjetoEscenario ob18 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.557F, 0.03F, 0.0307F, 0.22F, false, "");
        ObjetoEscenario ob19 =  new ObjetoEscenario("Caja de musica", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.255F, 0.176F, 0.058F, 0.113F, false, "");
        ObjetoEscenario ob20 = new ObjetoEscenario("Pastillas", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.24F, 0.05F, 0.03F, 0.088F, false, "");
        ObjetoEscenario ob21 = new ObjetoEscenario("Taza de café", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.224F, 0.159F, 0.035F, 0.09F, false, "");
        ObjetoEscenario ob22 = new ObjetoEscenario("Cigarros", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.42F, 0.893F, 0.024F, 0.04F, false, "");
        ObjetoEscenario ob23 = new ObjetoEscenario("Cuchillo", true,new ImageIcon("DatosAuxiliares/Objetos/Cuchillo.png"), 0.437F, 0.605F, 0.069F, 0.12F, true, "Supuesta arma usada en la escena del crimen. A pesar de que contiene sangre de la victima, la causa de la muerte no coincide con el tipo de herida del cuchillo. Definitivamente no es el arma del crimen.");
        ObjetoEscenario ob24 = new ObjetoEscenario("Linterna", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.43F, 0.747F, 0.046F, 0.05F, false, "");
        ObjetoEscenario ob25 = new ObjetoEscenario("Cámara", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.506F, 0.16F, 0.04F, 0.07F, false, "");
        ObjetoEscenario ob26 = new ObjetoEscenario("Carnet", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.674F, 0.695F, 0.032F, 0.051F, false, "");

        escenaDelCrimen.agregarObjetoCola(ob1);
        escenaDelCrimen.agregarObjetoCola(ob2);
        escenaDelCrimen.agregarObjetoCola(ob3);
        escenaDelCrimen.agregarObjetoCola(ob4);
        escenaDelCrimen.agregarObjetoCola(ob5);
        escenaDelCrimen.agregarObjetoCola(ob6);
        escenaDelCrimen.agregarObjetoCola(ob7);
        escenaDelCrimen.agregarObjetoCola(ob8);
        escenaDelCrimen.agregarObjetoCola(ob9);
        escenaDelCrimen.agregarObjetoCola(ob10);
        escenaDelCrimen.agregarObjetoCola(ob11);
        escenaDelCrimen.agregarObjetoCola(ob12);
        escenaDelCrimen.agregarObjetoCola(ob13);
        escenaDelCrimen.agregarObjetoCola(ob14);
        escenaDelCrimen.agregarObjetoCola(ob15);
        escenaDelCrimen.agregarObjetoCola(ob16);
        escenaDelCrimen.agregarObjetoCola(ob17);
        escenaDelCrimen.agregarObjetoCola(ob18);
        escenaDelCrimen.agregarObjetoCola(ob19);
        escenaDelCrimen.agregarObjetoCola(ob20);
        escenaDelCrimen.agregarObjetoCola(ob21);
        escenaDelCrimen.agregarObjetoCola(ob22);
        escenaDelCrimen.agregarObjetoCola(ob23);
        escenaDelCrimen.agregarObjetoCola(ob24);
        escenaDelCrimen.agregarObjetoCola(ob25);
        escenaDelCrimen.agregarObjetoCola(ob26);

        miniJuegos.add(escenaDelCrimen);


        ImageIcon bathImage = new ImageIcon("DatosAuxiliares/Minijuego/Escena del Baño.png");
        MiniJuego bathSecondFloor = new MiniJuego("Baño Segundo Piso", bathImage);

        ObjetoEscenario obBilletera = new ObjetoEscenario("Billetera", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (622-175)/1152f, 667f/765f, (725-622)/1152f, (719-667)/765f, false, "");
        ObjetoEscenario obJabon = new ObjetoEscenario("Jabón", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (342-100)/1152f, 363f/765f, (398-342)/1152f, (396-363)/765f, false, "");
        ObjetoEscenario obGel = new ObjetoEscenario("Gel de Manos", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (211-50)/1152f, 318f/765f, (255-211)/1152f, (395-318)/765f, false, "");
        ObjetoEscenario obLata = new ObjetoEscenario("Lata de Refresco", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (314-75)/1152f, 594f/765f, (351-314)/1152f, (647-594)/765f, false, "");
        ObjetoEscenario obPalo = new ObjetoEscenario("Palo de Trapear", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (102-25)/1152f, 427f/765f, (148-102)/1152f, (530-427)/765f, false, "");
        ObjetoEscenario obGrafiti = new ObjetoEscenario("Grafiti", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (109-25)/1152f, 282f/765f, (178-109)/1152f, (340-282)/765f, false, "");
        ObjetoEscenario obNotas = new ObjetoEscenario("Notas Adhesivas", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (111-25)/1152f, 697f/765f, (192-111)/1152f, (742-697)/765f, false, "");
        ObjetoEscenario obLata2 = new ObjetoEscenario("Lata de refresco 2", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (465-118)/1152f, 607f/765f, (521-465)/1152f, (645-607)/765f, false, "");
        ObjetoEscenario obHoja = new ObjetoEscenario("Hoja de papel", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (621-160)/1152f, 578f/765f, (688-621)/1152f, (607-578)/765f, true, "Contiene la contraseña de la computadora.");
        ObjetoEscenario obCepillo = new ObjetoEscenario("Cepillo de Dientes", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (555-125)/1152f, 322f/765f, (577-555)/1152f, (355-322)/765f, false, "");

        bathSecondFloor.agregarObjetoCola(obBilletera);
        bathSecondFloor.agregarObjetoCola(obJabon);
        bathSecondFloor.agregarObjetoCola(obGel);
        bathSecondFloor.agregarObjetoCola(obLata);
        bathSecondFloor.agregarObjetoCola(obPalo);
        bathSecondFloor.agregarObjetoCola(obGrafiti);
        bathSecondFloor.agregarObjetoCola(obNotas);
        bathSecondFloor.agregarObjetoCola(obLata2);
        bathSecondFloor.agregarObjetoCola(obHoja);
        bathSecondFloor.agregarObjetoCola(obCepillo);

        miniJuegos.add(bathSecondFloor);


        ImageIcon camerasImage = new ImageIcon("DatosAuxiliares/Minijuego/Sala de Cámaras.png");
        MiniJuego camerasRoom = new MiniJuego("Sala de Cámaras", camerasImage);

        ObjetoEscenario obLibro = new ObjetoEscenario("Libro", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (65-30)/1152f, (532-25)/765f, (166-65)/1152f, (561-532)/765f, false, "");
        ObjetoEscenario obCigarrillos = new ObjetoEscenario("Cigarrillos", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (943-305)/1152f, (748-32)/765f, (982-943)/1152f, (766-748)/765f, false, "");
        ObjetoEscenario obLlave = new ObjetoEscenario("Llave", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (893-305)/1152f, (620-25)/765f, (933-893)/1152f, (629-620)/765f, false, "");
        ObjetoEscenario obPluma = new ObjetoEscenario("Pluma", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (469-160)/1152f, (620-25)/765f, (512-469)/1152f, (627-620)/765f, false, "");
        ObjetoEscenario obCuadro = new ObjetoEscenario("Cuadro", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (46-25)/1152f, (106-25)/765f, (117-46)/1152f, (243-106)/765f, false, "");
        ObjetoEscenario obGrabador = new ObjetoEscenario("Grabador de Audio", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (861-280)/1152f, (571-25)/765f, (883-861)/1152f, (582-571)/765f, false, "");
        ObjetoEscenario obEstatua = new ObjetoEscenario("Estatua", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (640-210)/1152f, (171-5)/765f, (667-640)/1152f, (244-171)/765f, false, "");
        ObjetoEscenario obObraArte = new ObjetoEscenario("Obra de Arte", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (810-270)/1152f, (437-15)/765f, (845-810)/1152f, (505-437)/765f, false, "");
        ObjetoEscenario obGafas = new ObjetoEscenario("Gafas", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (373-120)/1152f, (561-25)/765f, (415-373)/1152f, (577-561)/765f, false, "");
        ObjetoEscenario obAuriculares = new ObjetoEscenario("Auriculares", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (613-205)/1152f, (543-25)/765f, (676-613)/1152f, (613-543)/765f, false, "");

        camerasRoom.agregarObjetoCola(obLibro);
        camerasRoom.agregarObjetoCola(obCigarrillos);
        camerasRoom.agregarObjetoCola(obLlave);
        camerasRoom.agregarObjetoCola(obPluma);
        camerasRoom.agregarObjetoCola(obCuadro);
        camerasRoom.agregarObjetoCola(obGrabador);
        camerasRoom.agregarObjetoCola(obEstatua);
        camerasRoom.agregarObjetoCola(obObraArte);
        camerasRoom.agregarObjetoCola(obGafas);
        camerasRoom.agregarObjetoCola(obAuriculares);

        miniJuegos.add(camerasRoom);


        ImageIcon storageImage = new ImageIcon("DatosAuxiliares/Minijuego/Almacén.png");
        MiniJuego storageRoom = new MiniJuego("Almacén", storageImage);

        ObjetoEscenario obLampara = new ObjetoEscenario("Lampara", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (95-30)/1152f, 699f/896f, (140-95)/1152f, (802-699)/896f, false, "");
        ObjetoEscenario obSpray = new ObjetoEscenario("Spray", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (882-227)/1152f, 809f/896f, (916-882)/1152f, (879-809)/896f, false, "");
        ObjetoEscenario obBotella = new ObjetoEscenario("Botella", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (356-90)/1152f, 697f/896f, (382-356)/1152f, (791-697)/896f, false, "");
        ObjetoEscenario obEsfera = new ObjetoEscenario("Esfera del Mundo", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (248-73)/1152f, 592f/896f, (317-248)/1152f, (660-592)/896f, false, "");
        ObjetoEscenario obTelescopio = new ObjetoEscenario("Telescopio", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (366-103)/1152f, 548f/896f, (478-366)/1152f, (602-548)/896f, false, "");
        ObjetoEscenario obBuho = new ObjetoEscenario("Buho de Madera", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (385-100)/1152f, 222f/896f, (422-385)/1152f, (270-222)/896f, false, "");
        ObjetoEscenario obPergamino = new ObjetoEscenario("Pergamino", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (625-170)/1152f, 779f/896f, (742-625)/1152f, (823-779)/896f, false, "");
        ObjetoEscenario obLupa = new ObjetoEscenario("Lupa", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (707-186)/1152f, 839f/896f, (806-707)/1152f, (872-839)/896f, false, "");
        ObjetoEscenario obCopaRota = new ObjetoEscenario("Copa Rota", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (761-195)/1152f, 549f/896f, (791-761)/1152f, (607-549)/896f, false, "");
        ObjetoEscenario obEstatua2 = new ObjetoEscenario("Estatua", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (826-213)/1152f, 499f/896f, (867-826)/1152f, (639-499)/896f, false, "");

        storageRoom.agregarObjetoCola(obLampara);
        storageRoom.agregarObjetoCola(obSpray);
        storageRoom.agregarObjetoCola(obTelescopio);
        storageRoom.agregarObjetoCola(obBotella);
        storageRoom.agregarObjetoCola(obCopaRota);
        storageRoom.agregarObjetoCola(obEsfera);
        storageRoom.agregarObjetoCola(obBuho);
        storageRoom.agregarObjetoCola(obEstatua2);
        storageRoom.agregarObjetoCola(obPergamino);
        storageRoom.agregarObjetoCola(obLupa);

        miniJuegos.add(storageRoom);
    }






    //METODOS PARA CREAR, GUARDAR Y CARGAR PARTIDA
    
    private static final String CARPETA_GUARDADOS = "partidas_guardadas/";
    private static final String PREFIJO_ARCHIVO = "partida_";
    private static final String EXTENSION = ".dat";

    /**
     * MÉTODO 1: GUARDAR LA PARTIDA ACTUAL
     * Este método guarda TODO el estado del juego en un archivo.
     * Se llamará cuando el jugador presione "Guardar Partida".
     */
    public boolean guardarPartida() {
        // 1. Verificar que haya una partida para guardar
        if (partidaActual == null) {
            System.out.println("Error: No hay partida activa para guardar.");
            return false; // No se pudo guardar
        }
        
        // 2. Obtener el ID de la partida (será 1, 2 o 3)
        String idPartida = partidaActual.getIdPartida();
        
        // 3. Determinar en qué slot guardar (1, 2 o 3)
        int numeroSlot = 1; // Por defecto slot 1
        try {
            numeroSlot = Integer.parseInt(idPartida);
        } catch (NumberFormatException e) {
            // Si el ID no es número, usamos slot 1
            numeroSlot = 1;
        }
        
        // 4. Crear la carpeta si no existe
        File carpeta = new File(CARPETA_GUARDADOS);
        if (!carpeta.exists()) {
            boolean carpetaCreada = carpeta.mkdirs();
            if (!carpetaCreada) {
                System.out.println("Error: No se pudo crear la carpeta de guardados.");
                return false;
            }
        }
        
        // 5. Crear el nombre del archivo (ej: "partidas_guardadas/partida_1.dat")
        String rutaArchivo = CARPETA_GUARDADOS + PREFIJO_ARCHIVO + numeroSlot + EXTENSION;
        
        try {
            // 6. Abrir el archivo para escritura ("rw" = lectura y escritura)
            RandomAccessFile archivo = new RandomAccessFile(rutaArchivo, "rw");
            
            // 7. Convertir la partida completa a bytes
            //    ¡Esto guarda AUTOMÁTICAMENTE todo: jugador, diario, maletín, escenarios!
            byte[] bytesPartida = Convert.toBytes(partidaActual);
            
            // 8. Escribir PRIMERO el tamaño (cuántos bytes ocupa la partida)
            archivo.writeInt(bytesPartida.length);
            
            // 9. Escribir los bytes de la partida
            archivo.write(bytesPartida);
            
            // 10. Cerrar el archivo
            archivo.close();
            
            System.out.println("Partida guardada exitosamente en: " + rutaArchivo);
            return true; // ¡Éxito!
            
        } catch (Exception error) {
            System.out.println("Error al guardar la partida: " + error.getMessage());
            return false; // Falló
        }
    }
    
    /**
     * MÉTODO 2: CARGAR UNA PARTIDA DESDE UN SLOT
     * Este método recupera TODO el estado guardado.
     * Se llamará cuando el jugador seleccione "Cargar Partida" y elija un slot.
     */
    public Partida cargarPartida(int numeroSlot) {
        // 1. Verificar que el slot sea válido (1, 2 o 3)
        if (numeroSlot < 1 || numeroSlot > 3) {
            System.out.println("Error: El número de slot debe ser 1, 2 o 3.");
            return null;
        }
        
        // 2. Crear el nombre del archivo a cargar
        String rutaArchivo = CARPETA_GUARDADOS + PREFIJO_ARCHIVO + numeroSlot + EXTENSION;
        
        // 3. Verificar si el archivo existe
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.out.println("No hay partida guardada en el slot " + numeroSlot);
            return null;
        }
        
        try {
            // 4. Abrir el archivo para lectura ("r" = solo lectura)
            RandomAccessFile archivoAcceso = new RandomAccessFile(rutaArchivo, "r");
            
            // 5. Leer PRIMERO el tamaño (cuántos bytes debemos leer)
            int tamañoDatos = archivoAcceso.readInt();
            
            // 6. Crear un array de bytes del tamaño correcto
            byte[] datosPartida = new byte[tamañoDatos];
            
            // 7. Leer TODOS los bytes del archivo
            archivoAcceso.readFully(datosPartida);
            
            // 8. Cerrar el archivo
            archivoAcceso.close();
            
            // 9. Convertir los bytes de vuelta a un objeto Partida
            //    ¡Esto recupera AUTOMÁTICAMENTE todo: jugador, diario, maletín, escenarios!
            Partida partidaCargada = (Partida) Convert.toObject(datosPartida);
            
            System.out.println("Partida cargada exitosamente del slot " + numeroSlot);
            
            // 10. IMPORTANTE: Reparar las referencias después de cargar
            repararReferenciasDespuesDeCargar(partidaCargada);
            
            return partidaCargada; // ¡Devolvemos la partida cargada!
            
        } catch (Exception error) {
            System.out.println("Error al cargar la partida: " + error.getMessage());
            return null; // Falló
        }
    }
    
    /**
     * MÉTODO 3: REPARAR REFERENCIAS DESPUÉS DE CARGAR
     * Cuando cargamos una partida, algunos objetos necesitan "reconectarse".
     * Esto soluciona problemas comunes después de cargar.
     */
    private void repararReferenciasDespuesDeCargar(Partida partida) {
        if (partida == null) return;
        
        // Obtener el jugador de la partida cargada
        Jugador jugador = partida.getJugador();
        
        // Si el jugador tiene un escenario actual...
        if (jugador.getEscenarioActual() != null) {
            // Obtener el nombre de ese escenario
            String nombreEscenarioActual = jugador.getEscenarioActual().getNombre();
            
            // Buscar ese escenario en la lista de escenarios de la partida
            for (Escenario escenario : partida.getEscenarios()) {
                if (escenario.getNombre().equals(nombreEscenarioActual)) {
                    // ¡Encontrado! Actualizar la referencia
                    jugador.setEscenarioActual(escenario);
                    break; // Salir del bucle
                }
            }
        }
    }
    
    /**
     * MÉTODO 4: VER SI HAY PARTIDA GUARDADA
     * Para saber si un botón de "Cargar" debe estar activo o no.
     */
    public boolean existePartidaGuardada(int numeroSlot) {
        if (numeroSlot < 1 || numeroSlot > 3) {
            return false;
        }
        
        String rutaArchivo = CARPETA_GUARDADOS + PREFIJO_ARCHIVO + numeroSlot + EXTENSION;
        File archivo = new File(rutaArchivo);
        
        // Devuelve true si el archivo existe y no está vacío
        return archivo.exists() && archivo.length() > 0;
    }
    
    /**
     * MÉTODO 5: OBTENER INFORMACIÓN PARA MOSTRAR
     * Para poner texto descriptivo en los botones de "Cargar Partida".
     */
    public String obtenerInfoPartida(int numeroSlot) {
        // Si no hay partida guardada
        if (!existePartidaGuardada(numeroSlot)) {
            return "Slot " + numeroSlot + ": Libre";
        }
        
        try {
            // Cargar la partida temporalmente para ver su información
            Partida partida = cargarPartida(numeroSlot);
            
            if (partida == null) {
                return "Slot " + numeroSlot + ": Error";
            }
            
            Jugador jugador = partida.getJugador();
            String nombreJugador = jugador.getNombre();
            
            // Si no tiene nombre, poner uno por defecto
            if (nombreJugador == null || nombreJugador.isEmpty()) {
                nombreJugador = "Jugador";
            }
            
            // Contar objetos en el maletín
            int cantidadObjetos = jugador.getMaletin().size();
            
            // Formatear la información
            String informacion = "Slot " + numeroSlot + ": " + nombreJugador + 
                               " - " + cantidadObjetos + " objetos";
            
            return informacion;
            
        } catch (Exception e) {
            return "Slot " + numeroSlot + ": Error";
        }
    }
    /**
 * MÉTODO 6: PREPARAR Y GUARDAR NUEVA PARTIDA
 * Se llama JUSTO DESPUÉS de crearNuevaPartida() y ANTES de empezar a jugar.
 * Guarda el estado inicial vacío de la partida.
 */
public boolean prepararNuevaPartida(String idPartida, String nombreJugador) {
    // 1. Crear la partida nueva (ya lo haces en NuevaPartida.java)
    boolean creada = crearNuevaPartida(idPartida, nombreJugador);
    
    if (!creada) {
        System.out.println("Error: No se pudo crear la nueva partida");
        return false;
    }
    
    // 2. Configurar estado inicial IMPORTANTE para tu juego
    // Esto depende de cómo inicie tu juego:
    
    // Ejemplo 1: Establecer escenario inicial
    if (partidaActual != null && partidaActual.getJugador() != null) {
        // Buscar el escenario "Entrada" o el inicial
        for (Escenario escenario : partidaActual.getEscenarios()) {
            if (escenario.getNombre().equals("Entrada")) {
                partidaActual.getJugador().setEscenarioActual(escenario);
                break;
            }
        }
    }
    
    // Ejemplo 2: Inicializar el diario con primera entrada
    if (partidaActual != null && partidaActual.getJugador() != null) {
        partidaActual.getJugador().getDiario().agregarDialogoImportante(
            "Sistema", 
            "Caso iniciado: " + java.time.LocalDate.now()
        );
    }
    
    // 3. Guardar el estado inicial
    boolean guardado = guardarPartida();
    
    if (guardado) {
        System.out.println("Nueva partida preparada y guardada: " + idPartida);
    } else {
        System.out.println("Error: Nueva partida creada pero no se pudo guardar");
    }
    
    return guardado;
}

}

