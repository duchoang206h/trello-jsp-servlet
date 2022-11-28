package models;

public class CardModel {
    private int id;
    private String description;
    private int order;
    private int listId;
    private int boardId;

    public CardModel(){}
    public CardModel(int boardId, int listId, String description, int order){
        this.boardId = boardId;
        this.listId = listId;
        this.description = description;
        this.order = order;
    }
    public int getOrder() {
        return order;
    }

    public int getBoardId() {
        return boardId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getListId() {
        return listId;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
