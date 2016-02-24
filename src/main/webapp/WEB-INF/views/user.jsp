<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <c:url value="/resources/css" var="cssUrl" />
        <c:url value="/resources/js" var="jsUrl" />
        <c:url value="/users" var="userURL" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${cssUrl}/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${cssUrl}/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${cssUrl}/font-awesome.min.css" />
        <title></title>
    </head>
    <body>
        <table cellpadding="20" align="center">
            <tr>
                <td width="350">
                    <h1>
                        Add a User
                    </h1>

                </td>
                <td width="350"> <h1>
                        Search User
                    </h1></td>
            </tr>
            <tr>
                <td>
                    <c:url var="addAction" value="/user/add" ></c:url>
                    <form:form name="adduser" action="${addAction}" commandName="user">
                        <table>
                            <c:if test="${!empty user.name}">
                                <tr>
                                    <td>
                                        <form:label path="id">
                                            <spring:message text="ID"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input path="id" readonly="true" size="8"  disabled="true" />
                                        <form:hidden path="id" />
                                    </td> 
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <form:label path="name">
                                        <spring:message text="Name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" maxlength="25" size="35"/>
                                </td> 
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="age">
                                        <spring:message text="Age"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="age" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="isAdmin">
                                        <spring:message text="Administrator"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:checkbox path="isAdmin" value='1'/>
                                    <input type="hidden" name="offset" value="${offset}"

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <c:if test="${!empty user.name}">
                                        <input type="submit"
                                               value="<spring:message text="Edit User"/>" />
                                    </c:if>
                                    <c:if test="${empty user.name}">
                                        <input type="submit"
                                               value="<spring:message text="Add User"/>" />
                                    </c:if>
                                </td>
                            </tr>
                        </table>	
                    </form:form>
                </td>
                <td valign="top">
                    <c:url var="addAction2" value="/user/find" ></c:url>
                    <form name="finduser" action="${addAction2}" method="GET" >
                        <table><tr>
                                <td>
                                    Name
                                </td>
                                <td>
                                    <input name="name" maxlength="25" size="35"/>
                                </td> 
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Find User" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
        <div class="container">
            <div class="well">
                <strong>List of Users ${searchFor}</strong>
            </div>
            <table class="table table-stripped">
                <tr>
                    <th>№</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Admin</th>
                    <th>Created<br>date</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listUsers}" var="user" varStatus="itr">
                    <tr>
                        <td>${offset + itr.index +1 }</td>
                        <td>${user.id }</td>
                        <td>${user.name }</td>
                        <td>${user.age }</td>
                        <td>${user.isAdmin }</td>
                        <td>${user.createdDate }</td>
                        <td><a href="<c:url value='/edit/${user.id}?offset=${offset}' />" >Edit</a></td>
                        <td><a href="<c:url value='/remove/${user.id}?offset=${offset}' />" >Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <tag:paginate max="15" offset="${offset}" count="${count}"
                          uri="${userURL}" next="&raquo;" previous="&laquo;" />
        </div>


        <script type="text/javascript" src="${jsUrl}/jquery.js"></script>
        <script type="text/javascript" src="${jsUrl}/bootstrap.min.js"></script>
    </body>
</html>