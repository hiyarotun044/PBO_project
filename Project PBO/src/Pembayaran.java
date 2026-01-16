public class Pembayaran {
    private int bulan;
    private int total;

    public Pembayaran(int bulan, int hargaPerBulan) {
        this.bulan = bulan;
        this.total = bulan * hargaPerBulan;
    }

    public int getTotal() {
        return total;
    }
}
