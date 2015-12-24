<!DOCTYPE html>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<g:form controller="login" action="logar">
			<label for="login">Login</label><br/>
			<g:field name="login"/><br/>
			<label for="senha">Senha</label><br/>
			<g:passwordField name="senha"/><br/>
			<g:submitButton name="signin" value="Sign In"/>
		</g:form>
	</body>
</html>