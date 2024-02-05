pipeline {
  agent any
  options {
    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '3')
  }    
  environment {
    TOMCAT_CREDS=credentials('pi-ssh-key')
    TOMCAT_SERVER="192.168.1.48"
    ROOT_WAR_LOCATION="/home/pi/tools/apache-tomcat-10.1.18/webapps"
    LOCAL_WAR_DIR="build/dist"
    WAR_FILE="app-0.1.0.war"
  }
  stages {
    stage('verify tooling') {
      steps {
        sh '''
          java -version
          ./bld version
        '''
      }
    }
    stage('download') {
      steps {
        sh './bld download purge'
      }
    }
    stage('compile') {
      steps {
        sh './bld clean compile'
      }
    }
    stage('precompile') {
      steps {
        sh './bld precompile'
      }
    }
    stage('test') {
      steps {
        sh './bld test'
      }
    }
    stage('war') {
      steps {
        sh './bld war'
      }
    }  
    stage('copy the war file to the Tomcat server') {
      steps {
        sh '''
          ssh -i $TOMCAT_CREDS $TOMCAT_CREDS_USR@$TOMCAT_SERVER "/home/pi/tools/apache-tomcat-10.1.18/bin/catalina.sh stop"
          ssh -i $TOMCAT_CREDS $TOMCAT_CREDS_USR@$TOMCAT_SERVER "rm -rf $ROOT_WAR_LOCATION/ROOT; rm -f $ROOT_WAR_LOCATION/ROOT.war"
          scp -i $TOMCAT_CREDS $LOCAL_WAR_DIR/$WAR_FILE $TOMCAT_CREDS_USR@$TOMCAT_SERVER:$ROOT_WAR_LOCATION/ROOT.war
          ssh -i $TOMCAT_CREDS $TOMCAT_CREDS_USR@$TOMCAT_SERVER "chown $TOMCAT_CREDS_USR:$TOMCAT_CREDS_USR $ROOT_WAR_LOCATION/ROOT.war"
          ssh -i $TOMCAT_CREDS $TOMCAT_CREDS_USR@$TOMCAT_SERVER "/home/pi/tools/apache-tomcat-10.1.18/bin/catalina.sh start"
        '''
      }
    }
  }
}