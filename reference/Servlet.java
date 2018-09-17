import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebServlet(urlPatterns="/ws")
public class Servlet extends WebSocketServlet{

    @Override
    public void configure(WebSocketServletFactory factory) {

        factory.register(WebSocketServer.class);

    }

}