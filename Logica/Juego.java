package Logica;

public class Juego {
    private String titulo;
    private String version;

    public Juego(String titulo, String version) {
        this.titulo = titulo;
        this.version = version;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public void iniciarJuego() {}
    public void cargarPartida() {}
    public void guardarPartida() {}
}
