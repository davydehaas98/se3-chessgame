package chessgameapi;

import chessgameshared.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class RESTServer {
    private static final int PORT = 8095;

    public static void main(String[] args) {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        Server server = new Server(PORT);
        server.setHandler(contextHandler);
        ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames", RESTService.class.getCanonicalName());
        try {
            server.start();
            server.join();
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        } finally {
            server.destroy();
        }
    }
}
