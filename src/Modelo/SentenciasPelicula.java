/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
/**
 *
 * @author aleja
 */
public class SentenciasPelicula extends Conexion {

    public boolean registrar(Pelicula pel) {
        String sql = "INSERT INTO pelicula (titulo, año, genero, duracion) VALUES (?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pel.getTitulo());
            ps.setInt(2, pel.getAnio());
            ps.setString(3, pel.getGenero());
            ps.setInt(4, pel.getDuracion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar la pelicula: " + e);
            return false;
        }
    }

    public boolean eliminar(Pelicula pel) {
        String sql = "DELETE FROM pelicula WHERE id=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pel.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la pelicula: " + e);
            return false;
        }
    }

    public boolean buscar(Pelicula pel) {
        String sql = "SELECT * FROM pelicula WHERE id= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pel.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pel.setId(rs.getInt("id"));
                    pel.setTitulo(rs.getString("titulo"));
                    pel.setAnio(rs.getInt("año"));
                    pel.setGenero(rs.getString("genero"));
                    pel.setDuracion(rs.getInt("duracion"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la pelicula: " + e);
            return false;
        }
    }
}