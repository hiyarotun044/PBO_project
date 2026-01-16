public abstract class Booking {
    protected Penghuni penghuni;
    protected int lamaSewa;
    protected String tipeKamar;
    protected int hargaPerBulan;

    public Booking(Penghuni penghuni, int lamaSewa, String tipeKamar, int hargaPerBulan) {
        this.penghuni = penghuni;
        this.lamaSewa = lamaSewa;
        this.tipeKamar = tipeKamar;
        this.hargaPerBulan = hargaPerBulan;
    }

    public int hitungTotal() {
        return lamaSewa * hargaPerBulan;
    }

    // 🔥 agar bisa tampil di JList
    public String infoSingkat() {
        return penghuni.getNama() + " - " + lamaSewa + " bulan (" + tipeKamar + ")";
    }

    public abstract void tampilkanBooking();
}
