package per.itachi.java.tools.danmuku.joint.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import per.itachi.java.tools.danmuku.app.generator.GeneratorConstant;
import per.itachi.java.tools.danmuku.app.generator.IdGeneratorSelector;
import per.itachi.java.tools.danmuku.app.manager.ContextConstant;

@Component
public class ContextFilter implements Filter {

    @Autowired
    private IdGeneratorSelector idGeneratorSelector;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            setupContext((HttpServletRequest) request);
            chain.doFilter(request, response);
        }
        finally {
            teardownContext();
        }
    }

    private void setupContext(HttpServletRequest request) {
        if (StringUtils.hasText(request.getHeader(ContextConstant.CONTEXT_PARAM_X_REQUEST_ID))) {
            MDC.put(ContextConstant.CONTEXT_PARAM_X_REQUEST_ID, request.getHeader(ContextConstant.CONTEXT_PARAM_X_REQUEST_ID));
        }
        else {
            MDC.put(ContextConstant.CONTEXT_PARAM_X_REQUEST_ID, (String) idGeneratorSelector.generate(GeneratorConstant.ID_GENERATOR_UUID));
        }
    }

    private void teardownContext() {
        MDC.remove(ContextConstant.CONTEXT_PARAM_X_REQUEST_ID);
    }
}
