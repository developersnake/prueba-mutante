# prueba-mutante
Prueba de MercadoLibre

Requisitos 

- IDE STS o Eclipse con plugin para spring.
- lombok.
- Maven 3.6.3 o superior.
- Motor de base de datos Postgres o de su prefencia, en caso de utilizar otro motor debe agregar la dependencia en el pom
  del proyecto y modificar los datos de conexión segun corresponda.
- JDK de java 8.

Compilar proyecto

1. Crear la base de datos 'pruebamutante'
2. Ejecutar el archivo pruebamutante.sql que se encuentra en la raiz del proyecto, sobre la base de datos 'pruebamutante'.
3. Descargar y configura lombok par el IDE.
3. Importar el proyecto a su IDE de preferencia STS o eclipse.
4. Abrir el archivo application.yml y modificar las propiedades del datasource, según corresponda con la conexión de base de datos.
5. En el IDE ir a maven Run As->Maven build y colocar los goal clean instal y run o ir a la carpeta raiz del proyecto donde
   se encuentra el pom.xml, abrir la consola de comando y ejecutar el comando mvn clean install.
6. Desde el IDE ejecutar como un projecto spring boot.
