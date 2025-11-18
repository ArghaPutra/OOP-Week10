public class MainKaryawan {

    public static void main(String[] args) {

        Pekerja pekerja = new Pekerja();
        pekerja.tanyaIdentitas();

        // contoh upcasting
        pekerja = new Ceo();
        pekerja.tanyaIdentitas();

        // contoh upcasting
        Karyawan karyawan = new Karyawan();
        pekerja = (Pekerja) karyawan;
        pekerja.tanyaIdentitas();
    }
}
