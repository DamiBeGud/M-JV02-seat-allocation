package study.apps;
import java.net.*;import java.net.http.*;import org.junit.jupiter.api.Test;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.beans.factory.annotation.Value;import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,properties="server.address=127.0.0.1")
class HttpTests{@Value("${local.server.port}")int port;
 @Test void nativeHttpRoute()throws Exception{var request=HttpRequest.newBuilder(URI.create("http://127.0.0.1:"+port+"/allocations")).timeout(java.time.Duration.ofSeconds(3)).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"http-case\",\"seats\":2}")).build();var response=HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());assertEquals(201,response.statusCode());}
}
