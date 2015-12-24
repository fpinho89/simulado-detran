package simuladodetran


class LoginInterceptor {

    LoginInterceptor() {
		matchAll().excludes(controller:"login")
	}

    boolean before() { 

    	if (session.usuario) {
    		println "Usuário logado"    		
    		true
    	} else {
    		println "Usuário não está logado."
    		redirect controller: "login", action: "login"
    		false
    	}
    }

    void afterView() {
        // no-op
    }
}
