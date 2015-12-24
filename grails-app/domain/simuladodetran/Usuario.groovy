package simuladodetran

class Usuario {

	String nome
	String sobrenome
	String login
	String senha

    static constraints = {
    	nome nullable:false, blank:false, maxSize:100
    	sobrenome  nullable:false, blank:false, maxSize:100
    	login  nullable:false, blank:false, maxSize:20
    	senha  nullable:false, blank:false, maxSize:100, password:true
    }
}

