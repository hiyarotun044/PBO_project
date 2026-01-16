import java.util.ArrayList;

public class Kost {
    private ArrayList<Penghuni> daftarPenghuni = new ArrayList<>();
    private Kamar kamar;

    public Kost(Kamar kamar) {
        this.kamar = kamar;
    }

    public void tambahPenghuni(Penghuni p) {
        daftarPenghuni.add(p);
    }

    public void tampilkanData() {
        System.out.println("\n=== DATA KOST ===");
        System.out.println("Kamar : " + kamar.getKodeKamar() + " (" + kamar.getTipe() + ")");
        System.out.println("Harga : " + kamar.getHarga());

        System.out.println("\nDaftar Penghuni:");
        for (int i = 0; i < daftarPenghuni.size(); i++) {
            System.out.println((i + 1) + ". " + daftarPenghuni.get(i).getNama());
        }
    }
}
