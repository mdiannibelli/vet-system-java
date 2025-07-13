package views;

import javax.swing.*;
import java.awt.*;

public class TicketAdoptionView extends JFrame {
    public JTextArea areaTicket;

    public TicketAdoptionView(String ticketText) {
        this.createView();
        this.areaTicket.setText(ticketText);
    }

    private void createView() {
        setTitle("Ticket de Adopción");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaTicket = new JTextArea();
        areaTicket.setEditable(false);
        areaTicket.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTicket.setLineWrap(true);
        areaTicket.setWrapStyleWord(true);
        areaTicket.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(areaTicket);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        // Botón para volver al menú principal
        JButton btnBack = new JButton("Volver al menú principal");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnBack);
        add(panelBoton, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> {
            dispose();
            new controller.MainMenuController(new views.MainMenuView());
        });

        setVisible(true);
    }
}
