package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Evaluacion_005fAlu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/scriptstyle.jsp");
    _jspx_dependants.add("/navbar.jsp");
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
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<link href=\"Styles/bootstrap/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<script src=\"Scripts/jquery/jquery3-5-1.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"Scripts/bootstrap/bootstrap.js\" type=\"text/javascript\"></script>\n");
      out.write("<title>Colegio San Jose</title>\n");
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js\"></script>\n");
      out.write("        <script src=\"Scripts/jsweb/ComponentePregunta.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write(" ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<link href=\"Styles/bootstrap/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<script src=\"Scripts/jquery/jquery3-5-1.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"Scripts/bootstrap/bootstrap.js\" type=\"text/javascript\"></script>\n");
      out.write("<title>Colegio San Jose</title>\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-danger\">\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">AppIDAT</a>\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("    </button>\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("        <ul class=\"navbar-nav mr-auto\">\n");
      out.write("            <li class=\"nav-item active\">\n");
      out.write("                <a class=\"nav-link\" href=\"home.jsp\">Home <span class=\"sr-only\">(current)</span></a>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"nav-item dropdown\">\n");
      out.write("                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                    Mantenimiento\n");
      out.write("                </a>\n");
      out.write("                <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n");
      out.write("                    <a class=\"dropdown-item\" href=\"ListaCursoController\">Lista de Cursos</a>\n");
      out.write("                    <a class=\"dropdown-item\" href=\"Evaluacion_Alu.jsp\">pregunta1</a>\n");
      out.write("                    <div class=\"dropdown-divider\"></div>\n");
      out.write("                    <a class=\"dropdown-item\" href=\"ListaAjaxCursoController\">Curso con Ajax</a>\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("        <form class=\"form-inline my-2 my-lg-0\">                    \n");
      out.write("            <a class=\"btn btn-outline-light my-2 my-sm-0\" href=\"#\" >Cerrar sesión</a>\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <button type=\"button\" class=\"btn btn-primary\" id=\"btnpregunta\" >pregunta1</button>\n");
      out.write("        <div id=\"pregunta1\">\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
