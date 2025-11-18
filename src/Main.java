public class Main {
    public static void main(String[] args) {
        Ceo c = new Ceo();
        Pekerja p = new Ceo();
        c = (Ceo)p;
        c.tanyaPendapatan();
    }
}
