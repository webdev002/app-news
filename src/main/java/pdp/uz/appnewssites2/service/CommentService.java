package pdp.uz.appnewssites2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appnewssites2.entity.Comment;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.CommentDto;
import pdp.uz.appnewssites2.repository.CommentRepository;
import pdp.uz.appnewssites2.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
   @Autowired
   CommentRepository commentRepository;

   @Autowired
    PostRepository postRepository;

    public List<Comment> getComment() {
        List<Comment> commentList = commentRepository.findAll();
        return commentList;
    }

    public ApiResponse addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setPost(postRepository.getById(commentDto.getPost()));
        commentRepository.save(comment);
        return new ApiResponse("Comment saqlandi",true);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {
        boolean exists = commentRepository.existsByTextAndIdNot(commentDto.getText(), id);
        if (exists) {
            return new ApiResponse("Bunday Id lik comment mavjud",false);
        }
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment = optionalComment.get();
        comment.setText(commentDto.getText());
        comment.setPost(postRepository.getById(commentDto.getPost()));
        commentRepository.save(comment);
        return new ApiResponse("Comment tahrirlandi",true);
    }
}
