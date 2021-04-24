package cn.lifecode.recordaccount.common;


import cn.lifecode.frameworkcore.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 请求过滤器
 *
 * @author luolin
 * @date 2021-01-23 14:36:32
 */
@Slf4j
@Order(value = 1)
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);

        //返回
        servletResponse.setCharacterEncoding("UTF-8");
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        BodyReaderHttpServletResponseWrapper wrapperedResponse = new BodyReaderHttpServletResponseWrapper(httpServletResponse);
        filterChain.doFilter(requestWrapper, wrapperedResponse);
        String data = new String(wrapperedResponse.getResponseData());
        log.info("返回数据：\r\n{}", FormatUtil.formatJson(data));
        ServletOutputStream out = servletResponse.getOutputStream();
        out.write(data.getBytes(StandardCharsets.UTF_8));
        out.flush();
    }

    @Override
    public void destroy() {

    }
}
