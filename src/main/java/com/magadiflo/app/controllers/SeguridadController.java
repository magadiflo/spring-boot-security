package com.magadiflo.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @PreAuthorize("authenticated"), "authenticated" será un rol, si el usuario es reconocido
// como usuario por el sistema (independiente del rol que tenga) será autenticado
@PreAuthorize("authenticated")
@RestController
@RequestMapping("/security")
public class SeguridadController {

    /***
     * Como no tiene anotación de seguridad. ¿Quiénes podrán acceder a este recurso?
     * Pues todos los usuarios que estén autenticados, por eso es que se puso la anotación
     * @PreAuthorize("authenticated") al inicio de esta clase
     */
    @GetMapping(value = "/all")
    public String all() {
        return "OK. Accesso permitido al recurso all";
    }

    /***
     * Tiene anotación de seguridad.
     * Se indica que únicamente pueden acceder a este recurso los usuarios con
     * rol USER o MANAGER.
     * Entre la anotación de @PreAuthorize("authenticated") y esta anotación,
     * prevalece la anotación más específica, en este caso la de este recurso
     * @PreAuthorize("hasRole('USER') OR hasRole('MANAGER')")
     */
    @PreAuthorize("hasRole('USER') OR hasRole('MANAGER')")
    @GetMapping(value = "/user")
    public String user() {
        return "OK. Acceso permitido al recurso user";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(value = "/manager")
    public String manager() {
        return "OK. Acceso permitido al recurso manager";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin")
    public String admin() {
        return "OK. Acceso permitido al recurso admin";
    }

}
