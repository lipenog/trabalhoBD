package org.example.controladores;

import org.example.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;

public class QuerryController {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static String userSemProdutos() throws SQLException {

        String sql = "SELECT * \n" +
                "FROM user \n" +
                "WHERE idUser NOT IN (SELECT idUser FROM produtos_venda);";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idUser") + "  :  ");
                stringBuffer.append(rs.getString("cpf") + "  :  ");
                stringBuffer.append(rs.getString("nome")+ "  :  ");
                stringBuffer.append(rs.getDate("datanasc").toLocalDate()+ "  :  ");
                stringBuffer.append(rs.getString("endereco")+ "  :  ");
                stringBuffer.append(rs.getString("sexo"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String todosUser() throws SQLException {

        String sql = "SELECT * FROM user ";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idUser") + "  :  ");
                stringBuffer.append(rs.getString("cpf") + "  :  ");
                stringBuffer.append(rs.getString("nome")+ "  :  ");
                stringBuffer.append(rs.getDate("datanasc").toLocalDate()+ "  :  ");
                stringBuffer.append(rs.getString("endereco")+ "  :  ");
                stringBuffer.append(rs.getString("sexo"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String gastoDosUsers() throws SQLException {

        String sql = "SELECT u.idUser, u.nome, p.preco * c.quantidade as precoTotal \n" +
                "FROM compra c, user u, produto p \n" +
                "WHERE c.idUser = u.idUser AND c.idProduto = p.idProduto";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idUser") + "  :  ");
                stringBuffer.append(rs.getString("nome")+ "  :  ");
                stringBuffer.append(rs.getDouble("precoTotal"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String vendedoresComSomaMaiorQue(double preco) throws SQLException {

        String sql = "SELECT idUser, sum(preco) as soma_produtos \n" +
                "FROM produtos_venda natural inner join produto \n" +
                "GROUP BY idUser \n" +
                "HAVING sum(preco) > ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setDouble(1, preco);
            ResultSet rs = stmt.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idUser") + "  :  ");
                stringBuffer.append(rs.getDouble("soma_produtos") + "");

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String usersSemTelOuVendemCat(int idCategoria) throws SQLException {

        String sql = "(SELECT nome \n" +
                "FROM user \n" +
                "WHERE idUser NOT IN (SELECT idUser FROM telefone))\n" +
                "union\n" +
                "(SELECT u.nome \n" +
                "FROM user u, produtos_venda pv, produto p \n" +
                "WHERE u.idUser = pv.idUser AND pv.idProduto = p.idProduto AND idCategoria = ?)";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getString("nome"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String usersQueComecaCom(String busca) throws SQLException {

        String sql = "SELECT nome \n" +
                "FROM user \n" +
                "WHERE nome like ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1, busca);
            ResultSet rs = stmt.executeQuery();

            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getString("nome"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String usersQueNasceramEntre(LocalDate d1, LocalDate d2) throws SQLException {

        String sql = "SELECT * \n" +
                "FROM user\n" +
                "WHERE datanasc BETWEEN ? AND ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setDate(1, Date.valueOf(d1));
            stmt.setDate(2, Date.valueOf(d2));
            ResultSet rs = stmt.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idUser") + "  :  ");
                stringBuffer.append(rs.getString("cpf") + "  :  ");
                stringBuffer.append(rs.getString("nome")+ "  :  ");
                stringBuffer.append(rs.getDate("datanasc").toLocalDate()+ "  :  ");
                stringBuffer.append(rs.getString("endereco")+ "  :  ");
                stringBuffer.append(rs.getString("sexo"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String mediaDeNotas(int idUser) throws SQLException {

        String sql = "SELECT avg(notaAvaliacao) as mediaAvaliacao \n" +
                "FROM avaliacao \n" +
                "WHERE idProduto IN (SELECT idProduto FROM produtos_venda WHERE idUser = ?)\n" +
                "GROUP BY idProduto\n" +
                "ORDER BY mediaAvaliacao desc;";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getDouble("mediaAvaliacao"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String produtosSemNota() throws SQLException {

        String sql = "SELECT idProduto \n" +
                "FROM produto p\n" +
                "WHERE NOT EXISTS (SELECT * FROM avaliacao a WHERE a.idProduto = p.idProduto);";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getInt("idProduto"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String usersQueVendemMaisQue(int idUser) throws SQLException {

        String sql = "SELECT DISTINCT u.nome\n" +
                "FROM user u, compra c, produto p\n" +
                "WHERE u.idUser = c.idUser AND p.idProduto = c.idProduto\n" +
                "\tAND c.quantidade * p.preco > ALL (SELECT (c.quantidade * p.preco) FROM compra c, produto p WHERE idUser = ?);";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getString("nome"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    public static String precoEDdd(double preco, String ddd) throws SQLException {

        String sql = "(SELECT u.nome\n" +
                "FROM user u, produtos_venda pv, produto p\n" +
                "WHERE u.idUser = pv.idUser AND pv.idProduto = p.idProduto AND p.preco > ?) \n" +
                "intersect\n" +
                "(SELECT u.nome\n" +
                "FROM user u, telefone t\n" +
                "WHERE u.idUser = t.idUser and t.ddd = ?)";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setDouble(1, preco);
            stmt.setString(2, ddd);
            ResultSet rs = stmt.executeQuery();
            StringBuffer stringBuffer = new StringBuffer();
            while (rs.next()) {

                stringBuffer.append(rs.getString("nome"));

                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }
}
