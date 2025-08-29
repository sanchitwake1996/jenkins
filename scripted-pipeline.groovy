node {
    stage('Test') {
        echo 'test sucsess'
    }
    stage('Build') {
        echo 'buld done'
    }
    stage('Test') {
        echo 'test complete'
    }
    stage('Deploy') {
        echo 'deploy done'
    }
}