# Herramientas utilizadas

- mvn
- spring boot
- springfox
- jwt
- h2database
- jwt auth0
- sts/eclipse

## Correr la aplicación

Debe ejecutarse el siguiente comando en la carpeta del proyecto

./mvnw spring-boot:run


## Diseño de consideraciones
- se utilizó la implementación de autenticación de spring
- la base de datos es en memoria con h2
- no se almacenan los usuario y las contraseñas h2 (es una hack solo para el demo)
- se implementó una creación de ordenes (ventas) por waiter (mesero/garsón)
- se implementó un reporte de ventas del día por waiter
- jms está implementado a modo de ejemplo, envía un mensaje pero no hace nada más

## Uso
- existen 2 usuarios **user1** y **user2** con password **password**
- se debe hacer un request con POST: http://localhost:8080/login usando este json
{
    "username":"user1",
    "password":"password"
}
- en el header **location** se va a retornar un jwt
- usar ese jwt para hacer request según documentación swagger http://localhost:8080/swagger-ui/index.html
- el endpoint del reporte de ventas diario se implementó segun este patrón [Microsoft](https://docs.microsoft.com/en-us/azure/architecture/patterns/async-request-reply)

## Faltantes
- unit test (solo hay una)
- swagger UI no muestra los headers
- en swagger no se muestra el endpoint /login (auto-generado de spring)
