package storage.api


import grails.rest.*
import grails.converters.*
import static groovy.json.JsonOutput.toJson

class CategoryController {
    def HdfsService
	static responseFormats = ['json', 'xml']
	
    def index() {
         render toJson(HdfsService.getCategorys(params.pid))  
 }
}
