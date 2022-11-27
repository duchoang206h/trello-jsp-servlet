package models;

public class ListModel {
    private int id;
    private String name;
    private int order;
    private int boardId;
    public ListModel(){}
    public ListModel(String name, int boardId, int order){
        this.name = name;
        this.boardId = boardId;
        this.order = order;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBoardId() {
        return boardId;
    }

    public int getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}