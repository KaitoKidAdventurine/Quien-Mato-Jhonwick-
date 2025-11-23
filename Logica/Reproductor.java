package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import DatosAuxiliaresLogica.Cancion;
import javazoom.jl.player.Player;

public class Reproductor {

    private static Reproductor instancia;

    private Player reproductorMP3;
    private Thread hiloReproduccion;
    private ArrayList<Cancion> canciones;
    private int indiceActual;
    private boolean enReproduccion;
    private float volumen = 1.0f;
//  private long posicionActual = 0;
//  private long tiempoInicioReproduccion = 0;
// los atributos de arriba los usare para colocar la posicion exacta de una cancion o algo asi.

    private Reproductor()
    {
        this.canciones = new ArrayList<Cancion>();
        this.indiceActual = 0;
        this.enReproduccion = true;

        // Cargar canción específica
        File archivoMusica = new File("Musica/Canciones/CancMenu.mp3");
        if (archivoMusica.exists()) {
            Cancion cancion = new Cancion("CancMenu", archivoMusica);
            canciones.add(cancion);
        } else {
            System.err.println("No se encontró el archivo de música: " + archivoMusica.getAbsolutePath());
        }
    }


    public static Reproductor getInstancia() {
        if (instancia == null) {
            instancia = new Reproductor();
        }
        return instancia;
    }


    public static void reiniciarInstancia() {
        if (instancia != null) {
            instancia.detenerCancion();
        }
        instancia = new Reproductor();
    }


    public static boolean existeInstancia() {
        return instancia != null;
    }




    public void cambiarVolumen(float vol) {
        setVolumen(vol);
    }

    public void subirVolumen() {
        float nuevoVolumen = volumen + 0.1f;
        if (nuevoVolumen > 1.0f) {
            nuevoVolumen = 1.0f;
        }
        setVolumen(nuevoVolumen);
        aplicarVolumenActual();
        System.out.println(" Volumen: " + (int)(volumen * 100) + "%");
    }

    public void bajarVolumen() {
        float nuevoVolumen = volumen - 0.1f;
        if (nuevoVolumen < 0.0f) {
            nuevoVolumen = 0.0f;
        }
        setVolumen(nuevoVolumen);
        aplicarVolumenActual();
        System.out.println(" Volumen: " + (int)(volumen * 100) + "%");
    }

    public void setVolumen(float nuevoVolumen) {
        this.volumen = Math.max(0.0f, Math.min(1.0f, nuevoVolumen));
    }

    public float getVolumen() {
        return volumen;
    }

    public int getVolumenPorcentaje() {
        return (int)(volumen * 100);
    }

    // Getters
    public Player getReproductorMP3() {
        return reproductorMP3;
    }

    public Thread getHiloReproduccion() {
        return hiloReproduccion;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
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

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

    public void setEnReproduccion(boolean enReproduccion) {
        this.enReproduccion = enReproduccion;
    }


    public void iniciarMusica()
    {
        if(enReproduccion)
            try {
                // Se usa el Thread para dejar reproduciendo la musica en segundo plano
                // y asi seguir con el programa.
                hiloReproduccion = new Thread(() -> {
                    try {

                        Cancion c = canciones.get(indiceActual);

                        // Convierto el File en un FileInput... para poder despues convertirlo en un
                        // Player
                        FileInputStream stream = new FileInputStream(c.getFile());

                        VolumeControlledInputStream volumeStream = new VolumeControlledInputStream(stream);

                        reproductorMP3 = new Player(volumeStream);
                        reproductorMP3.play();

                    } catch (Exception e) {
                        System.err.println("Error reproduciendo: " + e.getMessage());
                    }
                });
                hiloReproduccion.start();
            } catch (Exception e) {
                System.err.println("Error iniciando música: " + e.getMessage());
            }
    }


    // Se cambia a la cancion que se quiera en especifico por indice
    public void cambiarMusicaIndice(int i) {
        try {
            // Detener la reproducción actual
            if (reproductorMP3 != null) {
                reproductorMP3.close();
            }

            if(enReproduccion)
            {
                Cancion c = canciones.get(i);
                indiceActual = i;
                FileInputStream fileStream = new FileInputStream(c.getFile());

                VolumeControlledInputStream volumeStream = new VolumeControlledInputStream(fileStream);
                reproductorMP3 = new Player(volumeStream);

                hiloReproduccion = new Thread(() -> {
                    try {
                        reproductorMP3.play();
                    } catch (Exception e) {
                        System.err.println("Error reproduciendo: , " +
                                "esto no deberia aparecer pero por si acaso linea " +
                                "201 de la reproductora" + e.getMessage());
                    }
                });

                hiloReproduccion.start();
            }
        } catch (Exception e) {
            System.err.println("Error iniciando música: Linea 209 de el codigo de la reproductora" + e.getMessage());
        }
    }


    public void cambiarMusicaNombre(String nombre) {
        try {
            boolean encontrado = false;
            for (int i = 0; i < canciones.size() && !encontrado; i++) {
                if (canciones.get(i).getNombre().equals(nombre)) {
                    encontrado = true;
                    indiceActual = i;
                }
            }

            if (encontrado) {
                cambiarMusicaIndice(indiceActual);
            }

            else {
                throw new IllegalArgumentException("La canción '" + nombre + "' no fue encontrada");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private class VolumeControlledInputStream extends FilterInputStream {
        public VolumeControlledInputStream(InputStream in) {
            super(in);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int bytesRead = super.read(b, off, len);


            if (bytesRead > 0) {
                for (int i = off; i < off + bytesRead - 1; i += 2) {
                    // Convertir 2 bytes a short (16-bit PCM)
                    short sample = (short) ((b[i] & 0xFF) | (b[i + 1] << 8));

                    // Aplicar volumen
                    int sampleConVolumen = (int) (sample * volumen);

                    // Limitar para evitar overflow
                    if (sampleConVolumen > 32767) sampleConVolumen = 32767;
                    if (sampleConVolumen < -32768) sampleConVolumen = -32768;

                    sample = (short) sampleConVolumen;

                    // Volver a escribir los bytes
                    b[i] = (byte) (sample & 0xFF);
                    b[i + 1] = (byte) ((sample >> 8) & 0xFF);
                }
            }
            return bytesRead;
        }
    }

    public void cambiarMusicaSiguiente() {
        if (indiceActual == canciones.size() - 1) {
            cambiarMusicaIndice(0);
        }
        cambiarMusicaIndice(indiceActual + 1);
    }
    public void aplicarVolumenActual() {
        // Simplemente reinicia la reproducción actual
        if (enReproduccion) {
            try {
                if (reproductorMP3 != null) {
                    reproductorMP3.close();
                }
                Thread.sleep(100);
                iniciarMusica();
            } catch (Exception e) {
                System.err.println("Error en aplicarVolumenActual: " + e.getMessage());
            }
        }
    }

    public void detenerCancion()
    {
        enReproduccion = false;

        if (reproductorMP3 != null)
        {
            reproductorMP3.close();
        }
        if (hiloReproduccion != null && hiloReproduccion.isAlive())
        {
            hiloReproduccion.interrupt();
        }
    }

    public void activarMusica()
    {
        enReproduccion = true;
        iniciarMusica();
    }

    public void desactivarMusica()
    {
        reproductorMP3.close();
        enReproduccion = false;
    }

    // prueba para ver si funciona: Lo dejare para futuras comprobaciones.
    public void diagnosticar() {
        System.out.println("=== DIAGNÓSTICO REPRODUCTOR ===");
        System.out.println("Volumen: " + getVolumenPorcentaje() + "%");
        System.out.println(" Canciones cargadas: " + canciones.size());
        System.out.println(" En reproducción: " + enReproduccion);
        System.out.println(" Hilo activo: " + (hiloReproduccion != null && hiloReproduccion.isAlive()));

        if (!canciones.isEmpty()) {
            File archivo = canciones.get(0).getFile();
            System.out.println("Archivo: " + archivo.getAbsolutePath());
            System.out.println("Existe: " + archivo.exists());
            System.out.println(" Tamaño: " + archivo.length() + " bytes");
        }

        // Probar reproducción simple
        System.out.println(" Probando reproducción directa...");
        probarReproduccionDirecta();
    }


    private void probarReproduccionDirecta() {
        try {
            if (!canciones.isEmpty()) {
                FileInputStream stream = new FileInputStream(canciones.get(0).getFile());
                Player playerTest = new Player(stream);

                new Thread(() -> {
                    try {
                        System.out.println(" INICIANDO PRUEBA DE SONIDO...");
                        playerTest.play();
                        System.out.println("PRUEBA COMPLETADA");
                    } catch (Exception e) {
                        System.err.println("Error en prueba: " + e.getMessage());
                    }
                }).start();

                // Detiene la musica  después de 3 segundos
                new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                        playerTest.close();
                        System.out.println("⏹Prueba detenida");
                    } catch (Exception e) {}
                }).start();
            }
        } catch (Exception e) {
            System.err.println(" No se pudo realizar la prueba: " + e.getMessage());
        }
    }
}