	pipeline {
	agent any
	
	options {
	disableConcurrentBuilds()
	}
	
	environment {
	//PYTHONPATH = "${WORKSPACE}/section_4/code/cd_pipeline"
	}
	
	stages {
	
	/*
    stage('Init') {
        checkout scm
    

    stage('Build') {
        sh '''
        mvn -X clean package
        mv target/*.war ROOT.war
        '''
    }

*/

    stage("Execute Unit tests") {
	steps { runUnittests() }
	}

    stage("Execute Security tests") {
	steps { runSecuritytests() }
	}
	
	stage("Build Docker Image") {
	steps { buildDockerImage() }
	}

	stage("Deploy - QA") {
	steps { deploy('QA') }
	}
	
	stage("Execute REST Integration Tests - QA") {
	steps { runUAT(8888) }
	}
	
	stage("Deploy - Stage") {
	steps { deploy('stage') }
	}
	
	stage("Execute REST Integration Tests - Stage") {
	steps { runUAT(88) }
	}
	
	stage("Approve") {
	steps { approve() }
	}
	
	stage("Deploy - Prod") {
	steps { deploy('live') }
	}
	
	stage("Execute REST Integration Tests - Prod") {
	steps { runUAT(8080) }
	}
	
	}
	}

	// steps
	def buildDockerImage() {
	dir ('section_4/code/cd_pipeline' ) {
	//def appImage = docker.build("hands-on-jenkins/myapp:${BUILD_NUMBER}")
	}
	}
	
	def deploy(environment) {
	
	def containerName = ''
	def port = ''

    switch("${environment}") {
        case "dev": 
            containerName = "app_dev";
	        port = "8888";
            break;
        case "QA": 
            containerName = "app_qa";
	        port = "88";   
            break;
        case "Stage":
            containerName = "app_stage";
	        port = "88";            
            break;
        case "Prod": 
            containerName = "app_prod";
	        port = "80";
            break;
        default: 
 	        println "Environment not valid";
	        System.exit(0);
            break;           
    }
	//sh "docker ps -f name=${containerName} -q | xargs --no-run-if-empty docker stop"
	//sh "docker ps -a -f name=${containerName} -q | xargs -r docker rm"
	//sh "docker run -d -p ${port}:5000 --name ${containerName} hands-on-jenkins/myapp:${BUILD_NUMBER}"
	
	}
	
	
	def approve() {
	
	timeout(time:1, unit:'DAYS') {
	input('Do you want to deploy to live?')
	}
	
	}
	
	
	def runUnittests() {
	//sh "pip3 install --no-cache-dir -r ./section_4/code/cd_pipeline/requirements.txt"
	//sh "python3 section_4/code/cd_pipeline/tests/test_flask_app.py"
	}

    def runSecuritytests() {

    }

    def mergeToMaster() {

    }
	
	def runUAT(port) {
	//sh "section_4/code/cd_pipeline/tests/runUAT.sh ${port}"
	}

