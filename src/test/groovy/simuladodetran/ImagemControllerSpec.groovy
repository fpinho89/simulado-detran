package simuladodetran

import grails.test.mixin.*
import spock.lang.*

@TestFor(ImagemController)
@Mock(Imagem)
class ImagemControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.imagemList
            model.imagemCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.imagem!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def imagem = new Imagem()
            imagem.validate()
            controller.save(imagem)

        then:"The create view is rendered again with the correct model"
            model.imagem!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            imagem = new Imagem(params)

            controller.save(imagem)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/imagem/show/1'
            controller.flash.message != null
            Imagem.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def imagem = new Imagem(params)
            controller.show(imagem)

        then:"A model is populated containing the domain instance"
            model.imagem == imagem
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def imagem = new Imagem(params)
            controller.edit(imagem)

        then:"A model is populated containing the domain instance"
            model.imagem == imagem
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/imagem/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def imagem = new Imagem()
            imagem.validate()
            controller.update(imagem)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.imagem == imagem

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            imagem = new Imagem(params).save(flush: true)
            controller.update(imagem)

        then:"A redirect is issued to the show action"
            imagem != null
            response.redirectedUrl == "/imagem/show/$imagem.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/imagem/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def imagem = new Imagem(params).save(flush: true)

        then:"It exists"
            Imagem.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(imagem)

        then:"The instance is deleted"
            Imagem.count() == 0
            response.redirectedUrl == '/imagem/index'
            flash.message != null
    }
}
