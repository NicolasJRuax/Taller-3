https://github.com/NicolasJRuax/Taller-3.git

# Taller 3

## Descripción

Esta aplicación de Android está diseñada para demostrar el uso de `SharedPreferences`, almacenamiento interno y externo, y bases de datos SQLite para gestionar datos de usuario de manera segura y eficiente.

La aplicación permite al usuario:

- Ver un saludo personalizado que cambia según la hora del día.
- Ingresar su nombre y guardarlo en `SharedPreferences` y en una base de datos SQLite.
- Ver su nombre mostrado en pantalla.
- Cambiar el color de fondo de la aplicación y guardar la preferencia en `SharedPreferences`.
- Navegar entre pantallas de forma intuitiva.

## Estructura de la Aplicación

### Actividades Principales

1. **StartActivity**

   - Muestra un saludo personalizado basado en la hora del día ("Buenos días", "Buenas tardes", "Buenas noches") y el nombre del usuario si está guardado.
   - Contiene un botón que lleva a la actividad principal (`MainActivity`).
   - Carga el color de fondo desde `SharedPreferences` para mantener la preferencia del usuario.

2. **MainActivity**

   - Permite al usuario ingresar su nombre.
   - El usuario puede guardar su nombre en `SharedPreferences`, lo cual actualiza el saludo en `StartActivity` y muestra el nombre en pantalla.
   - El usuario puede guardar su nombre en una base de datos SQLite utilizando `UsuarioDBHelper`.
   - Incluye un ícono de configuración (engranaje) en la esquina superior derecha que lleva a la `ConfigActivity`.
   - Carga el color de fondo desde `SharedPreferences`.

3. **ConfigActivity**

   - Permite al usuario cambiar el color de fondo de la aplicación (rojo o verde).
   - Guarda la preferencia del color en `SharedPreferences`.
   - El cambio de color se refleja en todas las actividades al cargar el color de fondo desde `SharedPreferences`.
   - Incluye un botón para volver a la pantalla de inicio (`StartActivity`).

### Clases Auxiliares

1. **UsuarioDBHelper**

   - Extiende `SQLiteOpenHelper` para manejar la creación y actualización de la base de datos SQLite.
   - Proporciona métodos para guardar el nombre del usuario en la base de datos y obtener los nombres guardados.
   - Gestiona una base de datos llamada `usuarios.db` con una tabla `usuarios` que almacena `id` y `nombre`.

2. **Usuario**

   - Clase modelo que representa un usuario con `id` y `nombre`.
   - Incluye constructores, getters y setters.

## Relaciones entre Clases

- **StartActivity**, **MainActivity** y **ConfigActivity** extienden `AppCompatActivity` y representan las pantallas principales de la aplicación.
- **MainActivity** utiliza `UsuarioDBHelper` para interactuar con la base de datos SQLite.
- **Todas las actividades** utilizan `SharedPreferences` para guardar y cargar preferencias del usuario, como el nombre y el color de fondo.
- **StartActivity** muestra el saludo personalizado y el nombre del usuario obtenido de `SharedPreferences`.
- **MainActivity** permite al usuario actualizar el nombre en `SharedPreferences` y la base de datos SQLite.
- **ConfigActivity** permite al usuario cambiar el color de fondo y guarda esta preferencia en `SharedPreferences`; el cambio se refleja en todas las actividades.
- **UsuarioDBHelper** maneja la persistencia de datos estructurados en SQLite.

## Funcionamiento de la Aplicación

1. **Inicio de la Aplicación**

   - Al abrir la aplicación, se carga `StartActivity`.
   - La actividad muestra un saludo personalizado basado en la hora actual y, si está disponible, el nombre del usuario desde `SharedPreferences`.
   - El fondo de la pantalla se establece según el color guardado en `SharedPreferences`.

2. **Navegación a la Actividad Principal**

   - El usuario presiona el botón "Continuar" para ir a `MainActivity`.

3. **Gestión del Nombre del Usuario en `MainActivity`**

   - El usuario puede ingresar su nombre en un campo de texto.
   - Al presionar "Guardar nombre", el nombre se guarda en `SharedPreferences` y se muestra en la pantalla.
   - Al presionar "Guardar SQL", el nombre se guarda en la base de datos SQLite utilizando `UsuarioDBHelper`.

4. **Acceso a la Configuración**

   - El usuario puede acceder a la configuración presionando el ícono de engranaje en la esquina superior derecha.
   - Esto lleva a `ConfigActivity`.

5. **Configuración del Color de Fondo en `ConfigActivity`**

   - El usuario puede cambiar el color de fondo a rojo o verde.
   - La preferencia se guarda en `SharedPreferences`.
   - Al volver a otras actividades, el color de fondo se actualiza según la preferencia guardada.

6. **Almacenamiento y Persistencia de Datos**

   - **SharedPreferences**: Se utiliza para almacenar datos simples como el nombre del usuario y el color de fondo.
   - **SQLite**: Se utiliza para almacenar datos estructurados; en este caso, el nombre del usuario en una tabla.

## Cómo Ejecutar el Proyecto

1. Clona el repositorio o descarga los archivos del proyecto.
2. Abre el proyecto en Android Studio.
3. Sincroniza el proyecto con Gradle.
4. Conecta un dispositivo Android o configura un emulador.
5. Ejecuta la aplicación desde Android Studio.

