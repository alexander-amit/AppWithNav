package aph.com.appwithnav.model;

public class Subject {

    private int id;
    private String stream;
    private String name;

    public Subject(int id, String stream, String name) {
        this.id = id;
        this.stream = stream;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
