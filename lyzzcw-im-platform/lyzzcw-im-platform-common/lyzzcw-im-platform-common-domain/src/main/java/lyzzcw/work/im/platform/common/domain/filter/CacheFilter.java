package lyzzcw.work.im.platform.common.domain.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 缓存过滤器
 *
 * @author lzy
 * @date 2023/12/29
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "xssFilter")
public class CacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new CacheHttpServletRequestWrapper((HttpServletRequest) request), response);
    }
}
