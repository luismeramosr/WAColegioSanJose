package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/scriptstyle.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" crossorigin=\"anonymous\">-->\n");
      out.write("<link href=\"Styles/bootstrap/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<script src=\"Scripts/bootstrap/bootstrap.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"Scripts/jquery/jquery3-5-1.js\" type=\"text/javascript\"></script>");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"card bg-dark\" style=\"position: relative; width: 25rem; left: 36%; bottom: -9.5rem;\">\n");
      out.write("            <div class=\"card-header bg-dark\" style=\"color: white; font-size: 1.5rem;\">\n");
      out.write("                Iniciar sesión\n");
      out.write("            </div>\n");
      out.write("            <div class=\"card-body bg-light\">\n");
      out.write("                <form method=\"POST\" action=\"LoginController\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"txtUser\">Código</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"txtUser\" name=\"txtUser\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"txtPassword\">Contraseña</label>\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"txtPassword\" name=\"txtPassword\">\n");
      out.write("                    </div>\n");
      out.write("                    ");

                        if (request.getAttribute("result") != null) {
                            String result = request.getAttribute("result").toString();
                    
      out.write("\n");
      out.write("                    <span id=\"emailHelp\" class=\"form-text\">");
      out.print(result);
      out.write("</span>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\">Ingresar</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>    \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
