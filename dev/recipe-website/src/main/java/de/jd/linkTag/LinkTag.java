package de.jd.linkTag;

import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LinkTag extends SimpleTagSupport {

    private String path;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        Environment bean = webApplicationContext.getBean(Environment.class);
        if (Boolean.valueOf(bean.getProperty("developmentMode", Boolean.class, false))) {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            String fullPath = request.getContextPath() + path;
            out.write(fullPath);
        } else {
            out.write(path);
        }
        super.doTag();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
