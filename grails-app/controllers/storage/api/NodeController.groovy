package storage.api


import grails.rest.*
import grails.converters.*
import static groovy.json.JsonOutput.toJson

class NodeController {
    def HdfsService

	static responseFormats = ['json', 'xml']
        static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        render toJson(HdfsService.getNodes(params.pppid,params.ppid, params.pid))
    }
    def save(Node node) {
        println("node:" + node.properties)
        render toJson(HdfsService.createNode(params.pppid, params.ppid, params.pid, node.name, toJson(node.data)))
        println("node.data:"+ toJson(node.data))
    }

    def show(Node node) {
        render toJson(HdfsService.getNode(params.pppid, params.ppid, params.pid, params.id))
    }
	
}
