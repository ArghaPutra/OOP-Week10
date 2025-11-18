public abstract class Barang {
    protected String id;
    protected String nama;
    protected int harga;
    protected int stock;

    public Barang(String id, String nama, int harga, int stock) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stock = stock;
    }

    public String getId()      { return id; }
    public String getNama()    { return nama; }
    public int    getHarga()   { return harga; }
    public int    getStock()   { return stock; }

    public void minusStok(int jumlah) {
        stock -= jumlah;
        if (stock < 0) stock = 0;
    }
}
