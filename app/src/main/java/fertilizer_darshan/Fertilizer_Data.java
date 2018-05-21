package fertilizer_darshan;

public class Fertilizer_Data {
    int image;
    String name,kg;

    public Fertilizer_Data(int image, String name, String kg) {
        this.image = image;
        this.name = name;
        this.kg = kg;
    }

    public int getImage() {
        return image;
    }

    public String getName(int position) {
        return name;
    }

    public String getkg() { return kg; }
    }

