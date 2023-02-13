package org.example.panel.user;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JFrame {
    public UserPanel(){
        setSize(500, 310);
        setTitle("Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 4, 0));

        JButton cadastro = new JButton("Cadastrar");
        cadastro.setBackground(Color.decode("#6F4A8E"));
        cadastro.setForeground(Color.WHITE);
        cadastro.addActionListener(e -> {
            new UserForm();
        });
        add(panel);
        add(cadastro);
        setVisible(true);

        JButton atualizar = new JButton("Atualizar");
        atualizar.setBackground(Color.decode("#6F4A8E"));
        atualizar.setForeground(Color.WHITE);
        atualizar.addActionListener(e -> {
            new UserUpdateForm();
        });
        add(panel);
        add(atualizar);
        setVisible(true);

        JButton delete = new JButton("Deletar");
        delete.setBackground(Color.decode("#6F4A8E"));
        delete.setForeground(Color.WHITE);
        delete.addActionListener(e -> {
            new UserRemoveForm();
        });
        add(panel);
        add(delete);
        setVisible(true);

        JButton consulta = new JButton("Consultar");
        consulta.setBackground(Color.decode("#6F4A8E"));
        consulta.setForeground(Color.WHITE);
        consulta.addActionListener(e -> {
            new QuerryForm();
        });
        add(panel);
        add(consulta);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UserPanel();
    }
}
