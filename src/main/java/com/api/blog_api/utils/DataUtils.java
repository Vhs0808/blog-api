package com.api.blog_api.utils;

import com.api.blog_api.entity.PostEntity;
import com.api.blog_api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataUtils {

    private final PostRepository postRepository;

    //@PostConstruct
    public void savePosts() {

        List<PostEntity> postList = new ArrayList<>();
        PostEntity post1 = new PostEntity();
        post1.setAutor("Madona");
        post1.setData(LocalDate.now());
        post1.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                + " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more recently with desktop "
                + "publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        post1.setTitulo("Docker");

        PostEntity post2 = new PostEntity();
        post2.setAutor("Xuxa");
        post2.setData(LocalDate.now());
        post2.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
                + "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting,"
                + " remaining essentially unchanged. It was popularised in the 1960s with the release of "
                + "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
                + "software like Aldus PageMaker including versions of Lorem Ipsum.");
        post2.setTitulo("API REST");

        postList.add(post1);
        postList.add(post2);

        for (PostEntity post : postList) {
            PostEntity postSaved = postRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
}
