# Reto
Reto de principios de diseño

Hecho por: 
Juan Felipe Cadavid Zabala,
Kevin Cardenas Rivillas,
Emanuel Cardona Garcia,
Jhoan Sebastian Castillo Usurriaga,
Anderson Coronado Mazo

# Documento Técnico de Diseño
## Motor de Reglas para Reembolsos Médicos

---

## 1. Introducción

Este documento describe el diseño de un **Motor de Reglas para la evaluación de solicitudes de reembolso médico**.

El motor fue concebido como un componente **reutilizable e independiente** que puede ser consumido por distintos sistemas externos como portales web, APIs o módulos de auditoría.

Su propósito es automatizar la validación de solicitudes mediante **reglas de negocio**, controlar el flujo de estados de las solicitudes y garantizar que las acciones ejecutadas sobre ellas sean válidas. Además, el motor proporciona decisiones justificadas que permiten explicar por qué una solicitud fue aprobada o rechazada.

El diseño se fundamenta en principios de ingeniería de software como **SOLID** y **GRASP**, lo cual garantiza que el sistema sea **extensible, mantenible y fácil de comprender**. La solución también considera que ciertos procesos, como la verificación real del contenido de documentos médicos, deben realizarse manualmente por un revisor humano.

---

## 2. Contexto del Problema

La organización procesa solicitudes de **reembolso médico** realizadas por pacientes que desean recuperar el dinero pagado por medicamentos o procedimientos médicos.

Las solicitudes pueden corresponder a cuatro tipos principales:

- Reembolso de medicamentos
- Reembolso de procedimientos ambulatorios
- Reembolso por atención de urgencias
- Reembolso de exámenes diagnósticos

Cada solicitud contiene información clave como:

- Identificador
- Tipo de solicitud
- Valor solicitado
- Fecha del evento médico
- Paciente
- Prestador
- Documentos adjuntos
- Estado actual

El motor de reglas tiene como responsabilidad **validar estas solicitudes de manera automática** aplicando reglas de negocio definidas por la organización y controlar el flujo de estados para evitar inconsistencias en el proceso.

---

## 3. Modelo del Dominio

El núcleo del sistema es la clase **SolicitudReembolso**, que representa la solicitud realizada por el paciente.

Esta clase contiene toda la información necesaria para el proceso de evaluación, incluyendo:

- Datos del paciente
- Datos del prestador
- Documentos adjuntos
- Valor solicitado
- Estado actual

Las clases **Paciente** y **Prestador** representan las entidades involucradas en la atención médica. Estas clases permiten validar información básica como:

- la existencia del paciente
- si el prestador está habilitado

La clase **Documento** representa los archivos adjuntos a la solicitud. El sistema puede validar que los documentos requeridos estén presentes y correspondan al tipo adecuado. Sin embargo, el contenido real de los documentos debe ser revisado por un **revisor humano**.

---

## 4. Gestión de Estados

El flujo de una solicitud está controlado mediante un conjunto de estados definidos:

```
Draft → Submitted → UnderReview → Approved / Rejected
```

Para controlar estas transiciones se utiliza la clase **Transicion**, que define qué acción permite pasar de un estado a otro.

Cada transición define:

- un estado de origen
- una acción ejecutada
- el estado resultante

La clase **ControlDeEstados** actúa como el guardián del flujo del sistema y valida si una acción es permitida.

---

## 5. Sistema de Reglas

El sistema implementa las reglas de negocio mediante la interfaz:

```
IRegla
```

Esta interfaz define el contrato que debe cumplir cualquier regla mediante el método:

```
Evaluar(SolicitudReembolso)
```

Las reglas implementadas son:

- ReglaConsistencia
- ReglaFecha
- ReglaMonto
- ReglaPrestador
- ReglaDocumento

Estas reglas son ejecutadas por la clase **Reglamento**, que se encarga de aplicar todas las reglas configuradas a una solicitud.

---

## 6. Motor de Reglas

El acceso al motor se realiza mediante la interfaz:

```
IMotorDeReglas
```

Esta interfaz define las operaciones disponibles para los sistemas externos.

La clase **MotorDeReglas** coordina el proceso completo:

1. Valida las acciones mediante `ControlDeEstados`
2. Ejecuta las reglas mediante `Reglamento`
3. Retorna el resultado de la evaluación

Esta separación permite mantener una arquitectura clara entre **dominio, reglas y control del flujo del sistema**.

---

## 7. Validación Humana de Documentos

El sistema valida automáticamente la **existencia y tipo de documentos**, pero la verificación real del contenido debe ser realizada por un **revisor humano**.

Esto permite combinar:

- automatización del proceso
- validación experta por parte de un especialista

De esta manera se garantiza mayor confiabilidad en la evaluación de las solicitudes.

---

## 8. Conclusión

El diseño presentado permite construir un **motor de reglas extensible y reutilizable**.

La separación entre:

- el modelo de dominio
- el sistema de reglas
- el control de estados

permite agregar nuevas reglas de negocio sin modificar el núcleo del sistema.

Este enfoque facilita la **mantenibilidad, escalabilidad y reutilización** del motor en diferentes sistemas dentro de la organización.
