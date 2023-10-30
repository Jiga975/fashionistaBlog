package com.example.fashionista.service.Implementation;

import com.example.fashionista.dto.PostDto;
import com.example.fashionista.dto.PostResponse;
import com.example.fashionista.entity.Post;
import com.example.fashionista.exception.ResourceNotFoundException;
import com.example.fashionista.repository.PostRepository;
import com.example.fashionista.service.PostService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class PostServiceImp implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImp(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
    // covert dto to entity

        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        //convert post entity to postDto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {
       //if sort direction is in ascending order?, return
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                //else , in descending order?, return
                : Sort.by(sortBy).descending();
//create a pageable instance for pagination function
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);


       Page<Post> posts= postRepository.findAll(pageable);
//get content for page object
       List<Post>listOfPosts = posts.getContent();

      List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElement(posts.getNumberOfElements());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        //get post by id from the db and then update
        //in case post doesn't exist by the given id, throw an exception.
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTittle(postDto.getTittle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    //converting entity to dto with the method below
    private PostDto mapToDto(Post post){
        //using mapper
        PostDto postDto = modelMapper.map(post,PostDto.class);

        //what it looks like without mapper
//        PostDto postDto = PostDto.builder()
//                .id(post.getId())
//                .tittle(post.getTittle())
//                .description(post.getDescription())
//                .content(post.getContent())
//                .build();
        return postDto;
    }
    //converting from dto to entity
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
//        Post post = Post.builder()
//                .tittle(postDto.getTittle())
//                .description(postDto.getDescription())
//                .content(postDto.getContent())
//                .build();
        return post;
    }
}
