package loja.model.dao;

import loja.connection.ConnectionFactory;
import loja.model.bean.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void salva(Funcionario funcionario){
        String sql = "Insert INTO funcionario(cpf, matricula, nome, email, telefone) VALUES (?,?,?,?,?)";

        //
        Connection conn = null;
        PreparedStatement ps = null; //instrução SQL pré-compilada.
        try{
            //criação da conexão com o banco;
            conn = ConnectionFactory.createConnectionMYSQL();

            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getMatricula());
            ps.setString(3, funcionario.getNome());
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getTelefone());

            // execut query
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally { // fecha o ps e conn
            try{
                if (ps != null){
                    ps.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void updade(Funcionario funcionario){
        String sql = "UPDATE funcionario SET cpf = ?, matricula = ?, nome = ?, email = ?, telefone = ?"+
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // criar conexao com oo banco
            conn = ConnectionFactory.createConnectionMYSQL();
            ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getMatricula());
            ps.setString(3, funcionario.getNome());
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getTelefone());

            //Pegando o id para ser atualizado
            ps.setInt(6, funcionario.getId());

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void delete(int id){
        String sql = "DELETE FROM funcionario Where id = ?";
        Connection conn = null;
        PreparedStatement ps =null;
        try{
            conn = ConnectionFactory.createConnectionMYSQL();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<Funcionario> getFucionario(){
        String sql = "SELECT * FROM funcionario";

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        Connection conn = null;
        PreparedStatement ps = null;
        // recuperar os dados do banco
        ResultSet rset = null;

        try{
            conn = ConnectionFactory.createConnectionMYSQL();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            rset = ps.executeQuery();

            while(rset.next()){
                Funcionario funcionario = new Funcionario(); // voltando como instancia

                //recuperando os dados
                funcionario.setId(rset.getInt("id"));
                funcionario.setCpf(rset.getString("cpf"));
                funcionario.setMatricula(rset.getString("matricula"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setEmail(rset.getString("email"));
                funcionario.setTelefone(rset.getString("telefone"));

                funcionarios.add(funcionario);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(rset != null){
                    rset.close();
                }
                if (ps != null){
                    ps.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return funcionarios;
        }

    }
}
