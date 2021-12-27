package util;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.List;

public class UserTag implements Tag {

    private static final Logger LOG = LogManager.getLogger(UserTag.class);

    private List<User> users;

    private PageContext pageContext;

    private Tag parent;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void setParent(Tag t) {
        this.parent = t;
    }

    @Override
    public Tag getParent() {
        return parent;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public int doStartTag() {
        try {
            doTag();
        } catch (IOException e) {
            LOG.error("Message: ", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    @Override
    public void release() {

    }

    public void doTag() throws IOException {

        JspWriter out = pageContext.getOut();


        if (users.size() > 0) {
            StringBuilder output = new StringBuilder();
            output.append("<table class=\"table table-bordered\" id=\"users\">"
                    + "<thead><tr><th>login</th>" + "<th>First Name</th>"
                    + "<th>Last Name</th>" + "<th>Age</th>"
                    + "<th>Role Name</th>" + "<th>Actions</th></tr></thead>");
            for (User user : users) {

                output.append(
                        "<tbody><tr><td>" + user.getLogin() + "</td>" + "<td>"
                                + user.getFirstName() + "</td>" + "<td>"
                                + user.getLastName() + "</td>" + "<td>"
                                + user.getAge() + "</td>" + "<td>"
                                + user.getRole().getName() + "</td><td>");

                if (!user.getId().equals(id)) {
                    output.append("<form action=\"users/delete/" + user.getId() + "\" method=\"post\""
                            + "\" onclick=\"return confirm"
                            + "('Are you sure?')\"><input type=\"submit\" value=\"delete\"/></form>");
                }

                output.append("<form action=\"users/edit/" + user.getId() + "\" method=\"get\""
                        + "><input type=\"submit\" value=\"update\"/></form>");


                output.append("</td></tr></tbody>");
            }
            output.append("</table>");
            out.println(output.toString());
        }
    }
}
