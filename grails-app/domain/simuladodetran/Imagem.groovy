package simuladodetran

class Imagem {

	String nome
	String descricao

	static belongsTo = [questao:Questao]

    String toString(){
        this.nome
    }

    static constraints = {
    	nome nullable:false, blank:false, maxSize:100
    	descricao nullable:false, blank:false, maxSize:255
    }

    static mapping = {
    	table "tb_imagem"
    }
}
