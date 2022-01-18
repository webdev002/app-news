package pdp.uz.appnewssites2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appnewssites2.entity.Post;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.PostDto;
import pdp.uz.appnewssites2.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getPost() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public ApiResponse addPost(PostDto postDto) {
        boolean exists = postRepository.existsByTitle(postDto.getTitle());
        if (exists){
            return new ApiResponse("Bunday sarlovhali post mavjud",false);
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post saqlandi",true);

    }

    public ApiResponse editPost(Long id, PostDto postDto) {
        boolean exists = postRepository.existsByUrlAndIdNot(postDto.getUrl(), id);
        if (exists){
            return new ApiResponse("Bunday Idlik va url lik post mavjud",false);
        }
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = postOptional.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post tahrirlandi",true);
    }

    public ApiResponse deletePost(Long id) {
        postRepository.deleteById(id);
        return new ApiResponse("Post Ochirildi",true);
    }
}
