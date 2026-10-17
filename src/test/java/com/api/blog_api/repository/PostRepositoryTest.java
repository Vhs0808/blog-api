package com.api.blog_api.repository;

import com.api.blog_api.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
   PostRepository postRepository;

    @Test
    void deveSalvarERetornarPost(){
        //Arrange
        PostEntity post = new PostEntity("Vitor", "Alunos +devs", "Os alunos são do +devs");
        //Act
        PostEntity postSalvo = postRepository.save(post);
        //Assert
        Assertions.assertThat(postSalvo).isNotNull();
    }

}
