package dao;

import models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAO {
    public boolean create(UserModel user){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "insert into users  (name, email, password) values (?,?,?)";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
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
    public UserModel findOneByEmail (String email){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from users where email = ? limit 1";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            UserModel user = new UserModel();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public UserModel findOneById (int  userId){
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from users where id = ? limit 1";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            UserModel user = new UserModel();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public UserModel validate( String email, String password){
        try {
                UserModel user = this.findOneByEmail(email);
                if(user != null && BCrypt.checkpw(password, user.getPassword())) return user;
                return null;
        }catch (Exception e){
                System.out.print(e);
                return null;
        }
    }
    public boolean updateName(String name, int userId){
        try {
            String sql = "update users set name = ? where id = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, userId);
            ps.executeQuery();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean updateEmail(String email, int userId){
        try {
            String sql = "update users set email = ? where id = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, userId);
            ps.executeQuery();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean updatePassword(String password, int userId){
        try {
            String sql = "update users set password = ? where id = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(10)));
            ps.setInt(2, userId);
            ps.executeQuery();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
