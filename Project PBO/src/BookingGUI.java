import javax.swing.*;
import java.awt.*;

public class BookingGUI extends JFrame {

    private JTextField txtNama, txtLama;
    private JComboBox<String> cbTipeKamar;
    private JTextArea areaDetail;
    private JList<String> listBooking;
    private DefaultListModel<String> listModel;

    private Kamar kamarIsian, kamarKosongan;

    public BookingGUI() {
        setTitle("Aplikasi Booking Kost");
        setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== KAMAR =====
        kamarIsian = new Kamar("Isian", 1000000, 3, "I"); // I1,I2,I3
        kamarKosongan = new Kamar("Kosongan", 750000, 2, "K"); // K1,K2

        // ===== PANEL INPUT =====
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Booking"));

        panelInput.add(new JLabel("Nama Penghuni"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Lama Sewa (bulan)"));
        txtLama = new JTextField();
        panelInput.add(txtLama);

        panelInput.add(new JLabel("Tipe Kamar"));
        cbTipeKamar = new JComboBox<>(new String[]{"Isian", "Kosongan"});
        panelInput.add(cbTipeKamar);

        JButton btnBooking = new JButton("Booking");
        JButton btnHapus = new JButton("Hapus Terpilih");
        panelInput.add(btnBooking);
        panelInput.add(btnHapus);

        add(panelInput, BorderLayout.NORTH);

        // ===== LIST BOOKING =====
        listModel = new DefaultListModel<>();
        listBooking = new JList<>(listModel);
        listBooking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollList = new JScrollPane(listBooking);
        scrollList.setBorder(BorderFactory.createTitledBorder("Daftar Penghuni"));

        // ===== DETAIL OUTPUT =====
        areaDetail = new JTextArea();
        areaDetail.setEditable(false);
        areaDetail.setLineWrap(true);
        areaDetail.setWrapStyleWord(true);
        areaDetail.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollDetail = new JScrollPane(areaDetail);
        scrollDetail.setBorder(BorderFactory.createTitledBorder("Detail Booking"));

        // ===== CENTER PANEL =====
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(scrollList);
        centerPanel.add(scrollDetail);
        add(centerPanel, BorderLayout.CENTER);

        // ===== BOOKING ACTION =====
        btnBooking.addActionListener(e -> {
            try {
                String nama = txtNama.getText().trim();
                int lama = Integer.parseInt(txtLama.getText().trim());
                String tipe = cbTipeKamar.getSelectedItem().toString();

                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Nama tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Kamar kamarAktif = tipe.equals("Isian") ? kamarIsian : kamarKosongan;
                Penghuni p = new Penghuni(nama);
                BookingOnline booking = new BookingOnline(p, lama, kamarAktif);

                // Ambil unit kamar kosong (UnitKamar)
                UnitKamar unit = kamarAktif.bookingUnit(booking);
                if (unit == null) {
                    JOptionPane.showMessageDialog(this, "Kamar penuh!");
                    return;
                }

                // Tambahkan ke list JList
                listModel.addElement(nama + " | " + tipe + " | " + lama + " bulan | " + unit.getKodeKamar());

                // Update detail booking
                updateDetail();

                // Reset input
                txtNama.setText("");
                txtLama.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Lama sewa harus angka (contoh: 3)",
                        "Input Salah", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ===== HAPUS BOOKING =====
        btnHapus.addActionListener(e -> {
            int index = listBooking.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Pilih penghuni yang akan dihapus",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String selected = listBooking.getSelectedValue();
            String kodeKamar = selected.split("\\|")[3].trim();

            // Cari unit kamar di semua tipe
            UnitKamar unit = null;
            for (UnitKamar u : kamarIsian.getUnits())
                if (u.getKodeKamar().equals(kodeKamar)) unit = u;
            for (UnitKamar u : kamarKosongan.getUnits())
                if (u.getKodeKamar().equals(kodeKamar)) unit = u;

            if (unit != null) {
                unit.hapusBooking();              // Kosongkan unit
                listModel.remove(index);           // Hapus dari list
                updateDetail();                    // Update detail
            }
        });
    }

    // ===== UPDATE DETAIL =====
    private void updateDetail() {
        StringBuilder sb = new StringBuilder();

        sb.append("Kamar Isian:\n");
        for (UnitKamar u : kamarIsian.getUnits()) {
            if (u.isTerisi()) {
                BookingOnline b = u.getBooking();
                sb.append(u.getKodeKamar()).append(": ").append(b.getPenghuni().getNama())
                        .append(", ").append(b.getLamaSewa()).append(" bulan, Total: Rp ")
                        .append(b.hitungTotal()).append("\n");
            }
        }

        sb.append("\nKamar Kosongan:\n");
        for (UnitKamar u : kamarKosongan.getUnits()) {
            if (u.isTerisi()) {
                BookingOnline b = u.getBooking();
                sb.append(u.getKodeKamar()).append(": ").append(b.getPenghuni().getNama())
                        .append(", ").append(b.getLamaSewa()).append(" bulan, Total: Rp ")
                        .append(b.hitungTotal()).append("\n");
            }
        }

        areaDetail.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookingGUI().setVisible(true));
    }
}
