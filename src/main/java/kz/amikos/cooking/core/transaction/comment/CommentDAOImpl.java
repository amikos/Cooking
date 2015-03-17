package kz.amikos.cooking.core.transaction.comment;

import kz.amikos.cooking.web.models.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 861007350124 on 10.03.2015.
 */
@Repository
public class CommentDAOImpl implements CommentDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Long save(Comment comment) {
        return (Long) sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return (Comment) sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
    }
}
