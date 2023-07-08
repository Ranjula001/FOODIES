package com.project.foodies.PostManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController 
@RequestMapping(path = "/paf") 
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @CrossOrigin(origins = "http://localhost:3000")

    @PostMapping(path = "/addpost")  
    public @ResponseBody String addNewPost(@RequestParam String caption, @RequestParam String expression, @RequestParam("photo") MultipartFile photo) throws IOException {

    @PostMapping(path = "/addpost/{userId}")  
    public @ResponseBody String addNewPost(@PathVariable Integer userId,@RequestParam String caption, @RequestParam String expression, @RequestParam("photo") MultipartFile[] photos) throws IOException {

       
       
        List<byte[]> photoList = new ArrayList<>();

        for (MultipartFile photo : photos) {
            photoList.add(photo.getBytes());
        } 


       
        Post n = new Post();
        
        n.setCaption(caption);
        n.setExpression(expression);
        n.setPhoto(photo.getBytes());
        
        n.setUserId(userId);
        n.setCaption(caption);
        n.setExpression(expression);
        n.setPhotos(photoList);

        postRepository.save(n);
        return "Post Saved";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/allpost")
    public @ResponseBody Iterable<Post> getAllUsers() {
        
        return postRepository.findAll();
    }

  
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/user/{userId}")
    public @ResponseBody Iterable<Post> getPostsByUserId(@PathVariable Integer userId) {
        return postRepository.findByUserId(userId);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/updatepost/{id}")
    public @ResponseBody String updatePost(@PathVariable Integer id, @RequestParam String caption, @RequestParam String expression, @RequestParam("photo") MultipartFile photo) throws IOException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setCaption(caption);
            post.setExpression(expression);

            post.setPhoto(photo.getBytes());

            post.getPhotos().add(photo.getBytes());

            postRepository.save(post);
            return "Comment Updated";
        } else {
            return "not found";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000") 
    @DeleteMapping(path = "/deletepost/{id}")
    public @ResponseBody String deletePost(@PathVariable Integer id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return "Comment Deleted";
        } else {
            return "not found";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/post/{id}")
    public @ResponseBody Optional<Post> getPost(@PathVariable Integer id) {
        return postRepository.findById(id);
    }


    }
}