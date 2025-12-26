package Logica;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Juego {
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;

    private Juego() {
        this.titulo = "Juego";
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
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


    public void iniciarJuego() {
    }

    public void cargarPartida() {
    }

    public void guardarPartida() {
    }


    public void crearDialgParte1() {

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia.png");
        ImageIcon secretaria = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon guia2 = new ImageIcon("DatosAuxiliares/Personajes/Guia2.png");
        ImageIcon limpieza = new ImageIcon("DatosAuxiliares/Personajes/Limpieza.png");
        ImageIcon esposa = new ImageIcon("DatosAuxiliares/Personajes/Esposa.png");

        Dialogo g1 = new Dialogo("Buenas noches, soy el detective asignado al caso. Necesito hablar con usted.", "Detective", detective, true);
        Dialogo g2 = new Dialogo("Buenas noches, detective. Soy el guardia de seguridad del museo.", "Guardia", seguridad, true);

        Dialogo decisionGuardia = new Dialogo("¿Qué desea preguntarme?", "Guardia", seguridad, false);
        decisionGuardia.setOpciones(new LinkedList<>(Arrays.asList("¿Cuál es su trabajo en el museo?", "¿Qué relación tenía con el economista?",
                "¿Dónde estaba usted cuando ocurrieron los hechos?", "Eso es suficiente por ahora. Terminemos la conversación.")));

        //Pregunta 1: Trabajo en el museo
        Dialogo respuestaA = new Dialogo("Me encargo de vigilar las cámaras y patrullar las salas. Es mi responsabilidad mantener todo" +
                " seguro.", "Guardia", seguridad, true);

        //Pregunta 2: Relación con la víctima
        Dialogo respuestaB = new Dialogo("Casi ninguna. Era reservado, apenas cruzábamos palabras.", "Guardia", seguridad, true);

        //Pregunta 3: Dónde estaba en el momento de los hechos (dato clave)
        Dialogo respuestaC = new Dialogo("Estaba revisando las cámaras. Justo a la hora del asesinato, una de ellas falló en la sección" +
                " medieval. Fui a investigar y encontré el cuerpo.", "Guardia", seguridad, true);

        //Aquí añadimos el dato clave al diario
        /* añadirAlDiario("Guardia", "Las cámaras fallaron en la sección medieval justo a la hora del asesinato.");*/

        //Pregunta 4: Terminar conversación
        Dialogo respuestaD = new Dialogo("Entendido, detective. Si necesita algo más, estaré en mi puesto.", "Guardia", seguridad, true);

        //Cambio de personaje, Aqui empieza el guia

        Dialogo dect = new Dialogo("Buenas noches. Estoy interrogando al personal del museo. ¿Podría decirme quién es usted y " +
                "qué función cumple aquí?", "Detective", detective, true);
        Dialogo guia1 = new Dialogo("Claro, detective. Soy el guía principal del museo. Me encargo de recibir a los visitantes," +
                " explicarles las exposiciones y resolver cualquier duda que tengan.", "Guía", guia, true);

        // Primera decisión: preguntar o terminar
        Dialogo decision1 = new Dialogo("¿Desea saber algo más?", "Guía", guia, false);
        decision1.setOpciones(new LinkedList<>(Arrays.asList("¿Cómo era su trato con el economista?", "Eso es todo por ahora." +
                " Gracias por su tiempo.")));

        Dialogo respuesta1 = new Dialogo("La verdad, muy escaso. Era reservado, siempre metido en sus asuntos. " +
                "Apenas cruzábamos palabras.", "Guía", guia, true);

        Dialogo despedida1 = new Dialogo("A usted, detective. Si necesita algo más, estaré por aquí.", "Guía", guia, true);

        // Segunda decisión: preguntar ubicación o terminar
        Dialogo decision2 = new Dialogo("¿Desea saber dónde estaba durante el incidente o prefiere terminar la conversación?", "Guía", guia, false);
        decision2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba usted durante el incidente?", "Gracias. " +
                "Eso es todo por ahora.")));

        Dialogo respuesta2 = new Dialogo("Estaba en la sala de arte moderno, preparando la presentación de mañana. " +
                "Conozco el museo como la palma de mi mano, y no noté nada fuera de lo común.", "Guía", guia, true);

        // Dato clave
        /*añadirAlDiario("Guía", "El guía conoce el museo como la palma de su mano.");*/

        Dialogo despedida2 = new Dialogo("A usted, detective. Que tenga buena noche.", "Guía", guia, true);

        //Cambio de personaje, Aqui empieza la secretaria

        Dialogo s1 = new Dialogo("Buenas noches. Estoy reuniendo declaraciones del personal. ¿Podemos hablar un momento?", "Detective", detective, true);
        Dialogo s2 = new Dialogo("Por supuesto, detective. Soy la secretaria del director. Manejo su agenda, sus llamadas... y" +
                " a veces sus caprichos.", "Secretaria", secretaria, true);

        // Primera decisión
        Dialogo decs= new Dialogo("¿Quiere saber algo más o solo vino a saludar?", "Secretaria", secretaria, false);
        decs.setOpciones(new LinkedList<>(Arrays.asList("¿Tenía algún tipo de relación con el economista?", "No, eso sería todo por ahora.")));

        Dialogo resp1 = new Dialogo("Nada fuera de lo laboral. Era serio, distante... aunque a veces me lanzaba miradas que decían más " +
                "que sus palabras.", "Secretaria", secretaria, true);

        Dialogo desp1 = new Dialogo("Qué pena... justo cuando empezaba a disfrutar la charla. Estoy a su disposición, detective.", "Secretaria", secretaria, true);

        // Segunda decisión
        Dialogo decs2 = new Dialogo("¿Le interesa saber dónde estaba esa noche o ya me deja tranquila?", "Secretaria", secretaria, false);
        decs2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo.")));
        ;
        Dialogo resp2 = new Dialogo("En mi oficina, como siempre. Archivando papeles, atendiendo llamadas... y esperando que algo" +
                " interesante pasara. Supongo que lo logró.", "Secretaria", secretaria, true);

        Dialogo desp2 = new Dialogo("A usted, detective. Que tenga una noche... intrigante.", "Secretaria", secretaria, true);

        //Cambio de personaje, Aqui empieza la guia2

        Dialogo guiaM1 = new Dialogo("Buenas noches. Estoy hablando con el personal del museo. ¿Podría decirme su nombre y su función " +
                "aquí?", "Detective", detective, true);
        Dialogo guiaM2 = new Dialogo("Soy la segunda guía del museo. Me encargo de apoyar en las visitas... y de resolver los problemas " +
                "que otros prefieren ignorar.", "Guía 2", guia2, true);

        // Primera decisión
        Dialogo decis1 = new Dialogo("¿Algo más que quiera saber, o ya tiene suficiente para su informe?", "Guía 2", guia2, false);
        decis1.setOpciones(new LinkedList<>(Arrays.asList("¿Conocía bien al economista?", "Por ahora es suficiente. Gracias.")));

        Dialogo respt1 = new Dialogo("Lo justo. No era alguien con quien uno quisiera compartir un café. Siempre tan correcto," +
                " tan... aburrido.", "Guía 2", guia2, true);
        Dialogo relleno1 = new Dialogo("Aunque, claro, en este lugar todos llevamos máscaras. Algunas más pesadas que otras.", "Guía 2", guia2, true);

        Dialogo despd1 = new Dialogo("Como desee. Aunque si cambia de opinión, estaré por aquí... observando cómo se desenvuelven " +
                "las piezas.", "Guía 2", guia2, true);

        // Segunda decisión
        Dialogo decis2 = new Dialogo("¿Le interesa saber dónde estaba o ya tiene lo que vino a buscar?", "Guía 2", guia2, false);
        decis2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo." )));

        Dialogo respt2 = new Dialogo("En la sala de esculturas. Estaba revisando unos textos para la próxima muestra. Me gusta trabajar" +
                " sola... menos distracciones, menos tonterías.", "Guía 2", guia2, true);
        Dialogo relleno2 = new Dialogo("Pero bueno, en este museo el silencio nunca es garantía de tranquilidad.", "Guía 2", guia2, true);

        Dialogo despd2 = new Dialogo("A usted, detective. Que tenga una noche... tranquila. O lo más cerca que pueda estar de eso.", "Guía 2", guia2, true);

        //Cambio de personaje, Aqui empieza la de limpieza

        Dialogo l1 = new Dialogo("Hola. Estoy hablando con todos los empleados del museo. ¿Podría decirme su nombre y qué hace aquí?", "Detective", detective, true);
        Dialogo l2 = new Dialogo("Sí, claro. Soy el encargado de la limpieza del museo. Me encargo de dejar todo en orden cuando ya no" +
                " queda nadie.", "Limpieza", limpieza, true);
        Dialogo l3 = new Dialogo("Trabajo de noche, así que casi siempre estoy solo. Es tranquilo... aunque uno se encuentra cosas que" +
                " otros no notan.", "Limpieza", limpieza, true);

        // Primera decisión
        Dialogo de1 = new Dialogo("¿Quiere preguntarme algo más?", "Limpieza", limpieza, false);
        de1.setOpciones(new LinkedList<>(Arrays.asList("¿Conocía al economista?", "Está bien, gracias por su tiempo.")) );

        Dialogo res1 = new Dialogo("Lo veía pasar. Siempre con prisa, como si el tiempo le debiera algo. Nunca me saludó, pero tampoco" +
                " molestaba.", "Limpieza", limpieza, true);
        Dialogo rell1 = new Dialogo("Una vez dejó caer unos papeles y ni se dio cuenta. Los recogí y se los puse en su escritorio." +
                " Nunca dijo nada.", "Limpieza", limpieza, true);

        Dialogo des1 = new Dialogo("De nada. Si necesita algo, con gusto puedo ayudar.", "Limpieza", limpieza, true);

        // Segunda decisión
        Dialogo de2 = new Dialogo("¿Quiere saber dónde estaba anoche o ya terminó?", "Limpieza", limpieza, false);
        de2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió todo?", "Gracias. Eso es todo por ahora.")));

        Dialogo res2 = new Dialogo("En los baños, siempre empiezo por ahí. No escuché nada raro, solo el eco de mis pasos.", "Limpieza", limpieza, true);
        Dialogo rell2 = new Dialogo("A veces, mientras limpio, me encuentro con cosas que otros dejan olvidadas. Papeles, llaves," +
                " hasta notas raras. Supongo que es parte del trabajo.", "Limpieza", limpieza, true);

        // Dato clave
        /*añadirAlDiario("Limpieza", "Suele encontrarse con cosas útiles al estar solo limpiando.");*/

        Dialogo des2 = new Dialogo("Que tenga buena noche, jefe.", "Limpieza", limpieza, true);

        //Cambio de personaje, Aqui empieza la Esposa del jefe

        Dialogo e1 = new Dialogo("Buenas noches. Estoy hablando con todos los presentes en el museo. ¿Podría decirme quién es usted?", "Detective", detective, true);
        Dialogo e2 = new Dialogo("Soy la esposa del director del museo. Esta noche lo acompañé al evento, como es habitual en ocasiones" +
                " importantes.", "Esposa", esposa, true);
        Dialogo e3 = new Dialogo("No suelo involucrarme en los asuntos del museo, pero conozco bien a quienes lo rodean.", "Esposa", esposa, true);

        // Primera decisión
        Dialogo d1 = new Dialogo("¿Desea preguntarme algo más, detective?", "Esposa", esposa, false);
        d1.setOpciones(new LinkedList<>(Arrays.asList("¿Qué opinión tenía del economista?", "Gracias. No tengo más preguntas por ahora.")));
        ;
        Dialogo rp1 = new Dialogo("Era un hombre reservado, meticuloso. Mi esposo confiaba en él, aunque yo siempre lo encontré..." +
                " difícil de leer.", "Esposa", esposa, true);
        Dialogo relle1 = new Dialogo("A veces me preguntaba si ocultaba algo. Pero supongo que todos lo hacemos, ¿no cree?", "Esposa", esposa, true);

        Dialogo despdid1 = new Dialogo("Muy bien. Si recuerda algo más, estaré en el vestíbulo. Prefiero no quedarme sola por ahora.", "Esposa", esposa, true);

        // Segunda decisión
        Dialogo d2 = new Dialogo("¿Desea saber dónde estaba durante el incidente o prefiere dejarlo aquí?", "Esposa", esposa, false);
        d2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo.")));

        Dialogo rp2 = new Dialogo("Estaba en el salón principal, conversando con algunos invitados. No vi ni escuché nada fuera de " +
                "lo común.", "Esposa", esposa, true);
        Dialogo relle2 = new Dialogo("Aunque, para ser sincera, últimamente he notado cierta tensión en el ambiente. Nada concreto..." +
                " solo una sensación.", "Esposa", esposa, true);

        Dialogo despdid2 = new Dialogo("A usted, detective. Espero que encuentre respuestas pronto.", "Esposa", esposa, true);


    }

}
