import java.io.IOException;
import java.util.*;

interface IAnimal {
    void comer();
    void dormir();
    void correr();
    void nadar();
    void volar();
    void ponerHuevos();
    void amamantar();
}

abstract class Animal implements IAnimal {
    protected String nombre;
    protected int edad;

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

}

abstract class Ave extends Animal {
    protected double envergadura;

    public Ave(String nombre, int edad, double envergadura) {
        super(nombre, edad);
        this.envergadura = envergadura;
    }

    @Override
    public void volar() {

    }
}

class Gorrion extends Ave {
    public Gorrion(String nombre, int edad, double envergadura) {
        super(nombre, edad, envergadura);
    }

    public void comer() { System.out.println("El gorrión come semillas"); }
    public void dormir() { System.out.println("El gorrión duerme en un árbol"); }
    public void correr() { System.out.println("El gorrión camina un poco"); }
    public void nadar() {
        throw new UnsupportedOperationException("Error: El gorrion no nada → Incumplimiento de ISP");
    }
    public void volar() { System.out.println("El gorrión vuela alto"); }
    public void ponerHuevos() { System.out.println("El gorrión pone huevos"); }
    public void amamantar() {
        throw new UnsupportedOperationException("Error: El gorrión no amamanta → Incumplimiento de ISP");
    }
}

class Avestruz extends Ave {
    public Avestruz(String nombre, int edad, double envergadura) {
        super(nombre, edad, envergadura);
    }

    public void comer() { System.out.println("El avestruz come hierbas e insectos"); }
    public void dormir() { System.out.println("El avestruz duerme en el suelo"); }
    public void correr() { System.out.println("El avestruz corre rápido"); }
    public void nadar() {
        throw new UnsupportedOperationException("Error: El avestruz no nada → Incumplimiento de ISP");
    }
    public void ponerHuevos() { System.out.println("El avestruz pone huevos"); }
    public void amamantar() {
        throw new UnsupportedOperationException("Error: El avestruz no amamanta → Incumplimiento de ISP");
    }
}

class Perro extends Animal {
    public Perro(String nombre, int edad) {
        super(nombre, edad);
    }

    public void comer() { System.out.println("El perro come croquetas"); }
    public void dormir() { System.out.println("El perro duerme en su cama"); }
    public void correr() { System.out.println("El perro corre rápido"); }
    public void nadar() { System.out.println("El perro nada de vez en cuando"); }
    public void volar() {
        throw new UnsupportedOperationException("Error: El perro no puede volar → Incumplimiento de ISP");
    }
    public void ponerHuevos() {
        throw new UnsupportedOperationException("Error: El perro no pone huevos → Incumplimiento de ISP");
    }
    public void amamantar() { System.out.println("La perra amamanta a sus crías"); }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n\nElige un animal:");
            System.out.println("1. Gorrión");
            System.out.println("2. Avestruz");
            System.out.println("3. Perro");
            System.out.println("4. Salir");

            opcion = sc.nextInt();
            sc.nextLine();
            Animal animal = null;

//  En las opciones 1 y 2 los datos que debe ingresar son: (“Nombre”, Edad, Envergadura)
// En la opción 3 los datos que debe ingresar son: (“Nombre”, Edad)
            switch (opcion) {
                case 1:
                    animal = new Gorrion("Merlin", 4, 0.25);
                    break;
                case 2:
                    animal = new Avestruz("Loisa", 5, 2.0);
                    break;
                case 3:
                    animal = new Perro("Tobby", 3);
                    break;
                case 4:
                    System.out.println("Hasta la proxima");
                    continue;
                default:
                    System.out.println("Opción no válida");
                    continue;
            }



            final Animal animalSeleccionado = animal;

            System.out.println("\n=== Probando todos los métodos ===");

            probarMetodo(animalSeleccionado::comer);
            probarMetodo(animalSeleccionado::dormir);
            probarMetodo(animalSeleccionado::correr);
            probarMetodo(animalSeleccionado::nadar);
            probarMetodo(animalSeleccionado::volar);
            probarMetodo(animalSeleccionado::ponerHuevos);
            probarMetodo(animalSeleccionado::amamantar);

            System.out.println("Presione Enter para continuar");
            sc.nextLine();
        }while(opcion != 4);
    }

    private static void probarMetodo(Runnable metodo) {
        try {
            metodo.run();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}
