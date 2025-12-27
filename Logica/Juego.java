package Logica;

import java.util.Iterator;
import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Juego {
    private static Juego instance;
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;
    private Partida partidaActual;

    private Juego() {
        this.titulo = "Juego";
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
        //esto se quita mas adelante
        this.partidaActual = null;
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

    }


}
