package util;

import model.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.List;

public class UserTag implements Tag {

    private List<User> users;

    private PageContext pageContext;

    private Tag parent;

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
    public int doStartTag() throws JspException {
        try {
            doTag();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
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

                output.append("<a href=\"users/delete?id" + user.getId()
                        + "\" onclick=\"return confirm"
                        + "('Are you sure?')\">delete</a><span>,</span>");

                output.append("<a href= \"users/edit?" + user.getId()
                        + "\"> update</a></td></tr></tbody>");
            }
            output.append("</table>");
            out.println(output.toString());
        }
    }
}
