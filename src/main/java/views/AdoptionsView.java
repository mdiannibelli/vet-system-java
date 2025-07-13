package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import domain.entities.Adoption;

public class AdoptionsView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnDelete, btnBack;
    private DeleteListener deleteListener;
    private BackListener backListener;

    public AdoptionsView() {
        setTitle("Adopciones");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[] { "ID", "Adoptante", "Mascota", "Fecha" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        btnDelete = new JButton("Eliminar adopción");
        btnBack = new JButton("Volver atras");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnDelete);
        panelBotones.add(btnBack);

        add(panelBotones, BorderLayout.SOUTH);

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1 && deleteListener != null) {
                int id = (int) model.getValueAt(selectedRow, 0);
                deleteListener.onDelete(id);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una adopción para eliminar.");
            }
        });

        btnBack.addActionListener(e -> {
            if (backListener != null) {
                backListener.onBack();
            }
        });

        setVisible(true);
    }

    public void loadAdoptions(List<Adoption> adoptions) {
        model.setRowCount(0);
        for (Adoption a : adoptions) {
            model.addRow(new Object[] {
                    a.getId(),
                    a.getAdopter().getName(),
                    a.getPet().getName(),
                    a.getDateAdoption()
            });
        }
    }

    public void setDeleteListener(DeleteListener listener) {
        this.deleteListener = listener;
    }

    public void setBackListener(BackListener listener) {
        this.backListener = listener;
    }

    public interface DeleteListener {
        void onDelete(int id);
    }

    public interface BackListener {
        void onBack();
    }
}
