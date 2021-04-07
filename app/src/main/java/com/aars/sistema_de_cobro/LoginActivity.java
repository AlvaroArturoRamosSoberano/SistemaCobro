package com.aars.sistema_de_cobro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    private static Conexion con= new Conexion();
    Button btn_ingresar;
    EditText editText_usuario;
    EditText editText_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Inicio_sesion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    });
        editText_usuario = findViewById(R.id.editText_usuario);
        editText_contraseña = findViewById(R.id.editText_contraseña);
    }
    public void Inicio_sesion() throws SQLException {
        try {
            Statement comando = con.conexionBD().createStatement();
            String sql = ("SELECT * FROM cobradores WHERE usuario ='" + editText_usuario.getText().toString() + "and contraseña ='" +editText_contraseña.getText().toString()) +"'";
            ResultSet resultado = comando.executeQuery(sql);

            if(resultado.next()) {
                Intent menu = new Intent(LoginActivity.this, MainActivity.class );
                startActivity(menu);
            }else {
                Toast.makeText(LoginActivity.this,"Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception er) {
            Toast.makeText(getApplicationContext(),er.toString(), Toast.LENGTH_SHORT).show();
        }
        editText_usuario.setText("");
    }
}