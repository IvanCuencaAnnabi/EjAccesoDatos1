import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase Gui, está clase será la encargada de mostrar el menú, recoger todos los datos de teclado,
 * y la encargada de interactuar con todos los archivos y con el usuario.
 */
public class Gui {
    //Creamos un Scanner para poder leer por teclado y el ArrayList de alumnos que utilizará la clase
    Scanner teclado = new Scanner(System.in);
    ArrayList<Alumno> listadoAlumnos = new ArrayList<>();

    /**
     * Este constructor no pide ningun parámetro, se limita a llamar al método menu.
     */
    public Gui(){
        menu();
    }

    /**
     * Método menu, este método no devuelve nada; es el encargado de mostrar las opciones al usuario
     * y gestionar la opción elegida llamando al método correspondiente o imprimiendo el mensaje adecuado.
     */
    public void menu(){
        System.out.println("Selecciona una opcion: ");
        System.out.println("0.Salir");
        System.out.println("1.Crear un objeto");
        System.out.println("2.Eliminar un objeto");
        System.out.println("3.Listar objetos");
        System.out.println("4.Guardar en fichero");
        System.out.println("5.Cargar de fichero");
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 0:
                break;
            case 1:
                crearObjeto();
                break;
            case 2:
                eliminarObjeto();
                break;
            case 3:
                listarObjetos();
                break;
            case 4:
                switch (menuFichero()) {
                    case 1:
                        guardarTexto();
                        break;
                    case 2:
                        guardarBinario();
                        break;
                    case 3:
                        guardarXML();
                        break;
                    default:
                        System.out.println("¡¡Selecciona una opcion de las indicadas!!");
                        menu();
                }
                break;
            case 5:
                switch (menuFichero()) {
                    case 1:
                        cargarTexto();
                        break;
                    case 2:
                        cargarBinario();
                        break;
                    case 3:
                        cargarXML();
                        break;
                    default:
                        System.out.println("¡¡Selecciona una opcion de las indicadas!!");
                        menu();
                }
                menu();
                break;
            default:
                System.out.println("¡¡Selecciona una opcion de las indicadas!!");
                menu();
        }

    }

    /**
     * El método menuFichero se encarga de desplegar un menú secundario para cuando el usuario deba elegir
     * el tipo de archivo que desea trabajar.
     * @return int Devuelve un entero con la respuesta del usuario.
     */
    private int menuFichero() {
        System.out.println("Selecciona una opcion: ");
        System.out.println("1.Fichero de texto");
        System.out.println("2.Fichero binario");
        System.out.println("3.Fichero XML");

        return teclado.nextInt();
    }

    /**
     * El método crearObjeto no devuelve nada, se limita a pedir y recoger los datos del alumno
     * que nos proporcionará el usuario y con eso, genera un alumno y lo añade al ArrayList listadoAlumnos
     * creado anteriormente.
     */
    private void crearObjeto(){
        System.out.println("Introduzca el nombre del alumno: ");
        teclado.nextLine();
        String nombre = teclado.nextLine();
        System.out.println("Introduzca la edad del alumno: ");
        int edad = 0;
        try {
            edad = teclado.nextInt();
        } catch (InputMismatchException i) {
            System.out.println("Por favor, introduzca unicamente su edad en formato numérico");
            menu();
        }
        System.out.println("Introduzca la nota del alumno: ");
        float nota = teclado.nextFloat();
        System.out.println("Introduzca true si el alumno es bilingue, y false si no lo es: ");
        boolean bilingue = teclado.nextBoolean();

        Alumno a1 = new Alumno(nombre, edad, nota, bilingue);
        listadoAlumnos.add(a1);
        System.out.println("Alumno creado con exito con exito");
        menu();
    }

    /**
     * Método eliminarObjeto, este método no devuelve nada; se encarga de solicitar y recoger
     * el nombre del alumno que se desea eliminar y recorre el ArrayList comparando los nombres
     * hasta eliminar al que coincida.
     */
    private void eliminarObjeto(){
        System.out.println("Introduce el nombre del alumno a eliminar");
        String nombre = teclado.nextLine();

        for (Alumno a : listadoAlumnos) {
            if (nombre.equals(a.getNombre())) {
                listadoAlumnos.remove(a);
                System.out.println("Alumno eliminado con exito");
                menu();
            }
        }
    }

    /**
     * El método listarObjetos se limita a imprimir todos los alumnos del ArrayList en el formato
     * dado en el método toString de la clase Alumno.
     */
    private void listarObjetos(){
        for (Alumno a : listadoAlumnos) {
            System.out.println(a.toString());
        }
        menu();
    }

    /**
     * El método guardarTexto se encarga de guardar los alumnos que haya en el Arraylist en un fichero de texto.
     */
    private void guardarTexto(){
        try {
            ObjectOutputStream oOutput = new ObjectOutputStream(new FileOutputStream("datos.txt"));
            oOutput.writeObject(listadoAlumnos);

            System.out.println("Todo ha sido guardado correctamente");
            menu();
        }catch(IOException e){
            System.out.println("Ha habido un error guardando el archivo");
        }
    }

    /**
     * El método cargarTexto se encarga de añadir al Arraylist los alumnos que va leyendo del fichero de texto.
     */
    private void cargarTexto(){
        try {
            ObjectInputStream oInput = new ObjectInputStream(new FileInputStream("datos.txt"));
            listadoAlumnos.addAll((ArrayList<Alumno>) oInput.readObject());

            System.out.println("Todo ha sido cargado correctamente");
            menu();
        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Ha habido un error cargando el archivo");
        }
    }
    /**
     * El método guardarBinario se encarga de guardar los alumnos que haya en el Arraylist en un fichero binario.
     */
    private void guardarBinario() {
        try {
            ObjectOutputStream oOutput = new ObjectOutputStream(new FileOutputStream("datos.bin"));
            oOutput.writeObject(listadoAlumnos);

            System.out.println("Todo ha sido guardado correctamente");
            menu();
        }catch (IOException e){
            System.out.println("Ha habido un error guardando el archivo");
        }
    }
    /**
     * El método cargarBinario se encarga de añadir al Arraylist los alumnos que va leyendo del fichero binario.
     */
    private void cargarBinario(){

        try{
            ObjectInputStream oInput = new ObjectInputStream(new FileInputStream("datos.bin"));
            listadoAlumnos.addAll((ArrayList<Alumno>) oInput.readObject());

            System.out.println("Todo ha sido cargado correctamente");
            menu();

        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Ha habido un error cargando el archivo");
        }
    }
    /**
     * El método guardarXML se encarga de guardar los alumnos que haya en el Arraylist en un archivo XML.
     */
    private void guardarXML(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element aula = doc.createElement("aula");
            doc.appendChild(aula);

            for (Alumno listadoAlumno : listadoAlumnos) {

                Element alumno = doc.createElement("alumno");
                aula.appendChild(alumno);

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(String.valueOf(listadoAlumno.getNombre())));
                alumno.appendChild(nombre);

                Element edad = doc.createElement("edad");
                edad.appendChild(doc.createTextNode(String.valueOf(listadoAlumno.getEdad())));
                alumno.appendChild(edad);

                Element nota = doc.createElement("nota");
                nota.appendChild(doc.createTextNode(String.valueOf(listadoAlumno.getNota())));
                alumno.appendChild(nota);

                Attr bilingue = doc.createAttribute("bilingue");
                bilingue.setValue(String.valueOf(listadoAlumno.getBilingue()));
                alumno.setAttributeNode(bilingue);

            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\ivanz\\OneDrive\\Escritorio\\AD\\datos.xml"));

            transformer.transform(source, result);

            System.out.println("Todo ha sido guardado correctamente");
            menu();
        } catch (TransformerException | ParserConfigurationException e) {
            System.out.println("Ha habido un error guardando el archivo");
        }
    }
    /**
     * El método cargarXML se encarga de añadir al Arraylist los alumnos que va leyendo del fichero XML.
     */
    private void cargarXML(){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("C:\\Users\\ivanz\\OneDrive\\Escritorio\\AD\\datos.xml");

            doc.getDocumentElement().normalize();

            NodeList listaNodosAlumnos = doc.getElementsByTagName("alumno");

            for (int i = 0; i < listaNodosAlumnos.getLength(); i++) {
                Element e = (Element) listaNodosAlumnos.item(i);
                String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
                int edad = Integer.parseInt(e.getElementsByTagName("edad").item(0).getTextContent());
                float nota = Float.parseFloat(e.getElementsByTagName("nota").item(0).getTextContent());
                Boolean bilingue = Boolean.parseBoolean(e.getAttribute("bilingue"));

                listadoAlumnos.add(new Alumno(nombre, edad, nota, bilingue));

            }
        }catch(IOException | SAXException | ParserConfigurationException e){
            System.out.println("Ha habido un error cargando el archivo");
        }



    }


}