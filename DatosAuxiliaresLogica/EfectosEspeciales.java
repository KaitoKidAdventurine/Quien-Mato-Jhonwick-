package DatosAuxiliaresLogica;

import javax.sound.sampled.AudioSystem;
import java.io.File;
/*
public class EfectosEspeciales
{


        private Clip clipMusic;
        private Thread hiloReproduccion;
        private ArrayList<Cancion> efectosSonido;
        private int indiceActual;
        private boolean enReproduccion;
        private float volumen = 0.7f;

        public EfectosEspeciales() {
            this.efectosSonido = new ArrayList<Cancion>();
            this.indiceActual = 0;
            this.enReproduccion = false;

            // Cargar efecto especual en específica
            File archivoMusica = new File("Musica/Efectos de Sonido/Lluvia.wav");
            if (archivoMusica.exists()) {
                Cancion cancion = new Cancion("Lluvia", archivoMusica);
                efectosSonido.add(cancion);

            }

            else {
                System.err.println("No se encontró el archivo de música: " + archivoMusica.getAbsolutePath());
            }
        }

        // Getters

        public Clip getClipMusic() {
            return clipMusic;
        }

        public void setClipMusic(Clip clipMusic) {
            this.clipMusic = clipMusic;
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

        // Volumen
        public float getVolumen() {
            return volumen;
        }

        public void setVolumen(float nuevoVolumen) {
            // Asegurar que esté entre 0.0 y 1.0
            this.volumen = Math.max(0.0f, Math.min(1.0f, nuevoVolumen));
            aplicarVolumen();
        }

        public void subirVolumen() {
            setVolumen(volumen + 0.1f);
        }

        public void bajarVolumen() {
            setVolumen(volumen - 0.1f);
        }

        private void aplicarVolumen() {
            if (clipMusic != null && clipMusic.isOpen()) {
                try {
                    FloatControl gainControl = (FloatControl) clipMusic.getControl(FloatControl.Type.MASTER_GAIN);

                    // Convertir volumen (0.0-1.0) a decibeles (-80.0 a 6.0)
                    float min = gainControl.getMinimum(); // -80.0 (silencio)
                    float max = gainControl.getMaximum(); // 6.0 (máximo)
                    float rango = max - min;
                    float gain = (rango * volumen) + min;

                    gainControl.setValue(gain);
                } catch (Exception e) {
                    System.err.println("No se pudo ajustar el volumen: " + e.getMessage());
                }
            }
        }

        public void iniciarEfecto() {
            enReproduccion = true;
            try {
                // Se usa el Thread para dejar reproduciendo la musica en segundo plano
                // y asi seguir con el programa.
                hiloReproduccion = new Thread(() -> {
                    try {
                        while (enReproduccion) {
                            // Guardo el valor del primer archivo d musica de la lista.
                            Cancion c = efectosSonido.get(indiceActual);

                            // Convierto el MP3 en un AudioInput.... es para regular el volumen.
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(c.getFile());

                            // Ahora cojo el Audio... y lo uso para cargar la cancion en el programa
                            clipMusic = AudioSystem.getClip();
                            clipMusic.open(audioStream);
                            aplicarVolumen();

                            // Reproducir en bucle se pone Clip.LOOP_CONTINUOUSLY
                            clipMusic.loop(2);
                            clipMusic.close();
                            audioStream.close();
                        }
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
                detenerEfecto();
                try {
                    Thread.sleep(100); // Pequeña pausa
                } catch (InterruptedException e) {
                }

                indiceActual = i;
                iniciarMusica();

            } catch (Exception e) {
                System.err.println("Error iniciando música: " + e.getMessage());
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

        public void cambiarMusicaSiguiente() {
            if (indiceActual == canciones.size() - 1) {
                cambiarMusicaIndice(0);
            }
            cambiarMusicaIndice(indiceActual + 1);
        }

        public void detenerCancion() {
            enReproduccion = false;

            if (clipMusic != null && clipMusic.isRunning()) {
                clipMusic.stop();
            }
            if (clipMusic != null && clipMusic.isOpen()) {
                clipMusic.close();
            }
            if (hiloReproduccion != null && hiloReproduccion.isAlive()) {
                hiloReproduccion.interrupt();
            }
        }


}

 */
