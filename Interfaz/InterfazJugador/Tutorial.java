package Interfaz.InterfazJugador;


import Logica.Dialogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tutorial extends JFrame {
    private static final Logger logger = Logger.getLogger(Tutorial.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private ArrayList<Dialogo> dialogosTuto;

    /**
     * Creates new form Entrada
     */
    public Tutorial() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();

        dialogoActual=0;
        dialogosTuto = new ArrayList<>();
        crearDialogos();
        initComponents();


    }



    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fondo = new JLabel();
        cajaTexto = new JPanel();

        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);

        cajaTexto.setOpaque(false);
        cajaTexto.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.setLayout(null);

        getContentPane().add(cajaTexto);
        ponerFondo();
        ponerDialogo();
        pack();
    }
    public void ponerDialogo() {
        if(dialogoActual < dialogosTuto.size()) {
            Dialogo aux = dialogosTuto.get(dialogoActual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTMouseClicked(evt);
                }
            });

            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(dialogoActual==1 || dialogoActual== 3 || dialogoActual==5 || dialogoActual==9 || dialogoActual==17 || dialogoActual==34
                    || dialogoActual==43 || dialogoActual==61 || dialogoActual==76 || dialogoActual==82|| dialogoActual==85 ) {
                ponerFondo();
                getContentPane().revalidate();
                getContentPane().repaint();
            }
            dialogoActual++;
            }else {
            cajaTexto.removeAll();

        }


    }

    public void ponerFondo(){
        try {
            BufferedImage imagen = null;

            switch (dialogoActual) {
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
                    break;
                case 43:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por afuera.jpg"));
                    break;
                case 61:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));
                    break;
                case 76:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 1.jpg"));
                    break;
                case 82:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 3.jpg"));
                    break;
                case 85:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/oficin economico.jpg"));
                    break;
            }

            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono); // NOI18N
            fondo.setFocusable(false);
            fondo.setMaximumSize(tamPant);
            fondo.setMinimumSize(tamPant);
            fondo.setPreferredSize(tamPant);
            fondo.setBounds(0, 0, tamPant.width, tamPant.height);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getContentPane().add(fondo);
    }
    public void crearDialogos(){

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


    dialogosTuto.add(d1);
    dialogosTuto.add(d2);
    dialogosTuto.add(d3);
    dialogosTuto.add(d4);
    dialogosTuto.add(d5);
    dialogosTuto.add(d6);
    dialogosTuto.add(d7);
    dialogosTuto.add(d8);
    dialogosTuto.add(d9);
    dialogosTuto.add(d10);
    dialogosTuto.add(d11);
    dialogosTuto.add(d12);
    dialogosTuto.add(d13);
    dialogosTuto.add(d14);
    dialogosTuto.add(d15);
    dialogosTuto.add(d16);
    dialogosTuto.add(d17);
    dialogosTuto.add(d18);
    dialogosTuto.add(d19);
    dialogosTuto.add(d20);
    dialogosTuto.add(d21);
    dialogosTuto.add(d22);
    dialogosTuto.add(d23);
    dialogosTuto.add(d24);
    dialogosTuto.add(d25);
    dialogosTuto.add(d26);
    dialogosTuto.add(d27);
    dialogosTuto.add(d28);
    dialogosTuto.add(d29);
    dialogosTuto.add(d30);
    dialogosTuto.add(d31);
    dialogosTuto.add(d32);
    dialogosTuto.add(d33);
    dialogosTuto.add(d34);
    dialogosTuto.add(d35);
    dialogosTuto.add(d36);
    dialogosTuto.add(d37);
    dialogosTuto.add(d38);
    dialogosTuto.add(d39);
    dialogosTuto.add(d40);
    dialogosTuto.add(d41);
    dialogosTuto.add(d42);
    dialogosTuto.add(d43);
    dialogosTuto.add(d44);
    dialogosTuto.add(d45);
    dialogosTuto.add(d46);
    dialogosTuto.add(d47);
    dialogosTuto.add(d48);
    dialogosTuto.add(d49);
    dialogosTuto.add(d50);
    dialogosTuto.add(d51);
    dialogosTuto.add(d52);
    dialogosTuto.add(d53);
    dialogosTuto.add(d54);
    dialogosTuto.add(d55);
    dialogosTuto.add(d56);
    dialogosTuto.add(d57);
    dialogosTuto.add(d58);
    dialogosTuto.add(d59);
    dialogosTuto.add(d60);
    dialogosTuto.add(d61);
    dialogosTuto.add(d62);
    dialogosTuto.add(d63);
    dialogosTuto.add(d64);
    dialogosTuto.add(d65);
    dialogosTuto.add(d66);
    dialogosTuto.add(d67);
    dialogosTuto.add(d68);
    dialogosTuto.add(d69);
    dialogosTuto.add(d70);
    dialogosTuto.add(d71);
    dialogosTuto.add(d72);
    dialogosTuto.add(d73);
    dialogosTuto.add(d74);
    dialogosTuto.add(d75);
    dialogosTuto.add(d76);
    dialogosTuto.add(d77);
    dialogosTuto.add(d78);
    dialogosTuto.add(d79);
    dialogosTuto.add(d80);
    dialogosTuto.add(d81);
    dialogosTuto.add(d82);
    dialogosTuto.add(d83);
    dialogosTuto.add(d84);
    dialogosTuto.add(d85);
    dialogosTuto.add(d86);
    dialogosTuto.add(d87);
    dialogosTuto.add(d88);
    dialogosTuto.add(d89);
    dialogosTuto.add(d90);
    dialogosTuto.add(d91);

    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
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

