
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <%@include file="./head.jsp" %>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
             <%@include file="./menu.jsp" %>
        <main class="mdl-layout__content">
            <div class="page-content">
                <div class="mdl-grid center-items">
                    <div class="mdl-cell mdl-cell--4-col">
                        <div clas="mdl-card mdl-shadow--6dp">
                            <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                                <h2 class="mdl-card__title-text">
                                    <c:if test="${post != null}">
                                        Edit Post
                                    </c:if>
                                    <c:if test="${post == null}">
                                        Add Post
                                    </c:if>
                                </h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <c:if test="${post != null}">
                                <form name="myForm" action="update" method="post" onsubmit="validateForm()">
                            </c:if>
                            <c:if test="${post == null}">
                                <form name="myForm" action="insert" method="post" onsubmit="validateForm()">
                            </c:if>
                            <c:if test="${post != null}">
                                <input type="hidden" name="id" value="<c:out value='${post.post_id}' />" />
                            </c:if>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="title" id="title" value="<c:out value='${post.post_title}' />" />
                                <label class="mdl-textfield__label" for="title">Title</label>
                            </div>

                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfeild__input" type="text" name="body" id="body" value="<c:out value='${post.post_body}' />" />
                                <label class="mdl-textfield__label" for="body">Body</label>
                            </div>

                            <input type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" value="Submit" />

                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script type="text/javascript">
        function validateForm() { // validates that fields in form are filled out.
            let title = documnet.forms["myForm"]["title"].value;
            let body = documnet.forms["myForm"]["body"].value;
            if (title == "") {
                alert("Title must be filled out");
                return false;
            }
            if (body == "") {
                alert("Body must be filled out");
                return false;
            }
        }
    </script>
</body>
</html>