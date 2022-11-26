package dao;

import models.BoardModel;
import models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class BoardDAO {
    public boolean create(BoardModel board){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "insert into boards  (name, owerId) values (?,?)";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, board.getName());
            ps.setInt(2, board.getOwnerId());
            ps.executeUpdate();
            con.close();
            return true;
        }catch (Exception e){
            System.out.print(e);
            return false;
        }
    }
    /*public boolean update(UserModel user){

    }*/
    public ArrayList<BoardModel> findAllByUserId (int userId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from boards where boards.ownerId = ?";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            ArrayList<BoardModel> boards = new ArrayList<>();
            while(rs.next()){
                BoardModel board = new BoardModel();
                board.setId(rs.getInt("id"));
                board.setName(rs.getString("name"));
                board.setOwnerId(rs.getInt("ownerId"));
                boards.add(board);
            }
            return boards;
        }catch (Exception e){
            return null;
        }
    }
    public BoardModel findOneById(int id){
        try {
            String sql = "select * from boards where boards.id = ? limit 1";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            BoardModel board = new BoardModel();
            while(rs.next()){
                board.setId(rs.getInt("id"));
                board.setName(rs.getString("name"));
                board.setOwnerId(rs.getInt("ownerId"));
            }
            return board;
        }catch (Exception e){
            return null;
        }
    }


}