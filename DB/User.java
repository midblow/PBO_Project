package DB;

public class User {
    private int id;
    private String name;
    private String gmail;
    private String gender;
    private long nomorhp;
    private String alamat;

    public User(int id, String name, String gmail, String gender, long nomorhp, String alamat) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.gender = gender;
        this.nomorhp = nomorhp;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGmail() {
        return gmail;
    }

    public String getGender() {
        return gender;
    }

    public long getNomorhp() {
        return nomorhp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNomorhp(long nomorhp) {
        this.nomorhp = nomorhp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
