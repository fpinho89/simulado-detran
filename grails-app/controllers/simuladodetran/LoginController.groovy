import simuladodetran.*

class LoginController {
	

	def login () {
		render view:"login"
	}

	def logar (Usuario usuario) {

		println "${usuario.login} ${usuario.senha}"

		def user = Usuario.findByLoginAndSenha(usuario.login, usuario.senha.encodeAsMD5())

		if (user) {

			session.usuario = user
			redirect uri:"/"
		} else {
			flash.message = "Usuário não encontrado."
			render view:"login"
		}

	}

	def logout () {
		session.invalidate()
		render view:"login"
	}
}