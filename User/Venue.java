package User;

public class Venue {
    private int id;
    private String name;
    private String loc;
    private String image;

    // Constructor
    public Venue(int id, String name, String loc, String image) {
        this.id = id;
        this.name = name;
        this.loc = loc;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
