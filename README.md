# Ejemplo simple de microprofile sobre Wildfly 20

Aplicacion simple que muestra caracteristicas de microprofile sobre un contenedor Jakarta EE como wildfly, ademas con una pila de contenedores para demostrar funcionales importantes como openapi y opentracing.

Para verificacion simple:

```bash
mvn verify
```

Para compilar se incluye el plugins de docker para empaquetar todo el proyecto:

```bash
mvn clean package docker:build
```
En este caso viene con un archivo de configuracion personalizado para habilitar la exposicion de la informacion de opentracing a jeager para su visualizacion


Para correr el contenedor aislado se debe ejecutar lo siguiente:

```bash
docker run -p 8080:8080 -p 9990:9990 --rm -it helloworld-mp
```

Para acceder a la definicion openapi puede acceder a:

```bash
curl http://localhost:8080/openapi
```

Para acceder a las metricas del servidor y de la aplicacion puede acceder a:

```bash
curl http://localhost:9990/metrics
```

Para levantar todos los contenedores se utiliza compose por lo que se debe ejecutar en una terminal lo siguiente:

```bash
docker-compose up
```

Se levantaran contenedores de swagger y jeager.
Para ingresar al contenedor de sagger ui puede utilizar su navegador para acceder a este link <http://localhost:8888> en donde se debe utilizar el endpoint http://localhost:8080/openapi o directamente a este link <http://localhost:8888/?url=http://localhost:8080/openapi>

Para ingresar al contenedor de jeager puede utilizar su navegador para acceder a este link <http://localhost:16686/search>