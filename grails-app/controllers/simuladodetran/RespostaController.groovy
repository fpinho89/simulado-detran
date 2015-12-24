package simuladodetran

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RespostaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Resposta.list(params), model:[respostaCount: Resposta.count()]
    }

    def show(Resposta resposta) {
        respond resposta
    }

    def create() {
        respond new Resposta(params)
    }

    @Transactional
    def save(Resposta resposta) {
        if (resposta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (resposta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond resposta.errors, view:'create'
            return
        }

        resposta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'resposta.label', default: 'Resposta'), resposta.id])
                redirect resposta
            }
            '*' { respond resposta, [status: CREATED] }
        }
    }

    def edit(Resposta resposta) {
        respond resposta
    }

    @Transactional
    def update(Resposta resposta) {
        if (resposta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (resposta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond resposta.errors, view:'edit'
            return
        }

        resposta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'resposta.label', default: 'Resposta'), resposta.id])
                redirect resposta
            }
            '*'{ respond resposta, [status: OK] }
        }
    }

    @Transactional
    def delete(Resposta resposta) {

        if (resposta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        resposta.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'resposta.label', default: 'Resposta'), resposta.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'resposta.label', default: 'Resposta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
