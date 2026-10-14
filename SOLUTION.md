# Solución — Examen Práctico

## Qué se implementó

- Formulario de registro de clientes en Angular.
- Validación de campos obligatorios: nombre, apellido, teléfono y email.
- Validación del teléfono para aceptar únicamente dígitos.
- Validación del formato del email.
- Limpieza del formulario después de que el backend confirma el registro.
- Visualización de clientes en una tabla, incluyendo el email.
- Opción para eliminar un cliente desde su fila, usando su ID.
- En el backend, operaciones para consultar, guardar y eliminar clientes, y respuestas HTTP para indicar el resultado.

## Cómo se implementó

El formulario usa `FormsModule`, `ngForm` y `ngModel`. Cada campo tiene un nombre y las reglas de validación correspondientes. Al enviarlo, el componente comprueba si es válido y evita enviar la solicitud si faltan datos o el formato es incorrecto.

Si el guardado tiene éxito, el formulario se reinicia y se vuelve a consultar la lista de clientes. Si falla, se informa el error sin limpiar los datos ingresados.

Las llamadas HTTP están centralizadas en `ClientService`. El componente utiliza el servicio para consultar y guardar clientes, y para enviar la solicitud DELETE con el ID del cliente seleccionado. Después de una eliminación exitosa, vuelve a cargar la tabla.

## Cambios en base de datos / configuración

- Se agregó el campo `email` al modelo de cliente y debe estar disponible también en el DTO y en la tabla de base de datos.
- El identificador del cliente es autoincrementable y lo genera el backend/base de datos.
- La tabla `clients` usa las columnas `id`, `first_name`, `last_name`, `surname` y `phone`. Para completar la funcionalidad de email, debe incluir también `email`.
- El frontend se configura para comunicarse con el backend mediante la URL base `http://localhost:94/api`.
- El backend debe permitir solicitudes CORS desde el origen del frontend, normalmente `http://localhost:4200`.
- Las rutas configuradas en Angular deben coincidir exactamente con las rutas expuestas por el backend.

## Dificultades encontradas

- El formulario se había cerrado inmediatamente después de abrirse, por lo que sus campos no pertenecían al formulario. Se corrigió para envolver campos y botón de envío.
- TypeScript requería un `id` al crear un cliente. Se cambió la interfaz para que `id` fuera opcional, ya que lo genera el backend.
- Angular Material requiere que cada columna de `displayedColumns` tenga una definición `matColumnDef` correspondiente. Se agregó la columna de email.
- El primer intento de subir el frontend no incluyó los archivos en la carpeta del repositorio clonado. Se identificó que había que copiar el frontend a una subcarpeta del repositorio del backend y hacer el commit desde la raíz de ese repositorio.
- Al guardar un cliente, el frontend recibió HTTP 500 desde `POST /api/client`. Esto indica un error en el backend; se debe revisar el log de Spring Boot y verificar que el modelo, DTO, consulta SQL y tabla tengan el campo `email`.

## Mejoras que podrían aplicarse

- Reemplazar los `alert()` por mensajes visuales de Angular Material, por ejemplo, `MatSnackBar`.
- Mostrar errores de validación junto a cada campo y enumerar claramente los campos obligatorios faltantes.
- Añadir pruebas unitarias para el componente y el servicio.
- Centralizar el manejo de errores HTTP y presentar mensajes según el código de respuesta.
- Confirmar y documentar los códigos HTTP del backend para guardado, consulta y eliminación.
- Validar también los datos en el backend; la validación del navegador no sustituye la validación del servidor.
- Evitar duplicación de datos en el componente y mantener la tabla directamente enlazada al `MatTableDataSource`.