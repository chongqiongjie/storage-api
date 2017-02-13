package storage.api


import grails.rest.*
import grails.converters.*
import static groovy.json.JsonOutput.toJson

class ProjectController {
    def HdfsService 
	static responseFormats = ['json', 'xml']
	
    def index() { 
        render toJson(HdfsService.getProjects())
 
}
}
