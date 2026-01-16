import java.util.ArrayList;

public class Kamar {
    private String tipe;
    private int harga;
    private ArrayList<UnitKamar> units;

    public Kamar(String tipe, int harga, int jumlahUnit, String prefixKode) {
        this.tipe = tipe;
        this.harga = harga;
        units = new ArrayList<>();
        for (int i = 1; i <= jumlahUnit; i++) {
            units.add(new UnitKamar(prefixKode + i));
        }
    }
    public String getKodeKamar() {
        return this.getKodeKamar(); // Sesuaikan dengan nama variabel di class Kamar
    }

    public String getTipe() {
        return tipe;
    }

    public int getHarga() {
        return harga;
    }

    public ArrayList<UnitKamar> getUnits() {
        return units;
    }

    // Ambil unit kamar kosong pertama
    public UnitKamar bookingUnit(BookingOnline b) {
        for (UnitKamar u : units) {
            if (!u.isTerisi()) {
                u.tambahBooking(b);
                return u;
            }
        }
        return null;
    }

}
