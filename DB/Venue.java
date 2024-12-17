package DB;

public class Venue {
    private int idVenue;
    private String namaVenue;
    private String deskripsiFasilitas;
    private String alamat;
    private String penanggungJawab;
    private int kapasitas;
    private int harga;
    private String kota;
    private String gambar;
    private String jenisInstansi;
    private int idProvider;
    private boolean mainVenue;

    public Venue(int idVenue, String namaVenue, String deskripsiFasilitas, String alamat, String penanggungJawab,
                 int kapasitas, int harga, String kota, String gambar, String jenisInstansi, int idProvider, boolean mainVenue) {
        this.idVenue = idVenue;
        this.namaVenue = namaVenue;
        this.deskripsiFasilitas = deskripsiFasilitas;
        this.alamat = alamat;
        this.penanggungJawab = penanggungJawab;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.kota = kota;
        this.gambar = gambar;
        this.jenisInstansi = jenisInstansi;
        this.idProvider = idProvider;
        this.mainVenue = mainVenue;
    }

    public int getIdVenue() {
        return idVenue;
    }

    public void setIdVenue(int idVenue) {
        this.idVenue = idVenue;
    }

    public String getNamaVenue() {
        return namaVenue;
    }

    public void setNamaVenue(String namaVenue) {
        this.namaVenue = namaVenue;
    }

    public String getDeskripsiFasilitas() {
        return deskripsiFasilitas;
    }

    public void setDeskripsiFasilitas(String deskripsiFasilitas) {
        this.deskripsiFasilitas = deskripsiFasilitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(String penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenisInstansi() {
        return jenisInstansi;
    }

    public void setJenisInstansi(String jenisInstansi) {
        this.jenisInstansi = jenisInstansi;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public boolean isMainVenue() {
        return mainVenue;
    }

    public void setMainVenue(boolean mainVenue) {
        this.mainVenue = mainVenue;
    }
}
