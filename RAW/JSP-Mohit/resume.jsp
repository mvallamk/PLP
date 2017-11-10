<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Enter Personal Details</h2>
	<form:form action="addpersonal.htm" modelAttribute="candPers"
		method="post">

		<form:label path="candidateName"/>Name
		<form:input path="candidateName"
			pattern="^[A-Z]{1}[a-z]{0,}[ ]{1}[A-Z]{1}[a-z]{0,}$" required="TRUE"
			title="Mohit Deshmukh" maxlength="30"/>
		<form:errors path="candidateName"/>
		<br />

		<form:label path="address"/>Address
		<form:textarea path="address" required="TRUE" rows="5" size="10"></form:textarea>
		<form:errors path="address"/>
		<br />

		<form:label path="dob"/>Date of Birth
		<form:input path="dob" required="TRUE"/>
		<form:errors path="dob"/>
		<br />

		<form:label path="emailId"/>Email Id
		<form:input path="emailId"
			pattern="^[A-Za-z0-9._%+-]+@{1}[A-Za-z0-9.-]+.{1}[A-Za-z]{2,6}$"
			required="TRUE" title="Enter Valid MailId" maxlength="30"/>
		<form:errors path="emailId"/>
		<br />

		<form:label path="contactNumber"/>Contact Number
		<form:input path="contactNumber" pattern="[0-9]{10}" required="TRUE"
			title="Enter a ten digit number"/>
		<form:errors path="contactNumber"/>
		<br />

		<form:label path="maritalStatus"/>Marital Status
		<form:radiobutton path="maritalStatus" value="Married" />Married
		<form:radiobutton path="maritalStatus" value="Unmarried" />Unmarried
		<form:errors path="maritalStatus"/>
		<br />

		<form:label path="gender"/>Gender
		<form:radiobutton path="gender" value="Male" />Male
		<form:radiobutton path="gender" value="Female" />Female
		<form:radiobutton path="gender" value="Others" />Others
		<form:errors path="gender"/>
		<br />

		<form:label path="passportNumber"/>Passport Number
		<form:input path="passportNumber" pattern="^[A-Z]{1}[0-9]{7}$"
			required="TRUE" title="A1234567"/>
		<form:errors path="passportNumber"/>
		<br />

		<button type="submit">Save</button>
	</form:form>

	<h2>Enter Qualification Details</h2>
	<form:form action="addqual.htm" modelAttribute="candQual" method="post">
		<table border="1">
			<tr>
				<th><form:label path="qualificationName"/>Qualification</th>
				<th><form:label path="specializationArea"/>Specialization Area</th>
				<th><form:label path="collegeName"/>Institution Name</th>
				<th><form:label path="universityName"/>University/Board</th>
				<th><form:label path="yearOfPassing"/>Year of Passing</th>
				<th><form:label path="percentage"/>Percentage</th>
			</tr>

			<tr>
				<td><form:select path="qualificationName"/>
						<form:options items="${qualList}" />
				        <form:errors path="qualificationName"/></td>
				<td><form:input path="specializationArea"
						pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."/> 
					    <form:errors path="specializationArea"/></td>
				<td><form:input path="collegeName"
						pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."/> 
					    <form:errors path="collegeName"/></td>
				<td><form:input path="universityName"
						pattern="^[A-Za-z]{1}[A-Za-z. ]{1,}$" required="TRUE"
						title="Only letters and ."/>
					    <form:errors path="universityName"/></td>
				<td><form:select path="yearOfPassing"
						pattern="^[1-9]{1}[0-9]{3}$" required="TRUE" title="1999"/>
					    <form:options items="${yearList}" />
					    <form:errors path="yearOfPassing"/></td>
				<td><form:input path="percentage" pattern="[0-9]{1,3}"
						required="TRUE" title="99"/> 
						<form:errors path="percentage"/></td>
			</tr>

		</table>
		<button type="submit">Save</button>
	</form:form>

	<h2>Enter Work History Details</h2>
	<form:form action="addworkhist.htm" modelAttribute="candWork"
		method="post">
		<table border="1">
			<tr>
				<th><form:label path="whichEmployer"/>Which Employer</th>
				<th><form:label path="contactPerson"/>Contact Person</th>
				<th><form:label path="positionHeld"/>Position Held</th>
				<th><form:label path="companyName"/>Company Name</th>
				<th><form:label path="employmentFrom"/>Employment From</th>
				<th><form:label path="employmentTo"/>Employment To</th>
				<th><form:label path="reasonForLeaving"/>Reason For Leaving</th>
				<th><form:label path="responsibilities"/>Responsibilities</th>
				<th><form:label path="hrRepName"/>HR Representative Name</th>
				<th><form:label path="hrRepContactNumber"/>HR Representative Contact Number</th>
			</tr>

			<tr>
				<td><form:input path="whichEmployer"
						pattern="^[A-Z]{1}[a-z]{1,}[1-9]{0,}$" required="TRUE"
						title="Previous1"/> 
					    <form:errors path="whichEmployer"/></td>
				<td><form:input path="contactPerson"
						pattern="^[A-Z]{1}[a-z]{0,}[ ]{1}[A-Z]{1}[a-z]{0,}$"
						required="TRUE" title="Mohit Deshmukh" maxlength="30"/>
						<form:errors path="contactPerson"/></td>
				<td><form:input path="positionHeld" pattern="[A-Za-z ]{1,}"
						required="TRUE" title="Senior Analyst" maxlength="30"/>
						<form:errors path="positionHeld"/></td>
				<td><form:input path="companyName" pattern="[A-Za-z.& ]{1,}"
						required="TRUE" title="F.S. Finance & Consulting" maxlength="30"/>
						<form:errors path="companyName"/></td>
				<td><form:input path="employmentFrom"/> 
				 		<form:errors path="employmentFrom"/></td>
				<td><form:input path="employmentTo"/>
						<form:errors path="employmentTo"/></td>
				<td><form:textarea path="reasonForLeaving"/>
						<form:errors path="reasonForLeaving"/></td>
				<td><form:textarea path="responsibilities" /> <form:errors
						path="responsibilities"/></td>
				<td><form:input path="hrRepName"
						pattern="^[A-Z]{1}[a-z]{0,}[ ]{1}[A-Z]{1}[a-z]{0,}$"
						required="TRUE" title="Mohit Deshmukh" maxlength="30"/>
						<form:errors path="hrRepName"/></td>
				<td><form:input path="hrRepContactNumber" pattern="[0-9]{10}"
						required="TRUE" title="Enter a ten digit number"/>
						<form:errors path="hrRepContactNumber"/>		</td>
			</tr>

		</table>
		<button type="submit">Save</button>
	</form:form>
	<input type="button" onclick="addresume.htm" value="Submit">
</body>
</html>