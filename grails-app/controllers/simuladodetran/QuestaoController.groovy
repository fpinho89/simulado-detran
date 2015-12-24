package simuladodetran

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuestaoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Questao.list(params), model:[questaoCount: Questao.count()]
    }

    def show(Questao questao) {
        respond questao
    }

    def create() {
        respond new Questao(params)
    }

    @Transactional
    def save(Questao questao) {
        if (questao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (questao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond questao.errors, view:'create'
            return
        }

        questao.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questao.label', default: 'Questao'), questao.id])
                redirect questao
            }
            '*' { respond questao, [status: CREATED] }
        }
    }

    def edit(Questao questao) {
        respond questao
    }

    @Transactional
    def update(Questao questao) {
        if (questao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (questao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond questao.errors, view:'edit'
            return
        }

        questao.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questao.label', default: 'Questao'), questao.id])
                redirect questao
            }
            '*'{ respond questao, [status: OK] }
        }
    }

    @Transactional
    def delete(Questao questao) {

        if (questao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        questao.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questao.label', default: 'Questao'), questao.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
