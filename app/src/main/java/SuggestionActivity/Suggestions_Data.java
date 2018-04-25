package SuggestionActivity;

public class Suggestions_Data {
    int image,i;
    String name, Type;

    public Suggestions_Data(int image, String name, String Type, int i) {
        this.image = image;
        this.name = name;
        this.Type = Type;
        this.i = i;
    }

    public int getImage() {
        return image;
    }

    public String getName(int position) {
        return name;
    }

    public String getType() {
        return Type;
    }

    public int geti() {
        return i;
    }
}

