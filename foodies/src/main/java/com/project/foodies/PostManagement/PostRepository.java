package com.project.foodies.PostManagement;



import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {

    Iterable<Post> findByUserId(Integer userId);

}