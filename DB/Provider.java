package DB;

public class Provider {
    private int idProvider;
    private String gmail;
    private String username;
    private String lembaga;
    private String password;
    private long nomorHp;
    private String alamat;

    // Constructor
    public Provider(int idProvider, String gmail, String username, String lembaga, String password, long nomorHp, String alamat) {
        this.idProvider = idProvider;
        this.gmail = gmail;
        this.username = username;
        this.lembaga = lembaga;
        this.password = password;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
    }

    // Getters and Setters
    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLembaga() {
        return lembaga;
    }

    public void setLembaga(String lembaga) {
        this.lembaga = lembaga;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(long nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
