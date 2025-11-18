public class Handphone extends Barang {
    private String warna;
    public static int total = 0;

    public Handphone(String id, String nama, int harga, int stock, String warna) {
        super(id, nama, harga, stock);
        this.warna = warna;
        total++;
    }

    public String getWarna() {
        return warna;
    }
}
