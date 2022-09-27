package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    @RabbitListener(queues = "CREATE_POST_QUEUE")
    public void handler(String messsage) throws JsonProcessingException{
        Post post = objectMapper.readValue(messsage, Post.class);
        postRepository.save(post);
    }
}
