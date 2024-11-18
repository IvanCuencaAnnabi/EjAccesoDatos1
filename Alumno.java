import java.io.Serializable;
/**
 * Clase Alumno, va a representar una serie de datos de un alumno, como lo son el nombre, la edad, la nota y si el alumno
 * es bilingue o no. Esta clase implementa la interfaz Serializable, ya que deseamos poder añadir los objetos de la clase
 * en algun tipo de colección.
 */
public class Alumno implements Serializable {
    //Declaramos las variables
    private String nombre;
    private int edad;
    private float nota;
    private boolean bilingue;

    /**
     * Constructor de la clase alumno, recoge todos los parámetros y los asigna a las variables inicializadas en la clase.
     * @param nombre Nombre del alumno
     * @param edad Edad del alumno
     * @param nota Nota media del alumno
     * @param bilingue Booleano que asigna true a que el alumno es bilingue y false si no lo es
     */
    public Alumno(String nombre, int edad, float nota, boolean bilingue){
        this.nombre=nombre;
        this.edad=edad;
        this.nota=nota;
        this.bilingue=bilingue;
    }
    //Añadimos los metodos getter y setter de cada variable por si los llegasemos a necesitar en un futuro
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public float getNota() {
        return nota;
    }

    public boolean getBilingue(){
        return bilingue;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setBilingue(boolean bilingue) {
        this.bilingue = bilingue;
    }

    /**
     * Sobrescribimos el método toString para poder pedir el objeto en String en el formato que queramos.
     * @return Devuelve un String con los datos del objeto
     */
    @Override
    public String toString() {
        return "Alumno{" +
                " Nombre: " + nombre +
                ", Edad: " + edad +
                ", Nota: " + nota +
                ", Bilingue: " + bilingue +
                '}';
    }

}
