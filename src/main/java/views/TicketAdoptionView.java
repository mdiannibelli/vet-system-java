package views;

import javax.swing.*;
import java.awt.*;

public class TicketAdoptionView extends JFrame {
    public JTextArea areaTicket;

    public TicketAdoptionView(String ticketText) {
        setTitle("Adoption Ticket");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaTicket = new JTextArea(ticketText);
        areaTicket.setEditable(false);
        areaTicket.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(areaTicket), BorderLayout.CENTER);
        setVisible(true);
    }
}
