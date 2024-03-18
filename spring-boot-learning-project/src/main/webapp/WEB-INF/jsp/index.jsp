<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Index Page</h1>

	<form action="upload" method="post" enctype="multipart/form-data">
		Dosyayı seç: <input type="file" name="file" /> <input type="submit"
			value="Yükle" />
	</form>
	</br>

	<font color="green">
		<c:if test="${not empty file}">
			<a href="download/${file.originalFilename}">Tıkla ve indir ${file.originalFilename}</a>
		</c:if>
	</font>

	<form action="logout" method="post">
		<input type="submit" value="logout" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</br>

</body>
</html>