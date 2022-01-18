package pdp.uz.appnewssites2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appnewssites2.aop.CheckPermission;
import pdp.uz.appnewssites2.entity.Comment;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.CommentDto;
import pdp.uz.appnewssites2.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public HttpEntity<?> getComment(){
        List<Comment> comment = commentService.getComment();
        return ResponseEntity.ok(comment);
    }

    @CheckPermission(value = "ADD_COMMENT")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id,@RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
