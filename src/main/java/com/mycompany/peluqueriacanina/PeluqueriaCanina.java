package com.mycompany.peluqueriacanina;

import com.mycompany.peluqueriacanina.iu.Principal;

/**
 * Clase principal del sistema de gestión de peluquería canina.
 * Inicia la aplicación mostrando la ventana principal.
 */
public class PeluqueriaCanina {

    /**
     * Método principal que inicia la aplicación.
     * Crea y muestra la ventana principal centrada en la pantalla.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
       Principal princ = new Principal();
       princ.setVisible(true);
       princ.setLocationRelativeTo(null);
    }
}
