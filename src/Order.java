public class Order {
    private String id;
    private Barang barang;   // upcasting: bisa Handphone atau Voucher
    private int jumlah;
    private int total;

    public Order(String id, Barang barang, int jumlah, int total) {
        this.id = id;
        this.barang = barang;
        this.jumlah = jumlah;
        this.total = total;
    }

    public String getId()     { return id; }
    public Barang getBarang() { return barang; }
    public int getJumlah()    { return jumlah; }
    public int getTotal()     { return total; }
}
