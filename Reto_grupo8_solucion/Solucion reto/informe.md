# Refactorización aplicando ISP y High Cohesion

## 1. Introducción

En el desarrollo de software orientado a objetos es importante diseñar interfaces y clases que sean claras, mantenibles y coherentes. Para ello existen principios de diseño como el **Interface Segregation Principle (ISP)** y el concepto de **High Cohesion**.

El código original del ejercicio presentaba una interfaz demasiado grande que obligaba a varias clases a implementar métodos que no correspondían a su comportamiento real. Esto generaba excepciones innecesarias y un diseño poco flexible.

El objetivo de este reto es **identificar las violaciones de estos principios y refactorizar el código para corregirlas**, manteniendo la funcionalidad del programa.

---

## 2. Interface Segregation Principle (ISP)

El **Interface Segregation Principle** establece que:

> Las clases no deben verse obligadas a depender de métodos que no utilizan.

En el código original existía una interfaz llamada `IAnimal` que incluía muchos métodos:

* `comer()`
* `dormir()`
* `correr()`
* `nadar()`
* `volar()`
* `ponerHuevos()`
* `amamantar()`

El problema es que **no todos los animales pueden realizar todas estas acciones**. Por ejemplo:

| Animal   | Problema           |
| -------- | ------------------ |
| Perro    | No puede volar     |
| Pez      | No puede correr    |
| Gorrión  | No puede amamantar |
| Avestruz | No puede volar     |

Debido a esto, varias clases terminaban implementando métodos que simplemente lanzaban excepciones como:

```
throw new UnsupportedOperationException();
```

Esto es una clara violación del principio **ISP**, ya que las clases estaban obligadas a implementar comportamientos que no les correspondían.

---

## 3. High Cohesion

La **alta cohesión (High Cohesion)** significa que una clase o interfaz debe tener **una responsabilidad clara y específica**.

En el código original, la interfaz `IAnimal` mezclaba diferentes responsabilidades como:

* comportamiento básico del animal
* tipos de locomoción
* formas de reproducción

Esto generaba **baja cohesión**, ya que demasiadas funcionalidades estaban concentradas en una sola interfaz.

---

## 4. Refactorización aplicada

Para solucionar estos problemas se dividió la interfaz original en varias interfaces más pequeñas, cada una con una responsabilidad específica.

Se definieron las siguientes interfaces:

* `Animales` → comportamiento básico (comer y dormir)
* `Nadador` → animales que pueden nadar
* `Volador` → animales que pueden volar
* `Corredor` → animales que pueden correr
* `Oviparo` → animales que ponen huevos
* `Mamifero` → animales que amamantan a sus crías

De esta manera, cada clase implementa **únicamente las capacidades que realmente posee**.

Ejemplos:

* `Gorrion` implementa `Volador` y `Oviparo`
* `Avestruz` implementa `Corredor` y `Oviparo`
* `Perro` implementa `Mamifero` y `Corredor`
* `Pez` implementa `Nadador`

Esto permite eliminar métodos innecesarios y evitar excepciones.

---

## 5. Beneficios del nuevo diseño

La refactorización presenta varias ventajas:

* Cada interfaz tiene una **responsabilidad clara**.
* Las clases solo implementan **los comportamientos que realmente utilizan**.
* Se evita el uso de **excepciones innecesarias**.
* El sistema es **más flexible y fácil de mantener**.
* El diseño cumple con **ISP y High Cohesion**.

---

## 6. Conclusión

El código original presentaba problemas de diseño al utilizar una interfaz demasiado grande que obligaba a las clases a implementar comportamientos que no correspondían a su naturaleza.

Mediante la aplicación del **Interface Segregation Principle** y el concepto de **High Cohesion**, se logró dividir las responsabilidades en interfaces más pequeñas y especializadas.

El resultado es un sistema más claro, modular y mantenible, donde cada clase representa correctamente las capacidades del animal que modela.
