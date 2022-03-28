package src.main.java.com.jwa.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOPost daoPost = DAOPost.getInstance();
    private static final Logger LOGGER = Logger.getLogger(PostController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, res);
                    break;
                case "/insert":
                    addPost(req, res);
                    break;
                case "/delete":
                    deletePost(req, res);
                    break;
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/update":
                    updatePost(req, res);
                    break;
                default:
                    listPosts(req, res);
                    break;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL error", e);
        }
    }

    private void updatePost(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("post_id"));
        String title = req.getParameter("post_title");
        String body = req.getParameter("post_body");
        String created = req.getParameter("created");

        Post post = new Post(id, title, body, created);
        daoPost.update(post);
        res.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        String id = req.getParameter("post_id");
        Optional<Post> existingPost = daoPost.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-webapp/src/main/webapp/jsp/PostForm.jsp");
        existingPost.ifPresent(post -> req.setAttribute("post", post));
        dispatcher.forward(req, res);
    }

    private void deletePost(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("post_id"));
        Post post = new Post(id);
        daoPost.delete(post);
        res.sendRedirect("list");
    }

    private void addPost(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        String title = req.getParameter("post_title");
        String body = req.getParameter("post_body");
        String created = req.getParameter("created");

        Post newPost = new Post(title, body, created);
        daoPost.save(newPost);
        res.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-webapp/src/main/webapp/jsp/PostForm.jsp");
        dispatcher.forward(req, res);
    }

    private void listPosts(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-webapp/src/main/webapp/jsp/PostList.jsp");
        List<Post> posts = daoPost.findAll();
        req.setAttribute("posts", posts);
        dispatcher.forward(req, res);
    }
    
}
