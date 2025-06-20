# 🚧 Proyecto en Construcción

![Trabajador](https://em-content.zobj.net/thumbs/240/apple/354/man-construction-worker_1f477-200d-2642-fe0f.png)

Este proyecto aún se encuentra en desarrollo.  
Estamos trabajando arduamente para traerte algo genial.  
¡Vuelve pronto para más actualizaciones! 👷‍♂️💻

---

# ⚠️ Advertencia: Popups del Google Password Manager en Selenium

## Problema

Al usar **Selenium + ChromeDriver**, pueden aparecer popups de Chrome ("Guardar contraseña", "Cambiar la contraseña") que **WebDriver no puede manejar**.

## Causa

Si Chrome tiene sesión iniciada en Google:

* Ignora opciones de `ChromeOptions` como:

    * `credentials_enable_service`
    * `profile.password_manager_enabled`
* El administrador de contraseñas pasa a depender de la cuenta de Google.

## Consecuencia

Los popups aparecen aunque las opciones de Chrome estén configuradas, afectando la estabilidad de las pruebas.

## Solución recomendada

* No usar perfiles persistentes.
* Ejecutar en modo `--guest` o `--incognito`.
* Usar perfiles temporales (`user-data-dir` limpio).
* No iniciar sesión en Google durante las pruebas.

**Resumen:** Si Chrome está logueado en Google, WebDriver pierde el control sobre estos popups.


> 💡 *Puedes hacer fork o seguir el repositorio para no perderte ningún avance.*
