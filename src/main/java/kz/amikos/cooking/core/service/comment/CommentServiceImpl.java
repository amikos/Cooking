package kz.amikos.cooking.core.service.comment;

import kz.amikos.cooking.core.transaction.comment.CommentDAO;
import kz.amikos.cooking.core.transaction.comment.CommentDAOImpl;
import kz.amikos.cooking.web.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 861007350124 on 10.03.2015.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAOImpl commentDAO;

    @Override
    public Long save(Comment comment) {
        return commentDAO.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentDAO.getById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentDAO.getAll();
    }
}
