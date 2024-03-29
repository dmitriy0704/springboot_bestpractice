package my.home.todo.client.error.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class ToDoErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error(response.getStatusCode().toString());
        log.error(
                StreamUtils.copyToString(
                        response.getBody(), Charset.defaultCharset())
        );
    }
}
