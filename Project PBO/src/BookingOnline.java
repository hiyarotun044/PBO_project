public class BookingOnline {
    private Penghuni penghuni;
    private int lamaSewa;
    private Kamar kamar;

    public BookingOnline(Penghuni penghuni, int lamaSewa, Kamar kamar) {
        this.penghuni = penghuni;
        this.lamaSewa = lamaSewa;
        this.kamar = kamar;
    }

    public Penghuni getPenghuni() {
        return penghuni;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public int hitungTotal() {
        return lamaSewa * kamar.getHarga();
    }
}
