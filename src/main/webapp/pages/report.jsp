<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Report Application</h1>
		<div>
			<form:form action="search" modelAttribute="search" method="POST">
			    <table class="table">
			        <!-- Existing form fields for search criteria -->
			        <tr>
			            <td>Plan Name</td>
			            <td>
			                <form:select path="planname">
			                    <form:option value="">--Select--</form:option>
			                    <form:options items="${planname}" />
			                </form:select>
			            </td>
			            <td>Plan Status</td>
			            <td>
			                <form:select path="planstatus">
			                    <form:option value="">--Select--</form:option>
			                    <form:options items="${planstatus}" />
			                </form:select>
			            </td>
			            <td>Gender</td>
			            <td>
			                <form:select path="gender">
			                    <form:option value="">--Select--</form:option>
			                    <form:option value="Male">Male</form:option>
			                    <form:option value="Female">Female</form:option>
			                </form:select>
			            </td>
			        </tr>
			        <tr>
			            <td>Start Date</td>
			            <td><form:input path="planstartdate" type="date" /></td>
			            <td>End Date</td>
			            <td><form:input path="planenddate" type="date" /></td>
			        </tr>
			        <tr>
			            <td><a href="/">Reset</a></td>
			            <td><input type="submit" value="Search" class="btn btn-primary"></td>
			        </tr>
			    </table>
			</form:form>

			<hr/>
			<table class="table table-striped">
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Citizen Name</th>
			            <th>Plan Name</th>
			            <th>Plan Status</th>
			            <th>Gender</th>
			            <th>Start Date</th>
			            <th>End Date</th>
			            <th>Beneficial Amount</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:choose>
			            <c:when test="${empty plans}">
			                <tr>
			                    <td colspan="8" style="text-align: center;">No record found</td>
			                </tr>
			            </c:when>
			            <c:otherwise>
			                <c:forEach items="${plans}" var="plan" varStatus="index">
			                    <tr>
			                        <td>${index.count}</td>
			                        <td>${plan.citizenname}</td>
			                        <td>${plan.planname}</td>
			                        <td>${plan.planstatus}</td>
			                        <td>${plan.gender}</td>
			                        <td>${plan.planstartdate}</td>
			                        <td>${plan.planenddate}</td>
			                        <td>${plan.beneficialamount}</td>
			                    </tr>
			                </c:forEach>
			            </c:otherwise>
			        </c:choose>
			    </tbody>
			</table>
			<hr/>

			<!-- Links for exporting and emailing -->
			<div>
			    Export: 
			    <a href="Excel" class="btn btn-outline-primary">Excel</a> |
			    <a href="pdf" class="btn btn-outline-primary">PDF</a>
			  
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
			crossorigin="anonymous"></script>
	</div>
</body>
</html>
