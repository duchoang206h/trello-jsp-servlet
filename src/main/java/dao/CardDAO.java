package dao;

import models.BoardModel;
import models.CardModel;
import models.ListModel;
import models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class CardDAO {
    public boolean create(CardModel card){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "insert into cards  (description, `order`, boardId, listId) values (?,?,?,?)";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, card.getDescription());
            ps.setInt(2, card.getOrder());
            ps.setInt(3, card.getBoardId());
            ps.setInt(4, card.getListId());
            ps.execute();
            con.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /*public boolean update(UserModel user){

    }*/
    public ArrayList<CardModel> findByListIdAndBoardId (int listId, int boarId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from cards where listId = ? and boardId = ? order by cards.order asc ";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, listId);
            ps.setInt(2, boarId);
            ResultSet rs = ps.executeQuery();
            ArrayList<CardModel> cards = new ArrayList<>();
            while(rs.next()){
                CardModel card = new CardModel();
                card.setId(rs.getInt("id"));
                card.setDescription(rs.getString("description"));
                card.setOrder(rs.getInt("order"));
                card.setListId(rs.getInt("listId"));
                card.setBoardId(rs.getInt("boardId"));
                cards.add(card);
            }
            return cards;
        }catch (Exception e){
            return null;
        }
    }
    public CardModel findOneById(int id){
        try {
            String sql = "select * from cards where id = ? limit 1";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            CardModel card = new CardModel();
            while(rs.next()){
                card.setId(rs.getInt("id"));
                card.setDescription(rs.getString("description"));
                card.setOrder(rs.getInt("order"));
                card.setBoardId(rs.getInt("boardId"));
                card.setListId(rs.getInt("listId"));
            }
            return card;
        }catch (Exception e){
            return null;
        }
    }
    public int findLatestOrderByBoardIdAndListId(int boardId, int listId){
        try {
            String sql =  "select max(`order`) as maxorder from cards where boardId = ? and listId = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, boardId);
            ps.setInt(2, listId);
            ResultSet rs = ps.executeQuery();
            CardModel card = new CardModel();
            while(rs.next()){
                return rs.getInt("maxorder");
            }
            return 0;
        }catch (Exception e){
            return 0;
        }
    }
    public  boolean updateCardDescription(int boardId, int listId, int cardId, String description){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "update cards set description = ? where id = ? and boardId = ? and listId = ?;";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, description);
            ps.setInt(2, cardId);
            ps.setInt(3, boardId);
            ps.setInt(4, listId);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteOne(int boardId, int listId, int cardId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "delete from cards where boardId = ? and listId = ? and id = ? limit 1";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, boardId);
            ps.setInt(2, listId);
            ps.setInt(3, cardId);
            ps.executeUpdate();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
