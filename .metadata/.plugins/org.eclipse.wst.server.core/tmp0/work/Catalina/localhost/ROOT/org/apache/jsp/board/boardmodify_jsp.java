/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.82
 * Generated at: 2022-10-24 03:28:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class boardmodify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Board Write Page</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css\">\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n");
      out.write("        integrity=\"sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT\" crossorigin=\"anonymous\">\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js\"\r\n");
      out.write("        integrity=\"sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8\"\r\n");
      out.write("        crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.6.1.js\"></script>\r\n");
      out.write("    <style>\r\n");
      out.write("        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');\r\n");
      out.write("\r\n");
      out.write("        * {\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            font-family: 'Noto Sans KR', sans-serif;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #title {\r\n");
      out.write("            text-align: left;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #title:hover {\r\n");
      out.write("            font-weight: 700;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .writer:hover {\r\n");
      out.write("            color: #666666;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .alert {\r\n");
      out.write("            background: #fff;\r\n");
      out.write("            vertical-align: middle;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .titleline>div {\r\n");
      out.write("            font-size: 15px;\r\n");
      out.write("            font-weight: 900;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        div {\r\n");
      out.write("            font-size: 14px;\r\n");
      out.write("            font-weight: 500;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        i {\r\n");
      out.write("            margin-top: 15px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .bg-secondary {\r\n");
      out.write("            --badge-color: #999999;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .btn-bd-secondary {\r\n");
      out.write("            --bs-btn-font-weight: 500;\r\n");
      out.write("            --bs-btn-font-size: 12px;\r\n");
      out.write("            --bs-btn-padding-x: 10px;\r\n");
      out.write("            --bs-btn-padding-y: 3px;\r\n");
      out.write("            --bs-btn-border-color: #333333;\r\n");
      out.write("            --bs-btn-color: #333333;\r\n");
      out.write("            --bs-btn-bg: white;\r\n");
      out.write("            --bs-btn-hover-bg: #999999;\r\n");
      out.write("            --bs-btn-hover-color: white;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .pagination {\r\n");
      out.write("            --bs-pagination-color: #333333;\r\n");
      out.write("            --bs-pagination-active-bg: #999999;\r\n");
      out.write("            --bs-pagination-active-border-color: #999999;\r\n");
      out.write("            --bs-pagination-hover-color: #333333;\r\n");
      out.write("            --bs-pagination-font-size: 14px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .cate {\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .board {\r\n");
      out.write("            margin-top: 10px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        .nav {\r\n");
      out.write("            --bs-nav-link-color: #666666;\r\n");
      out.write("            --bs-nav-link-hover-color: #222222;\r\n");
      out.write("            --bs-nav-link-font-size: 14px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .nav-pills {\r\n");
      out.write("            --bs-nav-pills-link-active-bg: #999999;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        h5 {\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            font-weight: 900;\r\n");
      out.write("        }\r\n");
      out.write("        .btncenter{text-align: center;}\r\n");
      out.write("        \r\n");
      out.write("        input{width: 100%;}\r\n");
      out.write("        textarea{width: 100%; height: 500px;}\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("	<form id=\"frm\" action=\"/checkmodify.board\" method=\"post\">\r\n");
      out.write("	<div class=\"d-none\"><input name=\"seq\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${list.seq}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"></div>\r\n");
      out.write("	        <div class=\"board alert alert-secondary\" role=\"alert\">\r\n");
      out.write("	            <h5>Modify Page</h5>\r\n");
      out.write("	            <hr>\r\n");
      out.write("	            <div class=\"row titleline\">\r\n");
      out.write("	                <div class=\"col-12\">\r\n");
      out.write("	                    <input type=\"text\" name=\"titlepost\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${list.title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <hr>\r\n");
      out.write("	            <div class=\"row\">\r\n");
      out.write("	                <div class=\"col-12\">\r\n");
      out.write("	                <textarea name=\"contentspost\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${list.contents}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</textarea>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <hr>\r\n");
      out.write("	            <div class=\"row text-center\">\r\n");
      out.write("	                <div class=\"btncenter\">\r\n");
      out.write("	                    <button type=\"button\" class=\"btn btn-secondary\" id=\"toBoard\">List</button>\r\n");
      out.write("	                    <button class=\"btn btn-primary\">Write</button>\r\n");
      out.write("	                    <button type=\"button\" class=\"btn btn-secondary\" onclick=\"history.back()\">Back</button>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div>\r\n");
      out.write("	\r\n");
      out.write("	</form>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("	<script>\r\n");
      out.write("		$(\"#toBoard\").on(\"click\",function(){\r\n");
      out.write("			location.href=\"/list.board?cpage=1\";\r\n");
      out.write("		})\r\n");
      out.write("		\r\n");
      out.write("	</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
