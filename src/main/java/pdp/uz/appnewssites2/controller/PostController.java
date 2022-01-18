package pdp.uz.appnewssites2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appnewssites2.aop.CheckPermission;
import pdp.uz.appnewssites2.entity.Post;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.PostDto;
import pdp.uz.appnewssites2.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;


    @GetMapping
    public HttpEntity<?> getPost(){
        List<Post> post = postService.getPost();
        return ResponseEntity.ok(post);
    }


    @PostMapping
    @CheckPermission(value = "ADD_POST")
    public HttpEntity<?> addPost(@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "EDIT_POST")
    @PutMapping("{id}")
    public HttpEntity<?> editPost(@PathVariable Long id,@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public  HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
