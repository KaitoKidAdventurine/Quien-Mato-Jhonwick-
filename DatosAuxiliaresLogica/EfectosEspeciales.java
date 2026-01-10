package DatosAuxiliaresLogica;

import java.io.File;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Timer;

import javazoom.jl.player.Player;


public class EfectosEspeciales
{
    private static EfectosEspeciales instancia;
    private Player reproductorMP3;
    private Thread hiloReproduccion;
    private ArrayList<Cancion> efectosSonido;
    private int indiceActual;
    private boolean enReproduccion;
    private int volumenSistema = 30;



    public static EfectosEspeciales getInstancia() {
        if (instancia == null) {
            instancia = new EfectosEspeciales();
        }
        return instancia;
    }


        public EfectosEspeciales() {
            this.efectosSonido = new ArrayList<Cancion>();
            this.indiceActual = 0;
            this.enReproduccion = true;

            // Cargar efecto especual en específica
            File archivoMusica = new File("Musica/Efectos de Sonido/Lluvia.mp3");
            File archivoMusicaDos = new File("Musica/Efectos de Sonido/Telefono Sonando.mp3");
            File archivoMusicaTre = new File("Musica/Efectos de Sonido/Pasos.mp3");
            File archivo = new File("Musica/Efectos de Sonido/Abrir Diario.mp3");
            File archivoDos = new File("Musica/Efectos de Sonido/Escribir Diario.mp3");
            File archivoTres = new File("Musica/Efectos de Sonido/Boton.mp3");
            File musicacuatro = new File("Musica/Efectos de Sonido/AbrirTelefono.mp3");
            File musicacinco = new File("Musica/Efectos de Sonido/Mochila.mp3");
            File musicaseis = new File("Musica/Efectos de Sonido/SaliendoDelCarro.mp3");
            File efec = new File("Musica/Efectos de Sonido/NuevoTelefonoAlAbrir.mp3");
            File efect = new File("Musica/Efectos de Sonido/objetoEncontrado.mp3");
            File efectt = new File("Musica/Efectos de Sonido/colgarTelefono.mp3");
            File efecto = new File("Musica/Efectos de Sonido/susuros.mp3");
            File llam = new File("Musica/Efectos de Sonido/llamadaTelefono.mp3");
            File error = new File("Musica/Efectos de Sonido/error.mp3");

            if (archivoMusica.exists())
            {
                Cancion cancion = new Cancion("Lluvia", archivoMusica);
                Cancion cancion1 = new Cancion("Telefono Sonando", archivoMusicaDos);
                Cancion cancion2 = new Cancion("Pasos", archivoMusicaTre);
                Cancion cancion3 = new Cancion("Abrir Diario", archivo);
                Cancion cancion4 = new Cancion("Escribir Diario", archivoDos);
                Cancion cancion5 = new Cancion("Boton", archivoTres);
                Cancion cancion6 = new Cancion("AbrirTelefono", musicacuatro);
                Cancion cancion7 = new Cancion("Mochila", musicacinco);
                Cancion cancion8 = new Cancion("SaliendoDelCarro", musicaseis);
                Cancion cancion9 = new Cancion("NuevoTelefonoAlAbrir", efec);
                Cancion cancion10 = new Cancion("objetoEncontrado", efect);
                Cancion cancion11 = new Cancion("colgarTelefono", efectt);
                Cancion cancion12 = new Cancion("susuros", efecto);
                Cancion cancion13 = new Cancion("llamada", llam);
                Cancion cancion14 = new Cancion("Error", error);

                efectosSonido.add(cancion);
                efectosSonido.add(cancion1);
                efectosSonido.add(cancion2);
                efectosSonido.add(cancion3);
                efectosSonido.add(cancion4);
                efectosSonido.add(cancion5);
                efectosSonido.add(cancion6);
                efectosSonido.add(cancion7);
                efectosSonido.add(cancion8);
                efectosSonido.add(cancion9);
                efectosSonido.add(cancion10);
                efectosSonido.add(cancion11);
                efectosSonido.add(cancion12);
                efectosSonido.add(cancion13);
                efectosSonido.add(cancion14);
            }

            else {
                System.err.println("No se encontró el archivo de música: " + archivoMusica.getAbsolutePath());
            }
        }

    // Getters
    public Player getReproductorMP3() {
        return reproductorMP3;
    }

    public Thread getHiloReproduccion() {
        return hiloReproduccion;
    }

    public ArrayList<Cancion> getEfectosSonido() {
        return efectosSonido;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public boolean isEnReproduccion() {
        return enReproduccion;
    }


    // Setters
    public void setReproductorMP3(Player reproductorMP3) {
        this.reproductorMP3 = reproductorMP3;
    }

    public void setHiloReproduccion(Thread hiloReproduccion) {
        this.hiloReproduccion = hiloReproduccion;
    }

    public void setEfectosSonido(ArrayList<Cancion> efectosSonido) {
        this.efectosSonido = efectosSonido;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

    public void setEnReproduccion(boolean enReproduccion) {
        this.enReproduccion = enReproduccion;
    }



    // Metodos
    public void iniciarEfecto() {
        try{
            enReproduccion= true;
            // Detener hilo anterior si existe
            if (hiloReproduccion != null && hiloReproduccion.isAlive()) {
                hiloReproduccion.interrupt();
            }

            hiloReproduccion = new Thread(() -> {
                try {
                    while (enReproduccion && !Thread.currentThread().isInterrupted()) {
                        Cancion c = efectosSonido.get(indiceActual);
                        FileInputStream stream = new FileInputStream(c.getFile());
                        reproductorMP3 = new Player(stream);
                        reproductorMP3.play();
                        stream.close();
                    }
                } catch (Exception e) {
                    if (enReproduccion) {
                        System.err.println("Error reproduciendo: " + e.getMessage());
                    }
                }
            });
            hiloReproduccion.start();
            System.out.println("Música iniciada");

        } catch (Exception e) {
            System.err.println("Error iniciando música: " + e.getMessage());
        }
    }

        // Metodos de para buscar Efecto.

        // Se cambia a la cancion que se quiera en especifico por indice
        public void cambiarEfectoIndice(int i) {
            try {
                try {
                    // Detener el reproductor
                    if (reproductorMP3 != null) {
                        reproductorMP3.close();
                    }

                    if (enReproduccion) {
                        indiceActual = i;

                        // Reiniciar la reproduccion
                        if (hiloReproduccion != null && hiloReproduccion.isAlive()) {
                            hiloReproduccion.interrupt();
                        }
                        iniciarEfecto();
                    }
                } catch (Exception e) {
                    System.err.println("Error cambiando música: " + e.getMessage());
                }
            }catch (Exception e) {
                System.err.println("Error cambiando música: " + e.getMessage());
            }
        }


    public void cambiarEfectoNombre(String nombre) {
        try {
            boolean encontrado = false;
            for (int i = 0; i < efectosSonido.size() && !encontrado; i++) {
                if (efectosSonido.get(i).getNombre().equals(nombre)) {
                    encontrado = true;
                    indiceActual = i;
                }
            }

            if (encontrado) {
                cambiarEfectoIndice(indiceActual);
            }

            else {
                throw new IllegalArgumentException("La canción '" + nombre + "' no fue encontrada");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public void cambiarEfectoNombreUsarSoloUnaVez(String nombre) {
        try {
            boolean encontrado = false;
            for (int i = 0; i < efectosSonido.size() && !encontrado; i++) {
                if (efectosSonido.get(i).getNombre().equals(nombre)) {
                    encontrado = true;
                    indiceActual = i;
                }
            }

            if (encontrado)
            {
                sonarUnaVez();
            }

            else {
                throw new IllegalArgumentException("La canción '" + nombre + "' no fue encontrada");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void sonarUnaVez()
    {
        try{
            enReproduccion= true;
            // Detener hilo anterior si existe
            if (hiloReproduccion != null && hiloReproduccion.isAlive()) {
                hiloReproduccion.interrupt();
            }

            hiloReproduccion = new Thread(() -> {
                try {
                    Cancion c = efectosSonido.get(indiceActual);
                    FileInputStream stream = new FileInputStream(c.getFile());
                    reproductorMP3 = new Player(stream);
                    reproductorMP3.play();
                    stream.close();

                } catch (Exception e) {
                    if (enReproduccion) {
                        System.err.println("Error reproduciendo: " + e.getMessage());
                    }
                }
            });
            hiloReproduccion.start();
            System.out.println("Música iniciada");

        } catch (Exception e) {
            System.err.println("Error iniciando música: " + e.getMessage());
        }
    }




    // Cambiar Volumen del Efecto
    public void subirVolumen() {
        int nuevoVolumen = volumenSistema + 10;
        if (nuevoVolumen > 100) {
            nuevoVolumen = 100;
        }
        cambiarVolumen(nuevoVolumen / 100.0f);
        System.out.println(" Volumen: " + volumenSistema + "%");
    }

    public void cambiarVolumen(float vol) {
        int porcentaje = (int)(vol * 100);
        this.volumenSistema = Math.max(0, Math.min(100, porcentaje));
        ControlVolumen.setVolumenSistema(volumenSistema);
    }

    public void bajarVolumen() {
        int nuevoVolumen = volumenSistema - 10;
        if (nuevoVolumen < 0) {
            nuevoVolumen = 0;
        }
        cambiarVolumen(nuevoVolumen / 100.0f);
        System.out.println(" Volumen: " + volumenSistema + "%");
    }

    public void detenerSiEsNecesario()
    {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        if(e.getIndiceActual() != 0 && e.getHiloReproduccion().isAlive())
        {
            e.detenerEfecto();
        }
    }

    public void detenerEfecto()
    {
        // Detener la reproducción actual
        enReproduccion = false;

        // Detener el reproductor MP3
        if (reproductorMP3 != null) {
            reproductorMP3.close();
        }

        // Interrumpir el hilo de reproducción si está activo
        if (hiloReproduccion != null && hiloReproduccion.isAlive()) {
            hiloReproduccion.interrupt();
        }

        // Restablecer el booleano a true para futuras reproducciones
        enReproduccion = true;

        System.out.println("Efecto desactivado y booleano restablecido");
    }


    // Metodos para buscar rapido el sonido
    public void efectoDePasos()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Pasos");
    }

    public void efectoDeLluvia()
    {
        cambiarEfectoNombre("Lluvia");
    }

    public void efectoDeTelefonoResiviendoLlamda()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Telefono Sonando");
    }

    public void efectoDeAbrirDiario()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Abrir Diario");
    }

    public void efectoDeEscribirDiario()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Escribir Diario");
    }

    public void efectoDeBoton()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Boton");
    }

    public void efectoAbrirTelefono(){
        cambiarEfectoNombreUsarSoloUnaVez("NuevoTelefonoAlAbrir");
    }

    public void efectoDeBotonesTelefono()
    {
        cambiarEfectoNombreUsarSoloUnaVez("AbrirTelefono");
    }

    public void efectoAbrirMochila()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Mochila");
    }

    public void efectoSalirDelCarro()
    {
        cambiarEfectoNombreUsarSoloUnaVez("SaliendoDelCarro");
    }


    public void efectoObjetoEncontrado()
    {
        cambiarEfectoNombreUsarSoloUnaVez("objetoEncontrado");
    }

    public void efectoColgarTelefono()
    {
        cambiarEfectoNombreUsarSoloUnaVez("colgarTelefono");
    }

    public void efectoDeSusuros()
    {
    cambiarEfectoNombreUsarSoloUnaVez("susuros");
    }

    public void efectoLlamadaTelefono()
    {
        cambiarEfectoNombreUsarSoloUnaVez("llamada");
    }

    public void efectoError()
    {
        cambiarEfectoNombreUsarSoloUnaVez("Error");
    }




// Metodo para comprobar que funciona

    public void diagnosticar() {
        System.out.println("=== DIAGNÓSTICO EFECTOS ESPECIALES ===");
        System.out.println(" Efectos cargados: " + efectosSonido.size());
        for (int i = 0; i < efectosSonido.size(); i++) {
            Cancion efecto = efectosSonido.get(i);
            System.out.println("  " + i + ": " + efecto.getNombre() + " - Archivo: " + efecto.getFile().getAbsolutePath());
        }
        System.out.println(" En reproducción: " + enReproduccion);
        System.out.println(" Hilo activo: " + (hiloReproduccion != null && hiloReproduccion.isAlive()));
        System.out.println(" Índice actual: " + indiceActual);

        if (!efectosSonido.isEmpty() && indiceActual >= 0 && indiceActual < efectosSonido.size()) {
            File archivo = efectosSonido.get(indiceActual).getFile();
            System.out.println(" Archivo actual: " + archivo.getAbsolutePath());
            System.out.println(" Existe: " + archivo.exists());
            System.out.println(" Tamaño: " + archivo.length() + " bytes");
        }

        // Probar reproducción simple
        System.out.println(" Probando reproducción directa...");
        probarReproduccionDirecta();
    }

    private void probarReproduccionDirecta() {
        try {
            if (!efectosSonido.isEmpty() && indiceActual >= 0 && indiceActual < efectosSonido.size()) {
                FileInputStream stream = new FileInputStream(efectosSonido.get(indiceActual).getFile());
                Player playerTest = new Player(stream);

                new Thread(() -> {
                    try {
                        System.out.println(" INICIANDO PRUEBA DE EFECTO SONIDO...");
                        playerTest.play();
                        System.out.println("PRUEBA COMPLETADA");
                    } catch (Exception e) {
                        System.err.println("Error en prueba: " + e.getMessage());
                    }
                }).start();

                // Detiene el efecto después de 3 segundos
                new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                        playerTest.close();
                        System.out.println("Prueba de efecto detenida");
                    } catch (Exception e) {}
                }).start();
            }
        } catch (Exception e) {
            System.err.println(" No se pudo realizar la prueba: " + e.getMessage());
        }
    }



}






