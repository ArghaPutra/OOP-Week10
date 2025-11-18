public class Voucher extends Barang {
    private double pajak;      // misal 0.1 = 10%
    public static int total = 0;

    public Voucher(String id, String nama, int nominal, int stock, double pajak) {
        super(id, nama, nominal, stock);
        this.pajak = pajak;
        total++;
    }

    public int getNominal() {
        return harga;          // harga = nominal dasar
    }

    public double getPajak() {
        return pajak;
    }

    public int getHargaJual() {
        return (int) Math.round(harga + harga * pajak);
    }
}
