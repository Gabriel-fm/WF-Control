#WF Control Panel


WF Control Panel es una pequeña aplicación de escritorio en Java para 
el control de cuentas de correo, webs y bases de datos. (ver nota en
la sección siguiente)

Con la aplicación se intenta facilitar el uso de cuentas en el 
servidor de correo Webfaction, que aunque simple, a veces puede ser un 
poco complicado o poco ágil.

Con la aplicación se pretende conseguir en unos pocos clicks lo que en 
la web se consigue con otros tantos.

##Versión actual
Actualmente sólo se encuentra implementado el control de los correos y
se encuentra en un estado alfa, aún no existe una versión estable,
quedan muchos bugs por corregir.

Uso
===

Para utilizar la aplicación tan sólo hay que compilar el código y 
ejecutar la clase login como punto de entrada.

**Para su correcta ejecución se necesita de** la librería 
[aXMLRPC](https://github.com/timroes/aXMLRPC) incluida en el 
classpath.

TODO
====

Por hacer quedan muchas cosas... bastantes de hecho...

Por ahora sólo está implementado el control más básico de los correos 
electrónicos. Los pasos esenciales siguientes considero que son:

* Mejorar la interfaz gráfica del control de correos.
* Permitir el control de cuentas con una dirección y varios buzones
* Implementación de gestión de webs (esto es gordo)
* Implementación de gestión de bases de datos (esto también es gordo)

#Licencia

###WF Control Panel

WF Control Panel está creada bajo licencia GPL v3

Copyright (C) 2014 - Gabriel Franco <gabriel.franco.martinez@gmail.com>

####aXMLRPC

Librería aXMLRPC (C) - Tim Roes 
[Github](https://github.com/timroes/aXMLRPC) - 
[Licencia](https://github.com/timroes/aXMLRPC/blob/master/LICENSE)

