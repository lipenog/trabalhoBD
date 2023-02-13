package org.example.panel.user;

import org.example.controladores.QuerryController;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class QuerryForm extends JFrame {
    private QuerryController querryController;

    public QuerryForm(){
        querryController = new QuerryController();

        setSize(1360, 768);
        setTitle("Consultas: User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1, 3, 3));


        JButton botao1 = new JButton("Consulta User sem Produtos");
        botao1.setBackground(Color.decode("#6F4A8E"));
        botao1.setForeground(Color.WHITE);
        botao1.addActionListener(e -> {
            try{
                String txt = querryController.userSemProdutos();
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        JButton botao2 = new JButton("Listar Todos Users");
        botao2.setBackground(Color.decode("#6F4A8E"));
        botao2.setForeground(Color.WHITE);
        botao2.addActionListener(e -> {
            try{
                String txt = querryController.todosUser();
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        JButton botao3 = new JButton("Listar Gasto Dos Users");
        botao3.setBackground(Color.decode("#6F4A8E"));
        botao3.setForeground(Color.WHITE);
        botao3.addActionListener(e -> {
            try{
                String txt = querryController.gastoDosUsers();
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        JPanel panel1 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField precoInput = new JTextField(20);
        precoInput.setBackground(Color.decode("#EBEBEB"));


        JButton botao4 = new JButton("Vendedores com venda maior que");
        botao4.setBackground(Color.decode("#6F4A8E"));
        botao4.setForeground(Color.WHITE);
        botao4.addActionListener(e -> {
            try{
                System.out.println(precoInput.getText());
                String txt = querryController.vendedoresComSomaMaiorQue(Double.parseDouble(precoInput.getText()));
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });


        panel1.add(botao4);
        panel1.add(precoInput);

        JPanel panel2 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField categoriaInput = new JTextField(20);
        categoriaInput.setBackground(Color.decode("#EBEBEB"));


        JButton botao5 = new JButton("Listar User sem telefone ou vendem produto categoria");
        botao5.setBackground(Color.decode("#6F4A8E"));
        botao5.setForeground(Color.WHITE);
        botao5.addActionListener(e -> {
            try{
                String txt = querryController.usersSemTelOuVendemCat(Integer.parseInt(categoriaInput.getText()));
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel2.add(botao5);
        panel2.add(categoriaInput);

        JPanel panel3 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField nomeInput = new JTextField(20);
        nomeInput.setBackground(Color.decode("#EBEBEB"));

        JButton botao6 = new JButton("Consulta por nome");
        botao6.setBackground(Color.decode("#6F4A8E"));
        botao6.setForeground(Color.WHITE);
        botao6.addActionListener(e -> {
            try{
                String txt = querryController.usersQueComecaCom(nomeInput.getText());
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel3.add(botao6);
        panel3.add(nomeInput);

        JPanel panel4 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField data1Input = new JTextField(20);
        data1Input.setBackground(Color.decode("#EBEBEB"));

        JTextField data2Input = new JTextField(20);
        data2Input.setBackground(Color.decode("#EBEBEB"));

        JButton botao7 = new JButton("Users que nasceram entre");
        botao7.setBackground(Color.decode("#6F4A8E"));
        botao7.setForeground(Color.WHITE);
        botao7.addActionListener(e -> {
            try{
                String txt = querryController.usersQueNasceramEntre(Date.valueOf(data1Input.getText()).toLocalDate(), Date.valueOf(data2Input.getText()).toLocalDate());
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel4.add(botao7);
        panel4.add(data1Input);
        panel4.add(data2Input);

        JPanel panel5 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField idInput = new JTextField(20);
        idInput.setBackground(Color.decode("#EBEBEB"));

        JButton botao8 = new JButton("Media de avaliações do user");
        botao8.setBackground(Color.decode("#6F4A8E"));
        botao8.setForeground(Color.WHITE);
        botao8.addActionListener(e -> {
            try{
                String txt = querryController.mediaDeNotas(Integer.parseInt(idInput.getText()));
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel5.add(botao8);
        panel5.add(idInput);

        JButton botao9 = new JButton("Listar Produtos sem avaliações");
        botao9.setBackground(Color.decode("#6F4A8E"));
        botao9.setForeground(Color.WHITE);
        botao9.addActionListener(e -> {
            try{
                String txt = querryController.produtosSemNota();
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        JPanel panel6 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField idInput1 = new JTextField(20);
        idInput1.setBackground(Color.decode("#EBEBEB"));

        JButton botao10 = new JButton("Users que vendem mais que outro user");
        botao10.setBackground(Color.decode("#6F4A8E"));
        botao10.setForeground(Color.WHITE);
        botao10.addActionListener(e -> {
            try{
                String txt = querryController.usersQueVendemMaisQue(Integer.parseInt(idInput1.getText()));
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel6.add(botao10);
        panel6.add(idInput1);

        JPanel panel7 = new JPanel(new GridLayout(1, 3, 3, 3));

        JTextField precoInput1 = new JTextField(20);
        precoInput1.setBackground(Color.decode("#EBEBEB"));

        JTextField dddInput = new JTextField(20);
        dddInput.setBackground(Color.decode("#EBEBEB"));

        JButton botao11 = new JButton("DDD e preco De produto");
        botao11.setBackground(Color.decode("#6F4A8E"));
        botao11.setForeground(Color.WHITE);
        botao11.addActionListener(e -> {
            try{
                String txt = querryController.precoEDdd(Double.parseDouble(precoInput1.getText()), dddInput.getText());
                new QuerryResult(txt);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        panel7.add(botao11);
        panel7.add(precoInput1);
        panel7.add(dddInput);

        panel.add(botao1);
        panel.add(botao2);
        panel.add(botao3);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(botao9);
        panel.add(panel6);
        panel.add(panel7);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new QuerryForm();
    }
}
