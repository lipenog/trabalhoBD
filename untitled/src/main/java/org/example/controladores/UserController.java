package org.example.controladores;

import org.example.ConnectionManager;
import org.example.tabelas.User;

import java.sql.*;

public class UserController {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static void getAllUser() throws SQLException {

        String sql = "SELECT idUser, nome FROM user";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            while (rs.next()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(rs.getInt("idUser") + ": ");
                stringBuffer.append(rs.getString("nome") +", ");
                System.out.println(stringBuffer.toString());
            }
        }
    }

    public static User getUser(int idUser) throws SQLException {

        String sql = "SELECT * FROM user WHERE idUser = ?";
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User bean = new User();
                bean.setIdUser(idUser);
                bean.setNome(rs.getString("nome"));
                bean.setCpf(rs.getString("cpf"));
                bean.setSexo(rs.getString("sexo"));
                bean.setEndereco(rs.getString("endereco"));
                bean.setDataNasc(rs.getDate("datanasc").toLocalDate());
                return bean;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static boolean insert(User bean) throws Exception {

        String sql = "INSERT into user (cpf, nome, datanasc, endereco, sexo) " +
                "VALUES (?, ?, ?, ?, ? )";
        ResultSet keys = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, bean.getCpf());
            stmt.setString(2, bean.getNome());
            stmt.setDate(3, Date.valueOf(bean.getDataNasc()));
            stmt.setString(4, bean.getEndereco());
            stmt.setString(5, bean.getSexo());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setIdUser(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            if (keys != null) keys.close();
        }
        return true;
    }

    public static boolean update(User bean) throws Exception {

        String sql = "SELECT * FROM user WHERE idUser = ?";

        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(
                        sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
        ){

            stmt.setInt(1, bean.getIdUser());
            rs = stmt.executeQuery();

            if (rs.next()) {
                rs.updateString("cpf", bean.getCpf());
                rs.updateString("nome", bean.getNome());
                rs.updateDate("datanasc", Date.valueOf(bean.getDataNasc()));
                rs.updateString("endereco", bean.getEndereco());
                rs.updateString("sexo", bean.getSexo());
                rs.updateRow();
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (rs != null) rs.close();
        }
    }

    public static boolean delete(int idUser) throws Exception {

        String sql = "DELETE FROM user WHERE idUser = ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setInt(1, idUser);
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
