package simuladodetran

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TipoSimuladoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TipoSimulado.list(params), model:[tipoSimuladoCount: TipoSimulado.count()]
    }

    def show(TipoSimulado tipoSimulado) {
        respond tipoSimulado
    }

    def create() {
        respond new TipoSimulado(params)
    }

    @Transactional
    def save(TipoSimulado tipoSimulado) {
        if (tipoSimulado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoSimulado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoSimulado.errors, view:'create'
            return
        }

        tipoSimulado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoSimulado.label', default: 'TipoSimulado'), tipoSimulado.id])
                redirect tipoSimulado
            }
            '*' { respond tipoSimulado, [status: CREATED] }
        }
    }

    def edit(TipoSimulado tipoSimulado) {
        respond tipoSimulado
    }

    @Transactional
    def update(TipoSimulado tipoSimulado) {
        if (tipoSimulado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoSimulado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoSimulado.errors, view:'edit'
            return
        }

        tipoSimulado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoSimulado.label', default: 'TipoSimulado'), tipoSimulado.id])
                redirect tipoSimulado
            }
            '*'{ respond tipoSimulado, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoSimulado tipoSimulado) {

        if (tipoSimulado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoSimulado.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoSimulado.label', default: 'TipoSimulado'), tipoSimulado.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoSimulado.label', default: 'TipoSimulado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
