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
            String sql = "insert into boards  (name, ownerId) values (?,?)";
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
                return board;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public boolean updateName(String name, int boardId) {
        try {
            String sql = "update boards set name = ? where id = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, boardId);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public ArrayList<BoardModel> findByUserIdLimitAndOffset(int userId, int limit, int offset){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from boards where ownerId = ? limit ? offset ?";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
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
    public boolean deleteOneById(int id){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "delete from boards where boards.id = ? limit 1";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<BoardModel> searchByName(String name, int userId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from boards where ownerId = ? and name like ?";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + name + "%");
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
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<BoardModel> searchByNameLimitAndOffset(String name, int userId, int limit, int offset){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from boards where ownerId = ? and name like ? limit ? offset ?";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + name + "%");
            ps.setInt(3, limit);
            ps.setInt(4, offset);
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
}
