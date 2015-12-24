package simuladodetran

class Tag {

	String nome

	String toString(){
		this.nome
	}

    static constraints = {
    	nome nullable:false, blank:false, maxSize:100
    }

    static mapping = {
    	table "tb_tag"
    }
}
