package models;

public class BoardModel {
    private int id;
    private int ownerId;
    private String name;
    public BoardModel(int ownerId, String name){
        this.ownerId = ownerId;
        this.name = name;
    }
    public BoardModel(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
