<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Enter Personal Details</h2>
<form:form action="savepersonal.htm" modelAttribute="candPers" method="post">

<form:label path="candidateName">Name</form:label>
<form:input path="candidateName"></form:input>
<form:errors path="candidateName"></form:errors><br/>

<form:label path="address">Address</form:label>
<form:textarea path="address"></form:textarea>
<form:errors path="address"></form:errors><br/>

<form:label path="dob">Date of Birth</form:label>
<form:input path="dob"></form:input>
<form:errors path="dob"></form:errors><br/>

<form:label path="emailId">Email Id</form:label>
<form:input path="emailId"></form:input>
<form:errors path="emailId"></form:errors><br/>

<form:label path="contactNumber">Contact Number</form:label>
<form:input path="contactNumber"></form:input>
<form:errors path="contactNumber"></form:errors><br/>

<form:label path="maritalStatus">Marital Status</form:label>
<form:radiobutton path="maritalStatus" value="Married"/>Married
<form:radiobutton path="maritalStatus" value="Unmarried"/>Unmarried
<form:errors path="maritalStatus"></form:errors><br/>

<form:label path="gender">Gender</form:label>
<form:radiobutton path="gender" value="Male"/>Male
<form:radiobutton path="gender" value="Female"/>Female
<form:radiobutton path="gender" value="Others"/>Others
<form:errors path="gender"></form:errors><br/>

<form:label path="passportNumber">Passport Number</form:label>
<form:input path="passportNumber"></form:input>
<form:errors path="passportNumber"></form:errors><br/>

<button type="submit">Save</button>

</form:form>


<%-- <c:if test="${candQual ne null}">
 --%>
<h2>Enter Qualification Details</h2>
<form:form action="addqual.htm" modelAttribute="candQual" method="post">
<table border="1">
<tr><th><form:label path="qualificationName">Qualification</form:label></th>
<th><form:label path="specializationArea">Specialization Area</form:label></th>
<th><form:label path="collegeName">Institution Name</form:label></th>
<th><form:label path="universityName">University/Board</form:label></th>
<th><form:label path="yearOfPassing">Year of Passing</form:label></th>
<th><form:label path="percentage">Percentage</form:label></th></tr>

<tr><td><form:select path="specializationArea"><form:options items="${genderList}"/></form:select><form:errors path="qualificationName"></form:errors></td>
<td><form:input path="specializationArea"></form:input><form:errors path="specializationArea"></form:errors></td>
<td><form:input path="collegeName"></form:input><form:errors path="collegeName"></form:errors></td>
<td><form:input path="universityName"></form:input><form:errors path="universityName"></form:errors></td>
<td><form:select path="yearOfPassing"><form:options items="${yearList}"/></form:select><form:errors path="yearOfPassing"></form:errors></td>
<td><form:input path="percentage"></form:input><form:errors path="percentage"></form:errors></td>
</tr>
</table>
<button type="submit">Add More Qualifiction</button>
</form:form>



<h2>Enter Work History Details</h2>
<form:form action="saveworkhist.htm" modelAttribute="candWork" method="post">
<table border="1">
<tr><th><form:label path="whichEmployer">Qualification</form:label></th>
<th><form:label path="contactPerson">Specialization Area</form:label></th>
<th><form:label path="positionHeld">Institution Name</form:label></th>
<th><form:label path="companyName">University/Board</form:label></th>
<th><form:label path="employmentFrom">Year of Passing</form:label></th>
<th><form:label path="employmentTo">Grade/Percentage</form:label></th>
<th><form:label path="reasonForLeaving">On a scale of</form:label></th>
<th><form:label path="responsibilities">On a scale of</form:label></th>
<th><form:label path="hrRepName">On a scale of</form:label></th>
<th><form:label path="hrRepContactNumber">On a scale of</form:label></th></tr>

<tr>
<td><form:input path="whichEmployer"></form:input><form:errors path="whichEmployer"></form:errors></td>
<td><form:input path="contactPerson"></form:input><form:errors path="contactPerson"></form:errors></td>
<td><form:input path="positionHeld"></form:input><form:errors path="positionHeld"></form:errors></td>
<td><form:input path="companyName"></form:input><form:errors path="companyName"></form:errors></td>
<td><form:input path="employmentFrom"></form:input><form:errors path="employmentFrom"></form:errors></td>
<td><form:input path="employmentTo"></form:input><form:errors path="employmentTo"></form:errors></td>
<td><form:textarea path="reasonForLeaving"></form:textarea><form:errors path="reasonForLeaving"></form:errors></td>
<td><form:textarea path="responsibilities"/><form:errors path="responsibilities"></form:errors></td>
<td><form:input path="hrRepName"></form:input><form:errors path="hrRepName"></form:errors></td>
<td><form:input path="hrRepContactNumber"></form:input><form:errors path="hrRepContactNumber"></form:errors></td>
</tr>

</table>
<button type="submit">Save</button>
</form:form>
<input type="button" onclick="addresume.htm" value="Submit">
</body>
</html>