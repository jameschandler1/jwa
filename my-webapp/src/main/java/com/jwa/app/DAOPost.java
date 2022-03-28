package src.main.java.com.jwa.app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOPost implements DAOimplementInterface {
    
    private DAOPost() {
    
    }

    private static class SingletonHelper {
        private static final DAOPost INSTANCE = new DAOPost();
    }

    public static DAOPost getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Post> find(String id) throws SQLException {
        String sql = "SELECT post_id, post_title, post_body, created FROM posts WHERE post_id = ?";
        int id_post = 0;
        String post_title = "", post_body = "", created = "";
        Connection con = DataSourceFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) { //
            id_post = rs.getInt("post_id");
            post_title = rs.getString("post_title");
            post_body = rs.getString("post_body");
            created = rs.getString("created");
        }
        return Optional.of(new Post(id_post, post_title, post_body, created));
    }

    @Override
    public List<Post> findAll() throws SQLException {
        List<Post> post = new ArrayList<>();
        String sql = "SELECT post_id, post_title, post_body, created FROM posts";

        Connection con = DataSourceFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("post_id");
            String title = rs.getString("post_title");
            String body = rs.getString("post_body");
            String created = rs.getString("created");
            
            Post p = new Post(id, title, body, created);
            p.add(post);
        }
        return post;
    }

    @Override
    public boolean save(Post post) throws SQLException {
        String sql = "INSERT into posts (post_title, post_body, created) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        Connection con = DataSourceFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, post.getPostTitle());
        stmt.setString(2, post.getPostBody());
        stmt.setString(3, post.getCreated());
        rowInserted = stmt.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Post post) throws SQLException {
        String sql = "UPDATE posts SET post_title = ?, post_body = ?, created = ? WHERE post_id = ?";
        boolean rowUpdated = false;
        Connection con = DataSourceFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, post.getPostTitle());
        stmt.setString(2, post.getPostBody());
        stmt.setString(3, post.getCreated());
        stmt.setInt(4, post.getPostId());
        rowUpdated = stmt.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(Post post) throws SQLException {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        boolean rowDeleted = false;
        Connection con = DataSourceFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, post.getPostId());
        rowDeleted = stmt.executeUpdate() > 0;
        return rowDeleted;
    }

}
