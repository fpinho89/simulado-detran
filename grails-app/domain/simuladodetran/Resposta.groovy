package simuladodetran

class Resposta {

	String descricao

	static belongsTo = [questao:Questao]

    static constraints = {
    	descricao nullable:false, blank:false, maxSize: 255
    }

    static mapping = {
    	table "tb_resposta"
    }
}
