package simuladodetran

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ImagemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Imagem.list(params), model:[imagemCount: Imagem.count()]
    }

    def show(Imagem imagem) {
        respond imagem
    }

    def create() {
        respond new Imagem(params)
    }

    @Transactional
    def save(Imagem imagem) {
        if (imagem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imagem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imagem.errors, view:'create'
            return
        }

        imagem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'imagem.label', default: 'Imagem'), imagem.id])
                redirect imagem
            }
            '*' { respond imagem, [status: CREATED] }
        }
    }

    def edit(Imagem imagem) {
        respond imagem
    }

    @Transactional
    def update(Imagem imagem) {
        if (imagem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imagem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imagem.errors, view:'edit'
            return
        }

        imagem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'imagem.label', default: 'Imagem'), imagem.id])
                redirect imagem
            }
            '*'{ respond imagem, [status: OK] }
        }
    }

    @Transactional
    def delete(Imagem imagem) {

        if (imagem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        imagem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'imagem.label', default: 'Imagem'), imagem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagem.label', default: 'Imagem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
