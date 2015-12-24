package simuladodetran

class Categoria {

	String nome
	String descricao

	String toString(){
		this.nome
	}

    static constraints = {
		nome nullable:false, blank:false, maxSize:100
		descricao nullable:false, blank:false, maxSize:255
    }

    static mapping = {
    	table "tb_categoria"
    }
}
