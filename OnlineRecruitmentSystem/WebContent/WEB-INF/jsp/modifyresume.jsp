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

<h1>Resume</h1>

<a href="modifypersonalform.htm" >Modify Personal Details</a>

<c:if test="${candPers ne null}">
<form:form action="modifypersonal.htm" modelAttribute="candPers" method="post">

<form:label path="candidateName">Name</form:label>
<form:input path="candidateName" pattern="[A-Za-z\s]+" required="required" maxlength="20"></form:input>
<form:errors path="candidateName"></form:errors><br/>

<form:label path="address">Address</form:label>
<form:textarea path="address"required="TRUE" rows="5" size="10"></form:textarea>
<form:errors path="address"></form:errors><br/>

<form:label path="dob">Date of Birth</form:label>
<form:input type="date" path="dob" ></form:input>
<form:errors path="dob"></form:errors><br/>

<form:label path="emailId">Email Id</form:label>
<form:input path="emailId" pattern="^[A-Za-z0-9._%+-]+@{1}[A-Za-z0-9.-]+.{1}[A-Za-z]{2,6}$"
			required="TRUE" title="Enter Valid MailId" maxlength="30"></form:input>
<form:errors path="emailId"></form:errors><br/>

<form:label path="contactNumber">Contact Number</form:label>
<form:input path="contactNumber" pattern="[0-9]{10}" required="TRUE"
			title="Enter a ten digit number"></form:input>
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
<form:input path="passportNumber" pattern="^[A-Z]{1}[0-9]{7}$"
			required="TRUE" title="A1234567"></form:input>
<form:errors path="passportNumber"></form:errors><br/>

<button type="submit">Save</button>

</form:form>
</c:if>


<a href="modifyqualform.htm" >Modify Qualification Details</a>
<c:if test="${candQual ne null }">
<h2>Enter Qualification Details</h2>
<form:form action="modifyqual.htm" modelAttribute="candQual" method="post">
<table border="1">
<tr><th><form:label path="qualificationName">Qualification</form:label></th>
<th><form:label path="specializationArea">Specialization Area</form:label></th>
<th><form:label path="collegeName">Institution Name</form:label></th>
<th><form:label path="universityName">University/Board</form:label></th>
<th><form:label path="yearOfPassing">Year of Passing</form:label></th>
<th><form:label path="percentage">Percentage</form:label></th></tr>

<tr><td><form:select path="qualificationName"><form:options items="${qualifications}"/></form:select><form:errors path="qualificationName"></form:errors></td>
<td><form:input path="specializationArea" pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."></form:input><form:errors path="specializationArea"></form:errors></td>
<td><form:input path="collegeName" pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."></form:input><form:errors path="collegeName"></form:errors></td>
<td><form:input path="universityName" pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."></form:input><form:errors path="universityName"></form:errors></td>
<td><form:input path="yearOfPassing" pattern="^[1-9]{1}[0-9]{3}$" required="TRUE" title="1999"></form:input><form:errors path="yearOfPassing"></form:errors></td>
<td><form:input path="percentage" pattern="[0-9]{1,2}[.]{0,1}[0-9]{0,2}"
						required="TRUE" title="99"></form:input><form:errors path="percentage"></form:errors></td>
<td><input type="submit" value="Save"/></td>
</tr>
</table>

</form:form>
</c:if>


<a href="modifyworkhistform.htm" >Modify Work History Details</a>
<c:if test="${candWork ne null }">
<h2>Enter Work History Details</h2>
<form:form action="modifyworkhist.htm" modelAttribute="candWork" method="post">
<table border="1">
<tr><th><form:label path="whichEmployer">Employer Name</form:label></th>
<th><form:label path="contactPerson">Contact Person</form:label></th>
<th><form:label path="positionHeld">Position Held</form:label></th>
<th><form:label path="companyName">Company Name</form:label></th>
<th><form:label path="employmentFrom">Employment From</form:label></th>
<th><form:label path="employmentTo">Employment To</form:label></th>
<th><form:label path="reasonForLeaving">Reason for Leaving</form:label></th>
<th><form:label path="responsibilities">Responsibilities</form:label></th>
<th><form:label path="hrRepName">HR Name</form:label></th>
<th><form:label path="hrRepContactNumber">HR Contact Number</form:label></th></tr>

<tr>
<td><form:input path="whichEmployer" pattern="^[A-Z]{1}[a-z]{1,}[1-9]{0,}$" required="TRUE"
						title="Previous1"></form:input><form:errors path="whichEmployer"></form:errors></td>
<td><form:input path="contactPerson" pattern="^[A-Z]{1}[a-z]{0,}[ ]{1}[A-Z]{1}[a-z]{0,}$"
						required="TRUE" title="Mohit Deshmukh" maxlength="30"></form:input><form:errors path="contactPerson"></form:errors></td>
<td><form:input path="positionHeld" pattern="[A-Za-z ]{1,}"
						required="TRUE" title="Senior Analyst" maxlength="30"></form:input><form:errors path="positionHeld"></form:errors></td>
<td><form:input path="companyName" pattern="[A-Za-z.& ]{1,}"
						required="TRUE" title="F.S. Finance & Consulting" maxlength="30"></form:input><form:errors path="companyName"></form:errors></td>
<td><form:input type="date" path="employmentFrom" ></form:input><form:errors path="employmentFrom"></form:errors></td>
<td><form:input type="date"  path="employmentTo"></form:input><form:errors path="employmentTo"></form:errors></td>
<td><form:textarea path="reasonForLeaving"></form:textarea><form:errors path="reasonForLeaving"></form:errors></td>
<td><form:textarea path="responsibilities"/><form:errors path="responsibilities"></form:errors></td>
<td><form:input path="hrRepName" pattern="^[A-Z]{1}[a-z]{0,}[ ]{1}[A-Z]{1}[a-z]{0,}$"
						required="TRUE" title="Mohit Deshmukh" maxlength="30"></form:input><form:errors path="hrRepName"></form:errors></td>
<td><form:input path="hrRepContactNumber" pattern="[0-9]{10}"
						required="TRUE" title="Enter a ten digit number"/>
						<form:errors path="hrRepContactNumber"></form:errors></td>
</tr>

</table>
<button type="submit">Save</button>
</form:form>
</c:if>

</body>
</html>