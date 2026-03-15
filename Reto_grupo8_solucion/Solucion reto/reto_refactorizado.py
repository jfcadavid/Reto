from abc import ABC, abstractmethod

class Animales(ABC):
    @abstractmethod
    def comer(self): pass
    
    @abstractmethod
    def dormir(self): pass
    
class Nadador(ABC):
    @abstractmethod
    def nadar(self):
        pass

class Volador(ABC):
    @abstractmethod
    def volar(self):
        pass

class Corredor(ABC):
    @abstractmethod
    def correr(self):
        pass

class Oviparo(ABC):
    @abstractmethod
    def poner_huevos(self):
        pass

class Mamifero(ABC):
    @abstractmethod
    def amamantar(self):
        pass

class Animal(Animales):
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

class Ave(Animal, Oviparo):
    def __init__(self, nombre, edad, envergadura):
        super().__init__(nombre, edad)
        self.envergadura = envergadura

class Gorrion(Ave, Volador):
    def comer(self): print("El gorrión come semillas")
    def dormir(self): print("El gorrión duerme en un árbol")
    def volar(self): print("El gorrión vuela alto")
    def poner_huevos(self): print("El gorrión pone huevos")

class Avestruz(Ave, Corredor):
    def comer(self): print("El avestruz come hierbas e insectos")
    def dormir(self): print("El avestruz duerme en el suelo")
    def poner_huevos(self): print("El avestruz pone huevos")
    def correr(self): print("El avestruz corre rápido")

class Perro(Animal, Mamifero, Corredor):
    def comer(self): print("El perro come croquetas")
    def dormir(self): print("El perro duerme en su cama")
    def amamantar(self): print("El perro amamanta a sus cachorros")
    def correr(self): print("El perro corre feliz")

class Pez(Animal, Nadador):
    def comer(self): print("El pez come algas")
    def dormir(self): print("El pez duerme en el agua")
    def nadar(self): print("El pez nada en el agua")

def probar_metodo(Metodo):
    try:
        Metodo()
    except NotImplementedError as e:
        print(e)

def main():
    while True:
        print("\nSeleccione un animal:")
        print("1. Gorrión")
        print("2. Avestruz")
        print("3. Perro")
        print("4. Pez")
        print("5. Salir")
        opcion = input("Ingrese el número del animal: ")
        if opcion == '1':
            animal_select = Gorrion("Felipe", 2, 0.25)
        elif opcion == '2':
            animal_select = Avestruz("Gertrudis", 5)
        elif opcion == '3':
            animal_select = Perro("Kira", 3)
        elif opcion == '4':
            animal_select = Pez("Nemo", 1)
        elif opcion == '5':
            print("¡Hasta luego!")
            break
        else:
            print("Opción no válida, por favor intente de nuevo.")
            continue
        print(f"\nHas seleccionado: {animal_select.nombre} (Edad: {animal_select.edad} años)")
        print("Probando métodos:")
        metodos = [
            ("comer", "Este animal no puede comer"),
            ("dormir", "Este animal no puede dormir"),
            ("volar", "Este animal no puede volar"),
            ("correr", "Este animal no puede correr"),
            ("nadar", "Este animal no puede nadar"),
            ("poner_huevos", "Este animal no pone huevos"),
            ("amamantar", "Este animal no amamanta"),
        ]

        for nombre, mensaje in metodos:
            metodo = getattr(animal_select, nombre, None)

            if callable(metodo):
                probar_metodo(metodo)
            else:
                print(mensaje)

        input("\nPresione Enter para continuar...")

if __name__ == "__main__":
    main()