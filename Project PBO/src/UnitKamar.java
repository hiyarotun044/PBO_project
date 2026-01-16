public class UnitKamar {
    private String kodeKamar;
    private boolean terisi;
    private BookingOnline booking;

    public UnitKamar(String kodeKamar) {
        this.kodeKamar = kodeKamar;
        this.terisi = false;
        this.booking = null;
    }

    public String getKodeKamar() {
        return kodeKamar;
    }

    public boolean isTerisi() {
        return terisi;
    }

    public BookingOnline getBooking() {
        return booking;
    }

    public boolean tambahBooking(BookingOnline b) {
        if (!terisi) {
            this.booking = b;
            this.terisi = true;
            return true;
        }
        return false;
    }

    public void hapusBooking() {
        this.booking = null;
        this.terisi = false;
    }
}
