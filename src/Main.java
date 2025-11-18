import java.util.Scanner;

public class Main {

    // Simpan Handphone & Voucher sebagai array Barang -> upcasting
    private static Barang[] daftarBarang = new Barang[100];
    private static int jumlahBarang = 0;

    private static Order[] daftarPesanan = new Order[100];
    private static int jumlahPesanan = 0;

    private static int hpCounter = 0;
    private static int vCounter  = 0;
    private static int orderCounter = 0;

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("-----------Menu Toko Voucher & HP-----------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            String pilih = in.nextLine();

            switch (pilih) {
                case "1" -> pesanBarang();
                case "2" -> lihatPesanan();
                case "3" -> barangBaru();
                case "0" -> { return; } // keluar program
                default  -> System.out.println("Salah Input");
            }
        }
    }

    // ================== MENU 3: BARANG BARU ==================

    private static void barangBaru() {
        System.out.print("Voucher / Handphone (V/H): ");
        String jenis = in.nextLine().trim().toLowerCase();

        if (jenis.equals("h")) {
            String id = String.format("H%02d", ++hpCounter);
            System.out.print("Nama : ");
            String nama = in.nextLine();
            System.out.print("Harga : ");
            int harga = Integer.parseInt(in.nextLine());
            System.out.print("Stok : ");
            int stok = Integer.parseInt(in.nextLine());
            System.out.print("Warna : ");
            String warna = in.nextLine();

            // upcasting: simpan sebagai Barang
            Barang b = new Handphone(id, nama, harga, stok, warna);
            daftarBarang[jumlahBarang++] = b;

            System.out.println("Handphone telah berhasil diinput");

        } else if (jenis.equals("v")) {
            String id = String.format("V%02d", ++vCounter);
            System.out.print("Nama : ");
            String nama = in.nextLine();
            System.out.print("Harga : ");
            int nominal = Integer.parseInt(in.nextLine());
            System.out.print("Stok : ");
            int stok = Integer.parseInt(in.nextLine());
            System.out.print("PPN : ");
            double pajak = Double.parseDouble(in.nextLine());

            Barang b = new Voucher(id, nama, nominal, stok, pajak);
            daftarBarang[jumlahBarang++] = b;

            System.out.println("Voucher telah berhasil diinput");
        } else {
            System.out.println("Salah Input");
        }
    }

    // ================== MENU 1: PESAN BARANG ==================

    private static void pesanBarang() {
        if (jumlahBarang == 0) {
            System.out.println("Barang tidak tersedia");
            return;
        }

        System.out.println("Daftar Barang Toko Voucher & HP");
        for (int i = 0; i < jumlahBarang; i++) {
            Barang b = daftarBarang[i];
            if (b == null) continue;

            System.out.println("ID   : " + b.getId());
            System.out.println("Nama : " + b.getNama());
            if (b instanceof Voucher v) {          // downcasting
                System.out.println("Nominal : " + v.getNominal());
            }
            System.out.println("Stok : " + b.getStock());
            if (b instanceof Handphone) {
                System.out.println("Harga : " + b.getHarga());
            } else if (b instanceof Voucher v) {
                System.out.println("Harga : " + v.getHargaJual());
            }
            System.out.println("----------------------------------------");
        }

        System.out.println("Ketik 0 untuk batal");
        System.out.print("Pesan Barang (ID) : ");
        String id = in.nextLine().trim();
        if (id.equals("0")) return;

        Barang dipilih = null;
        for (int i = 0; i < jumlahBarang; i++) {
            if (daftarBarang[i] != null && daftarBarang[i].getId().equalsIgnoreCase(id)) {
                dipilih = daftarBarang[i];
                break;
            }
        }

        if (dipilih == null) {
            System.out.println("Barang tidak ditemukan");
            return;
        }

        System.out.print("Masukkan Jumlah : ");
        int qty = Integer.parseInt(in.nextLine());
        if (qty <= 0 || qty > dipilih.getStock()) {
            System.out.println("Stok tidak mencukupi");
            return;
        }

        int total;
        if (dipilih instanceof Voucher v) {
            total = v.getHargaJual() * qty;
            System.out.println(qty + " @ " + v.getNama() + " " + v.getNominal()
                    + " dengan total harga " + total);
        } else {
            total = dipilih.getHarga() * qty;
            System.out.println(qty + " @ " + dipilih.getNama()
                    + " dengan total harga " + total);
        }

        System.out.print("Masukkan jumlah uang : ");
        int bayar = Integer.parseInt(in.nextLine());
        if (bayar < total) {
            System.out.println("Jumlah uang tidak mencukupi");
            return;
        }

        dipilih.minusStok(qty);

        String oid = buatOrderId(dipilih);
        daftarPesanan[jumlahPesanan++] = new Order(oid, dipilih, qty, total);

        System.out.println("Berhasil dipesan");
    }

    private static String buatOrderId(Barang b) {
        String prefix;
        if (b instanceof Handphone) prefix = "OH";
        else                        prefix = "OV";
        return prefix + b.getId() + "-" + (orderCounter++);
    }

    // ================== MENU 2: LIHAT PESANAN ==================

    private static void lihatPesanan() {
        if (jumlahPesanan == 0) {
            System.out.println("(Belum ada pesanan)");
            return;
        }

        System.out.println("Daftar Pesanan Toko Multiguna");
        for (int i = 0; i < jumlahPesanan; i++) {
            Order o = daftarPesanan[i];
            Barang b = o.getBarang();

            System.out.println("ID   : " + o.getId());
            System.out.println("Nama : " + b.getNama());

            if (b instanceof Voucher v) {
                System.out.println("Nominal : " + v.getNominal());
            }

            System.out.println("Jumlah : " + o.getJumlah());
            System.out.println("Total  : " + o.getTotal());
            System.out.println("----------------------------------------");
        }
    }
}
