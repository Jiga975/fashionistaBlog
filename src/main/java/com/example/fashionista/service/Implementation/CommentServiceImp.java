package com.example.fashionista.service.Implementation;

import com.example.fashionista.dto.CommentDto;
import com.example.fashionista.entity.Comment;
import com.example.fashionista.entity.Post;
import com.example.fashionista.exception.BlogApiException;
import com.example.fashionista.exception.ResourceNotFoundException;
import com.example.fashionista.repository.CommentRepository;
import com.example.fashionista.repository.PostRepository;
import com.example.fashionista.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id
        Post post =postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("post","id",postId));
        //set post to comment entity
        comment.setPost(post);

        //comment entity to Db
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        //retrieve comments by postId
        List<Comment>comments=commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //1. retrieve post from the database using post Id, if the postId no dey exist, throw exception
        Post post =postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("post","id",postId));

        //2. retrieve comment from the database using commentId, if the commentId no dey exist, throw exception
        Comment comment =commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("comment","id",commentId));
        //3. check if comment does not belong to the post throw exception else return comment
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment no belong to this post");
        }
        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //1. retrieve post from the database using post Id, if the postId no dey exist, throw exception
        Post post =postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("post","id",postId));

        //2. retrieve comment from the database using commentId, if the commentId no dey exist, throw exception
        Comment comment =commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to  post");
        }
        comment.setName(commentRequest.getName());
        comment.setMail(commentRequest.getMail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //1. retrieve post from the database using post Id, if the postId no dey exist, throw exception
        Post post =postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("post","id",postId));

        //2. retrieve comment from the database using commentId, if the commentId no dey exist, throw exception
        Comment comment =commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to  post");
        }
        commentRepository.delete(comment);



    }


    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setMail(comment.getMail());
        commentDto.setBody(commentDto.getBody());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment =new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setMail(comment.getMail());
        comment.setBody(comment.getBody());

        return comment;
    }
}
