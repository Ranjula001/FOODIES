package com.project.foodies.CommentManagement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/paf")
public class CommentController {


    @Autowired
    private CommentRepository commentRepository;
    
    @PostMapping(path = "/addComment")
     public @ResponseBody String addNewUser(@RequestParam Integer comment_id, @RequestParam Integer user_id, @RequestParam Integer post_id, @RequestParam String text) {

         Comment c = new Comment();
         c.setComment_id(comment_id);
         c.setUser_id(user_id);
         c.setPost_id(post_id);
         c.setText(text);
         
         commentRepository.save(c);


        return "Comment Saved";
    }

    @GetMapping(path = "/allComments")
    public @ResponseBody Iterable<Comment> getAllComment() {
    return commentRepository.findAll();

    }
    


@DeleteMapping(path = "/deleteComment/{id}")
public @ResponseBody String deleteUser(@PathVariable Integer id) {
    Optional<Comment> userOptional = commentRepository.findById(id);

    if (userOptional.isPresent()) {
        Comment comment = userOptional.get();
        commentRepository.delete(comment);
        return "Comment Deleted";
    }
    return "Comment not found"; 




}

@PutMapping(path = "/updateComment/{Id}")
public @ResponseBody String updateComment(@PathVariable("Id") Integer Id, @RequestParam(required = false) String text) {
    Optional<Comment> comment = commentRepository.findById(Id);
    if (comment.isPresent()) {
        Comment updatedComment = comment.get();
      
        if (text != null) {
            updatedComment.setText(text);
        }
        commentRepository.save(updatedComment);
        return "Comment with ID " + Id + " has been updated.";
    } else {
        return "Comment with ID " + Id + " not found.";
    }
}


}
