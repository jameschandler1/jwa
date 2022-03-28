<%@page import='java.util.Calendar' %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">Lesson 3 Practice Hands-On (SOA testing)</span>
        <!-- Add spacer, to align navigation to the right -->
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation. We hide it in small screens. -->
        <tag:formatDate type="both" value="<%= Calendar.getInstance().getTime() %>" ></tag:formatDate>
        <nav class="mdl-navigation mdl-layout--large-creen-only">
            <a class="mdl-navigation__link" href="/my-webapp/jsp/PostForm.jsp">Add Post</a>
            <a class="mdl-navigation__link" href="/my-webapp/jsp/PostList.jsp">List Posts</a>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Lesson 3 Practice Hands-On (SOA testing)</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="/my-webapp/jsp/PostForm.jsp">Add Post</a>
        <a class="mdl-navigation__link" href="/my-webapp/jsp/PostList.jsp">List Posts</a>
    </nav>
</div>