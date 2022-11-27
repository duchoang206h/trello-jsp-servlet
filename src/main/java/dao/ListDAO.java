package dao;

import models.BoardModel;
import models.ListModel;
import models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class ListDAO {
    public boolean create(ListModel list){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "insert into lists  (name, order, boardId) values (?,?,?)";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, list.getName());
            ps.setInt(2, list.getOrder());
            ps.setInt(3, list.getBoardId());
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
    public ArrayList<ListModel> findByBoardId (int boarId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from lists where boardId = ?";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, boarId);
            ResultSet rs = ps.executeQuery();
            ArrayList<ListModel> lists = new ArrayList<>();
            while(rs.next()){
                ListModel list = new ListModel();
                list.setId(rs.getInt("id"));
                list.setName(rs.getString("name"));
                list.setBoardId(rs.getInt("boardId"));
                list.setOrder(rs.getInt("order"));
                lists.add(list);
            }
            return lists;
        }catch (Exception e){
            return null;
        }
    }
    public ListModel findOneById(int id){
        try {
            String sql = "select * from boards where boards.id = ? limit 1";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ListModel list = new ListModel();
            while(rs.next()){
                list.setId(rs.getInt("id"));
                list.setName(rs.getString("name"));
                list.setOrder(rs.getInt("order"));
                list.setBoardId(rs.getInt("boardId"));
            }
            return list;
        }catch (Exception e){
            return null;
        }
    }


}
