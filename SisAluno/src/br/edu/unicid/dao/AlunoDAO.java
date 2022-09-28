package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

import br.edu.unicid.been.Aluno;
import br.edu.unicid.util.ConnectionFactory;

public class AlunoDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Aluno aluno;
	
	public AlunoDAO() throws Exception {
		
		try {
			this.conn = ConnectionFactory.getConnection();
			}	catch(Exception e) {
					throw new Exception("erro: \n" + e.getMessage());
				}
			}
	//Salvar Aluno
	public void salvar(Aluno aluno) throws Exception{
		if(aluno == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO tb_aluno(rgm, nome, email, dataNascimento, endereco) values (?, ?, ?, ?, ?)";
			conn = this.conn;
			ps.setInt(1, aluno.getRgm());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.setDate(4, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setString(5, aluno.getEndereco());
			ps.executeUpdate();
		}catch (SQL Exception sqle){
			throw new Exception("Erro ao inserir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	//Atualiza Aluno
	public void atualizar (Aluno aluno) throws Exception{
		if(aluno == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE tb_aluno set nome=?, email=?, dataNascimento=?, endereco=? WHERE rgm = ?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setString(4, aluno.getEndereco());
			ps.setInt(5, aluno.getRgm());
			ps.executeUpdate();
		}catch (SQL Exception sqle){
			throw new Exception("Erro ao alterar dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
}