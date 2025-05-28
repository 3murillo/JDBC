package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jdbc.DB;

public class Program {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		
		Connection con = DB.getConexao();
		System.out.println("*** Conexão Realizada ***");
		
		PreparedStatement st = con.prepareStatement("INSERT INTO aluno (nome, sexo, dt_nasc) + VALUES (?, ?, ?);");
		st.setString(1, "João");
		st.setString(2, "Masculino");
		Calendar c = new GregorianCalendar(2000, 1, 1);
		st.setDate(3, new Date(c.getTimeInMillis()));
		
		int linhas = st.executeUpdate();
		System.out.println("Linhas inseridas" + linhas);
		
		DB.fechaConexao();
		
		System.out.println("*** Conexão Encerrada ***");

	}

}

