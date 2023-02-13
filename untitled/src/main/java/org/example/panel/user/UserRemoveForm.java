package org.example.panel.user;

import org.example.controladores.UserController;

import javax.swing.*;
import java.awt.*;

public class UserRemoveForm extends JFrame {
    private UserController userController;
    public UserRemoveForm() {
        userController = new UserController();

        setSize(500, 310);
        setTitle("Remover User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 0, 3));
        
        JLabel idLabel = new JLabel("Id User");
        idLabel.setForeground(Color.decode("#6F4A8E"));
        idLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(idLabel);

        JTextField idInput = new JTextField(20);
        idInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(idInput);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setBackground(Color.decode("#6F4A8E"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            try{
                userController.delete(Integer.parseInt(idInput.getText()));
            } catch (Exception exc){
                System.out.println(exc.getMessage());
            }

        });

        add(panel);
        add(submitButton);
        setVisible(true);
    }
    public static void main(String[] args) {
        new UserRemoveForm();
    }
}
