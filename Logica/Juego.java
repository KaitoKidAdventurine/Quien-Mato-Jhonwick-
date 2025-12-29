package Logica;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

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
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
        miniJuegos = new ArrayList<>();
        //esto se quita mas adelante
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
    public void guardarPartida()
    {

    }

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
        ImageIcon escCrimen = new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Escena del Crimen.png");
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
        ObjetoEscenario ob21 = new ObjetoEscenario("Taza de cafe", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.224F, 0.159F, 0.035F, 0.09F, false, "");
        ObjetoEscenario ob22 = new ObjetoEscenario("Cigarros", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.42F, 0.893F, 0.024F, 0.04F, false, "");
        ObjetoEscenario ob23 = new ObjetoEscenario("Cuchillo", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.437F, 0.605F, 0.069F, 0.12F, true, "Supuesta arma usada en la escena del crimen. A pesar de que contiene sangre de la victima, la causa de la muerte no coincide con el tipo de herida del cuchillo. Definitivamente no es el arma del crimen.");
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
    }
    public void crearDialogosActo1(){

        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon limpieza = new ImageIcon("DatosAuxiliares/Personajes/Conserje.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia.png");
        ImageIcon guia2 = new ImageIcon("DatosAuxiliares/Personajes/Guia 2.png");
        ImageIcon vagabundo = new ImageIcon("DatosAuxiliares/Personajes/Vagabundo.png");

        //Dialogo de la secretaria en la entrada
        //Breve Introduccion
        Dialogo d1 = new Dialogo("Señora, necesito hablar con usted nuevamente. Algunas cosas no me terminan de cerrar.", "DetectiveEntrada1",
                detective, true);
        Dialogo d2 = new Dialogo("¿Otra vez? Ya le dije todo lo que sabía. Pero adelante, pregunte lo que necesite.", "SecretariaEntrada1",
                secretaria, true);

        // Primera decisión
        List<String> opciones1 = new LinkedList<String>(Arrays.asList(
                "¿Qué tipo de relación tenía con el economista?",
                "¿Notó algo extraño en el comportamiento del guardia?",
                "¿Sabe si alguien más tenía acceso a su computadora?",
                "No quiero saber nada más"
        ));

        // Camino vacío: guardia
        Dialogo vacio1 = new Dialogo("¿El guardia? Siempre fue puntual y correcto. No hablaba mucho, pero hacía su trabajo.", "SecretariaEntrada1",
                secretaria, true);

        // Camino vacío: acceso a computadora
        Dialogo vacio2 = new Dialogo("No, su computadora era personal. Siempre la tenía bloqueada. Nunca compartía su contraseña con nadie.",
                "SecretariaEntrada1", secretaria, true);

        // Camino principal: relación
        Dialogo d3 = new Dialogo("Dijo que apenas lo conocía, pero su lenguaje corporal decía otra cosa. ¿Está segura de que no había nada más?",
                "DetectiveEntrada1", detective, true);
        Dialogo d4 = new Dialogo("No me gusta hablar de mi vida privada, detective. Pero si insiste...", "SecretariaEntrada1",
                secretaria, true);

        // Segunda decisión
        List<String> opciones2 = new LinkedList<>(Arrays.asList(
                "No es curiosidad, es relevante para la investigación",
                "¿Le tenía afecto?",
                "No quiero saber nada más"
        ));

        Dialogo d5 = new Dialogo("No es curiosidad, es relevante para la investigación. Si hay algo que pueda ayudarnos a entender su estado emocional," +
                " necesito saberlo.", "DetectiveEntrada1", detective, true);
        Dialogo d6 = new Dialogo("Tuvimos algo. Fue breve. Él decía que no quería complicaciones. Yo... me ilusioné más de la cuenta.", "SecretariaEntrada1",
                secretaria, true);

        // Dato clave 1
        /* añadirAlDiario("Secretaria", "La secretaria tuvo una relación amorosa con el economista.");*/

        Dialogo d7 = new Dialogo("Gracias por su sinceridad. Esto podría ser importante.", "DetectiveEntrada1", detective, true);

        // Tercera decisión
        List<String> opciones3 = new LinkedList<>( Arrays.asList(
                "¿Él le confiaba información personal?",
                "¿Recuerda si mencionó algo sobre su contraseña?",
                "No quiero saber nada más"
        ));

        // Camino vacío: información personal
        Dialogo vacio3 = new Dialogo("Era reservado. Incluso cuando estábamos juntos, evitaba hablar de su trabajo. Decía que era mejor así.",
                "SecretariaEntrada1", secretaria, true);

        // Camino principal: contraseña
        Dialogo d8 = new Dialogo("¿Su contraseña? No la sé completa. Pero recuerdo que usaba frases en latín. Una vez me pidió que anotara " +
                "algo para él...", "SecretariaEntrada1", secretaria, true);
        Dialogo d9 = new Dialogo("Decía: 'Umbra mortis... algo'. No lo terminé de leer. Pero esas palabras estaban al principio.",
                "SecretariaEntrada1", secretaria, true);

        // Dato clave 2
        /*añadirAlDiario("Secretaria", "Fragmento de contraseña: comienza con 'Umbra mortis'.");*/

        Dialogo cierre = new Dialogo("Gracias. Eso será todo por ahora.", "DetectiveEntrada1", detective, true);
        Dialogo despedida = new Dialogo("Espero que le sirva de algo. No me gusta recordar ciertas cosas.", "SecretariaEntrada1", secretaria, true);

        //Guardia de la Sala de Seguridad1
        Dialogo s1 = new Dialogo("Guardia, necesito revisar algunos detalles sobre las cámaras de seguridad.", "DetectiveSalaSeguridad1", detective, true);
        Dialogo s2 = new Dialogo("¿Otra vez? Bueno... adelante. Aunque no sé si puedo decirle algo que no haya dicho ya.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo s3 = new Dialogo("A veces los detalles más pequeños son los que hacen la diferencia.", "DetectiveSalaSeguridad1", detective, true);
        Dialogo s4 = new Dialogo("Supongo... aunque no me gusta pensar que algo se me pudo haber pasado.", "GuardiaSalaSeguridad1", seguridad, true);

        // Primera decisión
        List<String> opc3 = new LinkedList<>(Arrays.asList(
                "¿Todas las cámaras funcionaban esa noche?",
                "¿Notó algo extraño en los monitores?",
                "¿Alguien más tiene acceso a la sala de seguridad?",
                "No quiero saber nada más"
        ));

        // Camino vacío: acceso a la sala
        Dialogo vacio1a = new Dialogo("Solo yo tengo acceso directo. El jefe también, pero rara vez entra. Confían en que yo lo manejo todo.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo vacio1b = new Dialogo("Además, todo queda registrado. Si alguien más hubiera entrado, lo sabría.", "GuardiaSalaSeguridad1", seguridad, true);

        // Camino vacío: monitores
        Dialogo vacio2a = new Dialogo("Los monitores estaban normales. A veces hay interferencias menores, pero nada fuera de lo común... creo.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo vacio2b = new Dialogo("Aunque ahora que lo pienso, hubo un parpadeo en la pantalla 3... pero fue tan rápido que ni lo reporté.", "GuardiaSalaSeguridad1", seguridad, true);

        // Camino principal: cámaras
        Dialogo c5 = new Dialogo("¿Todas las cámaras funcionaban esa noche? Necesito estar seguro.", "DetectiveSalaSeguridad1", detective, true);
        Dialogo c6 = new Dialogo("Bueno... en realidad no. Hubo un par de fallos. Lo reporté, pero no pensé que fueran importantes.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo c7 = new Dialogo("¿Qué tipo de fallos?", "DetectiveSalaSeguridad1", detective, true);
        Dialogo c8 = new Dialogo("Desconexiones. Cortes breves. Nada que activara alarmas, pero sí lo suficiente para dejar huecos.", "GuardiaSalaSeguridad1", seguridad, true);

        // Segunda decisión
        List<String> opc4 =  new LinkedList<>(Arrays.asList(
                "¿Qué cámaras fallaron exactamente?",
                "¿Es común que se caigan?",
                "No quiero saber nada más"
        ));

        // Camino vacío: frecuencia de fallos
        Dialogo vacio3a = new Dialogo("A veces se reinician solas. El sistema es viejo. Pero nunca había pasado algo así en una noche tan importante.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo vacio3b = new Dialogo("De todos modos, siempre reviso los registros al final del turno. No me gusta dejar cabos sueltos.", "GuardiaSalaSeguridad1", seguridad, true);

        // Camino principal: cámaras específicas
        Dialogo c9 = new Dialogo("La del callejón trasero se cayó entre la 1 y las 3 de la mañana. Pensé que era un corte de señal temporal.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo c10 = new Dialogo("Y la de la sala donde encontraron al economista... se congeló por unos segundos justo a la hora estimada del deceso.", "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo c11 = new Dialogo("No hay grabación de ese momento. Solo un salto en el tiempo. Como si alguien hubiera querido borrar algo.", "GuardiaSalaSeguridad1", seguridad, true);

        // Datos clave
        /* añadirAlDiario("Guardia", "La cámara del callejón dejó de funcionar entre la 1:00 y las 3:00 a.m.");
        añadirAlDiario("Guardia", "La cámara de la escena del crimen se congeló brevemente durante el momento del asesinato.");*/

        Dialogo d12 = new Dialogo("¿Y no le pareció sospechoso?", "DetectiveSalaSeguridad1", detective, true);
        Dialogo d13 = new Dialogo("Ahora que lo dice... sí. Pero en el momento pensé que era una coincidencia. No quería sonar paranoico.",
                "GuardiaSalaSeguridad1", seguridad, true);
        Dialogo d14 = new Dialogo("A veces uno se acostumbra tanto a las fallas técnicas que deja de ver lo que tiene delante.",
                "GuardiaSalaSeguridad1", seguridad, true);

        Dialogo cierre2 = new Dialogo("Gracias. Esto cambia las cosas.", "DetectiveSalaSeguridad1", detective, true);
        Dialogo despedida2 = new Dialogo("Si necesita revisar los registros, están en el servidor. Aunque... no sé si todo quedó grabado como debería.",
                "Seguridad", seguridad, true);


        // Dialogo limpiador baño inferior
        Dialogo b1 = new Dialogo("Buenas noches. ¿Tiene un momento? Necesito hacerle unas preguntas.", "DetectiveBañoInferior1", detective, true);
        Dialogo b2 = new Dialogo("Claro, jefe. Aunque no sé si puedo ayudar mucho... yo solo limpio por aquí abajo.", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo b3 = new Dialogo("A veces los que limpian ven más de lo que creen. ¿Ha notado algo fuera de lugar últimamente?", "DetectiveBañoInferior1", detective, true);
        Dialogo b4 = new Dialogo("Bueno... depende de lo que llame 'fuera de lugar'. Hay cosas raras todo el tiempo en este museo.", "LimpiadorBañoInferior1", limpieza, true);

        // Primera decisión
        List<String> opcion1 =  new LinkedList<>(Arrays.asList(
                "¿Encontró algún objeto que no debería estar ahí?",
                "¿Ha visto a alguien bajar aquí cuando no debería?",
                "¿Notó algo raro en los baños esta semana?",
                "No quiero saber nada más"
        ));

        // Camino vacío: baños
        Dialogo vacio4a = new Dialogo("Los baños estaban limpios. Bueno, dentro de lo que cabe. Nada fuera de lo común.", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo vacio4b = new Dialogo("Aunque alguien dejó una taza de café en el lavamanos... pero eso pasa más de lo que imagina.", "LimpiadorBañoInferior1", limpieza, true);

        // Camino vacío: gente bajando
        Dialogo vacio5a = new Dialogo("A veces escucho pasos, pero no sé quién baja. Yo no me meto con nadie, solo barro y subo.", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo vacio5b = new Dialogo("Una vez vi a la esposa del director pasar por aquí, no me saludó. Iba apurada, como si buscara algo.", "LimpiadorBañoInferior1", limpieza, true);

        // Camino principal: objetos encontrados
        Dialogo o5 = new Dialogo("¿Objetos? Bueno... encontré unas llaves hace unos días. Cerca del depósito, tiradas en el suelo.", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo o6 = new Dialogo("Pensé que eran del almacén, así que las guardé. Pero luego se me olvidaron en el bolsillo.", "LimpiadorBañoInferior1", limpieza, true);

        // Segunda decisión
        List<String> opcion2 = new LinkedList<>(Arrays.asList(
                "¿Qué tipo de llaves eran?",
                "¿Dónde están ahora?",
                "No quiero saber nada más"
        ));

        // Camino vacío: tipo de llaves
        Dialogo vacio6a = new Dialogo("Eran metálicas, con una etiqueta vieja. No decía mucho, solo un número. Pensé que eran de algún armario.", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo vacio6b = new Dialogo("No me fijé mucho, la verdad. Las metí en el bolsillo y seguí limpiando.", "LimpiadorBañoInferior1", limpieza, true);

        // Camino principal: ubicación actual
        Dialogo u7 = new Dialogo("¿Dónde están esas llaves ahora?", "DetectiveBañoInferior1", detective, true);
        Dialogo u8 = new Dialogo("En mi casa. Me di cuenta cuando vacié el uniforme. Pensaba traerlas hoy, pero con todo esto...", "LimpiadorBañoInferior1", limpieza, true);
        Dialogo u9 = new Dialogo("¿Se da cuenta de lo importante que puede ser eso? Necesito esas llaves de inmediato.", "DetectiveBañoInferior1", detective, true);
        Dialogo u10 = new Dialogo("Sí, sí, lo entiendo. Vivo cerca. Si quiere, puedo ir por ellas ahora mismo.", "LimpiadorBañoInferior1", limpieza, true);

        // Dato clave
        /*añadirAlDiario("Limpiador", "El limpiador encontró las llaves del almacén y las llevó por accidente a su casa.");*/

        Dialogo u11 = new Dialogo("Hágalo. Pero por ahora, no le diga a nadie que las encontró. ¿De acuerdo?", "DetectiveBañoInferior1", detective, true);
        Dialogo u12 = new Dialogo("¿En secreto? Bueno... si usted lo dice. No quiero meterme en líos.", "LimpiadorBañoInferior1", limpieza, true);

        Dialogo cierre3 = new Dialogo("Gracias. Avíseme en cuanto las tenga.", "DetectiveBañoInferior1", detective, true);
        Dialogo despedida3 = new Dialogo("Sí, voy saliendo ya mismo.", "LimpiadorBañoInferior1", limpieza, true);

        // Dueño en la oficina
        Dialogo j1 = new Dialogo("Hola, no pude evitar distraerme con todos esos trofeos. Necesito hacerle unas preguntas, si no le molesta.", "DetectiveOficina1", detective, true);
        Dialogo j2 = new Dialogo("¿Otra ronda de interrogatorios, detective? ¿No se cansa de buscar fantasmas?", "JefeOficina1", dueno, true);
        Dialogo j3 = new Dialogo("Solo intento entender lo que pasó. Cualquier detalle puede ser útil.", "DetectiveOficina1", detective, true);
        Dialogo j4 = new Dialogo("Claro, claro... aunque si me pregunta a mí, esto ya está más que resuelto. Pero adelante, dispare.", "JefeOficina1", dueno, true);

        // Primera decisión
        List<String> op1 = new LinkedList<>(Arrays.asList(
                "¿Conocía bien al economista?",
                "¿Dónde estaba usted cuando ocurrió el incidente?",
                "Bonita oficina... ¿puedo preguntarle sobre la decoración?",
                "No quiero saber nada más"
        ));

        // Camino vacío: relación con el economista
        Dialogo vacio7a = new Dialogo("Lo conocía lo justo. Buen tipo, algo aburrido. Siempre hablando de números y teorías. Yo prefiero el arte, ya sabe.", "JefeOficina1", dueno, true);
        Dialogo vacio7b = new Dialogo("Aunque debo admitir que tenía una forma muy... peculiar de ver el mundo. Como si siempre supiera algo que los demás no.", "JefeOficina1", dueno, true);

        // Camino vacío: coartada
        Dialogo vacio8a = new Dialogo("Estaba en casa, durmiendo como un bebé. Pregúntele a mi esposa si no me cree... aunque no sé si le conviene despertarla.", "JefeOficina1", dueno, true);
        Dialogo vacio8b = new Dialogo("Además, si yo hubiera querido matar a alguien, créame que no dejaría pistas. Pero no se lo tome a mal, detective.", "JefeOficina1", dueno, true);

        // Camino que desbloquea segunda decisión
        Dialogo desbloquear1 = new Dialogo("¿Preguntando cosas personales? Vaya, detective... pensé que esto era una investigación, no una cita.", "JefeOficina1", dueno, true);
        Dialogo desbloquear2 = new Dialogo("Pero adelante, pregunte. Aunque no prometo responder con seriedad.", "JefeOficina1", dueno, true);

        // Segunda decisión (solo aparece si se elige la opción correcta)
        List<String> op2 = new LinkedList<>(Arrays.asList(
                "¿Qué son todos esos trofeos en su estantería?",
                "No quiero saber nada más"
        ));

        // Camino principal: trofeos
        Dialogo t7 = new Dialogo("¿Los trofeos? Ah, veo que tiene buen ojo. Son de cuando practicaba esgrima. Era bastante bueno, ¿sabe?", "JefeOficina1", dueno, true);
        Dialogo t8 = new Dialogo("Campeón regional tres años seguidos. Aunque ahora solo es buen material para presumir con los visitantes importantes.", "JefeOficina1", dueno, true);

        // Dato clave
        /*añadirAlDiario("Jefe del museo", "El jefe practicó esgrima cuando era joven.");*/

        Dialogo d15 = new Dialogo("¿Quiere una demostración? No se preocupe, no suelo atacar a los invitados... a menos que me acusen de asesinato.", "JefeOficina1", dueno, true);

        //Tercera decisión
        List<String> op3 = new LinkedList<>(Arrays.asList(
                "¿Aún practica esgrima?",
                "No quiero saber nada más"
        ));

        // Camino vacío: práctica actual
        Dialogo vacio9a = new Dialogo("Ya no. Las rodillas no perdonan, detective. Ahora solo practico con el control remoto y el sillón reclinable.", "JefeOficina1", dueno, true);

        Dialogo cierre4 = new Dialogo("Gracias por su tiempo. Si recuerda algo más, estaré cerca.", "DetectiveOficina1", detective, true);
        Dialogo despedida4 = new Dialogo("Cuando quiera, detective. Aunque si me pregunta, esto es una pérdida de tiempo con corbata.", "JefeOficina1", dueno, true);

        //Dialogo guias sala antiguedades
        Dialogo guia1 = new Dialogo("Vaya, esta sala siempre me ha parecido la más silenciosa del museo.", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo guia3 = new Dialogo("Es especial, ¿verdad? Tiene una energía distinta. A veces siento que las piezas me observan a mí.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo guia4 = new Dialogo("¿Cuánto tiempo lleva trabajando aquí?", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo guia5 = new Dialogo("Ocho años. He guiado a miles de personas por cada rincón de este lugar. Me lo conozco de memoria.", "Guia1SalaAntiguedades1", guia, true);

        // Primera decisión
        List<String> opc1 = new LinkedList<>( Arrays.asList(
                "¿Qué pieza de esta sala le parece más interesante?",
                "¿Le ha tocado ver cosas extrañas en sus turnos?",
                "¿Diría que nota cuando algo está fuera de lugar?",
                "No quiero saber nada más"
        ));

        // Camino vacío: pieza favorita
        Dialogo vacio10a = new Dialogo("Difícil elegir. Pero si me apura, diría el reloj solar romano. Es simple, pero preciso. Como deberían ser las cosas.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo vacio10b = new Dialogo("Aunque claro, la mayoría prefiere las armaduras. Supongo que tienen más... presencia.", "Guia1SalaAntiguedades1", guia, true);

        // Camino vacío: cosas extrañas
        Dialogo vacio11a = new Dialogo("Algunas sombras, ruidos, puertas que se cierran solas... pero nada que no tenga explicación lógica. O al menos eso me digo para dormir tranquilo.", "Guia1SalaAntiguedades1",guia, true);
        Dialogo vacio11b = new Dialogo("Aunque una vez juraría que vi una figura moverse entre las vitrinas. Pero era tarde, y estaba cansado.", "Guia1SalaAntiguedades1", guia, true);

        // Camino principal: percepción del entorno
        Dialogo p5 = new Dialogo("¿Fuera de lugar? Claro. Cuando uno ve lo mismo todos los días, cualquier mínimo cambio salta a la vista.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo p6 = new Dialogo("De hecho, ahora que lo menciona, hay algo que me pareció raro esta mañana.", "Guia1SalaAntiguedades1", guia, true);

        // Segunda decisión
        List<String> opc2 = new LinkedList<>(Arrays.asList(
                "¿Alguien estaba donde no debería?",
                "¿Qué vio fuera de lugar?",
                "No quiero saber nada más"
        ));

        // Camino vacío: pregunta ambigua
        Dialogo vacio12a = new Dialogo("No, no vi a nadie moverse. Solo noté una sensación rara, como si algo no encajara. Pero no sabría decir qué.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo vacio12b = new Dialogo("Quizás solo estaba cansado. A veces la mente juega con uno cuando está solo entre estas cosas antiguas.", "Guia1SalaAntiguedades1", guia, true);

        // Camino principal: detalle observado
        Dialogo detalle1 = new Dialogo("Una de las armaduras... de la otra sala. Siempre ha estado girada hacia la entrada, como si saludara a los visitantes.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo detalle2 = new Dialogo("Pero esta mañana la encontré girada hacia la pared. No es algo que se mueva solo, créame. Pesa como un camión.", "Guia1SalaAntiguedades1", guia, true);
        Dialogo detalle3 = new Dialogo("¿Y no hay forma de que alguien la haya movido por accidente?", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo detalle4 = new Dialogo("No sin ayuda. Y no hay registro de que se haya autorizado moverla. Por eso me pareció raro.", "Guia1SalaAntiguedades1", guia, true);

        // Dato clave 1 y 2
        /*añadirAlDiario("Guía 1", "Una de las armaduras fue encontrada en una posición inusual.");
        añadirAlDiario("Guía 1", "La armadura no pudo ser movida por una sola persona.");*/

        // Intervención de Guía 2
        Dialogo inter1 = new Dialogo("(Parece que el guía 2 viene hacia aquí... con la intención de interrumpir)", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo inter2 = new Dialogo("¿Están hablando de la armadura? Por favor, eso no es nada. Seguro alguien la empujó sin querer limpiando.", "Guia2SalaAntiguedades1", guia2, true);
        Dialogo inter3 = new Dialogo("No creo que valga la pena perder tiempo con eso, detective. Hay cosas más importantes, ¿no cree?", "Guia2SalaAntiguedades1", guia2, true);

        // Confrontación
        Dialogo det1 = new Dialogo("¿Le molesta que investigue detalles menores? A veces son esos los que resuelven un caso.", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo det2 = new Dialogo("No, claro que no. Solo digo que no deberíamos sacar conclusiones apresuradas por una estatua torcida.", "Guia2SalaAntiguedades1", guia2, true);
        Dialogo det3 = new Dialogo("No he sacado ninguna conclusión. Pero usted parece muy interesada en que no la saque.", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo det4 = new Dialogo("Yo solo intento ayudar. Si me necesita, estaré en la sala de esculturas.", "Guia2SalaAntiguedades1", guia2, true);

        Dialogo cierre5 = new Dialogo("Gracias por su tiempo. Seguiré observando por aquí.", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo despedida5 = new Dialogo("Cuando quiera, detective. Espero que encuentre lo que busca.", "Guia1SalaAntiguedades1", guia, true);

        // Reacción del detective (después del cierre)
        Dialogo reflexion1 = new Dialogo("(Demasiado interés en desviar la atención. Esa guía sabe más de lo que aparenta...)", "DetectiveSalaAntiguedades1", detective, true);
        Dialogo reflexion2 = new Dialogo("(Siento que me voy acercando... en el fondo lo siento)", "DetectiveSalaAntiguedades1", detective, true);

        // Dato clave 2
        /*añadirAlDiario("Guía 2", "La Guía 2 intentó restarle importancia al detalle de la armadura. Parece que tiene algo que ocultar.");*/

        // Dialogo callejon vagabundo
        Dialogo vagab1 = new Dialogo("Buenas noches. ¿Todo bien por aquí?", "DetectiveCallejon1", detective, true);
        Dialogo vagab2 = new Dialogo("¿Todo bien? Estoy en un callejón, con frío y sin cena. ¿Usted qué cree?", "VagabundoCallejon1", vagabundo, true);
        Dialogo vagab3 = new Dialogo("Tienes razón. No fue la mejor forma de empezar. Solo quería hablar un momento.", "DetectiveCallejon1", detective, true);
        Dialogo vagab4 = new Dialogo("Hablar no cuesta nada... pero tampoco llena el estómago.", "VagabundoCallejon1", vagabundo, true);

        // Primera decisión
        List<String> decision1 = new LinkedList<>(Arrays.asList(
                "¿Hace cuánto duerme aquí?",
                "¿Le molesta si me quedo un momento?",
                "¿Ha visto algo raro últimamente?",
                "No quiero saber nada más"
        ));

        // Camino vacío: tiempo en el callejón
        Dialogo vacio13a = new Dialogo("¿Qué importa? El tiempo pasa igual, tenga uno techo o no. Pero sí, llevo un buen rato viendo pasar sombras.", "VagabundoCallejon1", vagabundo, true);

        // Camino vacío: quedarse
        Dialogo vacio14a = new Dialogo("Mientras no me quite el rincón, siéntase como en casa. Aunque no tengo sofá ni café para ofrecerle.", "VagabundoCallejon1", vagabundo, true);

        // Camino principal: cosas raras
        Dialogo raras1 = new Dialogo("¿Raro? Aquí todo es raro. Pero sí, hay noches que el silencio se rompe de formas extrañas.", "VagabundoCallejon1", vagabundo, true);
        Dialogo raras2 = new Dialogo("¿Qué quiere saber exactamente?", "VagabundoCallejon1", vagabundo, true);

        // Segunda decisión
        List<String> decision2 = new LinkedList<>(Arrays.asList(
                "¿Escuchó algún ruido fuerte anoche?",
                "¿Vio a alguien salir del museo entre la 1 y las 3 a.m.?",
                "No quiero saber nada más"
        ));

        // Camino vacío: ruidos
        Dialogo vacio15a = new Dialogo("Ruidos hay siempre. Gatos, botellas, viento... o mis tripas. Nada que me quite el sueño.", "VagabundoCallejon1", vagabundo, true);

        // Camino principal: pregunta clave
        /*if(tienePanConJamon)*/ {
            Dialogo v1 = new Dialogo("¿Entre la 1 y las 3? Sí... vi a alguien. No saliendo, no. Subiendo.", "VagabundoCallejon1", vagabundo, true);
            Dialogo v2 = new Dialogo("Ocurrió por la escalera de incendios. Alguien más la bajo, cosa rara. Esa chatarra siempre está recogida.", "VagabundoCallejon1", vagabundo, true);
            Dialogo v3 = new Dialogo("No les vi la cara. Solo siluetas. Pero no eran de los míos, eso seguro.", "VagabundoCallejon1", vagabundo, true);

            // Datos clave
            /*añadirAlDiario("Vagabundo", "Alguien subió por la escalera de incendios entre la 1 y las 3 a.m la cual estaba ya bajada.");
            añadirAlDiario("Vagabundo", "La escalera de incendios fue desplegada por una segunda persona.");*/

            Dialogo cierre6a = new Dialogo("Gracias. Eso ayuda más de lo que cree.", "DetectiveCallejon1", detective, true);
            Dialogo despedida6a = new Dialogo("De nada. Y gracias por el pan. El jamón estaba... casi fresco.", "VagabundoCallejon1", vagabundo, true);

        }/* else*/ {
            Dialogo v4 = new Dialogo("¿Entre la 1 y las 3? Mmm... la memoria me falla cuando tengo el estómago vacío.", "VagabundoCallejon1", vagabundo, true);
            Dialogo v5 = new Dialogo("Pero quién sabe... tal vez si tuviera algo caliente en las manos, recordaría mejor.", "VagabundoCallejon1", vagabundo, true);
            Dialogo v6 = new Dialogo("Algo rico de comer, por ejemplo. No pido mucho. Solo un gesto.", "VagabundoCallejon1", vagabundo, true);

            Dialogo cierre6b = new Dialogo("Veré qué puedo hacer.", "DetectiveCallejon1", detective, true);
            Dialogo despedida6b = new Dialogo("Aquí estaré. No tengo a dónde ir.", "VagabundoCallejon1", vagabundo, true);
        }

        BinaryTreeNode<Dialogo> node1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> node2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> node3 = new BinaryTreeNode<>(vacio1);
        BinaryTreeNode<Dialogo> node4 = new BinaryTreeNode<>(vacio2);
        BinaryTreeNode<Dialogo> node5 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> node6 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> node7 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> node8 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> node9 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> node10 = new BinaryTreeNode<>(vacio3);
        BinaryTreeNode<Dialogo> node11 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> node12 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> node13 = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node14 = new BinaryTreeNode<>(despedida);
        BinaryTreeNode<Dialogo> node15 = new BinaryTreeNode<>(s1);
        BinaryTreeNode<Dialogo> node16 = new BinaryTreeNode<>(s2);
        BinaryTreeNode<Dialogo> node17 = new BinaryTreeNode<>(s3);
        BinaryTreeNode<Dialogo> node18 = new BinaryTreeNode<>(s4);
        BinaryTreeNode<Dialogo> node19 = new BinaryTreeNode<>(vacio1a);
        BinaryTreeNode<Dialogo> node20 = new BinaryTreeNode<>(vacio1b);
        BinaryTreeNode<Dialogo> node21 = new BinaryTreeNode<>(vacio2a);
        BinaryTreeNode<Dialogo> node22 = new BinaryTreeNode<>(vacio2b);
        BinaryTreeNode<Dialogo> node23 = new BinaryTreeNode<>(c5);
        BinaryTreeNode<Dialogo> node24 = new BinaryTreeNode<>(c6);
        BinaryTreeNode<Dialogo> node25 = new BinaryTreeNode<>(c7);
        BinaryTreeNode<Dialogo> node26 = new BinaryTreeNode<>(c8);
        BinaryTreeNode<Dialogo> node27 = new BinaryTreeNode<>(vacio3a);
        BinaryTreeNode<Dialogo> node28 = new BinaryTreeNode<>(vacio3b);
        BinaryTreeNode<Dialogo> node29 = new BinaryTreeNode<>(c9);
        BinaryTreeNode<Dialogo> node30 = new BinaryTreeNode<>(c10);
        BinaryTreeNode<Dialogo> node31 = new BinaryTreeNode<>(c11);
        BinaryTreeNode<Dialogo> node32 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> node33 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> node34 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> node35 = new BinaryTreeNode<>(cierre2);
        BinaryTreeNode<Dialogo> node36 = new BinaryTreeNode<>(despedida2);
        BinaryTreeNode<Dialogo> node37 = new BinaryTreeNode<>(b1);
        BinaryTreeNode<Dialogo> node38 = new BinaryTreeNode<>(b2);
        BinaryTreeNode<Dialogo> node39 = new BinaryTreeNode<>(b3);
        BinaryTreeNode<Dialogo> node40 = new BinaryTreeNode<>(b4);
        BinaryTreeNode<Dialogo> node41 = new BinaryTreeNode<>(vacio4a);
        BinaryTreeNode<Dialogo> node42 = new BinaryTreeNode<>(vacio4b);
        BinaryTreeNode<Dialogo> node43 = new BinaryTreeNode<>(vacio5a);
        BinaryTreeNode<Dialogo> node44 = new BinaryTreeNode<>(vacio5b);
        BinaryTreeNode<Dialogo> node45 = new BinaryTreeNode<>(o5);
        BinaryTreeNode<Dialogo> node46 = new BinaryTreeNode<>(o6);
        BinaryTreeNode<Dialogo> node47 = new BinaryTreeNode<>(vacio6a);
        BinaryTreeNode<Dialogo> node48 = new BinaryTreeNode<>(vacio6b);
        BinaryTreeNode<Dialogo> node49 = new BinaryTreeNode<>(u7);
        BinaryTreeNode<Dialogo> node50 = new BinaryTreeNode<>(u8);
        BinaryTreeNode<Dialogo> node51 = new BinaryTreeNode<>(u9);
        BinaryTreeNode<Dialogo> node52 = new BinaryTreeNode<>(u10);
        BinaryTreeNode<Dialogo> node53 = new BinaryTreeNode<>(u11);
        BinaryTreeNode<Dialogo> node54 = new BinaryTreeNode<>(u12);
        BinaryTreeNode<Dialogo> node55 = new BinaryTreeNode<>(cierre3);
        BinaryTreeNode<Dialogo> node56 = new BinaryTreeNode<>(despedida3);
        BinaryTreeNode<Dialogo> node57 = new BinaryTreeNode<>(j1);
        BinaryTreeNode<Dialogo> node58 = new BinaryTreeNode<>(j2);
        BinaryTreeNode<Dialogo> node59 = new BinaryTreeNode<>(j3);
        BinaryTreeNode<Dialogo> node60 = new BinaryTreeNode<>(j4);
        BinaryTreeNode<Dialogo> node61 = new BinaryTreeNode<>(vacio7a);
        BinaryTreeNode<Dialogo> node62 = new BinaryTreeNode<>(vacio7b);
        BinaryTreeNode<Dialogo> node63 = new BinaryTreeNode<>(vacio8a);
        BinaryTreeNode<Dialogo> node64 = new BinaryTreeNode<>(vacio8b);
        BinaryTreeNode<Dialogo> node65 = new BinaryTreeNode<>(desbloquear1);
        BinaryTreeNode<Dialogo> node66 = new BinaryTreeNode<>(desbloquear2);
        BinaryTreeNode<Dialogo> node67 = new BinaryTreeNode<>(t7);
        BinaryTreeNode<Dialogo> node68 = new BinaryTreeNode<>(t8);
        BinaryTreeNode<Dialogo> node69 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> node70 = new BinaryTreeNode<>(vacio9a);
        BinaryTreeNode<Dialogo> node71 = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node72 = new BinaryTreeNode<>(despedida4);
        BinaryTreeNode<Dialogo> node73 = new BinaryTreeNode<>(guia1);
        BinaryTreeNode<Dialogo> node74 = new BinaryTreeNode<>(guia3);
        BinaryTreeNode<Dialogo> node75 = new BinaryTreeNode<>(guia4);
        BinaryTreeNode<Dialogo> node76 = new BinaryTreeNode<>(guia5);
        BinaryTreeNode<Dialogo> node77 = new BinaryTreeNode<>(vacio10a);
        BinaryTreeNode<Dialogo> node78 = new BinaryTreeNode<>(vacio10b);
        BinaryTreeNode<Dialogo> node79 = new BinaryTreeNode<>(vacio11a);
        BinaryTreeNode<Dialogo> node80 = new BinaryTreeNode<>(vacio11b);
        BinaryTreeNode<Dialogo> node81 = new BinaryTreeNode<>(p5);
        BinaryTreeNode<Dialogo> node82 = new BinaryTreeNode<>(p6);
        BinaryTreeNode<Dialogo> node83 = new BinaryTreeNode<>(vacio12a);
        BinaryTreeNode<Dialogo> node84 = new BinaryTreeNode<>(vacio12b);
        BinaryTreeNode<Dialogo> node85 = new BinaryTreeNode<>(detalle1);
        BinaryTreeNode<Dialogo> node86 = new BinaryTreeNode<>(detalle2);
        BinaryTreeNode<Dialogo> node87 = new BinaryTreeNode<>(detalle3);
        BinaryTreeNode<Dialogo> node88 = new BinaryTreeNode<>(detalle4);
        BinaryTreeNode<Dialogo> node89 = new BinaryTreeNode<>(inter1);
        BinaryTreeNode<Dialogo> node90 = new BinaryTreeNode<>(inter2);
        BinaryTreeNode<Dialogo> node91 = new BinaryTreeNode<>(inter3);
        BinaryTreeNode<Dialogo> node92 = new BinaryTreeNode<>(det1);
        BinaryTreeNode<Dialogo> node93 = new BinaryTreeNode<>(det2);
        BinaryTreeNode<Dialogo> node94 = new BinaryTreeNode<>(det3);
        BinaryTreeNode<Dialogo> node95 = new BinaryTreeNode<>(det4);
        BinaryTreeNode<Dialogo> node96 = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node97 = new BinaryTreeNode<>(despedida5);
        BinaryTreeNode<Dialogo> node98 = new BinaryTreeNode<>(reflexion1);
        BinaryTreeNode<Dialogo> node99 = new BinaryTreeNode<>(reflexion2);
        BinaryTreeNode<Dialogo> node100 = new BinaryTreeNode<>(vagab1);
        BinaryTreeNode<Dialogo> node101 = new BinaryTreeNode<>(vagab2);
        BinaryTreeNode<Dialogo> node102 = new BinaryTreeNode<>(vagab3);
        BinaryTreeNode<Dialogo> node103 = new BinaryTreeNode<>(vagab4);
        BinaryTreeNode<Dialogo> node104 = new BinaryTreeNode<>(vacio13a);
        BinaryTreeNode<Dialogo> node105 = new BinaryTreeNode<>(vacio14a);
        BinaryTreeNode<Dialogo> node106 = new BinaryTreeNode<>(raras1);
        BinaryTreeNode<Dialogo> node107 = new BinaryTreeNode<>(raras2);
        BinaryTreeNode<Dialogo> node108 = new BinaryTreeNode<>(vacio15a);
        /*
        BinaryTreeNode<Dialogo> node109 = new BinaryTreeNode<>(v1);
        BinaryTreeNode<Dialogo> node110 = new BinaryTreeNode<>(v2);
        BinaryTreeNode<Dialogo> node111 = new BinaryTreeNode<>(v3);
        BinaryTreeNode<Dialogo> node112 = new BinaryTreeNode<>(cierre6a);
        BinaryTreeNode<Dialogo> node113 = new BinaryTreeNode<>(despedida6a);
        BinaryTreeNode<Dialogo> node114 = new BinaryTreeNode<>(v4);
        BinaryTreeNode<Dialogo> node115 = new BinaryTreeNode<>(v5);
        BinaryTreeNode<Dialogo> node116 = new BinaryTreeNode<>(v6);
        BinaryTreeNode<Dialogo> node117 = new BinaryTreeNode<>(cierre6b);
        BinaryTreeNode<Dialogo> node118 = new BinaryTreeNode<>(despedida6b);
        */
    }


}
