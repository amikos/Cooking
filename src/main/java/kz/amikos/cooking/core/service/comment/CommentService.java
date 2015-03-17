package kz.amikos.cooking.core.service.comment;

import kz.amikos.cooking.web.models.Comment;

import java.util.List;

/**
 * Created by 861007350124 on 10.03.2015.
 */
public interface CommentService {
    Long save(Comment comment);
    Comment getById(Long id);
    List<Comment> getAll();
}
