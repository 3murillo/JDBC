package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.Aluno;

public class AlunoJDBC {

	public static boolean salvar(Aluno a) throws SQLException {

		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		String sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?, ?, ?)";

		try {
			pst = cnx.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, a.getDt_nasc());
			if (pst.executeUpdate() >= 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			pst.close();
		}
		return false;
	}

	public static List<Aluno> listar() throws SQLException {

		Connection cnx = DB.getConexao();
		List<Aluno> alunos = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;

		try {

			st = cnx.createStatement();
			rs = st.executeQuery("SELECT * FROM aluno");
			while (rs.next()) {
				Aluno a = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("sexo"), rs.getDate("dt_nasc"));
				alunos.add(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			st.close();
		}
		return alunos;
	}

	public static boolean alterar(Aluno a) throws SQLException {

		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		String sql = "UPDATE aluno " + "   SET nome = ?, sexo = ? , dt_nasc= ?" + "   WHERE id = ?";
		try {
			pst = cnx.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, a.getDt_nasc());
			pst.setInt(4, a.getId());
			if (pst.executeUpdate() >= 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			pst.close();
		}
		return false;
	}

	public static boolean apagar(int id) throws SQLException {

		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		String sql = "DELETE FROM aluno WHERE id = ?";

		try {
			pst = cnx.prepareStatement(sql);
			pst.setInt(1, id);
			if (!pst.execute()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			pst.close();
		}
		return false;
	}

}