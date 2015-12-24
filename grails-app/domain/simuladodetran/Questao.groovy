package simuladodetran

class Questao {

	String enunciado

	Resposta respostaCorreta

	Categoria Categoria

	TipoSimulado tipoSimulado

	static hasMany = [respostas:Resposta, 
						imagens:Imagem,
						tags:Tag]

    static constraints = {
    	enunciado nullable:false, blank:false, maxSize:255
    }

    static mapping = {
    	table "tb_questao"
        tags joinTable: [name: "tb_questao_tag", key: "questao_id"]

    }
}
