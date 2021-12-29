package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class UTF8EncodingFilter implements Filter {


    public static final String UTF_8_ENCODING = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8_ENCODING);
        servletResponse.setCharacterEncoding(UTF_8_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}