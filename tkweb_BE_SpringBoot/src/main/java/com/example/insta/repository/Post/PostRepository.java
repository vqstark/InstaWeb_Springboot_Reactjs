package com.example.insta.repository.Post;

import com.example.insta.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long postId);

    @Query("SELECT p from Post p where p.id = :postId")
    Post findById1(Long postId);

    Page<Post> findByCreatedBy(Long userId, Pageable pageable);

    List<Post> findAll();

    List<Post> findByCreatedBy(Long userId);

    long countByCreatedBy(Long userId);

    @Query("SELECT p from Post p where p.createdBy in :followingUsersIds")
    List<Post> findAllPostsByFollowedUsers(@Param("followingUsersIds") List<Long> followingUsersIds);

    @Query("SELECT p from Post p where p.createdBy = :id")
    List<Post> findAllPostByCreate(Long id);
}

