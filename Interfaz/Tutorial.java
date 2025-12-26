package Interfaz;

import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.Escenarios.Entrada;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.MiniJuego.MinijuegoInterfaz;
import DatosAuxiliaresLogica.EfectosEspeciales;
import Logica.*;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tutorial extends JFrame {
    private static final Logger logger = Logger.getLogger(Tutorial.class.getName());
    private final Dimension tamPant;
    private Escenario tutorialParte1;
    private Escenario tutorialParte2;
    private Escenario tutorialParte2o;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;

    public Tutorial() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();

        tutorialParte1 = new Escenario("Tutorial Parte 1", "Punto inicial de partida", true);
        tutorialParte2 = new Escenario("Tutorial Parte 2-1", "Punto inicial de partida", true);
        tutorialParte2o = new Escenario("Tutorial Parte 2-2", "Punto inicial de partida", true);

        crearDialogosParte1();
        initComponents();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                crearDialogosParte2();
                ponerDialogoParte2();
            }
        };
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {
               dispose();
            }
        };

    }



    private void initComponents() {
        // Reproductor
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.cambiarMusicaNombre("Galeria Silenciosa");
        // Efectos especiales
        EfectosEspeciales e = EfectosEspeciales.getInstancia();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fondo = new JLabel();
        cajaTexto = new JPanel();

        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 0));
        cajaTexto.setOpaque(false);
        cajaTexto.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.setLayout(null);

        fondo.setFocusable(false);
        fondo.setMaximumSize(tamPant);
        fondo.setMinimumSize(tamPant);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0, 0, tamPant.width, tamPant.height);

        getContentPane().add(cajaTexto, 0);
        getContentPane().add(fondo, 1);
        ponerFondoParte1(0);
        ponerDialogoParte1();
        pack();
    }
    public void ponerDialogoParte1() {
        if(tutorialParte1.getNodoDialActual() == null || !(tutorialParte1.getArbolDial().nodeIsLeaf(tutorialParte1.getNodoDialActual()))) {

            Dialogo aux = tutorialParte1.getDialogoSiguiente(1);
            int nivelActualDial = tutorialParte1.getArbolDial().nodeLevel(tutorialParte1.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte1MouseClicked(evt);
                }
            });
            cT.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial == 1 || nivelActualDial == 3 || nivelActualDial == 5 || nivelActualDial == 9 || nivelActualDial == 17 || nivelActualDial == 34
                    || nivelActualDial == 43 || nivelActualDial == 61 || nivelActualDial == 76 || nivelActualDial == 82|| nivelActualDial == 85) {
                ponerFondoParte1(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }

        else {
            cajaTexto.removeAll();
            crearMinijuego();
            timer.schedule(tarea, 5000);

        }
        getContentPane().revalidate();
        getContentPane().repaint();



    }

    public void ponerDialogoParte2() {

        if(tutorialParte2.getNodoDialActual() == null || !(tutorialParte2.getArbolDial().nodeIsLeaf(tutorialParte2.getNodoDialActual()))) {
            if(!(tutorialParte2.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte2.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte2.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte2.getArbolDial().nodeLevel(tutorialParte2.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte2MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial == 0) {
                ponerFondoParte2(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }

        }else{
            ponerDialogoParte2o();
        }
    }

    public void ponerDialogoParte2o() {

        if(tutorialParte2o.getNodoDialActual() == null || !(tutorialParte2o.getArbolDial().nodeIsLeaf(tutorialParte2o.getNodoDialActual()))) {
            if(!(tutorialParte2o.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte2o.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte2o.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte2o.getArbolDial().nodeLevel(tutorialParte2o.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte2oMouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial ==0) {
                ponerFondoParte2(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }


        }else {
            iniciarMundo();
        }
    }




    public void ponerFondoParte1(int nivelActualDial){
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 1.jpg"));
                    break;
                case 1:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/1763958158881.jpg"));
                    break;
                case 3:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 3.jpg"));
                    break;
                case 5:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 4.jpg"));
                    break;
                case 9:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 5.png"));
                    break;
                case 17:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 6.jpg"));
                    break;
                case 34:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena Auto.jpg"));
                    EfectosEspeciales e = EfectosEspeciales.getInstancia();
                    e.efectoDeLluvia();
                    break;
                case 43:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por afuera.jpg"));
                    EfectosEspeciales e1 = EfectosEspeciales.getInstancia();
                    e1.detenerEfecto();
                    break;
                case 61:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));
                    EfectosEspeciales e2 = EfectosEspeciales.getInstancia();
                    e2.detenerEfecto();
                    break;
                case 76:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 1.jpg"));
                    break;
                case 82:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 3.jpg"));
                    break;
                case 85:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;
            }

            ImageIcon icono = new ImageIcon(imagen != null ? imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH) : null);
            fondo.setIcon(icono); // NOI18N


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void ponerFondoParte2(int nivelActualDial) {
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;

            }

            ImageIcon icono = new ImageIcon(Objects.requireNonNull(imagen).getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void ponerFondoParte2o(int nivelActualDial) {
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;

            }

            ImageIcon icono = new ImageIcon(Objects.requireNonNull(imagen).getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void crearDialogosParte1() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon amante = new ImageIcon("DatosAuxiliares/Personajes/Amante.png");
        ImageIcon conserge = new ImageIcon("DatosAuxiliares/Personajes/Conserge.png");
        ImageIcon esposa = new ImageIcon("DatosAuxiliares/Personajes/Esposa.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.jpg");
        ImageIcon vagabundo = new ImageIcon("DatosAuxiliares/Personajes/Vagabundo.jpg");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");

        Dialogo d1= new Dialogo("Ha sido una semana larga. Casi siento que no lo logro.  ", "", nada, true);
        Dialogo d2= new Dialogo("Creo que me merezco un pequeño descanso. Es sabado en la noche despues de todo, no creo que ocurra ningun problema. ", "", nada, true);
        Dialogo d3= new Dialogo("Voy a prender el televisor un momento, en la oficina me comentaron que hoy iban a pasar una pelicula muy buena a esta hora. ", "", nada, true);
        Dialogo d4= new Dialogo("Pondre el canal 34 a revisar si es verdad. Vere los primeros 10 minutos, si no me gusta como empieza no me va a interesar como termine.  ", "", nada, true);
        Dialogo d5= new Dialogo("Todavia necesito ponerme al dia con One Piece. No puedo desperdiciar el tiempo", "", nada, true);
        Dialogo d6= new Dialogo("-50 minutos despues.-", "", nada, true);
        Dialogo d7= new Dialogo("Ok. He de decir que la pelicula en realidad esta bastante buena", "", nada, true);
        Dialogo d8= new Dialogo("La tematica de que sean superheroes esta gastada, pero no me esperaba que de repente hubiera una invasion alienigena y un viajero temporal robotico llegara a salvar el dia, mientras que el segundo grupo fuera por ahoi cazando mitos con un perro.", "", nada, true);
        Dialogo d9= new Dialogo("No me queda nada mas que tragarme mi orgullo, esto parece que promete para una saga entera co 5 spin-off", "", nada, true);
        Dialogo d10= new Dialogo("Interrumpimos la programacion planificada para reportar una noticia de ultima hora.", "Presentadora", nada, true);
        Dialogo d11= new Dialogo("Genial. Justo cuando estaba la mejor parte. Solo espero que me lo pongan justo donde se quedo. No tolelaria perderme el final", "", nada, true);
        Dialogo d12= new Dialogo("Se reporta que en el famoso museo Logrenveich, creo que se pronuncia asi, se ha encontrado el cuerpo sin vida de uno de sus empleados.", "Presentadorea", nada, true);
        Dialogo d13= new Dialogo("¿Eso no es el museo en donde se iba a llevar a cabo la exposicion del Magnamen rojo?.", "", nada, true);
        Dialogo d14= new Dialogo("Con su exposicion mas ambiciosa la semana que viene, en donde hace presencia de su mas reciente adquisicion, El Magnamen rojo, el tercer rubi mas grande del mundo. El museo pasa por un momento delicado cuanto menos", "Presentadora", nada, true);
        Dialogo d15= new Dialogo("No se por que, pero presiento que de alguna forma me va a afectar esto.", "", nada, true);
        Dialogo d16= new Dialogo("- Ring ring ring ring ring.-", "", nada, true);
        Dialogo d17= new Dialogo("El mensajero de las malas noticias.", "", nada, true);
        Dialogo d18= new Dialogo("Espero que aprovecharas tus vacaciones", "Jefe", nada, true);
        Dialogo d19= new Dialogo("Algo asi.", "", nada, true);
        Dialogo d20= new Dialogo("¿Estas ocupado?.", "Jefe", nada, true);
        Dialogo d21= new Dialogo("La verdad es que si, ando en pijama, viendo una pelicula con mi taza favorita", "", nada, true);
        Dialogo d22= new Dialogo("Perfecto, estas libre entonces.", "Jefe", nada, true);
        Dialogo d23= new Dialogo("No he dicho eso.", "", nada, true);
        Dialogo d24= new Dialogo("Te necesitamos en el museo Logrimbver.", "Jefe", nada, true);
        Dialogo d25= new Dialogo("¿Tiene que ver con el cuerpo hallado?.", "", nada, true);
        Dialogo d26= new Dialogo("¿Como te enteraste?", "Jefe", nada, true);
        Dialogo d27= new Dialogo("Lo acaban de decir por el televisor", "", nada, true);
        Dialogo d28= new Dialogo("Mejor, ya estas enterado de la situacion entonces. Tienes 20 minutos para llegar.", "Jefe", nada, true);
        Dialogo d29= new Dialogo("Pero nunca estuve de acuerdo para ir en primer lugar.", "", nada, true);
        Dialogo d30= new Dialogo("-Biiiippppp.-", "", nada, true);
        Dialogo d31= new Dialogo("Demonios.", "", nada, true);
        Dialogo d32= new Dialogo("Parece que se acabo el descanso.", "", nada, true);
        Dialogo d33= new Dialogo("Y yo que ya estaba a punto de terminar la pelicula. Supongo que sera en otra ocasion.", "", nada, true);
        Dialogo d34= new Dialogo("Pero primero necesito ponerme una ropa adecuada para el trabajo.", "", nada, true);
        Dialogo d35= new Dialogo("Se reportan que para el dia de hoy habra fuertes lluvias en algunas regiones de la ciudad.","Emisora", nada, true);
        Dialogo d36= new Dialogo("No me digas. Recien me entero", "", nada, true);
        Dialogo d37= new Dialogo("Mira que tener que levantarme de mi comodo sillon solo para tener que investigar un caso a estas horas de la noche. Ser el mejor en lo que hago no siempre es bueno.", "", nada, true);
        Dialogo d38= new Dialogo("Ahora por lo que todos estaban esperando, la cancion You are like like a rainbow.", "Emisora", nada, true);
        Dialogo d39= new Dialogo("¿Quien le pone esos titulos horrorosos a las canciones?.", "", nada, true);
        Dialogo d40= new Dialogo("Oh baby, you are like a rainbow. Is hurting in my vains like hurricain. Your body, your booty and your eyes are like a bomb in myn heart.", "Cantante", nada, true);
        Dialogo d41= new Dialogo("Parece que no solo no saben poner nombres, tampoco saben componer ni cantar.", "", nada, true);
        Dialogo d42= new Dialogo("Voy a apagarlo. No se como los jovenes soportan este tipo de canciones hoy en dia.", "", nada, true);
        Dialogo d43= new Dialogo("-15 minutos despues-", "", nada, true);
        Dialogo d44= new Dialogo("Hola detective. Espero que haya descansado bien.", "Policia", policia, true);
        Dialogo d45= new Dialogo("Lo justo como para no volverme loco", "Detective", detective, true);
        Dialogo d46= new Dialogo("Si. Este trabajo tiende a hacer eso con la gente. Lo que vemos aqui puede quebrar a mas de uno.", "Policia", policia, true);
        Dialogo d47= new Dialogo("Y eso que todavia no has visto la mitad de lo que este trabajo tiene para ofrecer.", "Detective", detective, true);
        Dialogo d48= new Dialogo("Ni siquiera quiero imaginarlo.", "Policia", policia, true);
        Dialogo d49= new Dialogo("La verdad que no. ", "Detective", detective, true);
        Dialogo d50= new Dialogo("Y bien. ¿Con que exactamente estamos lidiando hoy? Escuche algo antes de venir, pero solamente algunos detalles.", "Detective", detective, true);
        Dialogo d51= new Dialogo("Hace aproximadamente una hora se encontro el cuerpo sin vida de Elliot Majader. Un economico encargado de las finanzas de museo.", "Policia", policia, true);
        Dialogo d52= new Dialogo("¿Que sabemos de el?", "Detective", detective, true);
        Dialogo d53= new Dialogo("No mucho. Una persona solitaria, sin amigos, no hablaba con nadie en el museo, sin pareja, probablemente sin amigos. Vivia en un apartamento pequeño en un rincon de la ciudad.", "Policia", policia, true);
        Dialogo d54= new Dialogo("¿Algun familiar cercano?", "Detective", detective, true);
        Dialogo d55= new Dialogo("Ninguno hasta donde sabemos.", "Policia", policia, true);
        Dialogo d56= new Dialogo("Interesante. La victima es una sombra, del tipo del que nadie se daria cuenta que desaparecio.", "Detective", detective, true);
        Dialogo d57= new Dialogo("Excepto que en este caso encontraron el cuerpo ", "Policia", policia, true);
        Dialogo d58= new Dialogo("¿Quien lo encontro?", "Detective", detective, true);
        Dialogo d59= new Dialogo("El guardia de seguridad. Se percato de que en un sector fallaban las camaras y decidio investigar la razon. Y bueno, encontro el cadaver de Elliot.", "Policia", policia, true);
        Dialogo d60= new Dialogo("Guiame hacia alli", "Detective", detective, true);
        Dialogo d61= new Dialogo("Claro, seria por este camino.", "Policia", policia, true);
        Dialogo d62= new Dialogo("Dime lo que han encontrado en la escena del crimen", "Detective", detective, true);
        Dialogo d63= new Dialogo("No mucho, el unico forense que queda trabajando a esta hora esta ocupado en otro caso.", "Policia", policia, true);
        Dialogo d64= new Dialogo("¿Y el resto?", "Detective", detective, true);
        Dialogo d65= new Dialogo("En su casa probablemente. ", "Policia", policia, true);
        Dialogo d66= new Dialogo("Por supuesto, ellos se pueden quedar en su casa descansando, pero yo tengo que venir a revisar una escena del crimen a esta hora de la noche.", "Detective", detective, true);
        Dialogo d67= new Dialogo("El jefe comento que usted era mas que suficiente para este caso. Comento que es hora de poner en practica esas habilidades tan especiales que dice tener.", "Policia", policia, true);
        Dialogo d68= new Dialogo("Razon no le falta. Soy el mejor en lo que hago. Pero eso no niega de que cuando termine exigire un dia de descanso en compensacion.", "Detective", detective, true);
        Dialogo d69= new Dialogo("Tambien menciono que diria algo como eso.", "Policia", policia, true);
        Dialogo d70= new Dialogo("No se preocupe detective. El caso esta practicamente cerrado. ", "Dueño", dueno, true);
        Dialogo d71= new Dialogo("¿Disculpa?", "Detective", detective, true);
        Dialogo d72= new Dialogo("Disculpado. Simplemente no hay necesidad de investigaciones posteriores. El pobre no pudo soportar la presion y escogio el camino facil.", "Dueño", dueno, true);
        Dialogo d73= new Dialogo("¿Acaso insinua que se suicido?", "Detective", detective, true);
        Dialogo d74= new Dialogo("No lo digo yo, las evidencias son claras.", "Dueño", dueno, true);
        Dialogo d75= new Dialogo("Eso lo decido yo, y por favor deje de molestar, interrumpe mi trabajo. Vamonos oficial", "Detective", detective, true);
        Dialogo d76= new Dialogo("Vera lo que digo cuando lo vea. ", "Dueño", dueno, true);
        Dialogo d77= new Dialogo("Podra ser excentrico y pesado, pero probablemente tenga razon. La escena apunta a un suicidio causado por el estres.", "Policia", policia, true);
        Dialogo d78= new Dialogo("Poco probable, los suicidios generados por trabajo rara vez ocurren en el centro laboral. Ademas segun lo que me comentaste su personalidad era mas bien reservada, dudo mucho que quisiera irse de forma tan llamativa.", "Detective", detective, true);
        Dialogo d79= new Dialogo("¿Y si se equivoca?", "Policia", policia, true);
        Dialogo d80= new Dialogo("Pocas veces lo hago.", "Detective", detective, true);
        Dialogo d81= new Dialogo("Eso he oido. Su ratio de casos resueltos es el mas alto en el cuartel. ", "Policia", policia, true);
        Dialogo d82= new Dialogo("Sin contar en los que he ayudado a cerrar como asesor.", "Detective", detective, true);
        Dialogo d83= new Dialogo("¿Es aqui?.", "Detective", detective, true);
        Dialogo d84= new Dialogo("Si, detras de esta puerta.", "Policia", policia, true);
        Dialogo d85= new Dialogo("Bueno, entonces vamos a entrar. ", "Detective", detective, true);
        Dialogo d86= new Dialogo("¿Por cierto, traiste el equipo para realizar el trabajo forense?.", "Detective", detective, true);
        Dialogo d87= new Dialogo("Si, aqui lo tengo", "Policia", policia, true);
        Dialogo d88= new Dialogo("Perfecto.", "Detective", detective, true);
        Dialogo d89= new Dialogo("Es hora de hacer mi trabajo. Mientras tanto, organiza a todo el personal del centro en la recepcion.", "Detective", detective, true);
        Dialogo d90= new Dialogo("Como ordene.", "Policia", policia, true);
        Dialogo d91= new Dialogo("Entonces solo quedamos tu y yo amigos. Dime como fue que terminaste asi.", "Detective", detective, true);


        BinaryTreeNode<Dialogo> node1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> node2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> node3 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> node4 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> node5 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> node6 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> node7 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> node8 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> node9 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> node10 = new BinaryTreeNode<>(d10);
        BinaryTreeNode<Dialogo> node11 = new BinaryTreeNode<>(d11);
        BinaryTreeNode<Dialogo> node12 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> node13 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> node14 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> node15 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> node16 = new BinaryTreeNode<>(d16);
        BinaryTreeNode<Dialogo> node17 = new BinaryTreeNode<>(d17);
        BinaryTreeNode<Dialogo> node18 = new BinaryTreeNode<>(d18);
        BinaryTreeNode<Dialogo> node19 = new BinaryTreeNode<>(d19);
        BinaryTreeNode<Dialogo> node20 = new BinaryTreeNode<>(d20);
        BinaryTreeNode<Dialogo> node21 = new BinaryTreeNode<>(d21);
        BinaryTreeNode<Dialogo> node22 = new BinaryTreeNode<>(d22);
        BinaryTreeNode<Dialogo> node23 = new BinaryTreeNode<>(d23);
        BinaryTreeNode<Dialogo> node24 = new BinaryTreeNode<>(d24);
        BinaryTreeNode<Dialogo> node25 = new BinaryTreeNode<>(d25);
        BinaryTreeNode<Dialogo> node26 = new BinaryTreeNode<>(d26);
        BinaryTreeNode<Dialogo> node27 = new BinaryTreeNode<>(d27);
        BinaryTreeNode<Dialogo> node28 = new BinaryTreeNode<>(d28);
        BinaryTreeNode<Dialogo> node29 = new BinaryTreeNode<>(d29);
        BinaryTreeNode<Dialogo> node30 = new BinaryTreeNode<>(d30);
        BinaryTreeNode<Dialogo> node31 = new BinaryTreeNode<>(d31);
        BinaryTreeNode<Dialogo> node32 = new BinaryTreeNode<>(d32);
        BinaryTreeNode<Dialogo> node33 = new BinaryTreeNode<>(d33);
        BinaryTreeNode<Dialogo> node34 = new BinaryTreeNode<>(d34);
        BinaryTreeNode<Dialogo> node35 = new BinaryTreeNode<>(d35);
        BinaryTreeNode<Dialogo> node36 = new BinaryTreeNode<>(d36);
        BinaryTreeNode<Dialogo> node37 = new BinaryTreeNode<>(d37);
        BinaryTreeNode<Dialogo> node38 = new BinaryTreeNode<>(d38);
        BinaryTreeNode<Dialogo> node39 = new BinaryTreeNode<>(d39);
        BinaryTreeNode<Dialogo> node40 = new BinaryTreeNode<>(d40);
        BinaryTreeNode<Dialogo> node41 = new BinaryTreeNode<>(d41);
        BinaryTreeNode<Dialogo> node42 = new BinaryTreeNode<>(d42);
        BinaryTreeNode<Dialogo> node43 = new BinaryTreeNode<>(d43);
        BinaryTreeNode<Dialogo> node44 = new BinaryTreeNode<>(d44);
        BinaryTreeNode<Dialogo> node45 = new BinaryTreeNode<>(d45);
        BinaryTreeNode<Dialogo> node46 = new BinaryTreeNode<>(d46);
        BinaryTreeNode<Dialogo> node47 = new BinaryTreeNode<>(d47);
        BinaryTreeNode<Dialogo> node48 = new BinaryTreeNode<>(d48);
        BinaryTreeNode<Dialogo> node49 = new BinaryTreeNode<>(d49);
        BinaryTreeNode<Dialogo> node50 = new BinaryTreeNode<>(d50);
        BinaryTreeNode<Dialogo> node51 = new BinaryTreeNode<>(d51);
        BinaryTreeNode<Dialogo> node52 = new BinaryTreeNode<>(d52);
        BinaryTreeNode<Dialogo> node53 = new BinaryTreeNode<>(d53);
        BinaryTreeNode<Dialogo> node54 = new BinaryTreeNode<>(d54);
        BinaryTreeNode<Dialogo> node55 = new BinaryTreeNode<>(d55);
        BinaryTreeNode<Dialogo> node56 = new BinaryTreeNode<>(d56);
        BinaryTreeNode<Dialogo> node57 = new BinaryTreeNode<>(d57);
        BinaryTreeNode<Dialogo> node58 = new BinaryTreeNode<>(d58);
        BinaryTreeNode<Dialogo> node59 = new BinaryTreeNode<>(d59);
        BinaryTreeNode<Dialogo> node60 = new BinaryTreeNode<>(d60);
        BinaryTreeNode<Dialogo> node61 = new BinaryTreeNode<>(d61);
        BinaryTreeNode<Dialogo> node62 = new BinaryTreeNode<>(d62);
        BinaryTreeNode<Dialogo> node63 = new BinaryTreeNode<>(d63);
        BinaryTreeNode<Dialogo> node64 = new BinaryTreeNode<>(d64);
        BinaryTreeNode<Dialogo> node65 = new BinaryTreeNode<>(d65);
        BinaryTreeNode<Dialogo> node66 = new BinaryTreeNode<>(d66);
        BinaryTreeNode<Dialogo> node67 = new BinaryTreeNode<>(d67);
        BinaryTreeNode<Dialogo> node68 = new BinaryTreeNode<>(d68);
        BinaryTreeNode<Dialogo> node69 = new BinaryTreeNode<>(d69);
        BinaryTreeNode<Dialogo> node70 = new BinaryTreeNode<>(d70);
        BinaryTreeNode<Dialogo> node71 = new BinaryTreeNode<>(d71);
        BinaryTreeNode<Dialogo> node72 = new BinaryTreeNode<>(d72);
        BinaryTreeNode<Dialogo> node73 = new BinaryTreeNode<>(d73);
        BinaryTreeNode<Dialogo> node74 = new BinaryTreeNode<>(d74);
        BinaryTreeNode<Dialogo> node75 = new BinaryTreeNode<>(d75);
        BinaryTreeNode<Dialogo> node76 = new BinaryTreeNode<>(d76);
        BinaryTreeNode<Dialogo> node77 = new BinaryTreeNode<>(d77);
        BinaryTreeNode<Dialogo> node78 = new BinaryTreeNode<>(d78);
        BinaryTreeNode<Dialogo> node79 = new BinaryTreeNode<>(d79);
        BinaryTreeNode<Dialogo> node80 = new BinaryTreeNode<>(d80);
        BinaryTreeNode<Dialogo> node81 = new BinaryTreeNode<>(d81);
        BinaryTreeNode<Dialogo> node82 = new BinaryTreeNode<>(d82);
        BinaryTreeNode<Dialogo> node83 = new BinaryTreeNode<>(d83);
        BinaryTreeNode<Dialogo> node84 = new BinaryTreeNode<>(d84);
        BinaryTreeNode<Dialogo> node85 = new BinaryTreeNode<>(d85);
        BinaryTreeNode<Dialogo> node86 = new BinaryTreeNode<>(d86);
        BinaryTreeNode<Dialogo> node87 = new BinaryTreeNode<>(d87);
        BinaryTreeNode<Dialogo> node88 = new BinaryTreeNode<>(d88);
        BinaryTreeNode<Dialogo> node89 = new BinaryTreeNode<>(d89);
        BinaryTreeNode<Dialogo> node90 = new BinaryTreeNode<>(d90);
        BinaryTreeNode<Dialogo> node91 = new BinaryTreeNode<>(d91);


        GeneralTree<Dialogo> auxTree = new GeneralTree<>();

        auxTree.insertNode(node1, null);
        auxTree.insertNode(node2, node1);
        auxTree.insertNode(node3, node2);
        auxTree.insertNode(node4, node3);
        auxTree.insertNode(node5, node4);
        auxTree.insertNode(node6, node5);
        auxTree.insertNode(node7, node6);
        auxTree.insertNode(node8, node7);
        auxTree.insertNode(node9, node8);
        auxTree.insertNode(node10, node9);
        auxTree.insertNode(node11, node10);
        auxTree.insertNode(node12, node11);
        auxTree.insertNode(node13, node12);
        auxTree.insertNode(node14, node13);
        auxTree.insertNode(node15, node14);
        auxTree.insertNode(node16, node15);
        auxTree.insertNode(node17, node16);
        auxTree.insertNode(node18, node17);
        auxTree.insertNode(node19, node18);
        auxTree.insertNode(node20, node19);
        auxTree.insertNode(node21, node20);
        auxTree.insertNode(node22, node21);
        auxTree.insertNode(node23, node22);
        auxTree.insertNode(node24, node23);
        auxTree.insertNode(node25, node24);
        auxTree.insertNode(node26, node25);
        auxTree.insertNode(node27, node26);
        auxTree.insertNode(node28, node27);
        auxTree.insertNode(node29, node28);
        auxTree.insertNode(node30, node29);
        auxTree.insertNode(node31, node30);
        auxTree.insertNode(node32, node31);
        auxTree.insertNode(node33, node32);
        auxTree.insertNode(node34, node33);
        auxTree.insertNode(node35, node34);
        auxTree.insertNode(node36, node35);
        auxTree.insertNode(node37, node36);
        auxTree.insertNode(node38, node37);
        auxTree.insertNode(node39, node38);
        auxTree.insertNode(node40, node39);
        auxTree.insertNode(node41, node40);
        auxTree.insertNode(node42, node41);
        auxTree.insertNode(node43, node42);
        auxTree.insertNode(node44, node43);
        auxTree.insertNode(node45, node44);
        auxTree.insertNode(node46, node45);
        auxTree.insertNode(node47, node46);
        auxTree.insertNode(node48, node47);
        auxTree.insertNode(node49, node48);
        auxTree.insertNode(node50, node49);
        auxTree.insertNode(node51, node50);
        auxTree.insertNode(node52, node51);
        auxTree.insertNode(node53, node52);
        auxTree.insertNode(node54, node53);
        auxTree.insertNode(node55, node54);
        auxTree.insertNode(node56, node55);
        auxTree.insertNode(node57, node56);
        auxTree.insertNode(node58, node57);
        auxTree.insertNode(node59, node58);
        auxTree.insertNode(node60, node59);
        auxTree.insertNode(node61, node60);
        auxTree.insertNode(node62, node61);
        auxTree.insertNode(node63, node62);
        auxTree.insertNode(node64, node63);
        auxTree.insertNode(node65, node64);
        auxTree.insertNode(node66, node65);
        auxTree.insertNode(node67, node66);
        auxTree.insertNode(node68, node67);
        auxTree.insertNode(node69, node68);
        auxTree.insertNode(node70, node69);
        auxTree.insertNode(node71, node70);
        auxTree.insertNode(node72, node71);
        auxTree.insertNode(node73, node72);
        auxTree.insertNode(node74, node73);
        auxTree.insertNode(node75, node74);
        auxTree.insertNode(node76, node75);
        auxTree.insertNode(node77, node76);
        auxTree.insertNode(node78, node77);
        auxTree.insertNode(node79, node78);
        auxTree.insertNode(node80, node79);
        auxTree.insertNode(node81, node80);
        auxTree.insertNode(node82, node81);
        auxTree.insertNode(node83, node82);
        auxTree.insertNode(node84, node83);
        auxTree.insertNode(node85, node84);
        auxTree.insertNode(node86, node85);
        auxTree.insertNode(node87, node86);
        auxTree.insertNode(node88, node87);
        auxTree.insertNode(node89, node88);
        auxTree.insertNode(node90, node89);
        auxTree.insertNode(node91, node90);

        tutorialParte1.setArbolDial(auxTree);
    }

    public void crearDialogosParte2() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon amante = new ImageIcon("DatosAuxiliares/Personajes/Amante.png");
        ImageIcon conserge = new ImageIcon("DatosAuxiliares/Personajes/Conserge.png");
        ImageIcon esposa = new ImageIcon("DatosAuxiliares/Personajes/Esposa.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.jpg");
        ImageIcon vagabundo = new ImageIcon("DatosAuxiliares/Personajes/Vagabundo.jpg");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");


        Dialogo d92 = new Dialogo("Detective, antes de entrar quiero explicarle cómo funcionará su investigación.", "Policia", policia, true);
        Dialogo d93 = new Dialogo("En la esquina superior izquierda tiene un diario. Allí se guardará toda la información importante que obtenga en cada diálogo.", "Policia", policia, true);
        Dialogo d94 = new Dialogo("Además, cuenta con un portafolios. En él se almacenarán los objetos relevantes que encuentre durante el caso.", "Policia", policia, true);
        Dialogo d95 = new Dialogo("Para que lo entienda mejor, el guardia encontró dos cosas en la escena: este cuchillo y una carta escrita por la víctima.", "Policia", policia, true);
        Dialogo d96 = new Dialogo("Perfecto. Entonces cada pista que obtenga en las conversaciones irá al diario, y cada objeto físico irá al portafolios.", "Detective", detective, true);
        Dialogo d97 = new Dialogo("Exactamente. Así podrá revisar todo lo que descubra en cualquier momento.", "Policia", policia, true);
        Dialogo d98 = new Dialogo("Muy bien, entregueme los objetos. Los guardaré en el portafolios.", "Detective", detective, true);
        Dialogo d99 = new Dialogo("Aquí tiene: el cuchillo y la carta. Ahora ya puede empezar a investigar.", "Policia", policia, true);

        // --- Primer árbol de decisión ---
        Dialogo decisionInicio = new Dialogo("¿Desea que le recuerde cómo usar el diario y el portafolios, o prefiere continuar?", "Policia", policia, true);
        decisionInicio.setOpciones(new LinkedList<>(Arrays.asList("Explíqueme otra vez cómo funciona el diario y el portafolios.", "No es necesario, ya entendí. Es hora de entrar.")));

        Dialogo respuestaA = new Dialogo("Claro. El diario guarda la información de los diálogos, y el portafolios los objetos físicos. Así nunca perderá nada importante.", "Policia", policia, true);

        Dialogo respuestaB = new Dialogo("Perfecto, detective. Adelante, el museo lo espera.", "Policia", policia, true);

        // Conexiones


        Dialogo d101 = new Dialogo("Ah, detective. Justo el héroe que necesitábamos. Aunque claro, yo podría resolver esto solo, pero me dijeron que usted se aburre si no lo llaman.", "Jefe", dueno, true);
        Dialogo d102 = new Dialogo("No se preocupe, estoy aquí para hacer mi trabajo.", "Detective", detective, true);
        Dialogo d103 = new Dialogo("Trabajo, dice. Bueno, al menos tiene un teléfono a su disposición para llamarme cuando se pierda... digo, cuando necesite orientación.", "Jefe", dueno, true);
        Dialogo d104 = new Dialogo("¿Un teléfono?", "Detective", detective, true);
        Dialogo d105 = new Dialogo("Sí, sí. Y no solo sirve para llamarme. También puede cambiar la música y los fondos de pantalla. Ya sabe, para que no se aburra mientras juega a ser Sherlock.", "Jefe", dueno, true);
        Dialogo d106 = new Dialogo("Interesante. Supongo que eso hará más llevadera la investigación.", "Detective", detective, true);
        Dialogo d107 = new Dialogo("Claro que sí. Pero lo mejor aún no lo he dicho: el mapa. El museo está dividido en varios escenarios, y en la pantalla verá flechas que le permitirán avanzar de uno a otro.", "Jefe", dueno, true);
        Dialogo d108 = new Dialogo("Así que puedo moverme libremente entre escenarios usando esas flechas.", "Detective", detective, true);
        Dialogo d109 = new Dialogo("Exacto. Y en cada escenario habrá objetos o personajes con los que podrá interactuar. Solo tiene que pulsarlos para recogerlos o hablar con ellos. Fácil, ¿no?", "Jefe", dueno, true);
        Dialogo d110 = new Dialogo("Eso será útil. Así podré obtener pistas y objetos directamente en cada lugar.", "Detective", detective, true);
        Dialogo d111 = new Dialogo("Exactamente. Aunque claro, si se pierde, siempre puede llamarme. No es que yo disfrute repetir las cosas, pero alguien tiene que salvarle la investigación.", "Jefe", dueno, true);

        Dialogo decisionJefe = new Dialogo("¿Quiere que le repita cómo usar el teléfono, el mapa y las interacciones, o ya se siente listo?", "Jefe", dueno, true);
        decisionJefe.setOpciones(new LinkedList<>(Arrays.asList("Repítalo, quiero estar seguro.", "No es necesario, ya entendí. Continuemos.")));

        Dialogo respJefeA = new Dialogo("Muy bien, aunque debería haberlo entendido a la primera. Teléfono para llamarme, música y fondos. Flechas para moverse entre escenarios. Y recuerde: pulse objetos o personajes para interactuar. ¿Contento?", "Jefe", dueno, true);

        Dialogo respJefeB = new Dialogo("Excelente. Al menos no tendré que repetirlo. Adelante, detective, el museo es suyo.", "Jefe", dueno, true);

        // Conexiones


        BinaryTreeNode<Dialogo> node92 = new BinaryTreeNode<>(d92);
        BinaryTreeNode<Dialogo> node93 = new BinaryTreeNode<>(d93);
        BinaryTreeNode<Dialogo> node94 = new BinaryTreeNode<>(d94);
        BinaryTreeNode<Dialogo> node95 = new BinaryTreeNode<>(d95);
        BinaryTreeNode<Dialogo> node96 = new BinaryTreeNode<>(d96);
        BinaryTreeNode<Dialogo> node97 = new BinaryTreeNode<>(d97);
        BinaryTreeNode<Dialogo> node98 = new BinaryTreeNode<>(d98);
        BinaryTreeNode<Dialogo> node99 = new BinaryTreeNode<>(d99);

        BinaryTreeNode<Dialogo> node300 = new BinaryTreeNode<>(decisionInicio);

        BinaryTreeNode<Dialogo> node301 = new BinaryTreeNode<>(respuestaA);
        BinaryTreeNode<Dialogo> node302 = new BinaryTreeNode<>(respuestaB);

        BinaryTreeNode<Dialogo> node101 = new BinaryTreeNode<>(d101);
        BinaryTreeNode<Dialogo> node102 = new BinaryTreeNode<>(d102);
        BinaryTreeNode<Dialogo> node103 = new BinaryTreeNode<>(d103);
        BinaryTreeNode<Dialogo> node104 = new BinaryTreeNode<>(d104);
        BinaryTreeNode<Dialogo> node105 = new BinaryTreeNode<>(d105);
        BinaryTreeNode<Dialogo> node106 = new BinaryTreeNode<>(d106);
        BinaryTreeNode<Dialogo> node107 = new BinaryTreeNode<>(d107);
        BinaryTreeNode<Dialogo> node108 = new BinaryTreeNode<>(d108);
        BinaryTreeNode<Dialogo> node109 = new BinaryTreeNode<>(d109);
        BinaryTreeNode<Dialogo> node110 = new BinaryTreeNode<>(d110);
        BinaryTreeNode<Dialogo> node111 = new BinaryTreeNode<>(d111);

        BinaryTreeNode<Dialogo> node303 = new BinaryTreeNode<>(decisionJefe);

        BinaryTreeNode<Dialogo> node304 = new BinaryTreeNode<>(respJefeA);
        BinaryTreeNode<Dialogo> node305 = new BinaryTreeNode<>(respJefeB);

        GeneralTree<Dialogo> auxTree = new GeneralTree<>();

        auxTree.insertNode(node92, null);
        auxTree.insertNode(node93, node92);
        auxTree.insertNode(node94, node93);
        auxTree.insertNode(node95, node94);
        auxTree.insertNode(node96, node95);
        auxTree.insertNode(node97, node96);
        auxTree.insertNode(node98, node97);
        auxTree.insertNode(node99, node98);
        auxTree.insertNode(node300, node99);

        auxTree.insertNode(node301, node300);
        auxTree.insertNode(node302, node300);

        GeneralTree<Dialogo> auxTree2 = new GeneralTree<>();

        auxTree2.insertNode(node101, null);
        auxTree2.insertNode(node102, node101);
        auxTree2.insertNode(node103, node102);
        auxTree2.insertNode(node104, node103);
        auxTree2.insertNode(node105, node104);
        auxTree2.insertNode(node106, node105);
        auxTree2.insertNode(node107, node106);
        auxTree2.insertNode(node108, node107);
        auxTree2.insertNode(node109, node108);
        auxTree2.insertNode(node110, node109);
        auxTree2.insertNode(node111, node110);
        auxTree2.insertNode(node303, node111);
        auxTree2.insertNode(node304, node303);
        auxTree2.insertNode(node305, node303);


        tutorialParte2.setArbolDial(auxTree);
        tutorialParte2o.setArbolDial(auxTree2);
    }

    public void crearMinijuego(){
        ImageIcon prueba = new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Cadaver.jpg");
        MiniJuego minijuego = new MiniJuego("Prueba", prueba);
        ObjetoEscenario ob1 = new ObjetoEscenario("Anillo", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/anillo.png"), 0.3F, 0.5F, 0.1F, 0.1F, true, "nada");
        ObjetoEscenario ob2 = new ObjetoEscenario("Carnet", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/carnet.png"), 0.4F, 0.1F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob3 = new ObjetoEscenario("Memoria USB", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/memoria.png"), 0.1F, 0.2F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob4 = new ObjetoEscenario("Mancha de sangre", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Mancha de sangre.png"), 0.7F, 0.7F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob5 = new ObjetoEscenario("Pluma", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/pluma.png"), 0.2F, 0.4F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob6 = new ObjetoEscenario("Tarjeta", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/tarjeta.png"), 0.3F, 0.2F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob7 = new ObjetoEscenario("Periodico", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/periodico.png"), 0.5F, 0.2F, 0.1F, 0.1F, true, "nada");
        ObjetoEscenario ob8 = new ObjetoEscenario("Telefono", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/telefono de la victima.png"), 0.03F, 0.1F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob9 = new ObjetoEscenario("Herida Grande", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Herida grande.png"), 0.55F, 0.9F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob10 = new ObjetoEscenario("Laptop", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Laptop.png"), 0.27F, 0.33F, 0.18F, 0.1F, false, "nada");
        ObjetoEscenario ob11 = new ObjetoEscenario("Cigarros", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/cigarros.png"), 0.62F, 0.2F, 0.19F, 0.1F, false, "nada");
        ObjetoEscenario ob12 = new ObjetoEscenario("Herida pequeña", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Herida pequeña.png"), 0.12F, 0.77F, 0.1F, 0.1F, true, "nada");

        minijuego.agregarObjetoCola(ob1);
        minijuego.agregarObjetoCola(ob2);
        minijuego.agregarObjetoCola(ob3);
        minijuego.agregarObjetoCola(ob4);
        minijuego.agregarObjetoCola(ob5);
        minijuego.agregarObjetoCola(ob6);
        minijuego.agregarObjetoCola(ob7);
        minijuego.agregarObjetoCola(ob8);
        minijuego.agregarObjetoCola(ob9);
        minijuego.agregarObjetoCola(ob10);
        minijuego.agregarObjetoCola(ob11);
        minijuego.agregarObjetoCola(ob12);

        MinijuegoInterfaz minijuegoInterfaz = new MinijuegoInterfaz(minijuego);

        minijuegoInterfaz.setBounds(0, 0, tamPant.width, tamPant.height);
        getContentPane().add(minijuegoInterfaz, 0);
        getContentPane().revalidate();
        getContentPane().repaint();

    }
    private void iniciarMundo(){
        Entrada entrada =  new Entrada();
        entrada.setVisible(true);
        timer2.schedule(tarea2, 2000);

    }
    private void cTParte1MouseClicked(MouseEvent evt) {
        ponerDialogoParte1();
        getContentPane().revalidate();
        getContentPane().repaint();

    }
    private void cTParte2MouseClicked(MouseEvent evt) {
        ponerDialogoParte2();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte2oMouseClicked(MouseEvent evt) {
        ponerDialogoParte2o();
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        EventQueue.invokeLater(() -> new Tutorial().setVisible(true));

    }

    private JLabel fondo;
    private JPanel cajaTexto;
    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
}


