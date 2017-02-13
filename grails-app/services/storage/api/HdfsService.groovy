package storage.api

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hdfs.DFSClient
import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class HdfsService {
    def  storage_path = "/user/hive/warehouse/ms.db/storage"
    def dfs_client
    HdfsService(){
        dfs_client =  new DFSClient(new URI("hdfs://192.168.1.3:9000"), new Configuration())
    }
    def createNode(projectId, categoryId, containerId, name, data){
        dfs_client.create("${storage_path}/project=${projectId}/category=${categoryId}/container=${containerId}/node=${name}/data.json", true)
            .with{ it << data << "\n"
                it.close()
            }  
        [projectId: projectId, categoryId:categoryId, containerId:containerId, name:name,data:data] 
    }
    
    def getProjects(){
        dfs_client.listPaths("${storage_path}").getPartialListing()*.getFullName("${storage_path}")
            .collect{it.split("/").last().split("=").last()}
   }

    def getCategorys(projectId){
        dfs_client.listPaths("${storage_path}/project=${projectId}").getPartialListing()
            *.getFullName("${storage_path}/project=${projectId}")
            .collect{it.split("/").last().split("=").last()}
    }

    def getContainers(projectId, categoryId){
        dfs_client.listPaths("${storage_path}/project=${projectId}/category=${categoryId}").getPartialListing()
            *.getFullName("${storage_path}/project=${projectId}/category=${categoryId}")
              .collect{it.split("/").last().split("=").last()}
    }

    def getNodes(projectId, categoryId, containerId){
        dfs_client.listPaths("${storage_path}/project=${projectId}/category=${categoryId}/container=${containerId}").getPartialListing()
            *.getFullName("${storage_path}/project=${projectId }/category=${categoryId}/container=${containerId}")
              .collect{it.split("/").last().split("=").last()}
    }
    
    def getNode(projectId, categoryId, containerId, nodeId){
        def content 
        dfs_client.open("${storage_path}/project=${projectId}/category=${categoryId}/container=${containerId}/node=${nodeId}/data.json")
            .with {   
                content = it.text
                it.close()
             }
        new JsonSlurper().parseText(content)
    }
}
