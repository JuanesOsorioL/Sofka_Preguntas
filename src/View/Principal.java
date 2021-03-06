package View;

import Controller.BDUtils;
import Model.Category;
import Model.Option;
import Model.Premio;
import Model.Question;
import Model.Round;
import java.sql.*;
import java.util.*;
import java.time.*;

public class Principal {

    public static void main(String[] args) throws SQLException {
        Round round = new Round();
        Scanner input = new Scanner(System.in);
        Category Category = new Category();
        int menu;
        boolean setting = false;
        boolean control = true;

        while (control) {
            System.out.println("******Menu******");
            System.out.println("1. Configurar Preguntas por defecto.");
            System.out.println("2. Configurar Preguntas.");
            System.out.println("3. Jugar.");
            System.out.println("4. Historial.");
            System.out.println("5. Salir.");
            menu = Integer.parseInt(input.nextLine());

            switch (menu) {
                case 1:
                    round = defaultQuestion(round);
                    setting = true;
                    break;

                case 2:
                    ArrayList<Category> listCategory = new ArrayList<Category>();
                    for (int i = 0; i < 5; i++) {
                        ArrayList<Question> listQuestion = new ArrayList<Question>();
                        System.out.print("Ingrese Nombre de la Categoria: ");
                        String nameCategory = input.nextLine();
                        System.out.print("Ingrese el nivel de dificultad de la Categoria en una escala de 1 a 5. no repetir numeros: ");
                        int Level = Integer.parseInt(input.nextLine());
                        for (int j = 0; j < 5; j++) {
                            System.out.print("Pregunta " + (j + 1) + " : ");
                            String nameQuestion = input.nextLine();
                            ArrayList<Option> listAnwer = new ArrayList<Option>();
                            for (int k = 0; k < 4; k++) {
                                boolean correct = false;
                                if (k == 0) {
                                    System.out.print("Respuesta " + (k + 1) + " ( Verdadera ) : ");
                                    correct = true;
                                } else {
                                    System.out.print("Respuesta " + (k + 1) + " ( Falsa ) : ");
                                }
                                String answer = input.nextLine();
                                Option respuesta = new Option(answer, correct);
                                listAnwer.add(k, respuesta);
                            }
                            Question question = new Question(nameQuestion, listAnwer);
                            listQuestion.add(j, question);
                        }
                        Category = new Category(nameCategory, listQuestion, (Level-1));
                        listCategory.add(i, Category);
                    }
                    round = new Round(listCategory);
                    setting = true;
                    break;

                case 3:
                    if (setting) {
                        System.out.print("Nombre Jugador: ");
                        String name = input.nextLine();
                        LocalDate Date = LocalDate.now();
                        Premio win = new Premio(1, name);
                        Category selectCategory;
                        Question selectQuestion;
                        for (int i = 0; i < 5; i++) {
                            selectCategory = new Category(round.SelecCategory(i));
                            System.out.println(selectCategory.getName());
                            System.out.println("Nivel : "+(selectCategory.getLevel() + 1));
                            selectQuestion = new Question(selectCategory.randomQuestion());
                            System.out.println(selectQuestion.getTitulo());
                            selectQuestion.randomoptions();
                            int n = input.nextInt();
                            if (selectQuestion.validate(n)) {
                                System.out.println("Respuesta correcta!!!!!");
                                System.out.println("Puntuaci??n actual = " + win.winQuestion(i + 1));
                                if ((selectCategory.getLevel() + 1) == 5) {
                                    System.out.println("Felicitaciones LLegaste al final con una Puntuaci??n perfecta.");
                                    n = 2;
                                } else {
                                    System.out.println("Quieres seguir con el pr??ximo nivel?.");
                                    System.out.println("1. Si");
                                    System.out.println("2. No");
                                    n = input.nextInt();
                                }
                                if (n == 2) {
                                    savePlayer(win.getNombre(), Date, win.getPremio(), selectCategory.getLevel() + 1);
                                    control = false;
                                    i = 5;
                                }
                            } else {
                                System.out.println("Respuesta incorrecta");
                                control = false;
                                System.out.println("Puntuaci??n actual = " + win.getPremio());
                                savePlayer(win.getNombre(), Date, win.getPremio(), selectCategory.getLevel());
                                i = 5;
                            }
                        }
                    } else {
                        System.out.println("Primero!! Registra las preguntas");
                    }
                    break;
                case 4:
                     BDUtils.GetHistorial();
                    break;

                    case 5:
                    control = false;
                    break;
                    
                default:
                    break;
            }
        }
    }

    
    
    
    public static void savePlayer(String player, LocalDate fecha, int score, int level) throws SQLException {
        try {
            BDUtils.Connection();
            int resulta = BDUtils.setGuardar(player, fecha, score, level);
            if (resulta == 1) {
                System.out.println("Jugador se guardo Correctamente");
            } else {
                System.out.println("Jugador no se guardo Correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BDUtils.desconectar();
        }
    }

    //se realizo esta funcion ya que no tenia claro si el programa debia de tener ya definidaslas preguntas 
    //"Precondiciones: Debe de tener 25 preguntas (5 preguntas por categor??as) para 5 rondas"
    
    public static Round defaultQuestion(Round round) {
        
        ArrayList<Category> listCategory = new ArrayList<Category>();
        ///////////////////////////categoria1
        ArrayList<Question> listQuestion = new ArrayList<Question>();
        // pretunta 1
        ArrayList<Option> listAnwer1 = new ArrayList<Option>();
        Option opt1 = new Option("30 minutos", true);
        Option opt2 = new Option("40 minutos", false);
        Option opt3 = new Option("60 minutos", false);
        Option opt4 = new Option("20 minutos", false);
        listAnwer1.add(opt1);
        listAnwer1.add(opt2);
        listAnwer1.add(opt3);
        listAnwer1.add(opt4);
        Question ques1 = new Question("??Cu??nto dura la pr??rroga en un partido de f??tbol?", listAnwer1);
        // pretunta 2
        ArrayList<Option> listAnwer2 = new ArrayList<Option>();
        Option opt5 = new Option("Brasil", true);
        Option opt6 = new Option("Argentina", false);
        Option opt7 = new Option("Colombia", false);
        Option opt8 = new Option("Venezuela", false);
        listAnwer2.add(opt5);
        listAnwer2.add(opt6);
        listAnwer2.add(opt7);
        listAnwer2.add(opt8);
        Question ques2 = new Question("??Qu?? selecci??n de f??tbol ha ganado m??s Mundiales?", listAnwer2);
        // pretunta 3
        ArrayList<Option> listAnwer3 = new ArrayList<Option>();
        Option opt9 = new Option("90 minutos", true);
        Option opt10 = new Option("190 minutos", false);
        Option opt11 = new Option("80 minutos", false);
        Option opt12 = new Option("30 minutos", false);
        listAnwer3.add(opt9);
        listAnwer3.add(opt10);
        listAnwer3.add(opt11);
        listAnwer3.add(opt12);
        Question ques3 = new Question("??Cu??nto dura un partido de f??tbol?", listAnwer3);
        // pretunta 4
        ArrayList<Option> listAnwer4 = new ArrayList<Option>();
        Option opt13 = new Option("Lionel Messi", true);
        Option opt14 = new Option("Cristiano Ronaldo", false);
        Option opt15 = new Option("James Rodriguez", false);
        Option opt16 = new Option("Iker Casillas", false);
        listAnwer4.add(opt13);
        listAnwer4.add(opt14);
        listAnwer4.add(opt15);
        listAnwer4.add(opt16);
        Question ques4 = new Question("??Qui??n es el m??ximo goleador del FC Barcelona?", listAnwer4);
        // pretunta 5
        ArrayList<Option> listAnwer5 = new ArrayList<Option>();
        Option opt17 = new Option("Real Madrid", true);
        Option opt18 = new Option("Barcelona", false);
        Option opt19 = new Option("Juventus", false);
        Option opt20 = new Option("Manchester United", false);
        listAnwer5.add(opt17);
        listAnwer5.add(opt18);
        listAnwer5.add(opt19);
        listAnwer5.add(opt20);
        Question ques5 = new Question("??Qu?? equipo ha ganado m??s Champions League de la historia?", listAnwer5);
        listQuestion.add(ques1);
        listQuestion.add(ques2);
        listQuestion.add(ques3);
        listQuestion.add(ques4);
        listQuestion.add(ques5);

        Category Category1 = new Category("Categoria de DEPORTES", listQuestion, 0);
        listCategory.add(Category1);

        //////////////////////////////////categoria2
        ArrayList<Question> listQuestion1 = new ArrayList<Question>();
        // pretunta 1
        ArrayList<Option> listAnwer6 = new ArrayList<Option>();
        Option opt21 = new Option("Louvre", true);
        Option opt22 = new Option("Galer??a Uffizi", false);
        Option opt23 = new Option("British Museum", false);
        Option opt24 = new Option("Museo del Prado", false);
        listAnwer6.add(opt21);
        listAnwer6.add(opt22);
        listAnwer6.add(opt23);
        listAnwer6.add(opt24);
        Question ques6 = new Question("??En que museo est?? la Mona Lisa?", listAnwer6);
        // pretunta 2
        ArrayList<Option> listAnwer7 = new ArrayList<Option>();
        Option opt25 = new Option("SIGLO XIX", true);
        Option opt26 = new Option("SIGLO XX", false);
        Option opt27 = new Option("SIGLO XVIII", false);
        Option opt28 = new Option("SIGLO XVII", false);
        listAnwer7.add(opt25);
        listAnwer7.add(opt26);
        listAnwer7.add(opt27);
        listAnwer7.add(opt28);
        Question ques7 = new Question("??En que siglo naci?? Van Gogh?", listAnwer7);
        // pretunta 3
        ArrayList<Option> listAnwer8 = new ArrayList<Option>();
        Option opt29 = new Option("SIGLO XV", true);
        Option opt30 = new Option("SIGLO XIII", false);
        Option opt31 = new Option("SIGLO XIV", false);
        Option opt32 = new Option("SIGLO XVI", false);
        listAnwer8.add(opt29);
        listAnwer8.add(opt30);
        listAnwer8.add(opt31);
        listAnwer8.add(opt32);
        Question ques8 = new Question("??En que siglo se inici?? el Renacimiento?", listAnwer8);
        // pretunta 4
        ArrayList<Option> listAnwer9 = new ArrayList<Option>();
        Option opt33 = new Option("1746", true);
        Option opt34 = new Option("1806", false);
        Option opt35 = new Option("1796", false);
        Option opt36 = new Option("1706", false);
        listAnwer9.add(opt33);
        listAnwer9.add(opt34);
        listAnwer9.add(opt35);
        listAnwer9.add(opt36);
        Question ques9 = new Question("??Cu??ndo naci?? Goya?", listAnwer9);
        //5
        ArrayList<Option> listAnwer10 = new ArrayList<Option>();
        Option opt37 = new Option("Miguel ??ngel", true);
        Option opt38 = new Option("Donatello", false);
        Option opt39 = new Option("Bernini", false);
        Option opt40 = new Option("Donatello V", false);
        listAnwer10.add(opt37);
        listAnwer10.add(opt38);
        listAnwer10.add(opt39);
        listAnwer10.add(opt40);
        Question ques10 = new Question("'La piedad' es una escultura de", listAnwer10);
        listQuestion1.add(ques6);
        listQuestion1.add(ques7);
        listQuestion1.add(ques8);
        listQuestion1.add(ques9);
        listQuestion1.add(ques10);

        Category Category3 = new Category("Categoria de ARTE", listQuestion1, 2);
        listCategory.add(Category3);

        //////////////////////////////////categoria3
        ArrayList<Question> listQuestion2 = new ArrayList<Question>();
        //1
        ArrayList<Option> listAnwer11 = new ArrayList<Option>();
        Option opt41 = new Option("Ballena azul", true);
        Option opt42 = new Option("Tiburon", false);
        Option opt43 = new Option("Tortuga", false);
        Option opt44 = new Option("Delfin", false);
        listAnwer11.add(opt41);
        listAnwer11.add(opt42);
        listAnwer11.add(opt43);
        listAnwer11.add(opt44);
        Question ques11 = new Question("??Cu??l es el mam??fero marino m??s grande?", listAnwer11);
        //2
        ArrayList<Option> listAnwer12 = new ArrayList<Option>();
        Option opt45 = new Option("Guepardo", true);
        Option opt46 = new Option("Leon", false);
        Option opt47 = new Option("Tigre", false);
        Option opt48 = new Option("Aguila", false);
        listAnwer12.add(opt45);
        listAnwer12.add(opt46);
        listAnwer12.add(opt47);
        listAnwer12.add(opt48);
        Question ques12 = new Question("??Cu??l es el mam??fero m??s r??pido del mundo?", listAnwer12);
        //3
        ArrayList<Option> listAnwer13 = new ArrayList<Option>();
        Option opt49 = new Option("Avestruz", true);
        Option opt50 = new Option("Aguila", false);
        Option opt51 = new Option("Condor", false);
        Option opt52 = new Option("Guacamaya", false);
        listAnwer13.add(opt49);
        listAnwer13.add(opt50);
        listAnwer13.add(opt51);
        listAnwer13.add(opt52);
        Question ques13 = new Question("??Cu??l es el ave m??s grande del mundo?", listAnwer13);
        //4
        ArrayList<Option> listAnwer14 = new ArrayList<Option>();
        Option opt53 = new Option("Indonesia", true);
        Option opt54 = new Option("Holanda", false);
        Option opt55 = new Option("Italia", false);
        Option opt56 = new Option("Espa??a", false);
        listAnwer14.add(opt53);
        listAnwer14.add(opt54);
        listAnwer14.add(opt55);
        listAnwer14.add(opt56);
        Question ques14 = new Question("??En qu?? pa??s habita el drag??n de Komodo?", listAnwer14);
        //5
        ArrayList<Option> listAnwer15 = new ArrayList<Option>();
        Option opt57 = new Option("Perezoso de tres dedos", true);
        Option opt58 = new Option("Tortuga", false);
        Option opt59 = new Option("Pez", false);
        Option opt60 = new Option("Hormiga", false);
        listAnwer15.add(opt57);
        listAnwer15.add(opt58);
        listAnwer15.add(opt59);
        listAnwer15.add(opt60);
        Question ques15 = new Question("??Cu??l es el animal m??s lento del mundo?", listAnwer15);
        listQuestion2.add(ques11);
        listQuestion2.add(ques12);
        listQuestion2.add(ques13);
        listQuestion2.add(ques14);
        listQuestion2.add(ques15);

        Category Category2 = new Category("Categoria de ANIMALES", listQuestion2, 1);
        listCategory.add(Category2);

        //////////////////////////categoria4
        ArrayList<Question> listQuestion3 = new ArrayList<Question>();
        //1
        ArrayList<Option> listAnwer16 = new ArrayList<Option>();
        Option opt61 = new Option("8", true);
        Option opt62 = new Option("10", false);
        Option opt63 = new Option("9", false);
        Option opt64 = new Option("7", false);
        listAnwer16.add(opt61);
        listAnwer16.add(opt62);
        listAnwer16.add(opt63);
        listAnwer16.add(opt64);
        Question ques16 = new Question("??Cu??ntos planetas conforman el sistema solar?", listAnwer16);
        //2
        ArrayList<Option> listAnwer17 = new ArrayList<Option>();
        Option opt65 = new Option("Venus", true);
        Option opt66 = new Option("Marte", false);
        Option opt67 = new Option("Mercurio", false);
        Option opt68 = new Option("Jupiter", false);
        listAnwer17.add(opt65);
        listAnwer17.add(opt66);
        listAnwer17.add(opt67);
        listAnwer17.add(opt68);
        Question ques17 = new Question("??Qu?? planeta posee la atm??sfera m??s caliente del sistema solar?", listAnwer17);
        //3
        ArrayList<Option> listAnwer18 = new ArrayList<Option>();
        Option opt69 = new Option("Tierra", true);
        Option opt70 = new Option("Mercurio", false);
        Option opt71 = new Option("Venus", false);
        Option opt72 = new Option("Marte", false);
        listAnwer18.add(opt69);
        listAnwer18.add(opt70);
        listAnwer18.add(opt71);
        listAnwer18.add(opt72);
        Question ques18 = new Question("??Cu??l es el planeta m??s denso del sistema solar?", listAnwer18);
        //4
        ArrayList<Option> listAnwer19 = new ArrayList<Option>();
        Option opt73 = new Option("Jupiter", true);
        Option opt74 = new Option("Tierra", false);
        Option opt75 = new Option("Marte", false);
        Option opt76 = new Option("Pluton", false);
        listAnwer19.add(opt73);
        listAnwer19.add(opt74);
        listAnwer19.add(opt75);
        listAnwer19.add(opt76);
        Question ques19 = new Question("??Cu??l es el planeta con m??s edad del sistema solar?", listAnwer19);
        //5
        ArrayList<Option> listAnwer20 = new ArrayList<Option>();
        Option opt77 = new Option("Marte", true);
        Option opt78 = new Option("Tierra", false);
        Option opt79 = new Option("Jupiter", false);
        Option opt80 = new Option("Urano", false);
        listAnwer20.add(opt77);
        listAnwer20.add(opt78);
        listAnwer20.add(opt79);
        listAnwer20.add(opt80);
        Question ques20 = new Question("??Cu??l es el segundo planeta m??s peque??o del sistema solar?", listAnwer20);
        listQuestion3.add(ques16);
        listQuestion3.add(ques17);
        listQuestion3.add(ques18);
        listQuestion3.add(ques19);
        listQuestion3.add(ques20);

        Category Category4 = new Category("Categoria de SISTEMA SOLAR", listQuestion3, 3);
        listCategory.add(Category4);

        //////////////////////////categoria5
        ArrayList<Question> listQuestion4 = new ArrayList<Question>();
        //1
        ArrayList<Option> listAnwer21 = new ArrayList<Option>();
        Option opt81 = new Option("6", true);
        Option opt82 = new Option("10", false);
        Option opt83 = new Option("9", false);
        Option opt84 = new Option("7", false);
        listAnwer21.add(opt81);
        listAnwer21.add(opt82);
        listAnwer21.add(opt83);
        listAnwer21.add(opt84);
        Question ques21 = new Question("Adivina cu??ntos a??os tengo sabiendo que la tercera parte de ellos menos 1 es igual a la sexta parte", listAnwer21);
        //2
        ArrayList<Option> listAnwer22 = new ArrayList<Option>();
        Option opt85 = new Option("3.141516", true);
        Option opt86 = new Option("3.444444", false);
        Option opt87 = new Option("4.000000", false);
        Option opt88 = new Option("3.149999", false);
        listAnwer22.add(opt85);
        listAnwer22.add(opt86);
        listAnwer22.add(opt87);
        listAnwer22.add(opt88);
        Question ques22 = new Question("??A cu??nto equivale ???", listAnwer22);
        //3
        ArrayList<Option> listAnwer23 = new ArrayList<Option>();
        Option opt89 = new Option("Superficie de un c??rculo", true);
        Option opt90 = new Option("Di??metro de un c??rculo", false);
        Option opt91 = new Option("Volumen de un cilindro", false);
        Option opt92 = new Option("superficiede paralelogramos", false);
        listAnwer23.add(opt89);
        listAnwer23.add(opt90);
        listAnwer23.add(opt91);
        listAnwer23.add(opt92);
        Question ques23 = new Question("??Qu?? formula es esta? S = ?? x R??", listAnwer23);
        //4
        ArrayList<Option> listAnwer24 = new ArrayList<Option>();
        Option opt93 = new Option("Equivalencia entre masa y energ??a", true);
        Option opt94 = new Option("La teor??a de la probabilidad", false);
        Option opt95 = new Option("Circunferencia de un c??rculo", false);
        Option opt96 = new Option("Volumen de un cubo", false);
        listAnwer24.add(opt93);
        listAnwer24.add(opt94);
        listAnwer24.add(opt95);
        listAnwer24.add(opt96);
        Question ques24 = new Question("??Qu?? expresa esta formula? e = mc??", listAnwer24);
        //5
        ArrayList<Option> listAnwer25 = new ArrayList<Option>();
        Option opt97 = new Option("10 juan, 30 el padre", true);
        Option opt98 = new Option("11 Juan, 31 el padre", false);
        Option opt99 = new Option("12 Juan, 32 el padre", false);
        Option opt100 = new Option("13 Juan, 33 el padre", false);
        listAnwer25.add(opt97);
        listAnwer25.add(opt98);
        listAnwer25.add(opt99);
        listAnwer25.add(opt100);
        Question ques25 = new Question("Juan tiene 20 a??os menos que su padre y este tiene el triple de los a??os de su hijo. ??Qu?? edad tienen cada uno?", listAnwer25);
        listQuestion4.add(ques21);
        listQuestion4.add(ques22);
        listQuestion4.add(ques23);
        listQuestion4.add(ques24);
        listQuestion4.add(ques25);

        Category Category5 = new Category("Categoria de MATEMATICAS", listQuestion4, 4);
        listCategory.add(Category5);
        round = new Round(listCategory);
        return round;
    }

}
