package Logica;

import java.io.*;
import java.util.*;
import javax.swing.*;

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


    public boolean existeArchivoGuardado(String id) {
        File archivo = new File("partidas_guardadas", "partida" + id + ".sav");
        return archivo.exists();
    }

    public boolean eliminarArchivoGuardado(String id) {
        boolean resultado = false;
        try {
            File archivo = new File("partidas_guardadas", "partida" + id + ".sav");
            resultado = archivo.exists() && archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
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
        // Método legacy sin parámetros. No hace nada por ahora.
    }

    /**
     * Guarda la `partidaActual` en disco usando su `idPartida` como slot.
     * Se crea/usa la carpeta `partidas_guardadas/` y el archivo `partida<ID>.sav`.
     * Devuelve true si se guardó correctamente.
     */
    public boolean guardarPartida()
    {
        if (partidaActual == null) {
            System.err.println("No hay partida cargada para guardar.");
            return false;
        }

        String id = partidaActual.getIdPartida();
        if (id == null || id.trim().isEmpty()) {
            System.err.println("La partida no tiene ID. No se puede guardar.");
            return false;
        }

        try {
            File dir = new File("partidas_guardadas");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, "partida" + id + ".sav");

            // Serializamos una copia (clone) para evitar inconsistencias durante el guardado
            Partida copia = partidaActual.clone();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(copia);
                oos.flush();
            }

            // Actualizar lista en memoria: reemplazar o agregar la partida guardada
            Iterator<Partida> it = partidas.iterator();
            while (it.hasNext()) {
                Partida p = it.next();
                if (p.getIdPartida().equals(id)) {
                    it.remove();
                    break;
                }
            }
            partidas.add(copia);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Carga la partida del slot indicado (1,2,3...) leyendo `partidas_guardadas/partida<slot>.sav`.
     * Si se carga correctamente, se añade/actualiza en `partidas` y se establece como `partidaActual` (clonada).
     * Devuelve true si la carga fue exitosa.
     */
    public boolean cargarPartida(int slot)
    {
        try {
            File file = new File("partidas_guardadas", "partida" + slot + ".sav");
            if (!file.exists()) {
                System.err.println("Archivo de partida no encontrado: " + file.getPath());
                return false;
            }

            Partida cargada;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                cargada = (Partida) ois.readObject();
            }

            if (cargada == null) {
                return false;
            }

            // Reemplazar o agregar en la lista de partidas
            Iterator<Partida> it = partidas.iterator();
            while (it.hasNext()) {
                Partida p = it.next();
                if (p.getIdPartida().equals(cargada.getIdPartida())) {
                    it.remove();
                    break;
                }
            }
            partidas.add(cargada);

            // Cargar como partida actual (clon para evitar referencias compartidas)
            setPartidaActual(cargada.clone());

            System.out.println("Partida cargada correctamente desde slot " + slot + ": " + cargada.getIdPartida());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<MiniJuego> getMiniJuegos() {        return miniJuegos;
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


        ImageIcon camerasImage = new ImageIcon("DatosAuxiliares/Minijuego/Sala de Cámaras.png");
        MiniJuego camerasRoom = new MiniJuego("Sala de Cámaras", camerasImage);

        ObjetoEscenario obLibro = new ObjetoEscenario("Libro", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (65-30+5)/1152f, (532-25)/765f, (166-65-5-25)/1152f, (561-532)/765f, false, "");
        ObjetoEscenario obCigarrillos = new ObjetoEscenario("Cigarrillos", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (943-305)/1152f, (748-32)/765f, (982-943)/1152f, (766-748)/765f, false, "");
        ObjetoEscenario obLlave = new ObjetoEscenario("Llave", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (893-305-2)/1152f, (620-25-2)/765f, (933-893+4)/1152f, (629-620+4)/765f, false, "");
        ObjetoEscenario obBoligrafo = new ObjetoEscenario("Bolígrafo", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (469-160-3)/1152f, (620-25-3)/765f, (512-469+6)/1152f, (627-620+6)/765f, false, "");
        ObjetoEscenario obCuadro = new ObjetoEscenario("Cuadro", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (46-25)/1152f, (106-25)/765f, (117-46)/1152f, (243-106)/765f, false, "");
        ObjetoEscenario obGrabador = new ObjetoEscenario("Grabador de Audio", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (861-280-2)/1152f, (571-25-2)/765f, (883-861+4)/1152f, (582-571+4)/765f, false, "");
        ObjetoEscenario obEstatua = new ObjetoEscenario("Estatua", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (640-210)/1152f, (171-5)/765f, (667-640)/1152f, (244-171)/765f, false, "");
        ObjetoEscenario obObraArte = new ObjetoEscenario("Obra de Arte", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (810-270)/1152f, (437-15)/765f, (845-810)/1152f, (505-437)/765f, false, "");
        ObjetoEscenario obGafas = new ObjetoEscenario("Gafas", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (373-120)/1152f, (561-25)/765f, (415-373)/1152f, (577-561)/765f, false, "");
        ObjetoEscenario obAuriculares = new ObjetoEscenario("Auriculares", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (613-205)/1152f, (543-25)/765f, (676-613)/1152f, (613-543)/765f, false, "");
        ObjetoEscenario obGafas2 = new ObjetoEscenario("Gafas 2", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (122-32)/1280f, (569-3)/800f, (175-122)/1280f, (580-569+3)/800f, false, "");
        ObjetoEscenario obVasos = new ObjetoEscenario("Vasos", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (277-70)/1280f, 664f/800f, (322-277)/1280f, (717-664)/800f, false, "");
        ObjetoEscenario obPlano = new ObjetoEscenario("Plano", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (119-75)/1280f, 732f/800f, (317-119)/1280f, (785-732)/800f, false, "");
        ObjetoEscenario obExtintor = new ObjetoEscenario("Extintor", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (77-20-2)/1280f, (410-2)/800f, (92-77+4)/1280f, (464-410+4)/800f, false, "");
        ObjetoEscenario obMochila = new ObjetoEscenario("Mochila", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (1025-262)/1280f, 611f/800f, (1116-1025)/1280f, (715-611)/800f, false, "");
        ObjetoEscenario obRetrato = new ObjetoEscenario("Retrato", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (14-7)/1280f, 548f/800f, (50-14)/1280f, (594-548)/800f, false, "");
        ObjetoEscenario obZapato = new ObjetoEscenario("Zapato", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (523-135)/1280f, 751f/800f, (585-523)/1280f, (775-751)/800f, false, "");
        ObjetoEscenario obZapato2 = new ObjetoEscenario("Zapato 2", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (684-175)/1280f, 744f/800f, (738-684)/1280f, (768-744)/800f, false, "");
        ObjetoEscenario obHuellas = new ObjetoEscenario("Huellas", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (330-90)/1280f, 196f/800f, (380-330)/1280f, (233-196)/800f, false, "");

        camerasRoom.agregarObjetoCola(obLibro);
        camerasRoom.agregarObjetoCola(obCigarrillos);
        camerasRoom.agregarObjetoCola(obLlave);
        camerasRoom.agregarObjetoCola(obBoligrafo);
        camerasRoom.agregarObjetoCola(obCuadro);
        camerasRoom.agregarObjetoCola(obGrabador);
        camerasRoom.agregarObjetoCola(obEstatua);
        camerasRoom.agregarObjetoCola(obObraArte);
        camerasRoom.agregarObjetoCola(obGafas);
        camerasRoom.agregarObjetoCola(obAuriculares);
        camerasRoom.agregarObjetoCola(obGafas2);
        camerasRoom.agregarObjetoCola(obVasos);
        camerasRoom.agregarObjetoCola(obPlano);
        camerasRoom.agregarObjetoCola(obExtintor);
        camerasRoom.agregarObjetoCola(obMochila);
        camerasRoom.agregarObjetoCola(obRetrato);
        camerasRoom.agregarObjetoCola(obZapato);
        camerasRoom.agregarObjetoCola(obZapato2);
        camerasRoom.agregarObjetoCola(obHuellas);

        miniJuegos.add(camerasRoom);


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
        ObjetoEscenario obHoja = new ObjetoEscenario("Hoja de papel", true, new ImageIcon("DatosAuxiliares/Objetos/Hoja.png"), (621-160)/1152f, 578f/765f, (688-621)/1152f, (607-578)/765f, true, "Contiene algunos escritos en latin.");
        ObjetoEscenario obCepillo = new ObjetoEscenario("Cepillo de Dientes", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (555-125)/1152f, 322f/765f, (577-555)/1152f, (355-322)/765f, false, "");
        ObjetoEscenario obLata3 = new ObjetoEscenario("Lata de Refresco 3", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (569-145)/1152f, 574f/765f, (588-569)/1152f, (606-574)/765f, false, "");
        ObjetoEscenario obCaramelo = new ObjetoEscenario("Caramelo", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (305-75)/1152f, 691f/765f, (338-305)/1152f, (715-691)/765f, false, "");
        ObjetoEscenario obMochilaVerde = new ObjetoEscenario("Mochila Verde", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (91-42)/1152f, 527f/765f, (181-91)/1152f, (656-527)/765f, false, "");
        ObjetoEscenario obToallaRoja = new ObjetoEscenario("Toalla Roja", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (307-80)/1152f, 389f/765f, (356-307)/1152f, (454-389)/765f, false, "");
        ObjetoEscenario obToallaBlanca = new ObjetoEscenario("Toalla Blanca", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (800-205)/1152f, 112f/765f, (836-800)/1152f, (239-112)/765f, false, "");

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
        bathSecondFloor.agregarObjetoCola(obLata3);
        bathSecondFloor.agregarObjetoCola(obCaramelo);
        bathSecondFloor.agregarObjetoCola(obMochilaVerde);
        bathSecondFloor.agregarObjetoCola(obToallaRoja);
        bathSecondFloor.agregarObjetoCola(obToallaBlanca);

        miniJuegos.add(bathSecondFloor);


        ImageIcon storageImage = new ImageIcon("DatosAuxiliares/Minijuego/Almacén.png");
        MiniJuego storageRoom = new MiniJuego("Almacén", storageImage);

        ObjetoEscenario obLampara = new ObjetoEscenario("Lámpara", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (95-30)/1152f, 699f/896f, (140-95)/1152f, (802-699)/896f, false, "");
        ObjetoEscenario obSpray = new ObjetoEscenario("Spray", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (882-227)/1152f, 809f/896f, (916-882)/1152f, (879-809)/896f, false, "");
        ObjetoEscenario obBotella = new ObjetoEscenario("Botella", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (356-90)/1152f, 697f/896f, (382-356)/1152f, (791-697)/896f, false, "");
        ObjetoEscenario obEsfera = new ObjetoEscenario("Esfera del Mundo", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (248-73)/1152f, 592f/896f, (317-248)/1152f, (660-592)/896f, false, "");
        ObjetoEscenario obTelescopio = new ObjetoEscenario("Telescopio", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (366-103)/1152f, 548f/896f, (478-366)/1152f, (602-548)/896f, false, "");
        ObjetoEscenario obBuho = new ObjetoEscenario("Búho de Madera", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (385-100)/1152f, 222f/896f, (422-385)/1152f, (270-222)/896f, false, "");
        ObjetoEscenario obPergamino = new ObjetoEscenario("Pergamino", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (625-170)/1152f, 779f/896f, (742-625)/1152f, (823-779)/896f, false, "");
        ObjetoEscenario obLupa = new ObjetoEscenario("Lupa", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (707-186)/1152f, 839f/896f, (806-707)/1152f, (872-839)/896f, false, "");
        ObjetoEscenario obCopaRota = new ObjetoEscenario("Copa Rota", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (761-195)/1152f, 549f/896f, (791-761)/1152f, (607-549)/896f, false, "");
        ObjetoEscenario obEstatua2 = new ObjetoEscenario("Estatua", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (826-213)/1152f, 499f/896f, (867-826)/1152f, (639-499)/896f, false, "");
        ObjetoEscenario obMaleta = new ObjetoEscenario("Maleta", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (504-125)/1152f, 549f/896f, (619-504-25)/1152f, (600-549)/896f, false, "");
        ObjetoEscenario obMaceta = new ObjetoEscenario("Maceta", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (477-122)/1152f, 454f/896f, (508-477)/1152f, (495-454)/896f, false, "");
        ObjetoEscenario obPeriodico = new ObjetoEscenario("Periodico", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (394-100)/1152f, 831f/896f, (500-394-20)/1152f, (879-831)/896f, false, "");
        ObjetoEscenario obEscoba = new ObjetoEscenario("Escoba", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (970-250)/1152f, 647f/896f, (1022-970)/1152f, (744-272)*0.25f/896f, false, "");
        ObjetoEscenario obVasijaRota = new ObjetoEscenario("VasijaRota", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (937-238)/1152f, 778f/896f, (994-937)/1152f, (834-778)/896f, false, "");
        ObjetoEscenario obLibros = new ObjetoEscenario("Libro", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (842-215)/1152f, 699f/896f, (900-842)/1152f, (730-699)/896f, true, "");
        ObjetoEscenario obGuante = new ObjetoEscenario("Guante", true, new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), (355-95)/1152f, 875f/896f, (408-355)/1152f, (889-875+5)/896f, false, "");

        storageRoom.agregarObjetoCola(obLampara);
        storageRoom.agregarObjetoCola(obSpray);
        storageRoom.agregarObjetoCola(obBotella);
        storageRoom.agregarObjetoCola(obEsfera);
        storageRoom.agregarObjetoCola(obTelescopio);
        storageRoom.agregarObjetoCola(obBuho);
        storageRoom.agregarObjetoCola(obPergamino);
        storageRoom.agregarObjetoCola(obLupa);
        storageRoom.agregarObjetoCola(obCopaRota);
        storageRoom.agregarObjetoCola(obEstatua2);
        storageRoom.agregarObjetoCola(obMaleta);
        storageRoom.agregarObjetoCola(obMaceta);
        storageRoom.agregarObjetoCola(obPeriodico);
        storageRoom.agregarObjetoCola(obEscoba);
        storageRoom.agregarObjetoCola(obVasijaRota);
        storageRoom.agregarObjetoCola(obLibros);
        storageRoom.agregarObjetoCola(obGuante);

        miniJuegos.add(storageRoom);
    }
    
    
}

