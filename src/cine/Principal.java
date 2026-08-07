/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cine;

import Controlador.CtrlPelicula;
import Modelo.Pelicula;
import Modelo.SentenciasPelicula;
import Vista.frmPelicula;
/**
 *
 * @author aleja
 */

public class Principal {

    public static void main(String[] args) {
        Pelicula modelo = new Pelicula();
        SentenciasPelicula consultas = new SentenciasPelicula();
        frmPelicula vista = new frmPelicula();
        CtrlPelicula controlador = new CtrlPelicula(modelo, consultas, vista);
        controlador.inicio();
    }

}
