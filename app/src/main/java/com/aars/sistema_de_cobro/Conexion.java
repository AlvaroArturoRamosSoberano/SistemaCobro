package com.aars.sistema_de_cobro;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection conexion = null;

    //FUNCION PARA CONCECTARNOS
    public Connection conexionBD() {
        // Registramos el driver de PostgresSQL
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Sistema_cobro",
                    "postgres", "i637 jack");
        }catch (Exception er) {
            System.err.println(er.getMessage());
        }
        return conexion;
    }
    //FUNCION PARA CERRAR CONEXION
    protected void cerrar_funcion(Connection con) throws Exception {
        con.close();
    }
}
