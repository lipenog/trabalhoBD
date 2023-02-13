package org.example.panel.user;

import javax.swing.*;

public class QuerryResult extends JFrame {
    private JTextArea areaDeTexto;

    public QuerryResult(String txt){
        setSize(500, 310);
        setTitle("Consultas: User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        areaDeTexto = new JTextArea();
        areaDeTexto.setText(txt);

        add(areaDeTexto);
        setVisible(true);
    }

}
