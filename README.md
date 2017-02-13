/project/[project_id]/category/[category_id]/container/[container_id]/node/[node_id]

category_id: [item|filter|parameter]
container_id: [data_source|classsify_name|classify_job|dashboard_job]

/project/junipter/category/item/container/model.d_boleme_order/node

/project/junipter/category/parameter/container/[classify_job_id]/node
{name:"", data: {cluster_num : "3", })

classify job:
data
parameter


hdfs: /user/hive/warehouse/storage.db
project=[jupiter]/category=[parameter]/container=[aaa_20150101_20150601]/node=0001/data.json

1. create project structure
    service: hdfsService, setNode(project_id, category_id, container_id, node_id, json_str)
                          getNode(project_id, category_id, container_id, node_id)
                          getProject(), getCategory(), getContainer()
    controller: project, category, container, node
    domain class: node:  {name, data}
   step1 : write hardcode, to ensure all workflow is okay. (1 day)
   step2 : implement read hdfs path, project, category, container (2 day)
   step3 : set node, get node (1 day)

/project/junipter/category/filter/container/aaa_20150101_20160101_201601010401/node
