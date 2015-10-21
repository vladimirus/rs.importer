package rs.importer.service;

import org.springframework.stereotype.Service;
import rs.importer.model.Comment;

import java.util.Collection;

@Service
public class CommentValidator implements Validator<Comment>{
    public Collection<Comment> filter(Collection<Comment> items) {
        return items;
    }
}
